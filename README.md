This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…


For this proyect use next methology and tools:
- MVVM
- Clean Architecture
- Koin
- Ktor
- SOLID
- Singleton
- Flows
- Kotlin compartido con iOS
- Ui Jetpack Compose compartida con iOS
- Patrones de Diseño
- Backend con Kotlin y Ktor

### Video / Image App on Android / iOS

<img width="702" alt="Captura de pantalla 2024-08-02 a la(s) 01 12 05" src="https://github.com/user-attachments/assets/a2873efa-8a89-47eb-8ffb-a2281012a2a5">
