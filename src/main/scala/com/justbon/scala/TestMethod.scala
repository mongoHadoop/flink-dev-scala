package com.justbon.scala

object TestMethod {
  def addInt( a:Int, b:Int ) : Int = {
    var sum:Int = 0
    sum = a + b

    return sum
  }

  /****
    * Scala 中的方法跟 Java 的类似，方法是组成类的一部分。
    * Scala 中的函数则是一个完整的对象，Scala 中的函数其实就是继承了 Trait 的类的对象。
    * Scala 中使用 val 语句可以定义函数，def 语句定义方法。
    * @param x
    * @return
    */
  def m(x: Int) = x + 3 //方法
  val f = (x: Int) => x + 3 //函数


  // 函数 f 和 值 v 作为参数，而函数 f 又调用了参数 v
  def apply(f: Int => String, v: Int) = f(v)

  def main(args: Array[String]) {
    println( "Returned Value : " + addInt(5,7) );
  }
}

/****
  * 方法定义由一个 def 关键字开始，紧接着是可选的参数列表，一个冒号 : 和方法的返回类型，一个等于号 = ，最后是方法的主体。
  * def functionName ([参数列表]) : [return type] = {
  * function body
  * return [expr]
  * }
  * 以上代码中 return type 可以是任意合法的 Scala 数据类型。参数列表中的参数可以使用逗号分隔。
  * 如果方法没有返回值，可以返回为 Unit，这个类似于 Java 的 void, 实例如下：
  */
