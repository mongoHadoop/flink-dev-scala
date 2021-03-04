package com.justbon.scala

class Point(val xc: Int, val yc: Int) {
  var x: Int = xc
  var y: Int = yc
  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
    println ("x 的坐标点 : " + x);
    println ("y 的坐标点 : " + y);
  }
}

class Location(override val xc: Int, override val yc: Int,
               val zc :Int) extends Point(xc, yc){
  var z: Int = zc

  def move(dx: Int, dy: Int, dz: Int) {
    x = x + dx
    y = y + dy
    z = z + dz
    println ("x 的坐标点 : " + x);
    println ("y 的坐标点 : " + y);
    println ("z 的坐标点 : " + z);
  }
}

/***
  * Scala继承一个基类跟Java很相似, 但我们需要注意以下几点：
  *
  * 1、重写一个非抽象方法必须使用override修饰符。
  * 2、只有主构造函数才可以往基类的构造函数里写参数。
  * 3、在子类中重写超类的抽象方法时，你不需要使用override关键字。
  *
  * Scala中的类不声明为public，一个Scala源文件中可以有多个类。
  * 以上实例的类定义了两个变量 x 和 y ，一个方法：move，方法没有返回值。
  * Scala 的类定义可以有参数，称为类参数，如上面的 xc, yc，类参数在整个类中都可以访问。
  * 接着我们可以使用 new 来实例化类，并访问类中的方法和变量：
  *
  *
  * Scala 使用 extends 关键字来继承一个类。实例中 Location 类继承了 Point 类。Point 称为父类(基类)，Location 称为子类。
  * override val xc 为重写了父类的字段。
  * 继承会继承父类的所有属性和方法，Scala 只允许继承一个父类。
  */

class Person {
  var name = ""
  override def toString = getClass.getName + "[name=" + name + "]"
}

class Employee extends Person {
  var salary = 0.0
  override def toString = super.toString + "[salary=" + salary + "]"
}

/****
  * 在 Scala 中，是没有 static 这个东西的，但是它也为我们提供了单例模式的实现方法，那就是使用关键字 object。
  * Scala 中使用单例模式时，除了定义的类之外，
  * 还要定义一个同名的 object 对象，
  * 它和类的区别是，object对象不能带参数。
  * 当单例对象与某个类共享同一个名称时，
  * 他被称作是这个类的伴生对象：companion object。
  * 你必须在同一个源文件里定义类和它的伴生对象。
  * 类被称为是这个单例对象的伴生类：companion class。类和它的伴生对象可以互相访问其私有成员。
  */
object Test {
  def main(args: Array[String]) {
    val point = new Point(10, 20)
    printPoint

    def printPoint{
      println ("x 的坐标点 : " + point.x);
      println ("y 的坐标点 : " + point.y);
    }
  }
}
