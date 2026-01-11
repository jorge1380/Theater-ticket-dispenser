# Theater Ticket Dispenser 🎭

A comprehensive software solution designed to automate the process of seat selection, ticket sales, and reservation management for a theater. This project demonstrates core principles of Object-Oriented Programming (OOP) and User Interface design.

## 📋 Table of Contents
* [Overview](#-overview)
* [Key Features](#-key-features)
* [System Architecture](#-system-architecture)
* [Technologies Used](#-technologies-used)
* [Installation](#-installation)
* [Usage](#-usage)
* [Author](#-author)

---

## 🎭 Overview
The **Theater Ticket Dispenser** provides a streamlined interface for both administrators and customers. It handles the complexity of real-time seat mapping, dynamic pricing based on seat location, and booking lifecycle management.



## ✨ Key Features
* **Interactive Seat Map:** Visual representation of the theater layout with real-time status (Available, Reserved, Occupied).
* **Dynamic Pricing:** Automatic calculation of ticket costs based on:
    * **Seat Zone:** VIP, Standard, or Restricted Visibility.
    * **User Category:** Adult, Student, or Senior discounts.
* **Reservation System:** Users can reserve seats temporarily or purchase them directly.
* **Admin Dashboard:** Tools to view occupancy rates and total revenue generated per show.
* **Data Persistence:** Capability to save and load theater states using Text Files.

## 🏗️ System Architecture
The project follows a modular structure (Model-View-Controller) to ensure clean code and maintainability:
* **Model:** Handles the data logic (Seats, Users, Prices).
* **View:** The Graphical User Interface (GUI) or Command Line Interface (CLI).
* **Controller:** Manages the communication between the UI and the internal logic.



## 🛠️ Technologies Used
* **Primary Language:** Java
* **Version Control:** Git & GitHub

## ⚙️ Installation

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/jorge1380/Theater-ticket-dispenser.git](https://github.com/jorge1380/Theater-ticket-dispenser.git)
   cd Theater-ticket-dispenser
   ```
## Usage
  To launch the application, run the main entry point:

   ```bash
  java -jar TheaterDispenser.jar
   ```
