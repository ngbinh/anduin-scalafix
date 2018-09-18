package fix

object Novalinabstractrewrite_v1_0_Test {
  // Add code that needs fixing here.
  trait a0 {
    def aVal: Int = 0
    def aDef: String = ""
    def anotherVal: Double = 1.0f
    val anAbstractValIsOk: Int
  }

  object a1 extends a0 {
    override val aVal = 2
    def anotherDef: String = aDef
    val anAbstractValIsOk: Int = aVal
  }
}
