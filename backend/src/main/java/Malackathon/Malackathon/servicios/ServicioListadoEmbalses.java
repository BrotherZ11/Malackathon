package Malackathon.Malackathon.servicioListadoEmbalses;

import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import Malackathon.Malackathon.dtos.AsignacionEntrenamientoDTO;
import Malackathon.Malackathon.dtos.RutinaDTO;
import Malackathon.Malackathon.entities.ListadoEmbalses;
import Malackathon.Malackathon.repositories.RepositorioListadoEmbalses;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ServicioListadoEmbalses {

     @Autowired
    private RepositorioListadoEmbalses RepositoriolistadoEmbalses;

    public List<ListadoEmbalses> getAllListadoEmbalses() {
        return RepositoriolistadoEmbalses.findAll();
    }

    public List<ListadoEmbalses> getListadoEmbalsesByXY(double posicionXYDTO) {
        return RepositoriolistadoEmbalses.getListadoEmbalsesByXY(posicionXYDTO);
    }

    public Optional<ListadoEmbalses> getListadoEmbalsesById(Long id) {
        return RepositoriolistadoEmbalses.findById(id);
    }

    public ListadoEmbalses saveListadoEmbalses(ListadoEmbalses listadoEmbalses) {
        return RepositoriolistadoEmbalses.save(listadoEmbalses);
    }

    public Optional<ListadoEmbalses> updateListadoEmbalses(Long id, ListadoEmbalses listadoEmbalsesDetails) {
        return RepositoriolistadoEmbalses.findById(id)
            .map(listadoEmbalses -> {
                listadoEmbalses.setCodigo(listadoEmbalsesDetails.getCodigo());
                listadoEmbalses.setNombre(listadoEmbalsesDetails.getNombre());
                //listadoEmbalses.setEmbalse(listadoEmbalsesDetails.getEmbalse());
                listadoEmbalses.setX(listadoEmbalsesDetails.getX());
                listadoEmbalses.setY(listadoEmbalsesDetails.getY());
                listadoEmbalses.setDemarc(listadoEmbalsesDetails.getDemarc());
                //listadoEmbalses.setCauce(listadoEmbalsesDetails.getCauce());
                listadoEmbalses.setGoogle(listadoEmbalsesDetails.getGoogle());
                listadoEmbalses.setOpenstreetmap(listadoEmbalsesDetails.getOpenstreetmap());
                listadoEmbalses.setWikidata(listadoEmbalsesDetails.getWikidata());
                listadoEmbalses.setProvincia(listadoEmbalsesDetails.getProvincia());
                listadoEmbalses.setCcaa(listadoEmbalsesDetails.getCcaa());
                listadoEmbalses.setTipo(listadoEmbalsesDetails.getTipo());
                listadoEmbalses.setTitular(listadoEmbalsesDetails.getTitular());
                listadoEmbalses.setUso(listadoEmbalsesDetails.getUso());
                //listadoEmbalses.setCotaCoron(listadoEmbalsesDetails.getCotaCoron());
                //listadoEmbalses.setAltCimien(listadoEmbalsesDetails.getAltCimien());
                listadoEmbalses.setInforme(listadoEmbalsesDetails.getInforme());
                return RepositoriolistadoEmbalses.save(listadoEmbalses);
            });
    }

    public boolean deleteListadoEmbalses(Long id) {
        return RepositoriolistadoEmbalses.findById(id)
            .map(listadoEmbalses -> {
                RepositoriolistadoEmbalses.delete(listadoEmbalses);
                return true;
            })
            .orElse(false);
    }
}
