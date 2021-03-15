package com.justbon.flink.transform

import org.apache.flink.streaming.api.scala._

object SplitedStreamTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //创建数据集
    val dataStream1: DataStream[(String, Int)] = env.fromElements(("a", 3), ("d", 4), ("c",
      2), ("c", 5), ("a", 5))
    //合并两个DataStream数据集
    val splitedStream: SplitStream[(String, Int)] = dataStream1.split(t => if (t._2 % 2 == 0)
      Seq("even") else Seq("odd"))

    //筛选出偶数数据集
    val evenStream: DataStream[(String, Int)] = splitedStream.select("even")
    evenStream.print()
    //筛选出奇数数据集
    val oddStream: DataStream[(String, Int)] = splitedStream.select("odd")
    //筛选出奇数和偶数数据集
    val allStream: DataStream[(String, Int)] = splitedStream.select("even", "odd")
    env.execute("DemoTransform")
  }

}
