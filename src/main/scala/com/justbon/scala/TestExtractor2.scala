package com.justbon.scala

/***
  * 提取器是从传递给它的对象中提取出构造该对象的参数。
  *
  * Scala 标准库包含了一些预定义的提取器，我们会大致的了解一下它们。
  *
  * Scala 提取器是一个带有unapply方法的对象。unapply方法算是apply方法的反向操作：
  * unapply接受一个对象，然后从对象中提取值，提取的值通常是用来构造该对象的值。
  */
object TestExtractor2 {
  def main(args: Array[String]) {
    val x = TestExtractor2(5)
    println(x)

    x match
    {
      case TestExtractor2(num) => println(x + " 是 " + num + " 的两倍！")
      //unapply 被调用
      case _ => println("无法计算")
    }

  }
  def apply(x: Int) = x*2
  def unapply(z: Int): Option[Int] = if (z%2==0) Some(z/2) else None

}

/*****
  * 在我们实例化一个类的时，可以带上0个或者多个的参数，编译器在实例化的时会调用 apply 方法。我们可以在类和对象中都定义 apply 方法。
  *
  * 就像我们之前提到过的，unapply 用于提取我们指定查找的值，它与 apply 的操作相反。 当我们在提取器对象中使用 match 语句是，unapply 将自动执行，如下所示：
  */
