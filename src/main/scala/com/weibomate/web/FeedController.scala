package com.weibomate.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import weibo4j.Oauth
import weibo4j.util.WeiboConfig
import weibo4j.Timeline
import weibo4j.model.WeiboException
import weibo4j.model.Status
import org.springframework.beans.factory.annotation.Autowired

@Controller
@RequestMapping(Array("/feed"))
class FeedController {
  val logger = LoggerFactory.getLogger(classOf[FeedController])
  @Autowired var weiboService : WeiboService = _
  @Autowired var statusService : SolrService = _

  @RequestMapping(method = Array(RequestMethod.GET))
  def feed(@RequestParam("code") code: String) = {
    val statuses = weiboService.fetch(code)
    statusService.feed(statuses)
    "feed"
  }
}