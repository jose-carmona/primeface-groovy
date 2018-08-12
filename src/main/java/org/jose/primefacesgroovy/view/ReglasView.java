package org.jose.primefacesgroovy.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.jose.primefacesgroovy.domain.ReglaUsada;
import org.jose.primefacesgroovy.service.ReglasService;

@ManagedBean(name="dtReglasView")
@ViewScoped
public class ReglasView implements Serializable {

    private List<ReglaUsada> reglas;
    private ReglaUsada sel;

    @ManagedProperty("#{reglasService}")
    private ReglasService service;

    @PostConstruct
    public void init() {
        reglas = service.load();
    }

    public ReglaUsada getSelected() {
        return this.sel;
    }

    public void setSelected(ReglaUsada selected) {
        this.sel = selected;
    }

    public List<ReglaUsada> getReglas() {
        return reglas;
    }

    public void setService(ReglasService service) {
        this.service = service;
    }

    public String edit() {
      return "rc??faces-redirect=true&query=" + this.sel.getReglaCalculo();
    }

}
