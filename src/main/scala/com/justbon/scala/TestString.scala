package com.justbon.scala

/**
  * Scala 字符串
  *
  * 实例定义了变量 greeting，为字符串常量，它的类型为 String (java.lang.String)。
  * 在 Scala 中，字符串的类型实际上是 Java String，它本身没有 String 类。
  * 在 Scala 中，String 是一个不可变的对象，所以该对象不可被修改。这就意味着你如果修改字符串就会产生一个新的字符串对象。
  * 但其他对象，如数组就是可变的对象。接下来我们会为大家介绍常用的 java.lang.String 方法。
  */
object TestString {

  val greeting: String = "Hello,World!"

  def main(args: Array[String]) {
    println( greeting )

// String 对象是不可变的，如果你需要创建一个可以修改的字符串，可以使用 String Builder 类，如下实例:
    val buf = new StringBuilder;
    buf += 'a'
    buf ++= "bcdef"
    println( "buf is : " + buf.toString );

//我们可以使用 length() 方法来获取字符串长度：
    var palindrome = "www.runoob.com";
    var len = palindrome.length();
    println( "String Length is : " + len );
    //string1.concat(string2);

    //创建格式化字符串 String format() 方法可以返回 String 对象而不是 PrintStream 对象。以下实例演示了 printf() 方法的使用：
    var floatVar = 12.456
    var intVar = 2000
    var stringVar = "菜鸟教程!"
    var fs = printf("浮点型变量为 " +
      "%f, 整型变量为 %d, 字符串为 " +
      " %s", floatVar, intVar, stringVar)
    println(fs)
  }
}
