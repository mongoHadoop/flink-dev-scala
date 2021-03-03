package com.justbon.scala

object TestArray {

  def main(args: Array[String]): Unit = {
    var z:Array[String] = new Array[String](3)
    //或者
    var z_ = new Array[String](3)
    //或者
    var z__ = Array("Runoob", "Baidu", "Google")
    var myList = Array(1.9, 2.9, 3.4, 3.5)

    // 输出所有数组元素
    for ( x <- myList ) {
      println( x )
    }

    // 计算数组所有元素的总和
    var total = 0.0;
    for ( i <- 0 to (myList.length - 1)) {
      total += myList(i);
    }
    println("总和为 " + total);

    // 查找数组中的最大元素
    var max = myList(0);
    for ( i <- 1 to (myList.length - 1) ) {
      if (myList(i) > max) max = myList(i);
    }
    println("最大值为 " + max);
  }
}
