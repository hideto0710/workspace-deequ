import Dependencies._

ThisBuild / scalaVersion     := "2.12.14"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "workspace-deequ",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "com.amazon.deequ" % "deequ" % "2.0.0-spark-3.1",
    libraryDependencies += "org.postgresql" % "postgresql" % "42.2.23"
  )
