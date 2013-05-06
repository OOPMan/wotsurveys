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

import org.scalatra.ScalatraServlet
import org.slf4j.LoggerFactory

import com.mchange.v2.c3p0.ComboPooledDataSource
import java.util.Properties

trait SlickSupport extends ScalatraServlet {

  val logger = LoggerFactory.getLogger(getClass)

  val cpds = {
    val props = new Properties
    props.load(getClass.getResourceAsStream("/c3p0.properties"))
    val cpds = new ComboPooledDataSource
    cpds.setProperties(props)
    logger.info("Created c3p0 connection pool")
    cpds
  }

  val profile = cpds.getDriverClass match {
    case "org.postgresql.Driver" => scala.slick.driver.PostgresDriver
  }

  import profile.simple._
  import Database.threadLocalSession

  def closeDbConnection() {
    logger.info("Closing c3po connection pool")
    cpds.close
  }

  val db = Database.forDataSource(cpds)

  override def destroy() {
    super.destroy()
    closeDbConnection
  }

  // Tables
  object Surveys extends Table[(Int, String, Boolean)]("surveys") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def active = column[Boolean]("active", O.Default(true))

    def * = id ~ name ~ active
  }

  object Questions extends Table[(Int, String, Int, Boolean)]("questions") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def question = column[String]("question")
    def maximum_answers = column[Int]("maximum_answers", O.Default(1))
    def active = column[Boolean]("active", O.Default(true))

    def * = id ~ question ~ maximum_answers ~ active
  }

  object Answers extends Table[(Int, String)]("answers") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def answer = column[String]("answer", O.NotNull)

    def * = id ~ answer
  }

  object Users extends Table[(String, String)]("users") {
    def username = column[String]("username", O.NotNull)
    def server = column[String]("server", O.NotNull)

    def pk = primaryKey("user_pk", (username, server))
    def * = username ~ server
  }

  object Surveys_Questions extends Table[(Int, Int)]("surveys_questions") {
    def survey_id = column[Int]("survey_id", O.NotNull)
    def question_id = column[Int]("question_id", O.NotNull)

    def pk = primaryKey("surveys_questions_pk", (survey_id, question_id))
    def survey = foreignKey("survey_fk", survey_id, Surveys)(_.id)
    def question = foreignKey("question_fk", question_id, Questions)(_.id)
    def * = survey_id ~ question_id
  }

  object Questions_Answers extends Table[(Int, Int)]("questions_answers") {
    def question_id = column[Int]("question_id", O.NotNull)
    def answer_id = column[Int]("answer_id", O.NotNull)

    def pk = primaryKey("questions_answers_pk", (question_id, answer_id))
    def question = foreignKey("question_fk", question_id, Questions)(_.id)
    def answer = foreignKey("answer_fk", answer_id, Answers)(_.id)
    def * = question_id ~ answer_id
  }

  object Users_Questions_Answers_Surveys extends Table[(String, String, Int, Int, Int)]("users_questions_answers_surveys") {
    def username = column[String]("username", O.NotNull)
    def server = column[String]("server", O.NotNull)
    def question_id = column[Int]("question_id", O.NotNull)
    def answer_id = column[Int]("answer_id", O.NotNull)
    def survey_id = column[Int]("survey_id", O.NotNull)

    def pk = primaryKey("users_questions_answers_surveys_pk", (username, server, question_id, answer_id, survey_id))
    def user = foreignKey("user_fk", (username, server), Users)(t => (t.username, t.server))
    def question = foreignKey("question_fk", question_id, Questions)(_.id)
    def answer = foreignKey("answer_fk", answer_id, Answers)(_.id)
    def survey = foreignKey("survey_fk", survey_id, Surveys)(_.id)
    def * = username ~ server ~ question_id ~ answer_id ~ survey_id
  }


}
