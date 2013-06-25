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

class Users extends WotSurveysStack with SlickSupport with JSONSupport {
  import profile.simple._
  import Database.threadLocalSession

  get("/") {
    //TODO: Implement proper User layer. Result returned should be the logged in user or an indicator that the user
    //TODO: needs to login. The system should redirect the user to a WG OpenID login page
    Map(
      "success" -> true,
      "users" -> List(Map(
        "name" -> "OOPMan",
        "server" -> "NA"
    )))
  }

  get("/:id") {
    //TODO: Admin function
  }

}
