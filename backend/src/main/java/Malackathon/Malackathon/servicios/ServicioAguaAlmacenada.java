package Malackathon.Malackathon.servicios;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Malackathon.Malackathon.excepciones.EntidadExistenteException;
import Malackathon.Malackathon.excepciones.EntidadNoEncontradaException;
import Malackathon.Malackathon.repositories.RepositorioAguaAlmacenada;
import Malackathon.Malackathon.entities.AguaAlmacenada;

@Service
@Transactional
public class ServicioAguaAlmacenada {
    @Autowired
    private RepositorioAguaAlmacenada RepositorioaguaAlmacenada;

    public List<AguaAlmacenada> getAllAguaAlmacenada() {
        return RepositorioaguaAlmacenada.findAll();
    }

    public Optional<AguaAlmacenada> getAguaAlmacenadaById(Long id) {
        return RepositorioaguaAlmacenada.findById(id);
    }

    public AguaAlmacenada saveAguaAlmacenada(AguaAlmacenada aguaAlmacenada) {
        return RepositorioaguaAlmacenada.save(aguaAlmacenada);
    }

    public Optional<AguaAlmacenada> updateAguaAlmacenada(Long id, AguaAlmacenada aguaAlmacenadaDetails) {
        return RepositorioaguaAlmacenada.findById(id)
            .map(aguaAlmacenada -> {
                aguaAlmacenada.setFecha(aguaAlmacenadaDetails.getFecha());
                //aguaAlmacenada.setAguaActual(aguaAlmacenadaDetails.getAguaActual());
                return RepositorioaguaAlmacenada.save(aguaAlmacenada);
            });
    }

    public boolean deleteAguaAlmacenada(Long id) {
        return RepositorioaguaAlmacenada.findById(id)
            .map(aguaAlmacenada -> {
                RepositorioaguaAlmacenada.delete(aguaAlmacenada);
                return true;
            })
            .orElse(false);
    }
}