# Citronix

## Project Overview
The **Citronix** project aims to develop a comprehensive farm management application specifically for lemon farms. This application will assist farmers in tracking their production, harvests, and sales of their produce. The goal is to simplify the management of farms, fields, trees, harvests, and sales, while optimizing productivity tracking based on the age of trees.

## Key Features

### Farm Management
- Create, modify, and view details of a farm, including its name, location, area, and creation date.
- Multi-criteria search feature for farms using **Criteria Builder**.

### Field Management
- Assign fields to a farm, each with a specified area.
- Ensure consistency of field areas: the total area of fields must be strictly less than the total area of the farm.

### Tree Management
- Track trees by planting date, age, and associated field.
- Automatically calculate the age of trees.
- Determine the annual productivity based on tree age:
  - **Young tree (< 3 years)**: 2.5 kg per season.
  - **Mature tree (3-10 years)**: 12 kg per season.
  - **Old tree (> 10 years)**: 20 kg per season.

### Harvest Management
- Track harvests by season (**winter, spring, summer, fall**).
- Allow one harvest per season (every three months).
- Record harvest date and total quantity harvested.

### Harvest Details
- Track quantity harvested from each tree for a given harvest.
- Link each harvest detail to a specific tree.

### Sales Management
- Record sales with details such as date, unit price, client, and associated harvest.
- Calculate revenue using the formula: **Revenue = quantity * unit price**.

> **Note:** Management features include full CRUD operations, adhering to business constraints.

## Business Constraints
- **Minimum field area**: A field must be at least **0.1 hectares (1,000 m²)**.
- **Maximum field area**: No field can exceed **50%** of the farm's total area.
- **Max number of fields per farm**: A farm can have up to **10 fields**.
- **Tree spacing**: Each field should contain trees such that the maximum density is **100 trees per hectare** (10 trees per 1,000 m²).
- **Tree lifespan**: Trees are considered non-productive beyond **20 years**.
- **Planting period**: Trees can only be planted between **March and May**, to suit the ideal climate.
- **Single harvest per season**: Each field can be associated with only one harvest per season.
- **No duplicate harvests for trees**: Trees cannot be included in more than one harvest for the same season.

## Technical Requirements
- **Spring Boot**: To create the REST API.
- **Layered Architecture**: Following the structure of **Controller**, **Service**, **Repository**, and **Entity** layers.
- **Data Validation**: Implemented using **Spring annotations**.
- **Interfaces and Implementations**: Used throughout the codebase.
- **Centralized Exception Management**: To handle errors consistently.
- **Unit Testing**: Conducted using **JUnit** and **Mockito**.
- **Lombok & Builder Pattern**: To simplify entity management.
- **MapStruct**: For converting between **Entities**, **DTOs**, and **View Models**.

## Getting Started

### Prerequisites
- **Java 11** or higher.
- **Maven** for dependency management.
- **Spring Boot** for running the application.

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/IBNESSAYEH/Citronix.git
   ```
2. Navigate to the project directory:
   ```sh
   cd citronix
   ```
3. Build the project using Maven:
   ```sh
   mvn clean install
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## Usage
- Access the application at **http://localhost:8080**.
- Use the API endpoints to manage farms, fields, trees, harvests, and sales.

## Testing
- Run unit tests using Maven:
  ```sh
  mvn test
  ```




