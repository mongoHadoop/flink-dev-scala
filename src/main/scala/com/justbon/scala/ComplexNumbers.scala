package com.justbon.scala

object ComplexNumbers {
  def main(args: Array[String]) {
    val c = new Complex(1.2, 3.4)
    val c2 = new Complex2(51.2, 8.4)
    println("imaginary part: " + c.im())
    println("imaginary part: " + c2.im)
  }
}

/****
  * 关于方法re和im还有一个小问题：你必须在名字后面加上一对括号来调用它们
  */
