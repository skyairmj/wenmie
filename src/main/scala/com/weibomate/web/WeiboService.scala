package com.weibomate.web

import weibo4j.model.Status
import weibo4j.Oauth
import weibo4j.Timeline
import scala.collection.JavaConverters._
import org.springframework.stereotype.Service

@Service
class WeiboService {
  def fetch(code: String): List[Status] = {
    val oauth = new Oauth()
    val token = oauth.getAccessTokenByCode(code)
    val tm = new Timeline()
    tm.client.setToken(token.getAccessToken())
    val status = tm.getFriendsTimeline()
    status.getStatuses().asScala.toList
  }
}