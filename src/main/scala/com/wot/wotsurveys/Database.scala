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

import scala.slick.driver.PostgresDriver.simple._
import Database.threadLocalSession

class Database extends WotSurveysStack with SlickSupport {

  //TODO: Secure this
  get("/db/create-tables") {
    db withSession {
      (Surveys.ddl ++ Questions.ddl ++ Answers.ddl ++ Users.ddl ++ Surveys_Questions.ddl ++ Questions_Answers.ddl ++ Users_Questions_Answers_Surveys.ddl).create
    }
  }
}
