name := "EbiznesMaszyna"

version := "1.0"

lazy val `ebiznesmaszyna` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Atlassian Releases" at "https://maven.atlassian.com/public/"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.12", "2.12.4")

//libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

libraryDependencies += guice
libraryDependencies += "com.typesafe.play" %% "play-slick" % "3.0.3"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3"
libraryDependencies += "org.xerial"        %  "sqlite-jdbc" % "3.21.0"
//libraryDependencies += "slick.driver.SQLiteDriver" %%

//libraryDependencies += "com.h2database" % "h2" % "1.4.196"

libraryDependencies += "com.h2database" % "h2" % "1.4.197"

libraryDependencies += specs2 % Test

//unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "com.mohiva" %% "play-silhouette" % "5.0.7",
  "org.webjars" %% "webjars-play" % "2.6.2",
  "org.webjars" % "bootstrap" % "3.1.1",
  "org.webjars" % "jquery" % "1.11.0",
  "net.codingwell" %% "scala-guice" % "4.2.3",
  cache
)