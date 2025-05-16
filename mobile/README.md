# Mobile QR Code Test App

This is a technical test application built in Kotlin using **Jetpack Compose** and **Hilt** for dependency injection. It demonstrates usage of modern Android development practices including:

- QR code generation and display.
- QR code scanning with CameraX and ML Kit.
- ViewModel state management with Kotlin Flows.
- Dependency injection via Hilt.
- API interaction using Retrofit and Moshi.
- Custom loading overlays and permission dialogs.
- Modular architecture and unit testing.

## Features

- 📸 **QR Scanner**: Scan a QR code using the device camera.
- 🔐 **Permission Handling**: Graceful UI prompts when camera permission is missing.
- 📤 **QR Generator**: Fetches a unique UID from a remote API and generates a QR code with expiration timer.
- 🧪 **Unit Tests**: Includes test coverage for ViewModel and repository layers using `MockK` and `kotlinx.coroutines.test`.

## Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **CameraX**
- **ML Kit**
- **Retrofit + Moshi**
- **Hilt**
- **ZXing** for QR generation
- **JUnit 4 + MockK**

## Structure

```
com.superformula.mobiletest
├── App.kt                       # Hilt entry point
├── MainActivity.kt              # Host activity
├── di/                          # Dependency injection modules
├── entities/                   # Data classes and mappers
├── network/                    # Retrofit API and repository
├── ui/
│   ├── components/             # Reusable UI elements (buttons, dialogs, overlays)
│   ├── navigation/             # Compose navigation routes
│   ├── screens/                # Home, QR Scanner, Seed (QR generator) screens
│   └── theme/                  # Android default theme files (Color, Type, Theme)
├── util/                       # Extensions, TimeProvider, QR utils, permission helpers
└── viewmodels/                 # SeedViewModel for managing state
```

## Setup

1. Clone the repo
2. Open in Android Studio Giraffe+ (required for Compose + KSP compatibility)
3. Run the app on an emulator or physical device (min SDK 26)

## API

The app uses a `/seed` endpoint to fetch a unique UID in the form of:

```json
{
  "seed": "abc-123",
  "expires_at": "2025-05-15T12:00:00Z"
}
```

The UID is displayed as a QR code and a countdown shows how many seconds remain until expiration.

## Tests

The project includes unit tests for:
- `SeedViewModel`
- `QRRepositoryImpl`

To run:
```
./gradlew testDebugUnitTest
```

## Notes

- The app gracefully handles expired QR codes and allows requesting a new UID.
- Camera permission is requested using the new AndroidX permissions API.
- The QR scanner uses `ML Kit Barcode Scanning`.
