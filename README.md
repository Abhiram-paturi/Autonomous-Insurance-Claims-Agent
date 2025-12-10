# Autonomous Insurance Claims Processing Agent

## Project Overview
This project implements an autonomous agent for **First Notice of Loss (FNOL)** claims processing. It extracts key fields from insurance claim documents, validates mandatory fields, routes claims to the appropriate workflow, and provides reasoning for the routing decision.

The project is divided into two parts:
- **Backend:** Spring Boot application for document parsing, field extraction, validation, and routing.
- **Frontend:** React.js application for uploading documents and displaying processed claim data.


## Tech Stack
- **Backend:** Java, Spring Boot, Maven
- **Frontend:** React.js, HTML, CSS, Axios
- **Database:** MySQL (if needed for future storage)
- **Others:** Postman (API testing)

---

## Folder Structure<br>
Autonomous-Insurance-Claims-Agent/<br>
├── backend/ # Spring Boot backend<br>
│ ├── src/main/java/com/fnol/<br>
│ ├── pom.xml<br>
│ └── application.properties<br>
├── frontend/ # React frontend<br>
│ ├── public/<br>
│ ├── src/<br>
│ ├── package.json<br>
│ └── README.md<br>
└── README.md # This file<br>


---

## Features
1. **Document Parsing:** Supports PDF and TXT files.
2. **Field Extraction:** Extracts Policy, Incident, Parties, Asset, and Claim details.
3. **Validation:** Identifies missing or inconsistent fields.
4. **Claim Routing:** Routes claims based on rules:
   - Damage < 25,000 → Fast-track
   - Missing mandatory fields → Manual review
   - Keywords like “fraud” → Investigation flag
   - Claim type = injury → Specialist queue
5. **JSON Output:** Returns extracted fields, missing fields, recommended route, and reasoning.

---

## Setup & Run<br>

### Backend<br>
1. Navigate to `backend` folder:<br>
   cd backend<br>
2. Build and run Spring Boot:<br>
   ->mvn clean install<br>
   ->mvn spring-boot:run<br>
3. API will be available at http://localhost:8080/api/fnol/analyze<br>




### Frontend<br>
1. Navigate to frontend folder:<br>
   cd frontend<br>
2. Install dependencies:<br>
   npm install<br>
3. Start frontend server:<br>
   npm start<br>
4. Open browser at http://localhost:3000<br>


**API Usage**<br>
     **Endpoint:** POST /api/fnol/analyze<br>
     **Consumes:** multipart/form-data (file upload)<br>
   **Response:** JSON containing extracted fields, missing fields, recommended route, and reasoning.<br>

**Notes**<br>
   This implementation uses regex-based extraction assuming structured document formats.<br>
   For unstructured documents, LLM-based extraction can be integrated (details in README).<br>



