FROM jetty:alpine

COPY target/*.war /var/lib/jetty/webapps/root.war
