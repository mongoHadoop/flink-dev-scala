package com.justbon.scala

class Complex(real: Double, imaginary: Double) {
  def re() = real
  def im() = imaginary
}

/**
  * Scala类使用和Java类似的语法进行定义。但是一个重要的不同点在于Scala中的类可以拥有参数，这样就可以得出我们下面关于对复数类（Complex）的定义：
  * 就像这样：new Complex(1.5, 2.3)。类定义中包括两个叫做re和im的方法，分别接受上面提到的两个参数。
  *
  * 　值得注意的是这两个方法的返回类型并没有显式的声明出来。他们会被编译器自动识别。
  * 在本例中他们被识别为Double 但是编译器并不总是像本例中的那样进行自动识别。
  * 不幸的是关于什么时候识别，什么时候不识别的规则相当冗杂。
  * 在实践中这通常不会成为一个问题，因为 当编译器处理不了的时候会发出相当的抱怨。作为一个推荐的原则，
  * Scala的新手们通常可以试着省略类型定义而让编译器通过上下文自己判断。久而久之，新手们就可以感知到什么时候应该省略类型，什么时候不应该。
  */
