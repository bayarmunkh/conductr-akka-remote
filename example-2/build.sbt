name := """example-2"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    javaOptions in Bundle ++= Seq("-Dhttp.port=8081"),
    normalizedName in Bundle := "server",
    BundleKeys.system := "ExampleSystem",
    BundleKeys.endpoints := Map(
      "server" -> Endpoint("tcp", 0, services = Set(URI("tcp:///server")))
    )
  )

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

