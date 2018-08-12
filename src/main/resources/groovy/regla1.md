# Ejemplo regla1

## Epígrafe cuarto

Ocupación de terrenos del dominio público municipal con mercancías, materiales de
construcción, escombros, vallas, puntales, asnillas, andamios, grúas, elevadores
y otras instalaciones análogas:

### Comprobaciones sobre datos del Objeto Tributario
```groovy
// La ordenanza no preve mas de un año de ocupación
assert objeto.datos["mercancias.dias"] < 365 : "La ordenanza no preve más de un año de ocupación"
assert objeto.datos["materiales.dias"] < 365 : "La ordenanza no preve más de un año de ocupación"
```

## Conceptos

1. Con mercancías, materiales, o productos de la industria y comercio, comprendidos
los vagones metálicos conocidos como contenedores, por m2 o fracción y día: ```t["mercancias"]```

```
def importes = [
  new Concepto(
    nombre: "mercancias",
    importe: objeto.datos["mercancias.m2"] * objeto.datos["mercancias.dias"] * tarifa.conceptos["mercancias"]
    )
]
```
