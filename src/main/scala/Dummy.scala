import org.apache.spark.{SparkConf, SparkContext}

object Dummy {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("dummy")
    val sc = new SparkContext(conf)

    val arr = sc.parallelize(Array(1,2,3,4,5,6,7))

    arr.map(a => a*a).foreach(println)
  }

}
