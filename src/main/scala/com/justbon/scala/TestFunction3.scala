package com.justbon.scala

object TestFunction3 {
  def main(args: Array[String]) {
    printStrings("Runoob", "Scala", "Python");
    println("返回值 : " + addInt())
    for (i <- 1 to 10)
      println(i + " 的阶乘为: = " + factorial(i) )

  }
  //Scala 允许你指明函数的最后一个参数可以是重复的，即我们不需要指定函数参数的个数，可以向函数传入可变长度参数列表。
  //
  //Scala 通过在参数的类型之后放一个星号来设置可变参数(可重复的参数)。例如：
  def printStrings( args:String* ) = {
    var i : Int = 0;
    for( arg <- args ){
      println("Arg value[" + i + "] = " + arg );
      i = i + 1;
    }
  }
//递归调用
  def factorial(n: BigInt): BigInt = {
    if (n <= 1)
      1
    else
      n * factorial(n - 1)
  }
//Scala 可以为函数参数指定默认参数值，使用了默认参数，你在调用函数的过程中可以不需要传递参数，这时函数就会调用它的默认参数值
  def addInt( a:Int=5, b:Int=7 ) : Int = {
    var sum:Int = 0
    sum = a + b

    return sum
  }
}
