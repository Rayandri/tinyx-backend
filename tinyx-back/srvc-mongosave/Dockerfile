FROM quay.io/quarkus/quarkus-micro-image:2.0

COPY target/*-runner.jar /app.jar

COPY scripts/dump.sh /usr/local/bin/dump.sh
RUN chmod +x /usr/local/bin/dump.sh

CMD ["java", "-jar", "/app.jar"]
