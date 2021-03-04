package com.justbon.scala

/***
  * Scala
  * 高阶函数是指使用其他函数作为参数、或者返回一个函数作为结果的函数。在Scala中函数是“一等公民”，
  * 所以允许定义高阶函数。
  * 这里的术语可能有点让人困惑，
  * 我们约定，使用函数值作为参数，或者返回值为函数值的“函数”和“方法”，均称之为“高阶函数”。
  */
object TestSeniorFunction2 {

  def main(args: Array[String]): Unit = {

    //1 作为参数的函数.
    //函数可以作为一个参数传入到一个方法当中去函数可以作为一个参数传入到一个方法当中去
    val myFunc1 =(x:Int) =>{

      x * x

    }

    val myArray = Array(1,3,5,7,9).map(myFunc1)

    println(myArray.mkString(","))

    //2 匿名函数
    val hiddenFunc = (x:Int,y:String) => x + y

    println(hiddenFunc)

  }
}
