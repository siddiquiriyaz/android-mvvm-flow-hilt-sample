# Android MVVM Flow Hilt Sample

A modern Android application demonstrating a scalable MVVM architecture using Kotlin Flow, StateFlow, Retrofit, and Hilt Dependency Injection.

The project showcases how to build a reactive Android UI with unidirectional data flow, proper separation of concerns, and lifecycle-aware state management.

---

## 🏗 Architecture

This project follows MVVM architecture principles with clear separation between UI, business logic, and data layers.

Layers

UI (Fragment)
Handles user interactions and observes UI state.

ViewModel
Manages UI state and communicates with the repository.

Repository
Handles data operations and API communication.

Network Layer (Retrofit)
Defines API endpoints and handles network requests.

Dependency Injection (Hilt)
Provides dependencies across the app.

Reactive State Management (StateFlow)
Emits and observes UI state updates.

Unidirectional data flow:
UI → ViewModel → Repository → API

The UI reacts to state updates emitted from the ViewModel using StateFlow.

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

## 📱 Features
This sample demonstrates two common API use cases.

## 1️⃣ GET Users
Fetches a list of users from a REST API and displays them in a RecyclerView.

Features included:
- API integration using Retrofit
-  State handling with StateFlow
- Efficient list updates using DiffUtil

## 2️⃣ POST Create User
Allows the user to create a new user through a simple form.

Features included:
- Input validation (name and email)
- Loader state during API call
- Success and error state handling

## 🔄 State Management

The UI observes a sealed UiState class to handle different screen states.

UiState
├── Idle
├── Loading
├── Success
└── Error

State Descriptions
- Idle – Default state before any action occurs
- Loading – Indicates an API request is in progress
- Success – Represents successful API response
- Error – Displays an error message when the request fails

This approach ensures predictable UI updates and clear state transitions.

## 🔁 Reactive Data Flow

User Action
↓
Fragment
↓
ViewModel
↓
Repository
↓
API Request
↓
Emit UiState
↓
Fragment observes StateFlow
↓
UI updates automatically

Using StateFlow ensures the UI always receives the latest state and remains consistent across lifecycle changes.
