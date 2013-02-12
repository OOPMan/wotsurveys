package com.wot.wotsurveys

import org.scalatra._
import scalate.ScalateSupport

class WoTSurveys extends WotSurveysStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }
  
}
