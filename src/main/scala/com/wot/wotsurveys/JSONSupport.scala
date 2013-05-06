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

import org.scalatra.json._
import org.json4s.{DefaultFormats, Formats}

trait JSONSupport extends JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }
}
