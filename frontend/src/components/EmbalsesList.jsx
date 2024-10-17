// src/components/EmbalsesList.js

import React, { useEffect, useState } from "react";
import axios from "axios";

const EmbalsesList = () => {
  const [embalses, setEmbalses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Función para obtener los embalses desde el backend
    const fetchEmbalses = async () => {
      try {
        const response = await axios.get(
          "http://localhost:3001/api/embalses/cercanos"
        );
        console.log("Datos de embalses:", response.data);
        setEmbalses(response.data);
      } catch (err) {
        setError("Error al cargar los embalses.");
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchEmbalses();
  }, []);

  if (loading) {
    return <div>Cargando embalses...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div className="container mt-4">
      <h1>Lista de Embalses</h1>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Ámbito</th>
            <th>Agua Total</th>
            <th>¿Genera Electricidad?</th>
          </tr>
        </thead>
        <tbody>
          {embalses.map((embalse) => (
            <tr key={embalse.CODIGO}>
              <td>{embalse.CODIGO}</td>
              <td>{embalse.NOMBRE}</td>
              <td>{embalse.AMBITO}</td>
              <td>{embalse.AGUA_TOTAL}</td>
              <td>{embalse.ELECTRICO_FLAG ? "Sí" : "No"}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default EmbalsesList;
