
/**
 * This file is part of WoTSurveys
 *
 * It is subject to the license terms in the LICENSE.txt file found in the top-level directory of this distribution and
 * at http://eclipse.org/org/documents/epl-v10.php.
 *
 * No part of WoTSurveys, including this file, may be copied, modified, propagated, or distributed except according
 * to the terms contained in the LICENSE file.
 */

import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object WotSurveysBuild extends Build {
  val Organization = "com.wot"
  val Name = "WoT Surveys"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.10.1"
  val ScalatraVersion = "2.2.1"

  lazy val project = Project(
    "wot-surveys",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ com.typesafe.startscript.StartScriptPlugin.startScriptForClassesSettings ++
      Seq(
        organization := Organization,
        name := Name,
        version := Version,
        scalaVersion := ScalaVersion,
        resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
        libraryDependencies ++= Seq(
          "com.typesafe.slick" %% "slick" % "1.0.0",
          "postgresql" % "postgresql" % "9.1-901.jdbc4",
          "c3p0" % "c3p0" % "0.9.1.2",
          "org.scalatra" %% "scalatra" % ScalatraVersion,
          "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
          "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
          "org.scalatra" %% "scalatra-json" % ScalatraVersion,
          "org.json4s"   %% "json4s-jackson" % "3.1.0",
          "ch.qos.logback" % "logback-classic" % "1.0.11" % "runtime",
          "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "compile;container",
          "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "compile;container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
        ),
        scalateTemplateConfig in Compile <<= (sourceDirectory in Compile) {
          base =>
            Seq(
              TemplateConfig(
                base / "webapp" / "WEB-INF" / "templates",
                Seq.empty, /* default imports should be added here */
                Seq.empty, /* add extra bindings here */
                Some("templates")
              )
            )
        }
      )
  )
}
