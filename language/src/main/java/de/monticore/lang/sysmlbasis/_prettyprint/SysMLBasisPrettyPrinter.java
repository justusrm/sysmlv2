package de.monticore.lang.sysmlbasis._prettyprint;

import de.monticore.lang.sysmlbasis._ast.ASTConstraintReference;
import de.monticore.lang.sysmlbasis._ast.ASTConstraintUsage;
import de.monticore.lang.sysmlbasis._ast.ASTDoAction;
import de.monticore.lang.sysmlbasis._ast.ASTEntryAction;
import de.monticore.lang.sysmlbasis._ast.ASTSpecialization;
import de.monticore.lang.sysmlbasis._ast.ASTSuccessionThen;
import de.monticore.lang.sysmlbasis._ast.ASTSysMLElement;
import de.monticore.lang.sysmlbasis._ast.ASTSysMLParameter;
import de.monticore.lang.sysmlbasis._ast.ASTSysMLQualifiedName;
import de.monticore.prettyprint.CommentPrettyPrinter;
import de.monticore.prettyprint.IndentPrinter;

import java.util.Iterator;

public class SysMLBasisPrettyPrinter extends SysMLBasisPrettyPrinterTOP {

  public SysMLBasisPrettyPrinter(IndentPrinter printer, boolean printComments) {
    super(printer, printComments);
  }

  // Fix pretty-print of SysMLQualifiedName with the intent of reproducing impl. of MCQualifiedName
  @Override
  public  void handle (ASTSysMLQualifiedName node) {
    if (this.isPrintComments()) {
      CommentPrettyPrinter.printPreComments(node, getPrinter());
    }
    Iterator<String> iter_name = node.getNameList().iterator();
    if (iter_name.hasNext()) {
      getPrinter().print(iter_name.next());
      while (iter_name.hasNext()) {
        getPrinter().stripTrailing();
        getPrinter().print(".");
        getPrinter().print(iter_name.next());
      }
    }
    if (this.isPrintComments()) {
      CommentPrettyPrinter.printPostComments(node, getPrinter());
    }
  }

  // SysMlActionsPrettyPrinter
  public void handle (ASTSuccessionThen node) {

    if (this.isPrintComments()) {
      CommentPrettyPrinter.printPreComments(node, getPrinter());
    }

    if (node.isPresentMCQualifiedName()) {
      node.getMCQualifiedName().accept(getTraverser());

      if (node.isPresentSysMLCardinality()) {
        node.getSysMLCardinality().accept(getTraverser());
      }

      for (ASTSpecialization spec : node.getSpecializationList()) {
        spec.accept(getTraverser());
      }

      getPrinter().print(" { ");
      for (ASTSysMLElement e : node.getSysMLElementList()) {
        e.accept(getTraverser());
      }
      getPrinter().print(" } ");

    }
    else if (node.sizeSysMLElements() == 1) {
      node.getSysMLElement(0).accept(getTraverser());
    }

    if (this.isPrintComments()) {
      CommentPrettyPrinter.printPostComments(node, getPrinter());
    }
  }


  public void handle(ASTConstraintUsage node) {

    if (this.isPrintComments()) {
      CommentPrettyPrinter.printPreComments(node, getPrinter());
    }

    node.getModifier().accept(getTraverser());

    if (node.isRequire()) {
      getPrinter().print("require ");
    } else if (node.isAssume()) {
      getPrinter().print("assume ");
    } else if (node.isAssert()) {
      getPrinter().print("assert ");
    }

    if (node.isNot()) { // opt: 0 req: 0
      getPrinter().print("not ");
    }

    node.getUserDefinedKeywordList().forEach(n -> n.accept(getTraverser()));

    getPrinter().print("constraint ");

    if (node.isPresentName()) {
      getPrinter().print(node.getName() + " ");
    }

    if (node.isPresentSysMLCardinality()) {
      node.getSysMLCardinality().accept(getTraverser());
    }

    node.getSpecializationList().forEach(n -> n.accept(getTraverser()));

    getPrinter().stripTrailing();
    getPrinter().print("(");

    Iterator<ASTSysMLParameter> iter_sysMLParameter = node.getSysMLParameterList().iterator();
    if (iter_sysMLParameter.hasNext()) {
      iter_sysMLParameter.next().accept(getTraverser());
      while (iter_sysMLParameter.hasNext()) {
        getPrinter().stripTrailing();
        getPrinter().print(",");
        iter_sysMLParameter.next().accept(getTraverser());
      }
    }

    getPrinter().stripTrailing();
    getPrinter().print(")");

    getPrinter().println("{ ");
    getPrinter().indent();

    node.getSysMLElementList().forEach(n -> n.accept(getTraverser()));

    if (node.isPresentExpression()) {
      node.getExpression().accept(getTraverser());
    }

    getPrinter().unindent();
    getPrinter().println();
    getPrinter().println("} ");

    if (this.isPrintComments()) {
      CommentPrettyPrinter.printPostComments(node, getPrinter());
    }
  }

  public void handle(ASTConstraintReference node) {

    if (this.isPrintComments()) {
      CommentPrettyPrinter.printPreComments(node, getPrinter());
    }

    if (node.isRequire()) {
      getPrinter().print("require ");
    } else if (node.isAssume()) {
      getPrinter().print("assume ");
    } else if (node.isAssert()) {
      getPrinter().print("assert ");
    }

    node.getUserDefinedKeywordList().forEach(n -> n.accept(getTraverser()));

    getPrinter().print("constraint ");

    if (node.isPresentMCQualifiedName()) {
      node.getMCQualifiedName().accept(getTraverser());
    }

    if (node.isPresentSysMLCardinality()) {
      node.getSysMLCardinality().accept(getTraverser());
    }

    node.getSpecializationList().forEach(n -> n.accept(getTraverser()));

    getPrinter().stripTrailing();
    getPrinter().print("(");

    Iterator<ASTSysMLParameter> iter_sysMLParameter = node.getSysMLParameterList().iterator();
    if (iter_sysMLParameter.hasNext()) {
      iter_sysMLParameter.next().accept(getTraverser());
      while (iter_sysMLParameter.hasNext()) {
        getPrinter().stripTrailing();
        getPrinter().print(",");
        iter_sysMLParameter.next().accept(getTraverser());
      }
    }

    getPrinter().stripTrailing();
    getPrinter().print(")");

    getPrinter().println("{ ");
    getPrinter().indent();

    node.getSysMLElementList().forEach(n -> n.accept(getTraverser()));

    if (node.isPresentExpression()) {
      node.getExpression().accept(getTraverser());
    }

    getPrinter().unindent();
    getPrinter().println();
    getPrinter().println("} ");

    if (this.isPrintComments()) {
      CommentPrettyPrinter.printPostComments(node, getPrinter());
    }

  }

  /**
   * Bugfix for generated pretty printer.
   *
   * @param node
   */
  @Override
  public void handle(ASTEntryAction node) {

    if (this.isPrintComments()) {
      CommentPrettyPrinter.printPreComments(node, getPrinter());
    }

    getPrinter().print("entry ");
    if (node.isPresentMCQualifiedName()) {
      node.getMCQualifiedName().accept(getTraverser());

      getPrinter().println("{ ");

      getPrinter().indent();
      node.getSysMLElementList().forEach(n -> n.accept(getTraverser()));
      getPrinter().unindent();

      getPrinter().println();
      getPrinter().println("} ");
    }
    else if (node.isPresentActionUsage()) {
      node.getActionUsage().accept(getTraverser());
    }
    else {
      getPrinter().stripTrailing();
      getPrinter().println(";");
    }

    if (this.isPrintComments()) {
      CommentPrettyPrinter.printPostComments(node, getPrinter());
    }
  }

  public void handle(ASTDoAction node) {

    if (this.isPrintComments()) {
      CommentPrettyPrinter.printPreComments(node, getPrinter());
    }

    getPrinter().print("do ");

    if (node.isPresentActionUsage()) {
      node.getActionUsage().accept(getTraverser());
    } else if (node.isPresentMCQualifiedName()) {
      node.getMCQualifiedName().accept(getTraverser());
      getPrinter().println(" { ");
      node.getSysMLElementList().forEach(n -> n.accept(getTraverser()));
      getPrinter().println(" } ");
    }

    if (this.isPrintComments()) {
      CommentPrettyPrinter.printPostComments(node, getPrinter());
    }
  }

}
