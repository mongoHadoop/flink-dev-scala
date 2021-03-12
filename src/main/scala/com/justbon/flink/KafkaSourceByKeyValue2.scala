package com.justbon.flink

import java.util
import java.util.{HashMap, Map, Properties}

import com.alibaba.fastjson.JSON
import com.justbon.java.util.JsonUtil
import com.justbon.kafkaschema.KafkaMsgSchema
import com.typesafe.scalalogging.Logger
import lombok.extern.slf4j.Slf4j
import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, _}
import org.apache.flink.streaming.connectors.kafka.internals.{KafkaTopicPartition, KeyedSerializationSchemaWrapper}
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer011, FlinkKafkaProducer011}

import scala.util.parsing.json.JSONObject


/***
  * 方式二：Kafka到Kafka
  *
  *    1.代码
  *
  * import java.util.Properties
  * import org.apache.flink.api.common.serialization.SimpleStringSchema
  * import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
  * import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer011, FlinkKafkaProducer011}
  *
  * //温度传感器读取样例类
  * case class SensorReading(id: String, timestamp: Long, temperature: Double)
  *
  * object KafkaSinkTest1 {
  * def main(args: Array[String]): Unit = {
  * val env = StreamExecutionEnvironment.getExecutionEnvironment
  *     env.setParallelism(1)
  *
  * import org.apache.flink.api.scala._
  * //从Kafka到Kafka
  * val properties = new Properties()
  *     properties.setProperty("bootstrap.servers", "localhost:9092")
  *     properties.setProperty("group.id", "consumer-group")
  *     properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  *     properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  *     properties.setProperty("auto.offset.reset", "latest")
  *
  * val inputStream = env.addSource(new FlinkKafkaConsumer011[String]("sensor", new SimpleStringSchema(), properties))
  * val dataStream = inputStream.map(x => {
  * val arr = x.split(",")
  * SensorReading(arr(0).trim, arr(1).trim.toLong, arr(2).trim.toDouble).toString   //转成String方便序列化输出
  * })
  *
  * //sink
  *     dataStream.addSink(new FlinkKafkaProducer011[String]("localhost:9092", "sinkTest", new SimpleStringSchema()))
  *     dataStream.print()
  *
  *     env.execute(" kafka sink test")
  *
  * }
  * }
  */
/**
  * 从Kafka获取key-value类型的数据
  */
object KafkaSourceByKeyValue2 {
  val topic_str="tp_flink_test";
  val topic_sink="tp_flink_sink"
  val brockerServer="10.0.9.101:9092,10.0.9.102:9092,10.0.9.103:9092"
  val zkServer="10.0.9.101:2181,10.0.9.102:2181,10.0.9.103:2181"

  private[this]val logger =Logger(this.getClass)
  def main(args: Array[String]): Unit = {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment
    environment.setParallelism(1)
    val properties = new Properties()
    //10.0.9.101:9092,10.0.9.102:9092,10.0.9.103:9092
    //properties.setProperty("zookeeper.connect", "10.0.9.101:2181,10.0.9.102:2181,10.0.9.103:2181")
    properties.setProperty("bootstrap.servers", brockerServer)
    properties.setProperty("group.id", "consumer-group1")
    properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")


    val propertiesP = new Properties()
    propertiesP.setProperty("zookeeper.connect", zkServer)
    propertiesP.setProperty("bootstrap.servers", brockerServer)



    val consumer = new FlinkKafkaConsumer011[String](topic_str, new KafkaMsgSchema(), properties)
//    方式一 : 指定topic, 指定partition的offset位置
    var kafkaOffsets :Map[KafkaTopicPartition, java.lang.Long]= new  HashMap[KafkaTopicPartition, java.lang.Long];
    //val kafkaOffsets = new java.util.HashMap[KafkaTopicPartition, java.lang.Long]

    kafkaOffsets.put(new KafkaTopicPartition(topic_str, 0), 2l);
    consumer.setStartFromSpecificOffsets(kafkaOffsets);
   // Map<KafkaTopicPartition, Long> Long参数指定的offset位置


   // consumer.setStartFromSpecificOffsets(offsets)
    //方式二: 从topic中最初的数据开始消费
    //consumer.setStartFromEarliest

  //方式四： 从最新的数据开始消费
    //consumer.setStartFromLatest

    val stream = environment.addSource(consumer)
    stream.setParallelism(4).print()
    var listStream=stream.map(m=>{
      var JSONObject=JSON.parseObject(m.toString)
   //   println("*****[baicBean] fusionNum:" + JSONObject)
      logger.info("*****[baicBean] fusionNum:{}",JSONObject )
    })

//    //sink
//    val producer = new FlinkKafkaProducer011[String](
//      topic_sink,
//      new KeyedSerializationSchemaWrapper[String](new KafkaMsgSchema()),
//      propertiesP,
//      FlinkKafkaProducer011.Semantic.AT_LEAST_ONCE);
//    stream.addSink(producer)
    environment.execute()
  }
}
