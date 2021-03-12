//package com.justbon.flink
//
//
//import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
//import org.apache.flink.table.api.{TableEnvironment, Types}
//import org.apache.flink.table.descriptors.{Json, Kafka, Rowtime, Schema}
//import org.apache.flink.types.Row
//import org.apache.flink.streaming.api.scala._
//
//object KafkaJsonConnector {
//
//  def main(args: Array[String]): Unit = {
//    val env = StreamExecutionEnvironment.getExecutionEnvironment
//    // create a TableEnvironment for streaming queries
//    val tableEnv = TableEnvironment.getTableEnvironment(env)
//
//    tableEnv
//      .connect(
//        new Kafka()
//          .version("0.10")
//          .topic("SM_USER_PROFILE")
//          .startFromEarliest()
//          .property("zookeeper.connect", "localhost:2181")
//          .property("bootstrap.servers", "localhost:9092"))
//      .withFormat(
//        new Json()
//          .deriveSchema()
//      )
//      .withSchema(
//        new Schema()
//          .field("COD_USERNO","string")
//          .field("COD_USER_ID","string")
//      )
//      .inAppendMode()
//      .registerTableSource("sm_user")
//
//    val stream = tableEnv.scan("sm_user")
//    tableEnv.toAppendStream[Row](stream).print().setParallelism(1)
//    env.execute("example")
//  }
//
//}