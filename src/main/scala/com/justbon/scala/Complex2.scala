package com.justbon.scala

class Complex2(real: Double, imaginary: Double) {
  def re = real
  def im = imaginary
}

/***
  * 你可能觉得吧这些函数当作变量使用，而不是当作函数进行调用，
  * 可能会更加令人感到舒服。事实上我们可以通过定义无参函数在Scala做到这点。
  * 这类 函数与其他的具有0个参数的函数的不同点在于他们定义时不需要在名字后面加括弧，
  * 所以在使用时也不用加（但是无疑的，他们是函数），因此，我们的 Complex类可以重新写成上面的样子
  */

