FROM java:8u111

COPY build/libs/*.jar /opt/ms-jwt.jar

EXPOSE 8081

ENTRYPOINT ["/usr/bin/java"]

CMD ["-jar", "/opt/ms-jwt.jar"]
