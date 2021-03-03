package com.justbon.scala

class Complex2(real: Double, imaginary: Double) {
  def re = real
  def im = imaginary

  override def toString() =
    "" + re + (if (im < 0) "" else "+") + im + "i"
}

/***
  * 你可能觉得吧这些函数当作变量使用，而不是当作函数进行调用，
  * 可能会更加令人感到舒服。事实上我们可以通过定义无参函数在Scala做到这点。
  * 这类 函数与其他的具有0个参数的函数的不同点在于他们定义时不需要在名字后面加括弧，
  * 所以在使用时也不用加（但是无疑的，他们是函数），因此，我们的 Complex类可以重新写成上面的样子
  */

/**
  *
  * Scala中的所有类都继承一个父类，当没有显示声明父类时（就像上面定义的Complex一样），
  * 它们的父类隐形指定为scala.AnyRef。
  *
  * 在子类中覆盖父类的成员是可能的。但是你需要通过override修饰符显示指定成员的覆盖。
  * 这样的规则可以避免意外覆盖的情况发生。作为演示，
  * 我们在Complex的定义中覆盖了Object的toString方法。
  * */
