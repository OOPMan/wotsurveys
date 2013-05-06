package com.wot.wotsurveys

/**
 * This file is part of WoTSurveys
 *
 * It is subject to the license terms in the LICENSE.txt file found in the top-level directory of this distribution and
 * at http://eclipse.org/org/documents/epl-v10.php.
 *
 * No part of WoTSurveys, including this file, may be copied, modified, propagated, or distributed except according
 * to the terms contained in the LICENSE file.
 */

class Surveys extends WotSurveysStack with SlickSupport with JSONSupport {
  import profile.simple._
  import Database.threadLocalSession

  def getSurveys(id: String = "") = {
    db withSession {
      var q = for {
        s <- Surveys if s.active === true
      } yield s
      if(id.length > 0) q = q.filter { s => s.id === id.toInt }
      q.list map { case (id, name, active) => Map("id" -> id, "name" -> name) }
    }
  }

  get("/") {
    getSurveys()
  }

  get("/:id") {
    getSurveys(params("id"))
  }
}
