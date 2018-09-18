/*
rule = "class:fix.NoValInAbstractRewrite_v1_0"
*/
package fix

object Novalinabstractrewrite_v1_0_Test {
  // Add code that needs fixing here.
  trait a0 {
    val aVal: Int = 0
    def aDef: String = ""
    val anotherVal: Double = 1.0f
    val anAbstractValIsOk: Int
  }

  object a1 extends a0 {
    override val aVal = 2
    def anotherDef: String = aDef
    val anAbstractValIsOk: Int = aVal
  }
}
