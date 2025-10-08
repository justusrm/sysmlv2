/* (c) https://github.com/MontiCore/monticore */
package de.monticore.lang.sysmlbasis.coco;

import de.monticore.lang.sysmlbasis._ast.ASTExitAction;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTExitActionCoCo;
import de.se_rwth.commons.logging.Log;

/**
 * MontiBelle has not semantics for ExitActions.
 */
public class NoExitActions implements SysMLBasisASTExitActionCoCo {

  @Override
  public void check(ASTExitAction node) {
    Log.error("0xFF007 ExitActions are not supported.");
  }

}
