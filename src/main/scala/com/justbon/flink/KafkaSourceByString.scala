package com.justbon.flink

import java.util.Properties

import akka.remote.serialization.StringSerializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

/***
  * FlinkKafkaConsumer 通用连接
  *
  * Flink’s Kafka consumer is called FlinkKafkaConsumer08 (or 09 for Kafka 0.9.0.x versions,
  * etc. or just FlinkKafkaConsumer for Kafka >= 1.0.0 versions).
  * It provides access to one or more Kafka topics.
  */
class KafkaSourceByString {
  def main(args: Array[String]): Unit = {

    val environment = StreamExecutionEnvironment.getExecutionEnvironment
    environment.setParallelism(1)
    import org.apache.flink.streaming.api.scala._
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "tuge1:9092")
    properties.setProperty("group.id", "fink01")
    properties.setProperty("key.deserializer", classOf[StringSerializer].getName)
    properties.setProperty("value.deserializer", classOf[StringDeserializer].getName)
    properties.setProperty("auto.offset.reset", "latest")
    val stream = environment.addSource(new FlinkKafkaConsumer[String]("wordsTopic", new SimpleStringSchema(),
      properties))

    val result = stream.flatMap(_.split(" "))
      .map((_, 1))
      .keyBy(0)
      .sum(1)
    result.print()
    environment.execute()


  }
}
