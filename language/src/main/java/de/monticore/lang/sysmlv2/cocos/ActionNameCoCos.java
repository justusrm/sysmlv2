/* (c) https://github.com/MontiCore/monticore */
package de.monticore.lang.sysmlv2.cocos;

import de.monticore.lang.sysmlbasis._ast.ASTActionDef;
import de.monticore.lang.sysmlbasis._ast.ASTActionUsage;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTActionDefCoCo;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTActionUsageCoCo;
import de.se_rwth.commons.logging.Log;

public class ActionNameCoCos implements SysMLBasisASTActionDefCoCo, SysMLBasisASTActionUsageCoCo {


  /**
   * Check that all super types (specializations) exist. They need to be Action definitions.
   */
  @Override
  public void check(ASTActionDef node) {

    if (node.getName().equals("done")) {
      Log.error("0xFF001 Action definition can not have the name \" done\".");
    }
  }

  /**
   * Check that all super types (specializations) exist. They might be Action definitions or usages.
   */
  @Override
  public void check(ASTActionUsage node) {
    if (node.getName().equals("done")) {
      Log.error("0xFF002 Action definition can not have the name \" done\".");
    }
  }
}
