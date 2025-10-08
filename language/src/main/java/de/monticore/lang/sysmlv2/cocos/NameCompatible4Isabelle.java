/* (c) https://github.com/MontiCore/monticore */
package de.monticore.lang.sysmlv2.cocos;

import de.monticore.lang.sysmlbasis._ast.ASTActionDef;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTActionDefCoCo;
import de.monticore.lang.sysmlbasis._ast.ASTConstraintDef;
import de.monticore.lang.sysmlbasis._ast.ASTRequirementDef;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTConstraintDefCoCo;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTRequirementDefCoCo;
import de.monticore.lang.sysmlbasis._ast.ASTSysMLPackage;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTSysMLPackageCoCo;
import de.monticore.lang.sysmlbasis._ast.ASTAttributeDef;
import de.monticore.lang.sysmlbasis._ast.ASTPartDef;
import de.monticore.lang.sysmlbasis._ast.ASTPortDef;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTAttributeDefCoCo;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTPartDefCoCo;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTPortDefCoCo;
import de.monticore.lang.sysmlbasis._ast.ASTStateDef;
import de.monticore.lang.sysmlbasis._cocos.SysMLBasisASTStateDefCoCo;
import de.se_rwth.commons.SourcePosition;
import de.se_rwth.commons.logging.Log;

public class NameCompatible4Isabelle implements SysMLBasisASTStateDefCoCo,
    SysMLBasisASTPartDefCoCo, SysMLBasisASTPortDefCoCo, SysMLBasisASTConstraintDefCoCo,
    SysMLBasisASTAttributeDefCoCo, SysMLBasisASTActionDefCoCo,
    SysMLBasisASTRequirementDefCoCo,
    SysMLBasisASTSysMLPackageCoCo {
  //check for name
  private void LogsCompatible4Isabelle(String name, SourcePosition start, SourcePosition end) {
    if(!name.matches("^(?!0-9)[a-zA-Z0-9_]+$") && !name.isEmpty()) {
      Log.error("0xFF005 This name is not Isabelle compatible", start, end);
    }
  }

  //state
  @Override
  public void check (ASTStateDef node) {
    String name = node.getName();
    LogsCompatible4Isabelle(name, node.get_SourcePositionStart(), node.get_SourcePositionEnd());
  }

  //part
  @Override
  public void check (ASTPartDef node) {
    String name = node.getName();
    LogsCompatible4Isabelle(name, node.get_SourcePositionStart(), node.get_SourcePositionEnd());
  }

  //port
  @Override
  public void check (ASTPortDef node) {
    String name = node.getName();
    LogsCompatible4Isabelle(name, node.get_SourcePositionStart(), node.get_SourcePositionEnd());
  }

  //constraint
  @Override
  public void check (ASTConstraintDef node) {
    String name = node.getName();
    LogsCompatible4Isabelle(name, node.get_SourcePositionStart(), node.get_SourcePositionEnd());
  }

  //attribute
  @Override
  public void check (ASTAttributeDef node) {
    String name = node.getName();
    LogsCompatible4Isabelle(name, node.get_SourcePositionStart(), node.get_SourcePositionEnd());
  }

  //action
  @Override
  public void check (ASTActionDef node) {
    String name = node.getName();
    LogsCompatible4Isabelle(name, node.get_SourcePositionStart(), node.get_SourcePositionEnd());
  }

  //requirement
  @Override
  public void check (ASTRequirementDef node) {
    String name = node.getName();
    LogsCompatible4Isabelle(name, node.get_SourcePositionStart(), node.get_SourcePositionEnd());
  }

  //package
  @Override
  public void check (ASTSysMLPackage node) {
    String name = node.getName();
    LogsCompatible4Isabelle(name, node.get_SourcePositionStart(), node.get_SourcePositionEnd());
  }
}
