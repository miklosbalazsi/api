Backend (Spring Boot)

Commands:

```bash
# generate gradle wrapper if needed
cd backend
gradle wrapper
# build
./gradlew bootJar
# run
java -jar build/libs/backend-0.0.1-SNAPSHOT.jar
```

A Dockerfile is provided for containerized runs.
