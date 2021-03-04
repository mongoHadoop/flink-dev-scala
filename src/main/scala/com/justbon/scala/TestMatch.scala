package com.justbon.scala

/***
  * Scala 模式匹配
  */
object Test3 {
  def main(args: Array[String]) {
    println(matchTest(3))
    println(matchTest2(1))
    println(matchTest2(2))
    println(matchTest2(3))

    val alice = new Person2("Alice", 25)
    val bob = new Person2("Bob", 32)
    val charlie = new Person2("Charlie", 32)
    ///MATCH模式的匹配使用
    for (person <- List(alice, bob, charlie)) {
      person match {
        case Person2("Alice", 25) => println("Hi Alice!")
        case Person2("Bob", 32) => println("Hi Bob!")
        case Person2(name, age) =>
          println("Age: " + age + " year, name: " + name + "?")
      }
    }
  }
  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }

  /***
    * 实例中第一个 case 对应整型数值 1,第二个 case 对应字符串值 two,
    * 第三个 case 对应类型模式,用于判断传入的值是否为整型,相比使用isInstanceOf来判断类型
    * 使用模式匹配更好.第四个 case 表示默认的全匹配备选项,
    * 即没有找到其他匹配时的匹配项,类似 switch 中的 default.
    * @param x
    * @return
    */
  def matchTest2(x: Any): Any = x match {
    case 1 => "one"
    case "two" => 2
    case y: Int => "scala.Int"
    case _ => "many"
  }
  // 样例类
  case class Person2(name: String, age: Int)
}