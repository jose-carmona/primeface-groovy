package org.jose.primefacesgroovy.service;

import java.io.Serializable;
import java.io.StringWriter;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import org.jose.primefacesgroovy.domain.ReglaUsada;

@ManagedBean(name = "reglasService")
@SessionScoped
public class ReglasService implements Serializable {

  List<ReglaUsada> list = null;

  public List<ReglaUsada> load() {

    try {
      InputStream input = new FileInputStream(new File(getClass().getClassLoader().getResource("/config/reglasUsadas.yaml").getFile()));

      // SneakYAML
      Constructor constructor = new Constructor(YamlConfig.class);
  		Yaml yaml = new Yaml( constructor );
      YamlConfig data = yaml.loadAs( input, YamlConfig.class );

      list = new ArrayList<ReglaUsada>(data.reglas.values());

    } catch (IOException e) {
      e.printStackTrace();
    }

    return list;
  }

  public ReglaUsada reglaById(String id) {
    ReglaUsada ru = list.stream().
                      filter(regla -> id.equals(regla.getId())).
                      findAny().
                      orElse(null);
    return ru;
  }

}
