package com.weibomate.web

import java.io.IOException
import java.util.ArrayList
import java.util.Collection
import org.apache.solr.client.solrj.SolrServerException
import org.apache.solr.client.solrj.impl.HttpSolrServer
import org.apache.solr.common.SolrInputDocument
import org.springframework.stereotype.Service
import weibo4j.model.Status
import org.apache.solr.client.solrj.SolrQuery
import scala.collection.JavaConverters._
import org.apache.solr.common.SolrDocument
import weibo4j.model.User

@Service
class SolrService extends StatusService {
  def search(keyword: String): List[Tweet] = {
    val server = new HttpSolrServer("http://solr.ap01.aws.af.cm/")
    val query = new SolrQuery
    query.setQuery("status_text:%s".format(keyword))
    val rsp = server.query(query)
    val solrDocuments = rsp.getResults
    return solrDocuments.asScala.toList.map((doc:SolrDocument) => {
      var tweet = new Tweet
      tweet.setId(doc.getFieldValue("id").toString)
      tweet.setText(doc.getFieldValue("status_text").toString)
      tweet.setThumbnailPic(doc.getFieldValue("status_thumbnail_pic").toString)
      tweet.setUserId(doc.getFieldValue("status_user_id").toString)
      tweet.setUserScreenName(doc.getFieldValue("status_user_screen_name").toString)
      tweet.setUserProfileImageUrl(doc.getFieldValue("status_user_profile_image_url").toString)
      tweet
    })
    //    Tweet.mock
  }

  def feed(statuses: List[Status]) = {
    try {
      val server = new HttpSolrServer("http://solr.ap01.aws.af.cm/")
      var docs = List[SolrInputDocument]()

      statuses.foreach(status => {
        val doc = new SolrInputDocument
        doc.addField("id", status.getIdstr)
        val retweetedStatus = status.getRetweetedStatus
        if (retweetedStatus == null) {
          doc.addField("status_text", status.getText)
        } else {
          val sourceUser = retweetedStatus.getUser
          doc.addField("status_text", "%s // @%s: %s".format(status.getText, sourceUser.getScreenName, retweetedStatus.getText))
        }
        doc.addField("status_thumbnail_pic", status.getThumbnailPic)
        val user = status.getUser
        if (user != null) {
          doc.addField("status_user_id", user.getId)
          doc.addField("status_user_screen_name", user.getScreenName)
          doc.addField("status_user_profile_image_url", user.getProfileImageUrl)
        }
        docs :+= doc
      })

      server.add(docs.asJava)
      server.commit
    } catch {
      case (e: SolrServerException) => e.printStackTrace
      case (e: IOException) => e.printStackTrace
    }
  }
}