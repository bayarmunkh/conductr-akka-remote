name := """example-1"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    javaOptions in Bundle ++= Seq("-Dhttp.port=8080"),
    normalizedName in Bundle := "client",
    BundleKeys.system := "ExampleSystem",
    BundleKeys.endpoints := Map(
      "client" -> Endpoint("tcp")
    )
  )

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)
