/* (c) https://github.com/MontiCore/monticore */
package de.monticore.lang.sysmlv2.cocos;

import de.monticore.lang.sysmlbasis._ast.ASTSysMLTransition;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTSysMLTransitionCoCo;
import de.monticore.lang.sysmlbasis._symboltable.ISysMLBasisScope;
import de.se_rwth.commons.logging.Log;

public class StateExistsCoCo implements SysMLBasisASTSysMLTransitionCoCo {
  @Override
  public void check (ASTSysMLTransition node) {
    ISysMLBasisScope scope = node.getEnclosingScope();
    boolean isSrcPresent = scope.resolveStateUsage(node.getSrc().getQName()).isPresent();
    boolean isTgtPresent = scope.resolveStateUsage(node.getSuccessionThen().getMCQualifiedName().getQName()).isPresent();

    if(!isSrcPresent) {
      Log.error("0x10029 Source state is not defined", node.get_SourcePositionStart(), node.get_SourcePositionEnd());
    }

    if(!isTgtPresent) {
      Log.error("0x10030 Target state is not defined", node.get_SourcePositionStart(), node.get_SourcePositionEnd());
    }
  }
}
