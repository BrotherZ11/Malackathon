import React, { useState } from "react";
import axios from "axios";

const EmbalsesSearch = () => {
  const [lat, setLat] = useState("");
  const [lon, setLon] = useState("");
  const [radius, setRadius] = useState(100);
  const [embalses, setEmbalses] = useState([]);

  const handleSearch = async () => {
    try {
      const response = await axios.get(`/api/embalses/search`, {
        params: { lat, lon, radius },
      });
      setEmbalses(response.data);
    } catch (error) {
      console.error("Error fetching embalses:", error);
    }
  };

  return (
    <div className="container">
      <h1>Buscar Embalses</h1>
      <div className="form-group">
        <label>Latitud:</label>
        <input
          type="text"
          className="form-control"
          value={lat}
          onChange={(e) => setLat(e.target.value)}
        />
      </div>
      <div className="form-group">
        <label>Longitud:</label>
        <input
          type="text"
          className="form-control"
          value={lon}
          onChange={(e) => setLon(e.target.value)}
        />
      </div>
      <div className="form-group">
        <label>Radio (km):</label>
        <input
          type="number"
          className="form-control"
          value={radius}
          onChange={(e) => setRadius(e.target.value)}
        />
      </div>
      <button className="btn btn-primary" onClick={handleSearch}>
        Buscar
      </button>

      {embalses.length > 0 && (
        <div className="mt-4">
          <h2>Embalses encontrados:</h2>
          <ul>
            {embalses.map((embalse) => (
              <li key={embalse.id}>
                {embalse.nombre} - {embalse.agua_actual} hm³
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};

export default EmbalsesSearch;
