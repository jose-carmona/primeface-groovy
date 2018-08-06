```groovy
// TODO: mover Objeto a java
class Objeto {
  Map datos
}

// TODO: mover a binding
def objeto = new Objeto( datos: [
                            "mercancias.m2": 20,
                            "mercancias.dias": 10,
                            "materiales.m2": 40,
                            "materiales.dias": 40,
                            "corteTrafico.horas": 5,
                            "concierto.identificacion": "",
                            "concierto.contenedores.nm": 0,
                            "concierto.pasteras.número": 0
                          ] )

// TODD: mover a java
class Tarifa {
  String descripcion
  Map conceptos
}

// TODO: mover a binding
def tarifa = new Tarifa( descripcion: "BOP 01-10-2017",
                         conceptos: [
                          "mercancias": 0.135,
                          "materiales": 0.135,
                          "<= 1 mes": 20.98,
                          "<= 3 meses": 72.99,
                          "<= 6 meses": 167.71,
                          "<= 12 meses": 351.76,
                          "corteTrafico": 12.28,
                          "concierto.contenedor": 325.90,
                          "concierto.pastera": 85.89
                         ] )

class Concepto {
  String nombre
  BigDecimal importe

  def Concepto plus(Concepto other) {
    new Concepto(importe: this.importe + other.importe)
  }
}
```

# Epígrafe cuarto

Ocupación de terrenos del dominio público municipal con mercancías, materiales de
construcción, escombros, vallas, puntales, asnillas, andamios, grúas, elevadores
y otras instalaciones análogas:

### Datos necesarios para el cálculo:

* Concepto.mercancias.m2
* Concepto.mercancias.dias
* Concepto.materiales.m2
* Concepto.materiales.días
* Concepto.corteTrafico.horas
* Concierto.identificacion
* Concierto.contenedores.número
* Concierto.pasteras.número

## Comprobaciones
```groovy
// La ordenanza no preve mas de un año de ocupación
assert objeto.datos["mercancias.dias"] < 365 : "La ordenanza no preve más de un año de ocupación"
assert objeto.datos["materiales.dias"] < 365 : "La ordenanza no preve más de un año de ocupación"
```

## Limpieza

```
// Si se posee concierto, no se aplican los conceptos 1, 2 y 3
```

## Conceptos

1. Con mercancías, **materiales**, o productos de la industria y comercio, comprendidos
los vagones metálicos conocidos como contenedores, por m2 o fracción y día:
0,135.

```groovy
def importes = [
  new Concepto(
    nombre: "mercancias",
    importe: objeto.datos["mercancias.m2"] * objeto.datos["mercancias.dias"] * tarifa.conceptos["mercancias"]
    )
]
```

2. Con materiales de construcción, vallas, puntales, asnillas, andamios, grúas,
elevadores, escombros, materiales de construcción, vagones para recogida o depósito
de los mismos y otros aprovechamientos análogos, por m2 o fracción al día:
0,135.

```groovy
importes.add(
  new Concepto(
    nombre: "materiales",
    importe: objeto.datos["materiales.m2"] * objeto.datos["materiales.dias"] * tarifa.conceptos["materiales"]
  )
)
```

3. La liquidación resultante de la aplicación de las tarifas establecidas en los
puntos 1 y 2 se verá incrementada con la que corresponda de las siguientes,
dependiendo del período hasta el que se mantenga la ocupación de vía pública con
mercancías o materiales de construcción:

 * Hasta un mes: 20,98.
 * Hasta tres meses: 72,99.
 * Hasta seis meses: 167,71.
 * Hasta doce meses: 351,76.

4. Por corte de calles al tráfico con motivo de ocupación de la vía pública por
alguno de los conceptos incluidos en este epígrafe, por hora o fracción:
12,28.

```groovy
​importes.add(
  new Concepto(
    nombre: "corteTrafico",
    importe: objeto.datos["corteTrafico.horas"] * tarifa.conceptos["corteTrafico"]
  )
)
```

- Superado cualquiera de estos períodos, la liquidación complementaria por prórroga
  se calculará de tal forma que la acumulación de prórrogas no dé como resultado
  una tarifa total inferior a la fijada para cualquiera de los períodos de tiempo
  preestablecidos.
- Se faculta a la Alcaldía Presidencia para que celebre conciertos con los titulares
de licencias de ocupación reguladas en este epígrafe bajo las siguientes condiciones:
a) El Pleno de la Corporación podrá señalar los límites dentro de los cuales
   hayan de celebrarse los conciertos.
b) Los titulares de la ocupación deberán ingresar el importe de la liquidación a
   la firma del concierto.
c) Los conciertos versarán sobre la superficie ocupada o sobre el tiempo medio de
   ocupación. En el supuesto de ocupación de la vía pública con contenedores para
   recogida de escombros, el concierto versará sobre el número de contenedores que
   de media se entiendan utilizados durante el año, a razón de ...

```groovy
importes.conciertoContenedores = concierto.contenedores.numero * Tarifa(conciertoContenedores)
```
   ... por cada contenedor, que se reduce a ...

```groovy
importes.conciertoContenedores = concierto.contenedores.numero * Tarifa(conciertoContenedores)
```
   ... en el caso de las pasteras, sin que le sea acumulable la tarifa de los
   apartados 1 y 2 de este epígrafe.

## Total Liquidación

```groovy
resultado.setPrincipal(importes.sum().importe)
```
