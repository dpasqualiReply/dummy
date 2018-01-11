import org.apache.spark.sql.SparkSession

object Dummy {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("dummy").master("local[*]").getOrCreate()
    val sc = spark.sparkContext

    val arr = sc.parallelize(Array(1,2,3,4,5,6,7))

    arr.map(a => a*a).foreach(println)
  }

}
