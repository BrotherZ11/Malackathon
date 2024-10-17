package Malackathon.Malackathon.servicios;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Malackathon.Malackathon.entities.Embalses;
import Malackathon.Malackathon.excepciones.EntidadExistenteException;
import Malackathon.Malackathon.excepciones.EntidadNoEncontradaException;
import Malackathon.Malackathon.repositories.RepositorioEmbalses;

@Service
@Transactional
public class ServicioEmbalses {
    private final RepositorioEmbalses repositorioEmbalses;

    @Autowired
    private RepositorioEmbalses RepositorioEmbalses;

    public List<Embalses> getAllEmbalses() {
        return RepositorioEmbalses.findAll();
    }

    public Optional<Embalses> getEmbalsesById(Long id) {
        return RepositorioEmbalses.findById(id);
    }

    public Embalses saveEmbalses(Embalses embalse) {
        return RepositorioEmbalses.save(embalse);
    }

    public Optional<Embalses> updateEmbalses(Long id, Embalses embalseDetails) {
        return RepositorioEmbalses.findById(id)
            .map(embalse -> {
                embalse.setAmbitoNombre(embalseDetails.getAmbitoNombre());
                embalse.setEmbalseNombre(embalseDetails.getEmbalseNombre());
                embalse.setAguaTotal(embalseDetails.getAguaTotal());
                embalse.setElectricoFlag(embalseDetails.getElectricoFlag());
                return RepositorioEmbalses.save(embalse);
            });
    }

    public boolean deleteEmbalses(Long id) {
        return RepositorioEmbalses.findById(id)
            .map(embalse -> {
                RepositorioEmbalses.delete(embalse);
                return true;
            })
            .orElse(false);
    }
}