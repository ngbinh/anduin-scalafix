/*
rule = "class:fix.NoFinalValRewrite"
*/
package fix

trait A {
  final val a = 1
  val bfinal = 1
  private final val fv = 1
  final private val fs = 1
  protected val fv1 = 1
}

class B extends A {
  override protected final val fv1 = 2
}
