import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";

function Embalses() {
  const [lat, setLat] = useState("");
  const [lon, setLon] = useState("");
  const [radius, setRadius] = useState(100); // Radio por defecto
  const [embalses, setEmbalses] = useState([]);
  const [estadisticas, setEstadisticas] = useState({});

  const getEmbalses = async () => {
    try {
      const response = await axios.post(
        "http://localhost:3001/api/embalses/cercanos",
        {
          lat: parseFloat(lat),
          lon: parseFloat(lon),
          radius: parseFloat(radius),
        }
      );
      console.log("Datos de embalses:", response.data);
      setEmbalses(response.data);
    } catch (error) {
      console.error("Error al obtener embalses:", error);
      alert("Error al obtener embalses.");
    }
  };

  const handleGeolocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        setLat(position.coords.latitude);
        setLon(position.coords.longitude);
      });
    } else {
      alert("Geolocalización no es soportada por este navegador.");
    }
  };

  return (
    <div className="container mt-5">
      <h1>Buscar Embalses Cercanos</h1>
      <form>
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
        <button type="button" className="btn btn-primary" onClick={getEmbalses}>
          Buscar
        </button>
        <button
          type="button"
          className="btn btn-secondary ml-2"
          onClick={handleGeolocation}
        >
          Usar mi ubicación
        </button>
      </form>

      {embalses.length > 0 && (
        <div className="mt-5">
          <h3>Embalses encontrados:</h3>
          <ul className="list-group">
            {embalses.map((embalse, index) => (
              <li key={index} className="list-group-item">
                {embalse.embalse_nombre} - {embalse.agua_total} m³ de agua
                actual
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}

export default Embalses;
