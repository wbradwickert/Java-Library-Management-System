# Library Inventory Management System

**Language:** Java | **Concepts:** File I/O, CRUD, Input Validation

### Project Overview
A robust command-line application designed to manage a library's book inventory. Unlike simple in-memory programs, this application implements **data persistence** by reading and writing to a local CSV database (`BooksAndAuthors.csv`), ensuring data survives between sessions.

### Key Features
* **Persistent Storage:** Custom file parsing logic to load/save book data from CSV.
* **Input Validation:** "Bulletproof" menu system that handles `NumberFormatExceptions` and invalid user inputs without crashing.
* **CRUD Operations:** Full functionality to **C**reate, **R**ead, **U**pdate, and **D**elete inventory items.
* **Dynamic Arrays:** Manual memory management logic to resize inventory arrays on the fly.

### Technical Structure
* `BookInventory.java`: The core controller handling the main application loop and file I/O.
* `Book.java` & `Author.java`: Domain models encapsulating data with proper getters/setters.
* `BooksAndAuthors.csv`: The flat-file database used for persistence.

### How to Run
1.  Compile the source files: `javac edu/ilstu/*.java`
2.  Run the main class: `java edu.ilstu.BookInventory`
