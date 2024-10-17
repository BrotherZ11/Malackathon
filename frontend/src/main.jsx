import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "bootstrap/dist/css/bootstrap.min.css";
import EmbalsesSearch from "./components/Embalses.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <EmbalsesSearch />
  </StrictMode>
);
