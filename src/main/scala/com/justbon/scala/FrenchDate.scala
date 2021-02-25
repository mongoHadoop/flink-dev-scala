package com.justbon.scala
import java.util.{Date, Locale}
import java.text.DateFormat._
object FrenchDate {
  def main(args: Array[String]) {
    val now = new Date
    val df = getDateInstance(LONG, Locale.CHINA)
    println(df format now)
  }
}
/**
  * 1. Java类库定义了一系列很有用的类,比如Date和DateFormat.由于Scala于Java能够进行很好的交互，我们不需要在Scala类库中实现等效的代码,而只需直接吧Java的相关类导入就可以了：
  *
  * 2. Scala的import语句看上去与Java的非常相似，但是它更加强大.你可以使用大括号来导入同一个包里的多个类，就像上面代码中第一行所做的那样.
  * 另一个不同点是当导入一个包中所有的类或者符号时，你应该使用下划线（_）而不是星号（*）.这是由于星号在Scala中是一个有效的标识符（例如作为方法名称）.
  *
  * 3. 第三行的import语句导入了DataFormat类中的所有成员，这使得静态方法getDateInstance和静态变量LONG可以被直接引用。
  *
  * 4. 在main函数中，我们首先建立了一个Java的Date实例。这个实例默认会包含当前时间.
  * 接下来我们一个使用刚才导入的静态函数getDateInstance定义了日期格式.
  * 最后我们将使用DataFotmat格式化好的日期打印了出来.
  * 最后一行代码显示了Scala的一个有趣 的语法：只有一个参数的函数可以使用下面这样的表达式来表示：
  * df format now
  * 其实就是下面的这个冗长的表达式的简洁写法 df.format(now)
  */