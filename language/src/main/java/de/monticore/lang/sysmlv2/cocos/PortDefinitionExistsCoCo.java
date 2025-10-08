/* (c) https://github.com/MontiCore/monticore */
package de.monticore.lang.sysmlv2.cocos;

import de.monticore.lang.sysmlbasis._ast.ASTPartDef;
import de.monticore.lang.sysmlbasis._ast.ASTPortUsage;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTPartDefCoCo;
import de.se_rwth.commons.logging.Log;
import java.util.List;

/**
 * Eine sinnvoll modellierte Part sollte zur Außenweltkommunikation mindestens über ein
 * Port besitzen. Diese CoCo prüft, ob mind. ein Port innerhalb des Part-Scopes definiert wurde.
 */
public class PortDefinitionExistsCoCo implements SysMLBasisASTPartDefCoCo {
  @Override
  public void check(ASTPartDef node) {
    List<ASTPortUsage> portUsages = node.getSysMLElements(ASTPortUsage.class);
    if(portUsages.isEmpty()) {
      Log.warn("0xFF0002 Part " + node.getName() + " must use at least one port!",
          node.get_SourcePositionStart(), node.get_SourcePositionEnd());
    }
  }
}
