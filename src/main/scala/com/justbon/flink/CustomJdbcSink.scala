package com.justbon.flink


import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
  * 自定义Sink实现
  * 自定义Sink 继承SinkFunction或RichSinkFunction即可
  * （将数据写入MySql）
  *
  * 当然你可以自己定义 Sink，有两种实现方式：
  * 1、实现 SinkFunction 接口。2、实现RichSinkFunction 类。
  * 后者增加了生命周期的管理功能。比如需要在 Sink 初始化的时候创建连接对象，则最好使用第二种。
  * 案例需求：把 StationLog 对象写入 Mysql 数据库中。
  */
object CustomJdbcSink {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    import org.apache.flink.api.scala._
    val stream = env.readTextFile(getClass.getResource("/words").getPath)
    val data = stream.map(line => {
      val lineWord = line.split(" ")
      new Person(lineWord(0).toInt, lineWord(1).toString, lineWord(2).toInt)
    })
    data.addSink(new MyCustomSink)
    env.execute("person_sink")
    print("执行完毕！")
  }

  //自定义一个Sink写入MySql
  class MyCustomSink extends RichSinkFunction[Person] {
    var conn: Connection = _
    var pres: PreparedStatement = _

    override def invoke(per: Person, context: SinkFunction.Context[_]): Unit = {
      pres.setInt(1, per.id)
      pres.setString(2, per.name)
      pres.setInt(3, per.age)
      pres.execute()
    }

    override def open(parameters: Configuration): Unit = {
      conn = DriverManager.getConnection("jdbc:mysql://tuge1:3306/test?characterEncoding=utf-8&userUnicode=true",
        "root",
        "123456")
      pres = conn.prepareStatement("insert into Person(Id,`Name`,Age) values(?,?,?)")
    }

    override def close(): Unit = {
      pres.close()
      conn.close()
    }


  }

  case class Person(id: Int, name: String, age: Int)

}