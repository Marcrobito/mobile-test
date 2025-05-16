# Mobile Test - Ktor Backend

This is the Ktor backend server for the Mobile Test application. It exposes a simple API for retrieving a seed (UID) and expiration timestamp, which the mobile client uses to generate and display a QR code.

## 🧩 Tech Stack

- **Ktor** 2.3.7
- **Kotlin** 2.1.20
- **Gradle Kotlin DSL**
- **Serialization** with Kotlinx
- **Docker** for containerization

## 🚀 Features

- `GET /seed`: Returns a JSON object containing a seed string and an expiration time.
- Centralized error handling with status pages.
- Modular routing structure for scalability.
- Configurable via `application.conf`.

## 📂 Project Structure

```text
src/
├── main/
│   ├── kotlin/
│   │   ├── Application.kt          # Application entry point
│   │   ├── Routing.kt              # Main routing module
│   │   ├── responses/ErrorResponse.kt
│   │   └── seed/
│   │       ├── Seed.kt            # Data model
│   │       └── SeedRoutes.kt      # Route: GET /seed
│   └── resources/
│       ├── application.conf       # Server configuration
│       └── reference.conf
```

## 🔧 Running Locally

### Prerequisites

- JDK 17
- Gradle (wrapper included)
- Docker (optional)

### With Gradle

```bash
./gradlew installDist
./build/install/mobile-test/bin/mobile-test
```

### With Docker

```bash
docker build -t mobile-test-backend .
docker run -p 8080:8080 mobile-test-backend
```

## 🛠️ API

### GET `/seed`

Returns a randomly generated seed with an expiration timestamp 30 seconds in the future.

#### Response

```json
{
  "seed": "h3s1-l9a3-x0t7",
  "expires_at": "2025-05-15T18:32:10Z"
}
```

## 📄 Configuration

Edit `src/main/resources/application.conf` to change the port or other settings:

```hocon
ktor {
  deployment {
    port = 8080
  }
  application {
    modules = [ com.superformula.ApplicationKt.module ]
  }
}
```

## 🧪 Tests

You can run unit tests using:

```bash
./gradlew test
```

## 📝 License

MIT or specify otherwise.
