package com.justbon.scala

object TestIterator {
  def main(args: Array[String]) {
    val it = Iterator("Baidu", "Google", "Runoob", "Taobao")

    while (it.hasNext){
      println(it.next())
    }
  }
}

/***
  * Scala Iterator（迭代器）不是一个集合，它是一种用于访问集合的方法。
  * 迭代器 it 的两个基本操作是 next 和 hasNext。
  * 调用 it.next() 会返回迭代器的下一个元素，并且更新迭代器的状态。
  * 调用 it.hasNext() 用于检测集合中是否还有元素。
  * 让迭代器 it 逐个返回所有元素最简单的方法是使用 while 循环：
  *
  * /
