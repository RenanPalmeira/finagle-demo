lazy val thriftApi = (project in file("thrift-api"))
  .settings(
    commonSettings
  )
  .settings(
    libraryDependencies ++= Seq(
        "org.apache.thrift" % "libthrift" % "0.9.3",
        "com.twitter" %% "scrooge-core" % "4.16.0" exclude("com.twitter", "libthrift"),
        "com.twitter" %% "finagle-thrift" % "6.44.0" exclude("com.twitter", "libthrift")
    )
  )

lazy val temperature = (project in file("temperature-service"))
  .settings(
    commonSettings
  )
  .dependsOn(thriftApi % "compile->compile")

lazy val sensor = (project in file("sensor-service"))
  .settings(
    commonSettings
  )
  .dependsOn(thriftApi % "compile->compile")

lazy val commonSettings = Seq(
  organization := "com.temperature",
  version := "0.1",
  scalaVersion := "2.12.1"
)
