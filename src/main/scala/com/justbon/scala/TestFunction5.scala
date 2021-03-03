package com.justbon.scala

/***
  * 闭包 是一个函数，返回值依赖于声明在函数外部的一个或多个变量。
  */
object TestFunction5 {

  //闭包是一个函数，返回值依赖于声明在函数外部的一个或多个变量。
  val multiplier_ = (i:Int) => i * 10
//函数体内有一个变量 i，它作为函数的一个参数。如下面的另一段代码：
  var factor = 3

  val multiplier = (i:Int) => i * factor
//在 multiplier 中有两个变量：i 和 factor。其中的一个 i 是函数的形式参数
  // ，在 multiplier 函数被调用时，i 被赋予一个新的值。然而，factor不是形式参数，而是自由变量，考虑下面代码：
  def main(args: Array[String]) {
    println( "muliplier(1) value = " +  multiplier(1) )
    println( "muliplier(2) value = " +  multiplier(2) )
  }
}
