# Autonomous Insurance Claims Processing Agent

## 📌 Project Overview

This repository implements an **Autonomous Insurance Claims Processing Agent** for **First Notice of Loss (FNOL)** documents. It processes uploaded PDF and TXT FNOL files, extracts key fields, validates mandatory information, applies routing rules, and returns a structured JSON response with a recommended workflow and explanation.

This project is structured as a full-stack application with a Spring Boot backend and a React frontend that allows users to upload documents and view processing results.

---

## 🛠 Tech Stack

**Backend**
- Java
- Spring Boot
- Maven
- Apache PDFBox (PDF parsing)

**Frontend**
- React.js
- Axios
- HTML/CSS

**API Testing**
- Postman

---

## 📁 Folder Structure

Autonomous-Insurance-Claims-Agent/<br>
├── backend/ # Spring Boot backend <br>
│ ├── src/main/java/com/fnol/ <br>
│ ├── pom.xml <br>
│ └── application.properties <br>
├── frontend/ # React frontend <br>
│ ├── public/ <br>
│ ├── src/ <br>
│ ├── package.json <br>
│ └── README.md <br>
└── README.md # Root README <br>


---

## 🚀 Features

### 🔹 Document Parsing
- Supports uploading **PDF** and **TXT** files.
- Extracts text using PDFBox or plain text read for .txt files.

### 🔹 Field Extraction
Extracts the following fields from claim documents:

**Policy Information**
- Policy Number  
- Policyholder Name  
- Effective Dates  

**Incident Information**
- Incident Date  
- Incident Time  
- Location  
- Description  

**Involved Parties**
- Claimant  
- Third Parties  
- Contact Details  

**Asset Details**
- Asset Type  
- Asset ID  
- Estimated Damage  

**Other Mandatory Fields**
- Claim Type  
- Attachments  
- Initial Estimate  

---

## ✅ Validation Logic

Identifies missing mandatory fields and flags them in the output.

---

## 📊 Claim Routing Rules

| Condition | Recommended Route |
|-----------|-------------------|
| Estimated damage < 25,000 | Fast-track |
| Any mandatory field missing | Manual review |
| Description contains words like “fraud”, “inconsistent”, “staged” | Investigation Flag |
| Claim type = injury | Specialist Queue |
| Otherwise | Standard Queue |

---

## 📦 Output Format

Every processed claim returns a single JSON object in this format:

```json
{
  "extractedFields": {},
  "missingFields": [],
  "recommendedRoute": "",
  "reasoning": ""
}
```

## Backend
```
cd backend
mvn clean install
mvn spring-boot:run
```
By default, the API will be available at:
```
http://localhost:8080/api/fnol/process
```

## Frontend
```
cd frontend
npm install
npm start
```
The frontend will run at:
```
http://localhost:3000
```
## 📡 API Usage
## Endpoint
 POST /api/fnol/process
## Consumes
 multipart/form-data (file upload)

## Response
JSON with extracted fields, missing fields, recommended route, and reasoning.

## 📝 Design Decisions

The current implementation uses regex-based extraction assuming semi-structured FNOL documents with consistent labeled fields.
Regex extraction provides deterministic and explainable field parsing.

For more unstructured document formats (e.g., free text forms or scanned content), an LLM-based parser can be integrated. That integration point is designed to be pluggable, meaning a future LLM processor can replace or extend the current extraction strategy without breaking the rest of the pipeline.

## 📌 Notes

The project focuses on correctness and clarity of claim processing logic rather than advanced AI.

Regex extraction assumes fairly standardized document structure, which matches the provided dummy FNOL samples.

LLM integration, if added, should be implemented in a separate module or strategy while retaining the same output schema.
