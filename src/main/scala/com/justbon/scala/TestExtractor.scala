package com.justbon.scala

/***
  * 提取器是从传递给它的对象中提取出构造该对象的参数。
  *
  * Scala 标准库包含了一些预定义的提取器，我们会大致的了解一下它们。
  *
  * Scala 提取器是一个带有unapply方法的对象。unapply方法算是apply方法的反向操作：
  * unapply接受一个对象，然后从对象中提取值，提取的值通常是用来构造该对象的值。
  */
object TestExtractor {
  def main(args: Array[String]) {

    println ("Apply 方法 : " + apply("Zara", "gmail.com"));
    println ("Unapply 方法 : " + unapply("Zara@gmail.com"));
    println ("Unapply 方法 : " + unapply("Zara Ali"));

  }
  // 注入方法 (可选)
  def apply(user: String, domain: String) = {
    user +"@"+ domain
  }

  // 提取方法（必选）
  /****
    * 以上对象定义了两个方法： apply 和 unapply 方法。通过 apply 方法我们无需使用 new 操作就可以创建对象。
    * 所以你可以通过语句 Test("Zara", "gmail.com") 来构造一个字符串 "Zara@gmail.com"。
    *
    * unapply方法算是apply方法的反向操作：unapply接受一个对象，然后从对象中提取值，
    * 提取的值通常是用来构造该对象的值。实例中我们使用 Unapply 方法从对象中提取用户名和邮件地址的后缀。
    *
    * 实例中 unapply 方法在传入的字符串不是邮箱地址时返回 None。代码演示如下：
    * unapply("Zara@gmail.com") 相等于 Some("Zara", "gmail.com")
    * unapply("Zara Ali") 相等于 None
    */
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2){
      Some(parts(0), parts(1))
    }else{
      None
    }
  }


}
