package org.jose.primefacesgroovy.domain;

public class ReglaUsada {

  private String id;
  private String exaccion;
  private String modelo;
  private String reglaCalculo;

  public String getId() {
    return this.id;
  }

  public String getExaccion() {
    return this.exaccion;
  }

  public String getModelo() {
    return this.modelo;
  }

  public String getReglaCalculo() {
    return this.reglaCalculo;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setExaccion(String exaccion) {
    this.exaccion = exaccion;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public void setReglaCalculo(String reglaCalculo) {
    this.reglaCalculo = reglaCalculo;
  }

}
