
import React, { useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [file, setFile] = useState(null);
  const [response, setResponse] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!file) {
      alert("Please select a file first!");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      setLoading(true);
      const res = await axios.post("http://localhost:8080/api/fnol/analyze", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      setResponse(res.data);
    } catch (err) {
      console.error(err);
      alert("Error uploading file or processing response!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="App">
      <h1>Autonomous Insurance Claims Processing Agent</h1>
      <form onSubmit={handleSubmit}>
        <input type="file" accept=".txt,.pdf" onChange={handleFileChange} />
        <button type="submit">Upload & Analyze</button>
      </form>

      {loading && <p>Processing...</p>}

      {response && (
        <div className="response">
          <h2>Extracted Fields:</h2>
          <pre>{JSON.stringify(response.extractedFields, null, 2)}</pre>

          <h2>Missing Fields:</h2>
          <pre>{JSON.stringify(response.missingFields, null, 2)}</pre>

          <h2>Recommended Route:</h2>
          <p>{response.recommendedRoute}</p>

          <h2>Reasoning:</h2>
          <p>{response.reasoning}</p>
        </div>
      )}
    </div>
  );
}

export default App;
