package org.jose.primefacesgroovy.service;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;


@ManagedBean(name = "groovyBean")
@ApplicationScoped
public class GroovyBean {

  private String text;
  private String result;

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return(this.text);
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getResult() {
    return(this.result);
  }

  public void execute( ) {
    runWithGroovyScriptEngine();
  }

  public void runWithGroovyScriptEngine() {

    try {
      Binding binding = new Binding();
      binding.setVariable("foo", new Integer(2));
      GroovyShell shell = new GroovyShell(binding);

      Object value = shell.evaluate(this.text);

      this.result = value.getClass().getName() + value.toString();
    }
    catch (Exception err) {
      this.result = err.toString();
    }

  }

}
