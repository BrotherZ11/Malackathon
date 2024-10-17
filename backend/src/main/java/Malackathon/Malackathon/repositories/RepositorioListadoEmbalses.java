package Malackathon.Malackathon.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Malackathon.Malackathon.entities.ListadoEmbalses;

public interface RepositorioListadoEmbalses extends JpaRepository<ListadoEmbalses, Long> {
    
    @Query(value = "SELECT * FROM listado_embalses " +
           "WHERE X =", nativeQuery = true)
    List<ListadoEmbalses> getEmbalsesByXY(@Param("posicionXYDTO") Long posicionXYDTO);

    List<ListadoEmbalses> getListadoEmbalsesByXY(double posicionXYDTO);
}
