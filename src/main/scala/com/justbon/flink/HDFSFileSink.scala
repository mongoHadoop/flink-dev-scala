package com.justbon.flink

import java.util.concurrent.TimeUnit

import org.apache.flink.api.common.serialization.SimpleStringEncoder
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment


/**
  * 基于HDFS的Sink
  */
object HDFSFileSink {

  def main(args: Array[String]): Unit = {

    //构建Flink上下文
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    import org.apache.flink.streaming.api.scala._

    //读取数据
    val data = env.readTextFile("./data/words")

    //构建Kafka Sink
    val outPutPath = "hdfs://tuge2:9000/FlinkDataTest"
    val sink = StreamingFileSink.forRowFormat(new Path(outPutPath), new SimpleStringEncoder[String]("UTF-8"))
      .withRollingPolicy(
        DefaultRollingPolicy.create()
          .withInactivityInterval(TimeUnit.MINUTES.toMinutes(1)) //文件不活动
          .withRolloverInterval(TimeUnit.MINUTES.toMinutes(5)) //每隔5分钟生成一个文件
          .withMaxPartSize(1024 * 1024 * 1024) //文件最大大小为1G
          .build()
      ).build()

    data.addSink(sink)
    env.execute()

  }

}
