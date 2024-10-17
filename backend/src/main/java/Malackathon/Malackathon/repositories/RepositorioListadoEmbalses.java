package Malackathon.Malackathon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Malackathon.Malackathon.entities.ListadoEmbalses;

public interface RepositorioListadoEmbalses extends JpaRepository<ListadoEmbalses, Long> {
    
    @Query(value = "SELECT * FROM listado_embalses " +
           "WHERE X =", nativeQuery = true)
    List<ListadoEmbalses> findEmbalsesWithinRadius(@Param("posicionXYDTO") Long posicionXYDTO);
}
