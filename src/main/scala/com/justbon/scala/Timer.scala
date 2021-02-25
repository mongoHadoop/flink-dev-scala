package com.justbon.scala

object Timer {
  //Unit关键字类似于C/C++中的void）
  def oncePerSecond(callback: () => Unit) {
    while (true) { callback(); Thread sleep 1000 }
  }
  def timeFlies() {
    println("time flies like an arrow...")
  }
  def main(args: Array[String]) {
    oncePerSecond(timeFlies) //把函数当值传递过去了
  }
}

/**
  *
  * Function
  * 函数在Scala语言里面也是一个对象，也许这对于Java程序员来说这比较令人惊讶。于是吧函数作为参数进行传递、把它们存贮在变量中、
  * 或者当作另一个函数的返回值都是可能的。把函数当成值进行操作是函数型编程语言的基石。
  *
  * 为了解释为什么吧函数当作值进行操作是十分有用的，我们来考虑一个计时器函数。
  * 这个函数的目的是每隔一段时间就执行某些操作。那么如何吧我们要做的 操作传入计时器呢？于是我们想吧他当作一个函数。
  * 这种目前的函数对于经常进行用户界面编程的程序员来说是最熟悉的：注册一个回调函数以便在事件发生后得到 通知。
  *
  * 在下面的程序中，计时器函数被叫做oncePerSceond，它接受一个回调函数作为参数。这种函数的类型被写作 () => Unit ，
  * 他们不接受任何参数也没有任何返回（Unit关键字类似于C/C++中的void）。程序的主函数调用计时器并传递一个打印某个句子的函数作为回调。
  * 换 句话说，这个程序永无止境的每秒打印一个“time flies like an arrow”。
  */