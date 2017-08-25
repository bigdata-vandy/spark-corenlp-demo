package edu.vanderbilt.accre.corenlpdemo

import org.apache.spark.sql.functions._
import com.databricks.spark.corenlp.functions._
import org.apache.spark.sql.SparkSession

object DemoApp {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
        .builder()
        .appName("TokenizerApp")
        .getOrCreate()

    val sqlContext = spark.sqlContext
    import sqlContext.implicits._

    val input = Seq(
      (1, "<xml>Stanford University is located in California. It is a great university.</xml>")
    ).toDF("id", "text")

    val output = input
      .select(cleanxml('text).as('doc))
      .select(explode(ssplit('doc)).as('sen))
      .select('sen, tokenize('sen).as('words), ner('sen).as('nerTags), sentiment('sen).as('sentiment))

      output.show(truncate = false)
  }
}
