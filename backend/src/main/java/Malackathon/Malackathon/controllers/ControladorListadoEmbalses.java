package Malackathon.Malackathon.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Malackathon.Malackathon.entities.ListadoEmbalses;
import Malackathon.Malackathon.servicios.ServicioListadoEmbalses;

@RestController
@RequestMapping("/api/listado-embalses")
public class ControladorListadoEmbalses {

    @Autowired
    private ServicioListadoEmbalses listadoEmbalsesServ;

    // Endpoint para obtener embalses cercanos a unas coordenadas dadas
    @GetMapping("/cercanos")
    public ResponseEntity<List<ListadoEmbalses>> getListadoEmbalsesByXY(
            @RequestParam double posicionXYDTO) {
        List<ListadoEmbalses> embalsesCercanos = listadoEmbalsesServ.getListadoEmbalsesByXY(posicionXYDTO);
        return ResponseEntity.ok(embalsesCercanos);
    }
}

