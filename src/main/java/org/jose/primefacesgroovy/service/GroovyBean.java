package org.jose.primefacesgroovy.service;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import org.jose.primefacesgroovy.util.CodeVisitor;

@ManagedBean(name = "groovyBean")
@ApplicationScoped
public class GroovyBean {

  private String markdown;
  private String groovyScript;
  private String result;
  private String html;

  public String getGroovyScript() {
    return(this.groovyScript);
  }

  public String getHtml() {
    return(this.html);
  }

  public void setMarkdown(String markdown) {
    this.markdown = markdown;
  }

  public String getMarkdown() {
    return(this.markdown);
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getResult() {
    return(this.result);
  }

  public void execute( ) {
    transformMarkdown();
    runWithGroovyScriptEngine();
  }

  public void transformMarkdown() {
    Parser parser = Parser.builder().build();
    Node document = parser.parse(this.markdown);

    CodeVisitor visitor = new CodeVisitor();
    document.accept(visitor);
    this.groovyScript = new String(visitor.code);

    HtmlRenderer renderer = HtmlRenderer.builder().build();
    this.html = renderer.render(document);
  }


  public void runWithGroovyScriptEngine() {

    try {
      Binding binding = new Binding();
      binding.setVariable("foo", new Integer(2));
      GroovyShell shell = new GroovyShell(binding);

      Object value = shell.evaluate(this.groovyScript);

      this.result = value.getClass().getName() + value.toString();
    }
    catch (Exception err) {
      this.result = err.toString();
    }

  }

}
