package fix

import scalafix.util.Whitespace
import scalafix.v1
import scala.meta._

final class NoFinalValRewrite extends v1.SyntacticRule("NoFinalValRewrite") {
  override def description: String = "Replaces final val with val"

  override def isRewrite: Boolean = true

  override def fix(implicit doc: v1.SyntacticDocument): v1.Patch = {
    doc.tree.collect {
      case t @ Defn.Val(mods, _, _, _) =>
        if (mods.indexWhere(_.is[Mod.Final]) != -1) {
          val tokens = t.tokens
          
          tokens
            .dropWhile(t => t.syntax != "final")
            .takeWhile(t => t.syntax == "final" || t.is[Whitespace])
            .map(v1.Patch.removeToken)
            .asPatch
        } else {
          v1.Patch.empty
        }
    }.asPatch
  }
}


