/* (c) https://github.com/MontiCore/monticore */
package de.monticore.lang.sysmlv2.cocos;

import de.monticore.lang.sysmlbasis._ast.ASTActionDef;
import de.monticore.lang.sysmlbasis._ast.ASTActionUsage;
import de.monticore.lang.sysmlbasis._ast.ASTDecideAction;
import de.monticore.lang.sysmlbasis._ast.ASTForkAction;
import de.monticore.lang.sysmlbasis._ast.ASTJoinAction;
import de.monticore.lang.sysmlbasis._ast.ASTLoopActionUsage;
import de.monticore.lang.sysmlbasis._ast.ASTMergeAction;
import de.monticore.lang.sysmlbasis._ast.ASTSysMLFirstSuccession;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTActionDefCoCo;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTActionUsageCoCo;
import de.monticore.lang.sysmlbasis._ast.ASTSysMLElement;
import de.se_rwth.commons.logging.Log;

public class ActionGeneratorCoCos implements SysMLBasisASTActionDefCoCo, SysMLBasisASTActionUsageCoCo {

  /**
   * Check that all super types (specializations) exist. They need to be Action definitions.
   */
  @Override
  public void check(ASTActionDef node) {

    //
  }

  /**
   * Check that all super types (specializations) exist. They might be Action definitions or usages.
   */
  @Override
  public void check(ASTActionUsage node) {
    int firstCount = 0;
    if(!(node instanceof ASTJoinAction || node instanceof ASTDecideAction || node instanceof ASTForkAction
        || node instanceof ASTMergeAction || node instanceof ASTLoopActionUsage)) {
      for (ASTSysMLElement x : node.getSysMLElementList()) {
        if(x instanceof ASTSysMLFirstSuccession) {
          firstCount++;
        }
      }
      if(firstCount != 1) {
        Log.error("0x10019 ActionUsage " + node.getName() + " has " + firstCount + " \"first\" usage, but needs exactly 1.");
      }
    }
  }
}
