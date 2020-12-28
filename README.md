
# Popular Movies

A simple project which aims in getting popular movies list built on Kotlin - MVVM architecture

## Tech stack & Open-source libraries
- Minimum SDK level 16

- JetPack
  - LiveData - notify domain layer data to views.
  - Databinding - inflating and managing view layer
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
  
- Architecture
  - Koin - dependency injection
  - MVVM Architecture 
  - Repository pattern
  
- Netwotk
  - Retrofit2 & Gson - constructing the REST API
  - OkHttp3 - interceptor, logging and mocking web server
  - Glide - image loader
  
- Testing
  - Mockito -  Junit tests for repository and viewmodel
  - Roboelectric - Unit tests for Room




