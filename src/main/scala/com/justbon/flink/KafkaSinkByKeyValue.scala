package com.justbon.flink


import java.lang
import java.util.Properties

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaProducer, KafkaSerializationSchema}
import org.apache.kafka.clients.producer.ProducerRecord


/**
  * Kafka作为SInk第二种（将Key,Value写入Kafka）
  *
  * 在控制台消费命令: $ ./bin/kafka-console-consumer.sh --bootstrap-server tuge1:9092 --topic t_topic --from-beginning
  * --property print-key=true
  */
object KafkaSinkByKeyValue {
  def main(args: Array[String]): Unit = {
    //定义Flink Streaming上下文环境
    val streamEnv: StreamExecutionEnvironment =
      StreamExecutionEnvironment.getExecutionEnvironment
    streamEnv.setParallelism(1) //默认情况下每个任务的并行度为1
    //导入隐式转换
    import org.apache.flink.streaming.api.scala._
    //读取netcat流中数据 （实时流）
    val stream1: DataStream[String] =
      streamEnv.socketTextStream("tuge1", 6666)
    //转换计算
    val result = stream1.flatMap(_.split(" "))
      .map((_, 1))
      .keyBy(0)
      .sum(1)
    //Kafka生产者的配置
    val props = new Properties()
    props.setProperty("bootstrap.servers", "tuge1:9092")

    //数据写入Kafka，并且是KeyValue格式的数据
    val kafkaSink = new FlinkKafkaProducer[(String, Int)]("t_topic",
      new KafkaSerializationSchema[(String, Int)] {
      override def serialize(element: (String, Int), timestamp: lang.Long) = {
        new ProducerRecord("t_topic", element._1.getBytes, (element._2 + "").getBytes())
      }
    }, props, FlinkKafkaProducer.Semantic.EXACTLY_ONCE)

    result.addSink(kafkaSink) //EXACTLY_ONCE 精确一次
    //执行
    streamEnv.execute()
  }
}