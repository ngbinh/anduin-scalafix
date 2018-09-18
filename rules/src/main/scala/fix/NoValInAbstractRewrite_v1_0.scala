package fix

import scalafix.v1
import scala.meta._

final class NoValInAbstractRewrite_v1_0 extends v1.SyntacticRule("NoValInAbstractRewrite_v1_0") {

  override def description: String = "Replaces vals in abstract class and trait by defs"

  override def isRewrite: Boolean = true

  override def fix(implicit doc: v1.SyntacticDocument): v1.Patch = {

    doc.tree.collect {
      case _ @ AbstractWithVals(vals) =>
        vals.map { v=>
          val valTokens =
            v.tokens.takeWhile(t => t.syntax == "val")
          valTokens.map(v1.Patch.replaceToken(_, "def")).asPatch.atomic
        }.asPatch
    }.asPatch

  }
}

object AbstractWithVals {
  def unapply(t: Tree): Option[List[Defn.Val]] = {
    val stats = t match {
      case Defn.Class(mods, _, _, _, templ)
        if mods.exists(_.is[Mod.Abstract]) =>
        templ.stats
      case Defn.Trait(_, _, _, _, templ) =>
        templ.stats
      case _ => List.empty
    }
    
    val vals = stats.flatMap {
      case v: Defn.Val =>
        Some(v)
      case _ => None
    }

    if (vals.isEmpty) None else Some(vals)
  }
}

