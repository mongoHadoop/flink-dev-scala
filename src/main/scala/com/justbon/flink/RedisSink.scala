package com.justbon.flink


import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.redis.RedisSink
import org.apache.flink.streaming.connectors.redis.common.config.{FlinkJedisConfigBase, FlinkJedisPoolConfig}
import org.apache.flink.streaming.connectors.redis.common.mapper.{RedisCommand, RedisCommandDescription, RedisMapper}

/**
  * 将计算结果存入Redis
  */
object RedisSink {
  def main(args: Array[String]): Unit = {
    //构建Flink环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    import org.apache.flink.streaming.api.scala._
    //从Socket获取数据
    val stream = env.socketTextStream("tuge2", 8888)
    //计算wordcount
    val result = stream.flatMap(_.split(" "))
      .map((_, 1))
      .keyBy(0)
      .sum(1)

    //构建Redis配置
    val redisConfig = new FlinkJedisPoolConfig.Builder().setDatabase(0).setHost("tuge1").setPort(6379).build()

    result.print()
    //将结果存入Redis
    result.addSink(new RedisSink[(String, Int)](redisConfig, new RedisMapper[(String, Int)] {
      override def getCommandDescription: RedisCommandDescription = new RedisCommandDescription(RedisCommand.HSET, "t_wc")

      override def getKeyFromData(t: (String, Int)): String = t._1 //单词

      override def getValueFromData(t: (String, Int)): String = t._2 + "" //单词出现的次数
    }))

    env.execute()

  }
}