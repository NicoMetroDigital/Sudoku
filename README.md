# 🧩 Sudoku Project

Ein Sudoku-Generator und -Löser mit **Spring Boot (Backend)** und **React (Frontend)**.

## 📌 Features

- **Generierung von Sudoku-Rätseln** (verschiedene Schwierigkeitsstufen)
- **Lösung von Sudoku-Rätseln** durch das Backend
- **Validierung der Benutzereingabe**
- **REST API mit Spring Boot**
- **Frontend mit React**

---

## 🚀 Installation & Setup

### 🔹 1. Klone das Repository
git clone https://github.com/dein-username/sudoku-project.git
cd sudoku-project
 2. Backend (Spring Boot)
📌 Voraussetzungen:
Java 17+
Maven
Backend starten:
Copy
Edit
cd backend
./mvnw spring-boot:run
Das Backend läuft jetzt unter: http://localhost:8080

🔹 3. Frontend (React)
📌 Voraussetzungen:
Node.js (mindestens v16)
Frontend starten:
Copy
Edit
cd frontend
npm install
npm start
Das Frontend läuft jetzt unter: http://localhost:3000

🛠 API-Endpunkte (Backend)
Methode	Endpunkt	Beschreibung
GET	/sudoku/new?difficulty=easy	Erstellt ein neues Sudoku (leicht)
POST	/sudoku/solve	Löst ein übermitteltes Sudoku
POST	/sudoku/validate	Prüft, ob das Sudoku korrekt ist




