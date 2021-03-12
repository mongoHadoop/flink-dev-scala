package com.justbon.flink


import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

/**
  *  从 HDFS读取内容
  */
object HDFSFileSource {
  def main(args: Array[String]): Unit = {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment
    environment.setParallelism(1)
    //
    import org.apache.flink.streaming.api.scala._
    val stream = environment.readTextFile("hdfs://tuge2:9000/FlinkDataTest/words")


    val result: DataStream[(String, Int)] = stream.flatMap(_.split(" "))
      .map((_, 1))
      .keyBy(0)
      .sum(1)


    result.print()
    environment.execute("FileSourceTest")

  }
}