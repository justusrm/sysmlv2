package de.monticore.lang.sysmlv2.symboltable.completers;

import de.monticore.lang.sysmlbasis._symboltable.ISysMLBasisScope;
import de.monticore.lang.sysmlbasis._ast.ASTPartDef;
import de.monticore.lang.sysmlbasis._ast.ASTSysMLReqType;
import de.monticore.lang.sysmlbasis._symboltable.ISysMLBasisScope;
import de.monticore.lang.sysmlbasis._symboltable.PartDefSymbol;
import de.monticore.lang.sysmlbasis._symboltable.SysMLPartsScope;
import de.monticore.lang.sysmlbasis._visitor.SysMLBasisVisitor2;
import de.monticore.lang.sysmlbasis._symboltable.ISysMLBasisScope;

public class RequirementClassificationCompleter implements SysMLBasisVisitor2 {

  @Override
  public void endVisit(ASTPartDef node) {
    var type = getReqType(node.getSymbol());
    node.getSymbol().setRequirementType(type);
  }

  private ASTSysMLReqType mergeClassification(ASTSysMLReqType a, ASTSysMLReqType b) {
    if (a == null && b != null) {
      return b;
    } else if (a != null && b == null) {
      return a;
    } else if (a == b) {
      return a;
    } else if (a == ASTSysMLReqType.MIXED || b == ASTSysMLReqType.MIXED) {
      return ASTSysMLReqType.MIXED;
    } else if (a == ASTSysMLReqType.UNKNOWN || b == ASTSysMLReqType.UNKNOWN) {
      return ASTSysMLReqType.UNKNOWN;
    } else {
      return ASTSysMLReqType.MIXED;
    }
  }

  private ASTSysMLReqType getReqType(PartDefSymbol symbol) {
    var res = getReqType(symbol.getSpannedScope());
    if (res == ASTSysMLReqType.MIXED) {
      return res;
    }

    var usageTypes = symbol.getSpannedScope().getPartUsageSymbols().values().stream().map((usage) ->
        usage.getPartDef().orElseGet(() -> {
          var tmp = new PartDefSymbol("");
          tmp.setRequirementType(ASTSysMLReqType.UNKNOWN);
          return tmp;
        }).getRequirementType());

    res = usageTypes.reduce(res, this::mergeClassification);

    return res != null ? res : ASTSysMLReqType.UNKNOWN;
  }

  private ASTSysMLReqType getReqType(ISysMLBasisScope scope) {
    var llr = false;
    var hlr = false;

    llr = !scope.getStateUsageSymbols().isEmpty();
    hlr = !scope.getRequirementUsageSymbols().isEmpty();
    hlr = hlr || !scope.getConstraintUsageSymbols().isEmpty();

    if (llr && hlr){
      return ASTSysMLReqType.MIXED;
    } else if (llr){
      return ASTSysMLReqType.LLR;
    } else if (hlr) {
      return ASTSysMLReqType.HLR;
    } else {
      // Indicate we could not decide just using state and requirement usages.
      return null;
    }
  }
}
