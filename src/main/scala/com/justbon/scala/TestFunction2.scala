package com.justbon.scala

object TestFunction2 {
  def main(args: Array[String]) {
   //一般情况下函数调用参数，就按照函数定义时的参数顺序一个个传递。但是我们也可以通过指定函数参数名，并且不需要按照顺序向函数传递参数，实例如下：
    printInt(b=5, a=7);
  }
  def printInt( a:Int, b:Int ) = {
    println("Value of a : " + a );
    println("Value of b : " + b );
  }
}
