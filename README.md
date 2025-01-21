## NotesKMP is a Kotlin Multiplatform project targeting Android, iOS.

<image src="https://github.com/user-attachments/assets/ed6fd623-73c9-467c-ae1c-570ed96d1a10" width="20%" />


<image src="https://github.com/user-attachments/assets/90db4edb-d264-4f8b-8aa8-77488b3807ab" width="20%" />


<image src="https://github.com/user-attachments/assets/976e0bbc-9fa6-445e-aa0f-c2c317531822" width="20%" />


<image src="https://github.com/user-attachments/assets/06485173-4d4a-4643-b2a0-2611dbd0c763" width="20%" />


---
<details>
<summary>How to build and run KMP project</summary>
To build and run this project you need next tools:
1. Android Studio
2. Xcode is required to run iOS applications on a simulated or real device.
3. JDK
4. Kotlin Multiplatform plugin
5. Kotlin plugin

In case of some issue with setting up the environment, please [consult here](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-setup.html#install-the-necessary-tools)

[How to run the project on an Android device](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html#run-your-application-on-android):
* Open project in Android Studio
* Choose Android Virtual Device or connect your real device
* Press run button

[How to run the project on iOS device:](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html#run-your-application-on-ios)
* Launch Xcode in a separate window to complete the initial setup
* In Android Studio, select iosApp in the list of run configurations and click Run. By default, the run configuration will start a simulated device available in Xcode and run the app there. If you don't have an available iOS configuration in the list, add a new run configuration.

</details>

---

### It is a simple app for storing and managing notes which contains the following features:
1. List of all notes. User can search for a note by title, and also filter notes by category. From this screen, notes can be created, edited, and deleted.
2. Screen for adding new category.
3. Screen for adding/editing notes.
4. Screen with note details.

### Architecture and design decisions:
* The application is built upon the foundation of "Clean Architecture". All logic is separated into 3 layers UI, Domain, and Data. The domain layer communicates with the Data layer using repository interfaces.
* The application employs the `MVVM` (Model-View-ViewModel) and `MVI` (Model-View-Intent) patterns to effectively decouple the model, view, and business logic layers. This separation enhances code clarity, flexibility, and testability.
* To establish a consistent and user-friendly interface, the application utilizes a `Design System`. It contains a custom Theme with custom Colors, Typography, etc.

### Libraries used
* Compose Multiplatform
* Navigation Compose
* Android ViewModel
* Koin (DI)
* Room
* Kermit (Logger)
* Kotlin Collections Immutable
* Material3
