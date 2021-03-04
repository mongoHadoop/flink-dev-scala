package com.justbon.scala

/***
  * Scala
  * 高阶函数是指使用其他函数作为参数、或者返回一个函数作为结果的函数。在Scala中函数是“一等公民”，
  * 所以允许定义高阶函数。
  * 这里的术语可能有点让人困惑，
  * 我们约定，使用函数值作为参数，或者返回值为函数值的“函数”和“方法”，均称之为“高阶函数”。
  */
object TestSeniorFunction4 {

  def main(args: Array[String]): Unit = {

    //3 高阶函数
    //3.2 高阶函数同样可以返回一个函数类型 (y:String) => y+x 即是一个函数
    def myFunc4(x:Int) = (y:String) => y+x
    println(myFunc4(50))
    val f=myFunc4(50)
    println(f("w"))
  }
}
