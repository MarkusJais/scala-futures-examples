import sbt._
import sbt.Keys._

object scala_futures_examples extends Build {

  lazy val scala_futures_examples = Project(
    id = "scala-futures-examples",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "scala-futures-examples",
      organization := "com.markusjais",
      version := "0.1-SNAPSHOT",
   	  scalaVersion := "2.10.3",
      resolvers ++= Seq(
            "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases"
      ),

      libraryDependencies ++= Seq(
        "com.github.axel22" %% "scalameter" % "0.3" withSources()
       )
    )
  )
}

