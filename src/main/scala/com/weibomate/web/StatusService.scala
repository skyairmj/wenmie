package com.weibomate.web

import weibo4j.model.Status

trait StatusService {
  def search(keyword: String): List[Tweet]
  def feed(statuses: List[Status])
}