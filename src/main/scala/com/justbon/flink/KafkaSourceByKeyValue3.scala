package com.justbon.flink


import java.util.Properties

import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, KafkaDeserializationSchema}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.flink.streaming.api.scala._

/**
  * 从Kafka获取key-value类型的数据 通用kafa 连接器
  */
object KafkaSourceByKeyValue3 {

  def main(args: Array[String]): Unit = {
    //初始化Flink上下文环境
    val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    environment.setParallelism(1)

    val proc = new Properties()
    proc.setProperty("group.id", "flink2")
    proc.setProperty("bootstrap.servers", "tuge1:9092")
    proc.setProperty("name.deserializer", classOf[StringDeserializer].getName)
    proc.setProperty("value.deserializer", classOf[StringDeserializer].getName)
    proc.setProperty("auto.offset.reset", "latest")
    //构建Kafka数据源
    val stream = environment.addSource(new FlinkKafkaConsumer[(String, String)]("wordsTopic", new
        KafkaDeserializationSchema[(String, String)] {
      override def isEndOfStream(t: (String, String)): Boolean = false


      override def deserialize(consumerRecord: ConsumerRecord[Array[Byte], Array[Byte]]): (String, String) = {
        if (consumerRecord != null) {
          var key = "null"
          var value = "null"
          if (consumerRecord.key() != null) {
            key = new String(consumerRecord.key(), "UTF-8")
          }
          if (consumerRecord.value() != null) {
            value = new String(consumerRecord.value(), "UTF-8")
          }
          (key, value)
        } else { //如果数据为空，则返回null数组
          (null, null)
        }
      }

      override def getProducedType: TypeInformation[(String, String)] = createTuple2TypeInformation(createTypeInformation[String], createTypeInformation[String])

    }, proc).setStartFromEarliest())
    //打印数据
    stream.print()
    //执行
    environment.execute()
  }


}
