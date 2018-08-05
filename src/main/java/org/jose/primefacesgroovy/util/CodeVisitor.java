package org.jose.primefacesgroovy.util;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.Text;

public class CodeVisitor extends AbstractVisitor {
  public String code;

  public CodeVisitor() {
    this.code = new String();
  }

  @Override
  public void visit(FencedCodeBlock fencedCodeBlock) {

    if(fencedCodeBlock.getInfo().equals("groovy") || fencedCodeBlock.getInfo().equals("")) {
      this.code += fencedCodeBlock.getLiteral();
    }

    visitChildren(fencedCodeBlock);
  }
}
