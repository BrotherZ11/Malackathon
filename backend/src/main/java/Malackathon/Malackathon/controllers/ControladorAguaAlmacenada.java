package Malackathon.Malackathon.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import Malackathon.Malackathon.dtos.AsignacionEntrenamientoDTO;
import Malackathon.Malackathon.mapper.Mapper;
import Malackathon.Malackathon.dtos.EjercicioDTO;
import Malackathon.Malackathon.dtos.EjercicioNuevoDTO;
import Malackathon.Malackathon.entities.Ejercicio;
import Malackathon.Malackathon.excepciones.EntidadExistenteException;
import Malackathon.Malackathon.excepciones.EntidadNoEncontradaException;
import Malackathon.Malackathon.security.JwtUtil;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
@RestController
@RequestMapping("/api/agua-almacenada")
public class ControladorAguaAlmacenada {

    @Autowired
    private AguaAlmacenada RepositorioAguaAlmacenada;

    @GetMapping
    public List<AguaAlmacenada> getAllAguaAlmacenada() {
        return RepositorioAguaAlmacenada.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AguaAlmacenada> getAguaAlmacenadaById(@PathVariable Long id) {
        return RepositorioAguaAlmacenada.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AguaAlmacenada createAguaAlmacenada(@RequestBody AguaAlmacenada aguaAlmacenada) {
        return RepositorioAguaAlmacenada.save(aguaAlmacenada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AguaAlmacenada> updateAguaAlmacenada(@PathVariable Long id, @RequestBody AguaAlmacenada aguaAlmacenadaDetails) {
        return RepositorioAguaAlmacenada.findById(id)
                .map(aguaAlmacenada -> {
                    aguaAlmacenada.setFecha(aguaAlmacenadaDetails.getFecha());
                    aguaAlmacenada.setAguaActual(aguaAlmacenadaDetails.getAguaActual());
                    return ResponseEntity.ok(RepositorioAguaAlmacenada.save(aguaAlmacenada));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAguaAlmacenada(@PathVariable Long id) {
        return RepositorioAguaAlmacenada.findById(id)
                .map(aguaAlmacenada -> {
                    RepositorioAguaAlmacenada.delete(aguaAlmacenada);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

@RestController
@RequestMapping("/api/listado-embalses")
public class ListadoEmbalsesController {

    @Autowired
    private ListadoEmbalsesRepository listadoEmbalsesRepository;

    @GetMapping
    public List<ListadoEmbalses> getAllListadoEmbalses() {
        return listadoEmbalsesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListadoEmbalses> getListadoEmbalsesById(@PathVariable Long id) {
        return listadoEmbalsesRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ListadoEmbalses createListadoEmbalses(@RequestBody ListadoEmbalses listadoEmbalses) {
        return listadoEmbalsesRepository.save(listadoEmbalses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListadoEmbalses> updateListadoEmbalses(@PathVariable Long id, @RequestBody ListadoEmbalses listadoEmbalsesDetails) {
        return listadoEmbalsesRepository.findById(id)
                .map(listadoEmbalses -> {
                    // Actualiza todos los campos necesarios
                    listadoEmbalses.setCodigo(listadoEmbalsesDetails.getCodigo());
                    listadoEmbalses.setNombre(listadoEmbalsesDetails.getNombre());
                    listadoEmbalses.setEmbalse(listadoEmbalsesDetails.getEmbalse());
                    // .. actualiza los demás campos ..
                    return ResponseEntity.ok(listadoEmbalsesRepository.save(listadoEmbalses));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteListadoEmbalses(@PathVariable Long id) {
        return listadoEmbalsesRepository.findById(id)
                .map(listadoEmbalses -> {
                    listadoEmbalsesRepository.delete(listadoEmbalses);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}