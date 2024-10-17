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
    private final RepositorioAguaAlmacenada repositorioAguaAlmacenada;

    @Autowired
    public ServicioAguaAlmacenada(RepositorioAguaAlmacenada repositorioAguaAlmacenada) {
        this.repositorioAguaAlmacenada = repositorioAguaAlmacenada;
    }

    /**
     * Obtiene AguaAlmacenada por id
     * @param id Id de la AguaAlmacenada
     * @Throws AguaAlmacenadaNoEncontrada si la AguaAlmacenada no existe
     * @return AguaAlmacenada
     */
    public AguaAlmacenada getAguaAlmacenada(Long id) {
        Optional<AguaAlmacenada> AguaAlmacenada = repositorioAguaAlmacenada.findById(id);
        return AguaAlmacenada.orElseThrow(() -> new EntidadNoEncontradaException());
    }

    public Long addAguaAlmacenada(Long idEntrenador, AguaAlmacenada AguaAlmacenada){
        if (repositorioAguaAlmacenada.existsByNombre(AguaAlmacenada.getNombre())) {
            throw new EntidadExistenteException("AguaAlmacenada ya existe");
        }
        AguaAlmacenada.setEntrenador(idEntrenador.intValue());
        repositorioAguaAlmacenada.save(AguaAlmacenada);
        return AguaAlmacenada.getId();
    }

    /**
     * Actualiza AguaAlmacenada en la base de datos
     * @param AguaAlmacenada AguaAlmacenada a actualizar
     * @Throws AguaAlmacenadaNoEncontrada si la AguaAlmacenada no existe previamente
     * @return AguaAlmacenada actualizada
     */
    public AguaAlmacenada updateAguaAlmacenada(AguaAlmacenada AguaAlmacenada) {
        return repositorioAguaAlmacenada.save(AguaAlmacenada);
    }

    /**
     * Elimina AguaAlmacenada de la base de datos
     * @param id Id de la AguaAlmacenada
     * @Throws AguaAlmacenadaNoEncontrada si la AguaAlmacenada no existe previamente
     */
    public void deleteAguaAlmacenada(Long id) {
        repositorioAguaAlmacenada.deleteById(id);
    }

    /**
     * Obtiene todas las AguaAlmacenadas de un Entrenador
     * @param entrenadorId Id del Entrenador
     * @throws
     * @return Lista de AguaAlmacenadas asociadas al Entrenador
     */
    public List<AguaAlmacenada> getAguaAlmacenadasPorEntrenador(Long entrenadorId) {
        return repositorioAguaAlmacenada.findByEntrenador(Long.valueOf(entrenadorId)).get();
    }




}