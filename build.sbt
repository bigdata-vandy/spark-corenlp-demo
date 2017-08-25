organization := "edu.vanderbilt.accre"

version := "0.1.0"

scalaVersion := "2.11.0"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.3" % "test",
  "org.apache.spark" %% "spark-core" % "2.2.0" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.2.0" % "provided",
  "databricks" % "spark-corenlp" % "0.2.0-s_2.11"
)

mainClass in assembly := Some("edu.vanderbilt.accre.corenlpdemo.DemoApp")

assemblyOption in assembly := (assemblyOption in assembly).value.copy(cacheUnzip = false)

val exportFullResolvers = taskKey[Unit]("debug resolvers")

resolvers += "Spark Packages" at "https://dl.bintray.com/spark-packages/maven"

exportFullResolvers := {
  for {
   (resolver,idx) <- fullResolvers.value.zipWithIndex
  } println(s"${idx}.  ${resolver.name}")
}
