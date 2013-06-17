package com.weibomate.web.config

import org.fusesource.scalate.spring.view.ScalateViewResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator

@EnableWebMvc
@ComponentScan(basePackages = Array("com.weibomate.web"))
@Configuration
class AppConfig extends WebMvcConfigurerAdapter {

  @Bean def viewNameTranslator = new DefaultRequestToViewNameTranslator

  @Bean def viewResolver = {
    var vr = new ScalateViewResolver
    vr.setCache(false)
    vr.setPrefix("/")
    vr.setSuffix(".ssp")
    vr.setContentType("text/html;charset=UTF-8")
    vr
  }

  override def addResourceHandlers (registry: ResourceHandlerRegistry) = {
    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    registry.addResourceHandler("/img/**").addResourceLocations("/img/");
  }

  override def configureDefaultServletHandling (configurer: DefaultServletHandlerConfigurer) = {
    configurer.enable();
  }

}