package org.jose.primefacesgroovy.service;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jose.primefacesgroovy.domain.ReglaUsada;

@ManagedBean(name = "reglasService")
@SessionScoped
public class ReglasService implements Serializable {

  public List<ReglaUsada> load() {
    List<ReglaUsada> list = new ArrayList<ReglaUsada>();

    list.add(new ReglaUsada("1","Exa1", "Modelo1", "Regla1"));
    list.add(new ReglaUsada("2","Exa1", "Modelo2", "Regla1"));
    list.add(new ReglaUsada("3","Exa1", "Modelo3", "Regla1"));
    list.add(new ReglaUsada("4","Exa2", "Modelo1", "Regla1"));
    list.add(new ReglaUsada("5","Exa3", "Modelo1", "Regla1"));
    list.add(new ReglaUsada("6","Exa4", "Modelo2", "Regla1"));

    return list;
  }

  private String getFile(String fileName) {
/*
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

    return result.toString(); */
    return null;
  }

}
