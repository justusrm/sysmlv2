package de.monticore.lang.sysmlbasis.symboltable.completers;

import de.monticore.lang.sysmlbasis.SysMLBasisMill;
import de.monticore.lang.sysmlbasis._ast.ASTEnumDef;
import de.monticore.lang.sysmlbasis._ast.ASTEnumUsage;
import de.monticore.lang.sysmlbasis._visitor.SysMLBasisVisitor2;
import de.monticore.types.check.SymTypeExpressionFactory;

/**
 * Enum-Literale sollen sich wie Fields verhalten. Allerdings haben die Dinger nicht immer einen Namen (auf
 * Lexer/Parser-Ebene). Deswegen kann man nicht einfach "EnumUsage extends Field = ..." in die Grammatik schreiben. Die
 * empfohlene Lösung des MC-Teams ist es die EnumUsageSymbols einfach duch FieldSymbols auszutauschen bzw. diese
 * hinzuzufügen.
 */
public class ConvertEnumUsagesToFields implements SysMLBasisVisitor2 {

  @Override
  public void visit(ASTEnumDef node) {
    // enum def is an object type
    var type = SymTypeExpressionFactory.createTypeObject(node.getName(), node.getEnclosingScope());

    for(var elem: node.getSysMLElementList()) {
      if(elem instanceof ASTEnumUsage) {
        var usage = (ASTEnumUsage) elem;
        // each named enum usage behaves like a static field whose type is the enclosing def
        if(usage.isPresentName()) {
          var field = SysMLBasisMill.fieldSymbolBuilder()
              .setAstNodeAbsent()
              .setName(usage.getName())
              .setIsStatic(true)
              .setType(type)
              .build();
          node.getSpannedScope().add(field);
        }
      }
    }

  }

}
