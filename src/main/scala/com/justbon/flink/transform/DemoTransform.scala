package com.justbon.flink.transform

import org.apache.flink.api.java.tuple._
import org.apache.flink.streaming.api.scala._
object DemoTransform {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    //报错信息:
    //No implicit arguments of type:Typelnformationt[XXX]
    //原因是没有导入以下隐士转换:
   val dataStream= env.fromElements((1, 5),(2, 2),(2, 4),(1, 3))
    //指定第一个字段为分区Key
    val result = dataStream.keyBy(0).sum(1)
    result.writeAsText("./test2.txt")

//    val dataStream2 = env.fromElements(("a", 3), ("d", 4),("d", 4), ("c", 2), ("c",5), ("a", 5))
//    //指定第一个字段为分区Key
//    val keyedStream2: KeyedStream[(String,Int), Tuple] = dataStream2.keyBy(0)
//    //滚动对第二个字段进行reduce相加求和
//    val reduceStream = keyedStream2.reduce { (t1, t2) =>
//      (t1._1, t1._2 + t2._2)
//
//    }
//    reduceStream.map(println(_))


    //指定第一个字段为分区Key
//    val keyedStream: KeyedStream[(Int, Int), Tuple] = dataStream.keyBy(0)
//    //对第二个字段进行sum统计
//    val sumStream: DataStream[(Int, Int)] = keyedStream.sum(1)
//    //输出计算结果
//    sumStream.print()

    env.execute("DemoTransform")

  }
}
