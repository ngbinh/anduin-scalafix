lazy val V = _root_.scalafix.sbt.BuildInfo
// Use a scala version supported by scalafix.
scalaVersion in ThisBuild := V.scala212

lazy val rules = project.settings(
  libraryDependencies += "ch.epfl.scala" %% "scalafix-core" % V.scalafixVersion
)

lazy val input = project.settings(
)

lazy val output = project

lazy val tests = project
  .settings(
    libraryDependencies += "ch.epfl.scala" % "scalafix-testkit" % V.scalafixVersion % Test cross CrossVersion.full,
    scalafixTestkitOutputSourceDirectories :=
      sourceDirectories.in(output, Compile).value,
    scalafixTestkitInputSourceDirectories :=
      sourceDirectories.in(input, Compile).value,
    scalafixTestkitInputClasspath :=
      fullClasspath.in(input, Compile).value
  )
  .dependsOn(input, rules)
  .enablePlugins(ScalafixTestkitPlugin)
