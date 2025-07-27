# IDEA

- **App Idea:** Job Tracker Desktop App
- **App Name:** HireLog
- **Brief:** Track where you've sent your CV, and monitor the status of each application.

## Core Features (CRUD)

Each "Enterprise" entry will have:

- **Name of Enterprise** (e.g., Google)
- **Position Applied For** (e.g., Backend Developer)
- **CV Sent?** (Yes/No)
- **Accepted?** (Yes/No/Waiting)
- **Attended Interview?** (Yes/No)
- Optional: Notes (for comments, contacts, etc.)

## Data Model

```java
public class Enterprise {
    private SimpleStringProperty name;
    private SimpleStringProperty position;
    private SimpleBooleanProperty cvSent;
    private SimpleStringProperty status; // "Waiting", "Accepted", "Rejected"
    private SimpleBooleanProperty attended;

    // Constructor, getters, setters...
}
```

## UI Layout (JavaFX)

- Login Page:
  - username
  - password
- Main Page:
  - Top: Input fields:
    - TextField: Name of Enterprise
    - TextField: Position
    - CheckBox: CV Sent
    - ComboBox: Status (Waiting, Accepted, Rejected)
    - CheckBox: Attended
  - Center: `TableView<Enterprise>`
  - Bottom: Buttons → Add, Update, Delete, Clear

## Data Storage

- `SQLITE`

## Sample Use Case

| Enterprise | Position        | CV Sent | Status   | Attended |
| ---------- | --------------- | ------- | -------- | -------- |
| Google     | Java Developer  | Yes     | Waiting  | No       |
| Amazon     | DevOps Engineer | Yes     | Accepted | Yes      |
