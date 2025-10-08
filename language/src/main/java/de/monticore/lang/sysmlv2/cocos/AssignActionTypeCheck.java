package de.monticore.lang.sysmlv2.cocos;

import de.monticore.lang.sysmlbasis._ast.ASTAssignmentActionUsage;
import de.monticore.lang.sysmlbasis._ast.ASTSendActionUsage;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTAssignmentActionUsageCoCo;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTSendActionUsageCoCo;
import de.monticore.lang.sysmlv2.types.SysMLDeriver;
import de.monticore.types.check.TypeCheck;

public class AssignActionTypeCheck implements SysMLBasisASTAssignmentActionUsageCoCo {

  @Override
  public void check(ASTAssignmentActionUsage node) {
    // Wir gehen davon aus, dass Send-Actions die Kanäle auf Ports nicht als Strom,
    // sondern Element-Weise (i.e. Event-basiert) verarbeiten
    var deriver = new SysMLDeriver(false);
    deriver.init();
    var tc = new TypeCheck();

    var type = deriver.deriveType(node.getValueExpression());
    if(!type.isPresentResult() || type.getResult().isObscureType()) {
      // Error should already be logged?
    }
    // Vergleich zum Target steht noch aus.
    // TODO Target zur Expression erheben (Grammatik ändenr), Checken, dann vergleichen
  }

}
