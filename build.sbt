name := """scala-play-com.tb.postgres-rest-api"""
organization := "tb"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"
libraryDependencies += guice
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.22"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test



