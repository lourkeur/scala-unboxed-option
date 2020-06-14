val catsVersion = "2.1.1"
val dottyVersion = "0.24.0"
val scalatestVersion = "3.1.2"

lazy val `scala-unboxed-option` = project.
  in(file(".")).
  settings(
    name := "scala-unboxed-option",
    version := "0.1-SNAPSHOT",
    scalaVersion := dottyVersion,
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-Xfatal-warnings",
      "-encoding", "utf8",
      "-Yexplicit-nulls",
    ),

    libraryDependencies += "org.scalatest" %% "scalatest" % scalatestVersion % Test,
    scalacOptions in Test -= "-Xfatal-warnings",

    libraryDependencies ++= Seq(
      ("org.scalacheck" %% "scalacheck" % "1.14.3" % Test).withDottyCompat(dottyVersion),
      ("org.typelevel" %% "cats-kernel-laws" % catsVersion % Test).withDottyCompat(dottyVersion),
    ),
  )
