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
    public ServicioEmbalses(RepositorioEmbalses repositorioEmbalses) {
        this.repositorioEmbalses = repositorioEmbalses;
    }
    public Ejercicio getEjercicio(Long id){
        Optional<Ejercicio> ejercicio = repositorioEmbalses.findById(id);
        return ejercicio.orElseThrow(() -> new EntidadNoEncontradaException());
    }

    public Long addEjercicio(Long idEntrenador, Ejercicio ejercicio){
        if (repositorioEmbalses.existsByNombre(ejercicio.getNombre())) {
            throw new EntidadExistenteException("Ejercicio ya existe");
        }
        ejercicio.setEntrenador(idEntrenador.intValue());
		repositorioEmbalses.save(ejercicio);
		return ejercicio.getId();
    }

    public Ejercicio updateEjercicio(Ejercicio ejercicio) {
        return repositorioEmbalses.save(ejercicio);
    }

    public void deleteEjercicio(Long id) {
        repositorioEmbalses.deleteById(id);
    }

    public List<Ejercicio> getEjerciciosPorEntrenador(Long id){
        return repositorioEmbalses.findByEntrenador(Long.valueOf(id)).get();
    }


}
