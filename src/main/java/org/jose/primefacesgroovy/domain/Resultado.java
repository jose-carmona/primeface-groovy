package org.jose.primefacesgroovy.domain;

import java.math.BigDecimal;
import java.util.HashMap;


public class Resultado {
  private BigDecimal principal;
  public HashMap<String, BigDecimal> conceptos;

  public void setPrincipal( BigDecimal importe) {
    this.principal = importe;
  }

  public void addConcepto( String concepto, BigDecimal importe) {
    if( conceptos == null ) {
      conceptos = new HashMap<String, BigDecimal>();
    }
    this.conceptos.put(concepto, importe);
  }

  @Override
  public String toString() {
    StringBuilder rt;
    rt = new StringBuilder("Liquidación. Principal = ");
    rt.append(this.principal);
    rt.append(". Conceptos: ");
    for (HashMap.Entry<String, BigDecimal> entry : conceptos.entrySet())
    {
      rt.append("[");
      rt.append(entry.getKey());
      rt.append(":");
      rt.append(entry.getValue());
      rt.append("]");
    }
    return rt.toString();
  }
}
