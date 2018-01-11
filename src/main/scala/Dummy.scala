import org.apache.kudu.spark.kudu.KuduContext
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.kudu.spark.kudu._


object Dummy {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("dummy")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.hive.HiveContext(sc)

    val kuduContext =
      new KuduContext("cloudera-vm.c.endless-upgrade-187216.internal:7051", sc)

    kuduContext.tableExists("testkudu")
    //kuduContext.tableExists("impala::default.testkudu")

    val rdd = sc.parallelize(Seq(1, "ciao")).map(Row(_))

    val schema = new StructType(Array(StructField("id", IntegerType, false),
      StructField("value", StringType, false)))

    val df = sqlContext.createDataFrame(rdd, schema)

    df.write.options(Map("kudu.master"-> "cloudera-vm.c.endless-upgrade-187216.internal:7051",
      "kudu.table"-> "testkudu")).mode("append").kudu
  }

}
