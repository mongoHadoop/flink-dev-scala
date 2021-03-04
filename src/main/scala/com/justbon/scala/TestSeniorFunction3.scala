package com.justbon.scala

/***
  * Scala
  * 高阶函数是指使用其他函数作为参数、或者返回一个函数作为结果的函数。在Scala中函数是“一等公民”，
  * 所以允许定义高阶函数。
  * 这里的术语可能有点让人困惑，
  * 我们约定，使用函数值作为参数，或者返回值为函数值的“函数”和“方法”，均称之为“高阶函数”。
  */
object TestSeniorFunction3 {

  def main(args: Array[String]): Unit = {



    //3 高阶函数  1、能够接受函数作为参数的方法，叫做高阶函数
    val func3:(Int,String) =>(String,Int)={

      (x,y)=>(y,x)

    }

    def myMethod3(hello:(Int,String) => (String,Int)):Int ={

      val resultFunc = hello(20,"hello")

      resultFunc._2

    }
    println(myMethod3(func3))
  }
}
