package com.justbon.scala

import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException

object TestException {
  def main(args: Array[String]) {
    try {
      val f = new FileReader("input.txt")
    } catch {
      case ex: FileNotFoundException =>{
        println("Missing file exception")
      }
      case ex: IOException => {
        println("IO Exception")
      }

      /***
        * catch字句里的内容跟match里的case是完全一样的。
        * 由于异常捕捉是按次序，如果最普遍的异常，Throwable，
        * 写在最前面，则在它后面的case都捕捉不到，因此需要将它写在最后面。
        */
    }finally {
      println("Exiting finally...")
    }
  }
}
