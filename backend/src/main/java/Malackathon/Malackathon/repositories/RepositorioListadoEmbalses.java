package Malackathon.Malackathon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Malackathon.Malackathon.entities.ListadoEmbalses;

public interface RepositorioListadoEmbalses extends JpaRepository<ListadoEmbalses, Long> {
    
    @Query(value = "SELECT le.*, e.AMBITO_NOMBRE, e.EMBALSE_NOMBRE, e.AGUA_TOTAL, e.ELECTRICO_FLAG, " +
           "aa.FECHA as FECHA_AGUA_ACTUAL, aa.AGUA_ACTUAL, " +
           "GROUP_CONCAT(DISTINCT er.NOMBRE_EMBALSE SEPARATOR ', ') as EMBALSES_RELACIONADOS, " +
           "ST_Distance_Sphere(point(le.X, le.Y), point(:x, :y)) as DISTANCIA " +
           "FROM LISTADO_EMBALSES le " +
           "JOIN EMBALSES e ON le.CODIGO = e.ID " +
           "LEFT JOIN AGUA_ALMACENADA aa ON e.ID = aa.ID " +
           "LEFT JOIN EMBALSES_RELACIONADOS er ON le.CODIGO = er.CODIGO_EMBALSE " +
           "GROUP BY le.CODIGO " +
           "ORDER BY DISTANCIA",
           nativeQuery = true)
    List<ListadoEmbalses> getListadoEmbalsesByXY(@Param("posicionXYDTO") double posicionXYDTO);
}
