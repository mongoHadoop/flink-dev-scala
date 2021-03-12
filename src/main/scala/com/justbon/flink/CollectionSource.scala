package com.justbon.flink

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
  * 从集合读取内容
  */
object CollectionSource {

  def main(args: Array[String]): Unit = {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment
    environment.setParallelism(1)
    import org.apache.flink.api.scala._
    val data = environment.fromCollection(Array("hello", "world", "hello", "hi"))
    data.print()
    environment.execute("collectionSource")

  }
}