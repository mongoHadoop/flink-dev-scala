package com.justbon.scala

import java.io._
//Scala 进行文件写操作，直接用的都是 java中 的 I/O 类 （java.io.File)：
import scala.io._

object TestIO {
  def main(args: Array[String]) {
    val writer = new PrintWriter(new File("test.txt" ))

    writer.write("菜鸟教程xx")
    writer.close()

    print("请输入菜鸟教程官网 : " )
    val line = StdIn.readLine()

    println("谢谢，你输入的是: " + line)

    println("文件内容为:" )

    Source.fromFile("test.txt" ).foreach{
      print
    }
  }
}
