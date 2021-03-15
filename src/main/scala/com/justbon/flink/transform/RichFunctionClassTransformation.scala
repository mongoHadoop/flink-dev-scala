package com.justbon.flink.transform


import java.sql.{Connection, DriverManager, PreparedStatement}

import com.justbon.flink.StationLog
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
  * 富函数类定义
  */
object RichFunctionClassTransformation {
  def main(args: Array[String]): Unit = {
    //构建Flink流式数据上下文执行环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    //设置并行度为1
    env.setParallelism(1)
    //设置隐式转换
    import org.apache.flink.streaming.api.scala._
    //获取通话成功的数据
    val data = env.readTextFile(getClass.getResource("/station.log").getPath).filter(_.split(",")(3).equals("success"))
      .map(line => {
        val arr = line.split(",")
        new StationLog(arr(0).trim, arr(1).trim, arr(2).trim, arr(3).trim, arr(4).trim.toLong, arr(5).trim.toLong)
      })

    data.map(new CallRichMapFunction)
      .print()


    env.execute()

  }


}

class CallRichMapFunction() extends RichMapFunction[StationLog, StationLog] {
  var conn: Connection = _
  var pre: PreparedStatement = _
  override def open(parameters: Configuration): Unit = {
    Class.forName("com.mysql.jdbc.Driver")
    conn = DriverManager.getConnection("jdbc:mysql://tuge1:3306/test", "root", "123456")
    pre = conn.prepareStatement("select `Name` from Person where id=?")
  }

  override def close(): Unit = {
    pre.close()
    conn.close()
  }

  override def map(in: StationLog): StationLog = {
    pre.setInt(1, 1) //注意设置mysql时，index是从1开始的
    val result = pre.executeQuery()
    if (result.next()) {
      in.callIn = result.getString(1)
    }
    in
  }
}