package fix

trait A {
  val a = 1
  val bfinal = 1
  private val fv = 1
  private val fs = 1
  protected val fv1 = 1
}

class B extends A {
  override protected val fv1 = 2
}
