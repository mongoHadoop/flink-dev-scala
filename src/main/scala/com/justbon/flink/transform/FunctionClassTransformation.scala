package com.justbon.flink.transform


import java.text.SimpleDateFormat

import com.justbon.flink.StationLog
import com.typesafe.scalalogging.Logger
import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment,_}
/**
  * 普通函数类举例
  */
object FunctionClassTransformation {
  private[this]val logger =Logger(this.getClass)
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)//导入隐式转换
    // 从本地读取文件
    var dataSet = env.readTextFile("file:///home/mongo/workspace/respository/flink-dev-scala/station.log")
    //dataSet.print()
    var data2:DataSet[StationLog]=dataSet.map(line=>{
      val arr = line.split(",")
     // var log=new StationLog(arr(0).trim, arr(1).trim, arr(2).trim, arr(3).trim, arr(4).trim.toLong, arr(5).trim.toLong)
      logger.info("map.....{}",arr)
      new StationLog(arr(0).trim, arr(1).trim, arr(2).trim, arr(3).trim, arr(4).trim.toLong, arr(5).trim.toLong)
    })
    data2.print()
    //定义时间输出格式
    val format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    //过滤掉那些通话成功的
    data2.filter(_.callType.equals("success"))
      .map(new CallMapFunction(format))
      .print()
    //env.execute("StationLog_FunctionClassTransformation")
    /***
      * 从报错日志可看出，自上次执行以来，没有定义新的数据接收器。对于离线批处理的算子，
      * 如：“count()”、“collect()”或“print()”等既有sink功能，还有触发的功能。
      * 我们上面调用了print()方法，会自动触发execute，
      * 所以最后面的一行执行器没有数据可以执行。所以去掉最后一行代码即可，用了print()方法后无需再调用execute()方法。
      */
  }

  //自定义函数的类
  class CallMapFunction(format: SimpleDateFormat) extends MapFunction[StationLog, String] {
    override def map(t: StationLog): String = {
      val startTime = t.callTime
      val endTime = t.callTime + t.duration * 1000
      "基站ID:" + t.sid + ",呼叫号码:" + t.callOut + ",被叫号码:" + t.callIn + ",呼叫时间:" + format.format(t.callTime) + ",结束时间:" + format.format(endTime)
    }
  }


}