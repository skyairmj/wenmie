package com.weibomate.web

import org.apache.solr.common.SolrDocument
import org.specs2.mutable.SpecificationWithJUnit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.context.TestContextManager
import com.weibomate.specs2.SpringScope
import org.springframework.test.context.ContextHierarchy
import weibo4j.model.Status
import com.weibomate.web.config.AppTestConfig

//@ContextConfiguration(classes = Array(classOf[AppTestConfig]), loader = classOf[AnnotationConfigContextLoader])
class SolrServiceSpecTest extends SpecificationWithJUnit with SpringScope {
  var solrService: SolrService = new SolrService

  "SearchService" should {
    "return all tweets matches given text" in {
      pending
      val tweets: List[Tweet] = solrService.search("微博")
      tweets must not be(null)
    }
    
    "feed given tweet" in {
      val status = new Status("""
          {
			"created_at": "Sun Jun 09 00:24:22 +0800 2013",
			"id": 3587074483282386,
			"mid": "3587074483282386",
			"idstr": "3587074483282386",
			"text": "六点起床很困难，背单词很困难，静下心很困难...但是总有一些人，五点可以起床，一天背六课单词，耐心读完一本书。谁也没有超能力，但是自己可以决定一天去做什么事情。你以为没有了路，事实上路可能就在前方一点点。那些比自己强大的人都在拼命，我们还有什么理由停下脚步？|转",
			"source": "<a href=\"http://app.weibo.com/t/feed/4UxMBN\" rel=\"nofollow\">天使丘比特</a>",
			"favorited": false,
			"truncated": false,
			"in_reply_to_status_id": "",
			"in_reply_to_user_id": "",
			"in_reply_to_screen_name": "",
			"pic_urls": [
				{
					"thumbnail_pic": "http://ww3.sinaimg.cn/thumbnail/8683331fjw1e5h6xav3aoj20c8096aan.jpg"
				}
			],
			"thumbnail_pic": "http://ww3.sinaimg.cn/thumbnail/8683331fjw1e5h6xav3aoj20c8096aan.jpg",
			"bmiddle_pic": "http://ww3.sinaimg.cn/bmiddle/8683331fjw1e5h6xav3aoj20c8096aan.jpg",
			"original_pic": "http://ww3.sinaimg.cn/large/8683331fjw1e5h6xav3aoj20c8096aan.jpg",
			"geo": null,
			"user": {
				"id": 2256745247,
				"idstr": "2256745247",
				"screen_name": "创业影院",
				"name": "创业影院",
				"province": "11",
				"city": "5",
				"location": "北京 朝阳区",
				"description": "2013年6月18日 13:30 “不卖身，你能不能融到钱？创业企业如何使用‘银行杠杆’”专场活动，在北京市石景山区科技馆多功能厅举行，...",
				"url": "",
				"profile_image_url": "http://tp4.sinaimg.cn/2256745247/50/5610524935/1",
				"profile_url": "venturecinema",
				"domain": "venturecinema",
				"weihao": "",
				"gender": "m",
				"followers_count": 79395,
				"friends_count": 1998,
				"statuses_count": 20975,
				"favourites_count": 7,
				"created_at": "Tue Jul 19 12:50:12 +0800 2011",
				"following": true,
				"allow_all_act_msg": true,
				"geo_enabled": true,
				"verified": true,
				"verified_type": 2,
				"remark": "",
				"allow_all_comment": true,
				"avatar_large": "http://tp4.sinaimg.cn/2256745247/180/5610524935/1",
				"verified_reason": "21世纪天使创业投资有限公司旗下产业-创业影院官方微博",
				"follow_me": false,
				"online_status": 0,
				"bi_followers_count": 1176,
				"lang": "zh-cn",
				"star": 0,
				"mbtype": 0,
				"mbrank": 0,
				"block_word": 0
			},
			"reposts_count": 1,
			"comments_count": 0,
			"attitudes_count": 0,
			"mlevel": 0,
			"visible": {
				"type": 0,
				"list_id": 0
			}
		}
          """)
      val tweets = List(status)
      solrService.feed(tweets)
      "" must be("")
    }
  }

}