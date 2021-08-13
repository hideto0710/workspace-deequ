package example

import org.apache.spark.sql.{DataFrame, SparkSession}

case class Item(
  id: Long,
  productName: String,
  description: String,
  priority: String,
  numViews: Long
)

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

  def itemsAsDataframe(session: SparkSession, items: Item*): DataFrame = {
    val rdd = session.sparkContext.parallelize(items)
    session.createDataFrame(rdd)
  }
}
