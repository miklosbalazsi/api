# Monorepo: Spring Boot (Gradle Kotlin) + Vite React TypeScript

This repository contains two main folders:

- `backend/` - Java Spring Boot application using Gradle Kotlin DSL (Java 17)
- `frontend/` - Vite + React + TypeScript frontend

Quick start (recommended):

Backend (requires Gradle 7+ or the Gradle wrapper):

```bash
# generate the Gradle wrapper if you don't have it
cd backend
gradle wrapper
# then build
./gradlew bootJar
# run
java -jar build/libs/backend-0.0.1-SNAPSHOT.jar
```

Frontend:

```bash
cd frontend
npm install
npm run dev
# open http://localhost:3000
```

Notes:
- Frontend dev server is configured to proxy `/api` to the backend at `http://localhost:8080`.
- If you want a Docker build for the backend, see `backend/Dockerfile`.

Next steps:
- Generate the Gradle wrapper in `backend/` using `gradle wrapper` if you need a local `./gradlew`.
- Adjust versions and dependencies as needed.
