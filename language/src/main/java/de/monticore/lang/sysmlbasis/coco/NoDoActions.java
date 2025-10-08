/* (c) https://github.com/MontiCore/monticore */
package de.monticore.lang.sysmlbasis.coco;

import de.monticore.lang.sysmlbasis._ast.ASTDoAction;
import de.monticore.lang.sysmlbasis._ast.ASTStateDef;
import de.monticore.lang.sysmlbasis._ast.ASTStateUsage;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTDoActionCoCo;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTStateDefCoCo;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTStateUsageCoCo;
import de.se_rwth.commons.logging.Log;

/**
 * MontiBelle has not semantics for DoActions.
 */
public class NoDoActions implements SysMLBasisASTStateUsageCoCo, SysMLBasisASTStateDefCoCo {

  @Override
  public void check(ASTStateUsage node) {
    if(node.isPresentDoAction()) {
      Log.error("0xFF003 DoActions are not supported.", node.get_SourcePositionStart(), node.get_SourcePositionEnd());
    }
  }

  @Override
  public void check(ASTStateDef node) {
    if(node.isPresentDoAction()) {
      Log.error("0xFF004 DoActions are not supported.", node.get_SourcePositionStart(), node.get_SourcePositionEnd());
    }
  }
}
