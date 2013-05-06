package com.wot.wotsurveys

class WoTSurveys extends WotSurveysStack {

  get("/") {
    contentType = "text/html"
    layoutTemplate("/WEB-INF/views/default.jade")
  }
}
