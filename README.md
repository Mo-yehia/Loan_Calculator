# Loan Repayment Schedule Calculator

## Overview
This project is a Java-based application built with Spring Boot that calculates a loan repayment schedule based on user input. The application supports various installment types and allows for custom grace periods, ensuring flexibility and accurate financial calculations.

## Features
- Input validation to ensure logical and correct values.
- Support for different installment types:
    - Annual
    - Semi-Annual
    - Quarterly
    - Monthly
- Dynamic calculation of repayment schedules, including:
    - Principal and interest breakdown.
    - Opening and closing balances.
- Grace period support with validation to match installment types.
- Dockerized deployment for easy setup and use.

## Requirements
- Java 23 or higher
- Maven 3.8 or higher
- Docker (optional for containerized deployment)

## Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd loan-repayment-schedule-calculator
```

### 2. Build the Project
Run the following command to build the application:
```bash
mvn clean install
```

### 3. Run the Application
To run the application, use:
```bash
java -jar target/loanCalculator-0.0.1-SNAPSHOT.jar
```

The application will start on [http://localhost:8080](http://localhost:8080).

### 4. Access the Frontend
Open the `loanForm.html` file in a browser to access the user interface for entering loan details.

## Docker Deployment

### 1. Build the Docker Image
Run the following command to build the Docker image:
```bash
docker build -t loan-calculator .
```

### 2. Run the Docker Container
Run the container using:
```bash
docker run -p 8080:8080 loan-calculator
```

## Input Constraints
1. **Loan Amount:** Must be greater than 0.
2. **Repayment Duration:**
    - Must be greater than 0 years.
3. **Grace Period:**
    - Can be `0` for all installment types.
    - Must not exceed or equal the repayment duration.
    - Must align with installment type:
        - Annual: Multiples of 12 months.
        - Semi-Annual: Multiples of 6 months.
        - Quarterly: Multiples of 4 months.
        - Monthly: Any positive value.
4. **Interest Rate:** Supports fractional values.

## Project Structure
- `LoanRequestDTO`: Data Transfer Object for capturing user inputs with validation annotations.
- `LoanCalculatorService`: Core logic for calculating repayment schedules.
- `LoanController`: REST API endpoints for handling loan calculation requests.
- `loanForm.html`: Frontend for user input.

## Example Usage
1. Enter loan details such as amount, interest rate, and repayment duration.
2. Select an installment type and specify a grace period (optional).
3. Click "Calculate Schedule" to generate the repayment schedule.

## Contributions
Contributions are welcome! Feel free to fork the repository and submit a pull request.

---

For any questions or issues, please contact [mohamed.yehia.work@gmail.com](mohamed.yehia.work@gmail.com)
