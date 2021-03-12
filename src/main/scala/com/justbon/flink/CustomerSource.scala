package com.justbon.flink
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

import scala.util.Random
/**
  * 自定义 Source
  * 当然也可以自定义数据源，有两种方式实现：
  *
  * 通过实现 SourceFunction 接口来自定义无并行度（也就是并行度只能为 1）的 Source。
  * 通过实现 ParallelSourceFunction 接口或者继承 RichParallelSourceFunction 来自定义有并行度的数据源。
  * 拿SourceFunction举例：
  */
object CustomerSource {

  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment =
      StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    import org.apache.flink.streaming.api.scala._
    var sourceFunction=new MyCustomerSource
    val stream = env.addSource(sourceFunction)
    stream.print()
    //sourceFunction.cancel()
    env.execute()
  }

  class MyCustomerSource extends SourceFunction[StationLog] {
    //是否终止数据流标记
    var flag = true;

    /**
      * 启动一个source
      * 大部分情况下，都需要在这个run里面实现一个循环，这样就产生数据了
      *
      * @param sourceContext
      */
    override def run(sourceContext: SourceFunction.SourceContext[StationLog]): Unit = {
      val random = new Random()
      var types = Array("fail", "busy", "barring", "success")
      while (flag) { //如果没有终止，继续获取数据
        1.to(5).map(i => {
          var callOut = "1860000%04d".format(random.nextInt(10000)) //%04d是随机生成4位数字
          var callIn = "1890000%04d".format(random.nextInt(10000))
          new StationLog("station_" + random.nextInt(10), callOut, callIn, types(random.nextInt(4
          )), System.currentTimeMillis(), 0)

        }).foreach(sourceContext.collect(_))
        Thread.sleep(2000) //每发送一次休息两秒

      }
    }

    /**
      * 终止流数据
      */
    override def cancel(): Unit = flag = false

  }
}
