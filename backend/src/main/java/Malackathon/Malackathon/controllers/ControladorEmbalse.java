package Malackathon.Malackathon.controllers;


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

import java.net.URI;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/api/embalses")
public class ControladorEmbalse {

    @Autowired
    private RepositorioEmbalse RepositorioEmbalse;

    @GetMapping
    public List<Embalse> getAllEmbalses() {
        return RepositorioEmbalse.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Embalse> getEmbalseById(@PathVariable Long id) {
        return RepositorioEmbalse.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Embalse createEmbalse(@RequestBody Embalse embalse) {
        return RepositorioEmbalse.save(embalse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Embalse> updateEmbalse(@PathVariable Long id, @RequestBody Embalse embalseDetails) {
        return RepositorioEmbalse.findById(id)
                .map(embalse -> {
                    embalse.setAmbitoNombre(embalseDetails.getAmbitoNombre());
                    embalse.setEmbalseNombre(embalseDetails.getEmbalseNombre());
                    embalse.setAguaTotal(embalseDetails.getAguaTotal());
                    embalse.setElectricoFlag(embalseDetails.getElectricoFlag());
                    return ResponseEntity.ok(RepositorioEmbalse.save(embalse));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmbalse(@PathVariable Long id) {
        return RepositorioEmbalse.findById(id)
                .map(embalse -> {
                    RepositorioEmbalse.delete(embalse);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
