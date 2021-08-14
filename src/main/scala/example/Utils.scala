package example

import org.apache.spark.sql.{DataFrame, SparkSession}

object Utils {
  def withSpark(func: SparkSession => Unit): Unit = {
    val session = SparkSession.builder()
      .master(System.getenv("SPARK_MASTER_URL"))
      .appName("Deequ")
      .getOrCreate()
    session.sparkContext.setCheckpointDir(System.getProperty("java.io.tmpdir"))

    try {
      func(session)
    } finally {
      session.stop()
      System.clearProperty("spark.driver.port")
    }
  }

  def tableAsDataframe(table: String)(implicit session: SparkSession): DataFrame = {
    session.read
      .format("jdbc")
      .option("url", s"jdbc:postgresql://${System.getenv("POSTGRES_HOST")}/${System.getenv("POSTGRES_DB")}")
      .option("dbtable", s"public.$table")
      .option("user", System.getenv("POSTGRES_USER"))
      .load()
  }
}
