**User Directory App**

This sample showcases a modern architectural approach to developing Android applications using a User Directory use case.

**Features**

• User List: A reactive directory of users with their basic information and avatars.
• User Detail: A detailed profile view for each user including contact and company information.
• Error Handling: Custom error states with integrated retry functionality for a resilient user experience.
• Type-Safe Navigation: Seamless navigation between screens using Jetpack Compose Navigation and a centralized Destination system.

**Tech Stack & Architecture**

This project follows Clean Architecture principles a and is structured into domain, ui and data layer.
• User Interface: Built entirely with Jetpack Compose and Material 3.
• Navigation: Single-activity architecture using Navigation Compose.
• Presentation Layer: Utilizes ViewModels per screen, exposing state via StateFlow for reactive UI updates.
• Asynchronous Operations: Powered by Kotlin Coroutines and Flow.
• Dependency Injection: Managed by Hilt.
• Networking: Retrofit for remote data fetching.
• Image Loading: Coil for asynchronous image processing.
• Unit Tests: Coverage for ViewModels, Use Cases, Repositories, and Data Mappers using JUnit 4 and MockK.
• Component Tests: UI testing for reusable Compose components using the Compose Test Rule.

**Project Structure**

• data/: Remote API definitions, data models, and repository implementations.
• domain/: Business logic including Use Cases, UI Models, and Mappers.
• ui/feature: Screen-level composables and their respective ViewModels
◦ ui/component/: Reusable UI elements (Avatar, InfoRows, Error states).
◦ ui/theme/: App-wide styling, colors, and typography.
• navigation/: Centralized navigation graph and type-safe route definitions.

**Expected Output**

<img width="663" height="814" alt="Screenshot 2026-07-05 at 8 13 19 PM" src="https://github.com/user-attachments/assets/47f6536a-7e27-4db9-8a93-d53d56747bc2" />
<img width="663" height="814" alt="Screenshot 2026-07-05 at 8 13 05 PM" src="https://github.com/user-attachments/assets/9b51f858-52d5-4eb2-b964-e6c36143fa25" />


