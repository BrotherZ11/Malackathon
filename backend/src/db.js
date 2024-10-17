const express = require('express');
const axios = require('axios');
const rateLimit = require('express-rate-limit');
const cors = require('cors');
const helmet = require('helmet');

const app = express();

// Middleware
app.use(helmet());
app.use(cors());
app.use(express.json());

// Limitar las peticiones para evitar abusos
const limiter = rateLimit({
  windowMs: 15 * 60 * 1000, // 15 minutos
  max: 100 // Máximo 100 peticiones por IP cada 15 minutos
});
app.use(limiter);

// URL de las APIs ORDS para acceder a las tablas
const EMBALSES_URL = "https://geeaebb849cba69-hackathon.adb.eu-madrid-1.oraclecloudapps.com/ords/test/embalsesutf8/";

// Endpoint para obtener embalses cercanos
app.post('/api/embalses/cercanos', async (req, res) => {
  const { lat, lon, radius = 100 } = req.body; // Radio por defecto de 100 km

  if (!lat || !lon) {
    return res.status(400).send("Latitud y longitud son requeridas.");
  }

  try {
    // Consulta los datos de embalses desde el servicio REST
    const embalsesResponse = await axios.get(EMBALSES_URL);
    const embalsesData = embalsesResponse.data.items;

    // Filtro de embalses usando la fórmula de Haversine
    const filteredEmbalses = embalsesData.filter(embalse => {
      const embalseLat = parseFloat(embalse.Y); // Asegúrate de que tienes las coordenadas
      const embalseLon = parseFloat(embalse.X);
      const distance = calculateHaversineDistance(lat, lon, embalseLat, embalseLon);
      return distance <= radius; // Filtrar por el radio
    });

    res.json(filteredEmbalses); // Devolver embalses filtrados
  } catch (error) {
    console.error("Error al consultar embalses:", error);
    res.status(500).send("Error al consultar embalses.");
  }
});

// Fórmula de Haversine para calcular la distancia entre dos puntos (lat/lon)
function calculateHaversineDistance(lat1, lon1, lat2, lon2) {
  const R = 6371; // Radio de la tierra en km
  const dLat = degreesToRadians(lat2 - lat1);
  const dLon = degreesToRadians(lon2 - lon1);
  const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(degreesToRadians(lat1)) * Math.cos(degreesToRadians(lat2)) *
            Math.sin(dLon / 2) * Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  return R * c; // Distancia en km
}

function degreesToRadians(degrees) {
  return degrees * (Math.PI / 180);
}

// Iniciar servidor
const PORT = process.env.PORT || 3001;
app.listen(PORT, () => {
  console.log(`Servidor corriendo en el puerto ${PORT}`);
});
