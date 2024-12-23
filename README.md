# ATM Simulator Project

This project is a simulation of an Automated Teller Machine (ATM) system, developed using **Java**, **Swing**, and **JavaCard**. The simulator provides a graphical interface to handle both client-side and server-side operations, delivering a functional representation of an ATM environment.

## Features
- **Client-Side Operations**: 
  - Simulates ATM user interactions.
  - Allows operations such as balance inquiries, withdrawals, deposits, and PIN management.
  
- **Server-Side Operations**: 
  - Handles backend processing for user accounts.
  - Ensures secure communication and data validation.

- **Graphical User Interface (GUI)**: 
  - Developed with **Java Swing** for an intuitive and interactive user experience.

- **JavaCard Integration**: 
  - Utilizes JavaCard technology to emulate secure card transactions.

## Technologies Used
- **Java**: Core programming language for implementing application logic.
- **Swing**: For building the user interface.
- **JavaCard**: For simulating smart card functionality.

## How to Run
1. Open the project in your preferred IDE.
2. Ensure the JavaCard JAR files (`apduio.jar` and `api.jar`) are added to the project dependencies. (you might need to change the version of the java compiler to java 1.8 or lower)
3. Use the `jcwde` command (available in the JavaCard Development Kit tools) to start the server-side component. For example:
   ```bash
   jcwde bank.app.txt
4. Go back to the project in your IDE and run the Main class to start the client-side application.
