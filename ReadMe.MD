
# FLagged Words Checker

## Access the Project: [https://flagged.lukorito.dev](https://flagged.lukorito.dev)

This is the backend portion of a fullstack project done to satisfy the assessment requirements by ABIE Frames Ltd.

The project is a simple flagged words checker that allows users to check if a word is flagged or not. The uploaded documents are stored in a database.

The app is built using Java Spring Boot and deployed on Microsoft Azure. The database used is PostgreSQL, also hosted separately on Azure. Continuous development (CI/CD) was done via GitHub Actions. The frontend (in a separate [repository](https://github.com/WafulaLukorito/flagged-Frontend.git)) is built with React.

## Backend Technologies Used
- Java Spring Boot
- Lombok (For cleaner code)
- PostgreSQL
- Azure App Service
- Azure Flexible Server (PostgreSQL)
- GitHub Actions (CI/CD)
- Postman (API Testing)

## API Endpoints
- POST https://flagged.azurewebsites.net/api/words/upload (Upload a text document)
- GET https://flagged.azurewebsites.net/api/words (Get all flagged words)
- GET https://flagged.azurewebsites.net/api/words/uploadedfiles (Get all uploaded files stored in the DB)
- POST 'https://flagged.azurewebsites.net/api/words/check (Check if a word is flagged or not)

The application can be accessed through [https://flagged.lukorito.dev](https://flagged.lukorito.dev)