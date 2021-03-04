package com.justbon.scala

/***
  * Scala
  * 高阶函数是指使用其他函数作为参数、或者返回一个函数作为结果的函数。在Scala中函数是“一等公民”，
  * 所以允许定义高阶函数。
  * 这里的术语可能有点让人困惑，
  * 我们约定，使用函数值作为参数，或者返回值为函数值的“函数”和“方法”，均称之为“高阶函数”。
  */
object TestSeniorFunction1 {

  def main(args: Array[String]): Unit = {

    val salaries = Seq(20000, 70000, 40000)
    println(salaries)
    val doubleSalary = (x: Int) => x * 2
    val newSalaries = salaries.map(doubleSalary)
    println(newSalaries)

    /**
      * 函数doubleSalary有一个整型参数x，返回x * 2.
      * 一般来说，在=>左边的元组是函数的参数列表，而右边表达式的值则为函数的返回值。
      * 函数doubleSalary被应用在列表salaries中的每一个元素。
      * 为了简化压缩代码，我们可以使用匿名函数，直接作为参数传递给map:
      */
    val newSalaries_ = salaries.map(x => x * 2)
    println(newSalaries_)

    /***
      * 注意在上述示例中x没有被显式声明为Int类型，这是因为编译器能够根据map函数期望的类型推断出x的类型。对于上述代码，一种更惯用的写法为：
      *
      * 既然Scala编译器已经知道了参数的类型（一个单独的Int），你可以只给出函数的右半部分，不过需要使用_代替参数名（在上一个例子中是x）
      */
    val newSalaries2 = salaries.map(_ * 2)
    println(newSalaries2)
    //1 作为参数的函数.
    //函数可以作为一个参数传入到一个方法当中去函数可以作为一个参数传入到一个方法当中去
    val myFunc1 =(x:Int) =>{

      x * x

    }

    val myArray = Array(1,3,5,7,9).map(myFunc1)

    println(myArray.mkString(","))

    //2 匿名函数
    val hiddenFunc = (x:Int,y:String) => x + y

    println(hiddenFunc)

  }
}
