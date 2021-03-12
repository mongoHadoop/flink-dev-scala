package com.justbon.flink.transform

import org.apache.flink.api.java.tuple._
import org.apache.flink.streaming.api.scala._

object DemoStreamUnion {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //创建不同的数据集
    val dataStream1: DataStream[(String, Int)] = env.fromElements(("a", 3), ("d", 4), ("c",
      2), ("c", 5), ("a", 5))
    val dataStream2: DataStream[(String, Int)] = env.fromElements(("d", 1), ("s", 2), ("a",
      4), ("e", 5), ("a", 6))
    val dataStream3: DataStream[(String, Int)] = env.fromElements(("a", 2), ("d", 1), ("s",
      2), ("c", 3), ("b", 1))
    //合并两个DataStream数据集
    val unionStream = dataStream1.union(dataStream2)
    unionStream.map(println(_))
    //合并多个DataStream数据集
    //val allUnionStream = dataStream1.union(dataStream2, dataStream3)
    //allUnionStream.print()
    env.execute("DemoTransform")

  }
}
