# primefaces-groovy
Ejecución de un script groggy embebido en una aplicación jee (primefaces)

Es una simple prueba de concepto que demuestra la posibilidad de ejecutar script en groovy embebidos en una aplicación JEE típica.

### Construcción

* Maven

```
mvn package
```

* Imagen Docker

```
docker build .
```

### Ejecución

* Maven

```
mvn jetty:run
```

* Imagen Docker

```
docker run -d -p 80:8080 -p 443:8443 josecarmona/primefacesgroovy
```
