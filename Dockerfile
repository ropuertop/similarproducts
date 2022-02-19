FROM amazoncorretto:11-alpine-jdk
COPY target/similarproductsservice-0.0.1-SNAPSHOT.jar /usr/local/lib/similarproducts.jar
ENV JAVA_OPTS="--add-modules java.se --add-exports java.base/jdk.internal.ref=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.nio=ALL-UNNAMED --add-opens java.base/sun.nio.ch=ALL-UNNAMED --add-opens java.management/sun.management=ALL-UNNAMED --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED"
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar /usr/local/lib/similarproducts.jar