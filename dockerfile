FROM openjdk
COPY target/stadium-0.0.1-SNAPSHOT.jar stadium-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","/stadium-0.0.1-SNAPSHOT.jar" ]