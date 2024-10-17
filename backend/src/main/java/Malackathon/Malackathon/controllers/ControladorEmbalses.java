package Malackathon.Malackathon.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import Malackathon.Malackathon.mapper.Mapper;
import Malackathon.Malackathon.excepciones.EntidadExistenteException;
import Malackathon.Malackathon.excepciones.EntidadNoEncontradaException;
import Malackathon.Malackathon.security.JwtUtil;
import Malackathon.Malackathon.repositories.*;
import Malackathon.Malackathon.entities.*;

import java.net.URI;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/api/embalses")
public class ControladorEmbalses {

    @Autowired
    private RepositorioEmbalses RepositorioEmbalses;

    @GetMapping
    public List<Embalses> getAllEmbalsess() {
        return RepositorioEmbalses.findAll();
    }

    @GetMapping("/")
    public ResponseEntity<Embalses> getEmbalsesByXY(@RequestParam Long posicionXYDTO) {
        return RepositorioEmbalses.findByXY(posicionXYDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Embalses> getEmbalsesById(@PathVariable Long id) {
        return RepositorioEmbalses.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Embalses createEmbalses(@RequestBody Embalses embalses) {
        return RepositorioEmbalses.save(embalses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Embalses> updateEmbalses(@PathVariable Long id, @RequestBody Embalses embalsesDetails) {
        return RepositorioEmbalses.findById(id)
                .map(embalses -> {
                    embalses.setAmbitoNombre(embalsesDetails.getAmbitoNombre());
                    embalses.setEmbalsesNombre(embalsesDetails.getEmbalsesNombre());
                    embalses.setAguaTotal(embalsesDetails.getAguaTotal());
                    embalses.setElectricoFlag(embalsesDetails.getElectricoFlag());
                    return ResponseEntity.ok(RepositorioEmbalses.save(embalses));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmbalses(@PathVariable Long id) {
        return RepositorioEmbalses.findById(id)
                .map(embalses -> {
                    RepositorioEmbalses.delete(embalses);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
