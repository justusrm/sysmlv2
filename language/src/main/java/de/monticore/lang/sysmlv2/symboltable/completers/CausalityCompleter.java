package de.monticore.lang.sysmlv2.symboltable.completers;

import de.monticore.lang.sysmlbasis._ast.ASTPartDef;
import de.monticore.lang.sysmlbasis._ast.ASTPortUsage;
import de.monticore.lang.sysmlbasis._ast.ASTSysMLCausality;
import de.monticore.lang.sysmlbasis._symboltable.PartDefSymbol;
import de.monticore.lang.sysmlbasis._visitor.SysMLBasisVisitor2;

/**
 * Berechnet und vervollständigt die Kausalität der PortUsages
 * anhand der besitzenden (umliegenden) PartDefinition.
 */
public class CausalityCompleter implements SysMLBasisVisitor2 {
  @Override
  public void visit(ASTPortUsage node) {
    if (node.getEnclosingScope().isPresentSpanningSymbol()) {
      var enclosingSymbol = node.getEnclosingScope().getSpanningSymbol();
      if(enclosingSymbol instanceof PartDefSymbol && enclosingSymbol.isPresentAstNode()) {
        var ast = (ASTPartDef) enclosingSymbol.getAstNode();
        node.getSymbol().setStrong(
            ast.getSysMLElements(ASTSysMLCausality.class).stream().noneMatch(ASTSysMLCausality::isInstant)
        );
      }
    }
  }
}
