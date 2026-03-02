# Android MVVM Flow Hilt Sample

A modern Android application demonstrating scalable MVVM architecture with Kotlin Flow, StateFlow, Retrofit, and Hilt Dependency Injection.

---

## 🏗 Architecture

This project follows Clean MVVM architecture principles:

- UI (Fragment)
- ViewModel
- Repository
- Network (Retrofit)
- Dependency Injection (Hilt)
- Reactive state management using StateFlow

Unidirectional data flow:
UI → ViewModel → Repository → API

---

## 🚀 Tech Stack

- Kotlin
- MVVM Architecture
- Kotlin Flow
- StateFlow
- Hilt
- Retrofit
- Coroutines
- RecyclerView + DiffUtil
- ViewBinding

---

## 🔄 State Management

The UI observes a sealed UiState class:

- Loading
- Success
- Error

This ensures clean and predictable state updates.
