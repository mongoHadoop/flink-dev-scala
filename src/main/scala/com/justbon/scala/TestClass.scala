package com.justbon.scala

object TestClass {

  def main(args: Array[String]) {
    val loc = new Location(10, 20, 15);
    // 移到一个新的位置
    loc.move(10, 10, 5);

    val fred = new Employee
    fred.name = "Fred"
    fred.salary = 50000
    println(fred)
  }
}
