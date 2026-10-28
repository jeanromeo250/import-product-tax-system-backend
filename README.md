# Import Product Tax System (Backend)

MID TERM EXAM OF WEBTECH 

Student: NUWOKWIZERWA Jean Romeo
Student ID: 27671

Lecturer: Patrick DUSHIMIMANA

Project name: Import Product Tax System

## About

This is a REST API for managing imported goods and their taxes. It keeps track of importers, customs officers, inspection bays, product categories, import declarations and tax assessments. A declaration goes through SUBMITTED, UNDER_ASSESSMENT, CLEARED or REJECTED, and a tax assessment can only be created for a CLEARED declaration.

## Built with

- Java 21
- Spring Boot (Spring Web, Spring Data JPA)
- PostgreSQL
- Gradle

## How to run

1. Install Java 21 and PostgreSQL.
2. Create an empty database:
   ```sql
   CREATE DATABASE import_product_tax_db;
   ```
3. Open `src/main/resources/application.properties` and set your own PostgreSQL username and password:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/import_product_tax_db
   spring.datasource.username=romeo
   spring.datasource.password=romeo123
   server.port=8082
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Start the application from the project folder:
   ```
   .\gradlew.bat bootRun      (Windows)
   ./gradlew bootRun          (Linux / Mac)
   ```
5. The API runs at `http://localhost:8082`. The tables are created automatically on the first start.

## Testing

The Postman collection is in the project root: `import-product-tax-system-midterm.postman_collection.json`.

1. Import the collection into Postman.
2. Run the A2 save requests and use each `/all` response to copy the generated UUIDs into the collection variables before running requests that need related records.
3. Use a declaration date that is today or later for successful D1 requests. The collection includes past-date and duplicate-date examples for the expected error responses.
4. Run each request named by its assignment requirement (A2, B1, C4, D2 and so on).

## Main endpoints

| Part | Endpoint |
|------|----------|
| A | CRUD under `/api/product-categories`, `/api/inspection-bays`, `/api/customs-officers`, `/api/importers`, `/api/import-declarations` |
| B | `/api/importers/by-company-name`, `/api/import-declarations/by-status`, `/api/import-declarations/between` |
| C | `/api/customs-officers/by-product-category`, `/api/inspection-bays/free`, `/api/product-categories/unused`, `/api/importers/of-customs-officer/{id}` |
| D | `/api/import-declarations/save`, `/api/import-declarations/{id}/status`, `/api/customs-officers/{id}/product-categories/{categoryId}`, `/api/customs-officers/{id}/inspection-bay` |
| E | `/api/tax-assessments/save`, `/api/tax-assessments/importer/{tinNumber}`, `/api/tax-assessments/delete/{id}` |
| F | `/api/import-declarations/stats/by-status`, `/api/import-declarations/cancel-day` |
| Dashboard| `/api/dashboard/summary`, `/api/import-declarations/page` |

## Project structure

```
src/main/java/rw/auca/import_product_tax_system
    domain        entities and the DeclarationStatus enum
    repository    Spring Data JPA repositories
    service       business rules
    controller    REST endpoints
```
