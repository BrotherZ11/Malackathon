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

    public boolean deleteEmbalses(Long id) {
        return RepositorioEmbalses.findById(id)
            .map(embalse -> {
                RepositorioEmbalses.delete(embalse);
                return true;
            })
            .orElse(false);
    }
}