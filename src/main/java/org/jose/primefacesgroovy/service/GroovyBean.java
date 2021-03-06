package org.jose.primefacesgroovy.service;

import java.io.Serializable;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import org.jose.primefacesgroovy.util.CodeVisitor;
import org.jose.primefacesgroovy.domain.Resultado;

@ManagedBean(name = "groovyBean")
@RequestScoped
public class GroovyBean implements Serializable {

  private String query;
  private String markdown;
  private String groovyScript;
  private String result;
  private String html;

  private Resultado resultado;

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

  public String getQuery() {
    return(this.query);
  }

  public void setQuery(String query) {
    this.query = query;
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

  public void load( ) {
    this.markdown = getFile(this.query);
    transformMarkdown();
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
      this.resultado = new Resultado();
      
      Binding binding = new Binding();
      binding.setVariable("resultado", this.resultado);
      GroovyShell shell = new GroovyShell(binding);

      Object value = shell.evaluate(this.groovyScript);

      this.result = resultado.toString();
    }
    catch (Exception err) {
      this.result = err.toString();
    }

  }

  private String getFile(String fileName) {

    StringBuilder result = new StringBuilder("");

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("/groovy/" + fileName + ".md").getFile());

    try (Scanner scanner = new Scanner(file)) {

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        result.append(line).append("\n");
      }

      scanner.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    return result.toString();

  }

}
