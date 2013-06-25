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
  import slick.session._

  // TODO: This may need to move elsewhere if it get used more generally
  protected def getAnswersForQuestion(questionId: Int)(implicit ss: Session) = {
      val query = for {
        a <- Answers
        qa <- Questions_Answers if a.id === qa.answer_id && qa.question_id === questionId
      } yield a
      query.list.map { case (id, answer) => Map (
        "id" -> id,
        "answer" -> answer
      )}
  }

  // TODO: This may need to move elsewhere if it get used more generally
  protected def getQuestionsForSurvey(surveyId: Int)(implicit ss: Session) = {
      val query = for {
        q <- Questions if q.active === true
        sq <- Surveys_Questions if q.id === sq.question_id && sq.survey_id == surveyId
      } yield q
      query.list.map { case (id, question, maximum_answers, active) => Map(
        "id" -> id,
        "question" -> question,
        "maximum_answers" -> maximum_answers,
        "answers" -> getAnswersForQuestion(id)
      )}
  }

  //TODO: Should Session come from implicit parameter?
  def getSurveys(id: String = "", extended: Boolean = false) = {
    db withSession { implicit ss: Session =>
      var q = for {
        s <- Surveys if s.active === true
      } yield s
      if(id.length > 0) q = q.filter { s => s.id === id.toInt }
      Map(
        "success" -> true,
        "surveys" -> q.list.map { case (id, name, active) => Map(
          "id" -> id,
          "name" -> name,
          "questions" -> (if(!extended) Nil else getQuestionsForSurvey(id))
        )})
    }
  }

  get("/") {
    getSurveys()
  }

  get("/:id") {
    getSurveys(params("id"))
  }

  get("/extended/:id") {
    getSurveys(params("id"), true)
  }
}
