package org.jose.primefacesgroovy.service;

import java.util.Map;

import org.jose.primefacesgroovy.domain.ReglaUsada;

public class YamlConfig {

	Map<String, ReglaUsada> reglas;

	public Map<String, ReglaUsada> getReglasUsadas() {
		return reglas;
	}

	public void setReglasUsadas(Map<String, ReglaUsada> reglas) {
		this.reglas = reglas;
	}

	@Override public String toString() {
		return "YamlConfig{" +
				"reglasUsadas=" + reglas +
				'}';
	}
}
