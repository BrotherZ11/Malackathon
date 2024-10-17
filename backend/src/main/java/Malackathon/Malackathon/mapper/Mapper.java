package Malackathon.Malackathon.mapper;

import Malackathon.Malackathon.dtos.*;
import Malackathon.Malackathon.entities.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Mapper {
    /**
     * Convierte una AguaAlmacenada a un DTO AguaAlmacenadaDTO
     * @param AguaAlmacenada
     * @param AguaAlmacenadaUriBuilder
     * @param EmbalsesUriBuilder
     * @return AguaAlmacenadaDTO
     */
    public static AguaAlmacenadaDTO toAguaAlmacenadaDTO(AguaAlmacenada AguaAlmacenada, Function<Long, URI> AguaAlmacenadaUriBuilder,
                                        Function<Long, URI> EmbalsesUriBuilder) {
        return AguaAlmacenadaDTO.builder()
                .id(AguaAlmacenada.getId())
                .nombre(AguaAlmacenada.getNombre())
                .descripcion(AguaAlmacenada.getDescripcion())
                .observaciones(AguaAlmacenada.getObservaciones())
                .Embalsess(AguaAlmacenada.getEmbalsess().stream()
                        .map(i->toEmbalsesDTO(i, EmbalsesUriBuilder))
                        .toList())
                .links(Links.builder()
                        .self(AguaAlmacenadaUriBuilder.apply(AguaAlmacenada.getId()))
                        .build())
                .build();
    }

    /**
     * Convierte EjercicicioEnAguaAlmacenada a EmbalsesDTO, conviertiendo su Embalses a EmbalsesDTO
     * @param Embalses
     * @param EmbalsesUriBuilder
     * @return
     */
    public static EmbalsesDTO toEmbalsesDTO (Embalses Embalses, Function<Long, URI> EmbalsesUriBuilder){
        return EmbalsesDTO.builder()
                .series(Embalses.getSeries())
                .repeticiones(Embalses.getRepeticiones())
                .duracionMinutos(Embalses.getDuracionMinutos())
                .Embalses(toEmbalsesDTO(Embalses.getEmbalses(), EmbalsesUriBuilder))
                .build();
    }

    /**
     * Convierte EjercicicioEnAguaAlmacenada a EmbalsesDTO, conviertiendo su Embalses a EmbalsesDTO
     * @param Embalses
     * @return
     */
    public static Embalses toEmbalses (EmbalsesDTO Embalses, Integer idEntrenador){
        return Embalses.builder()
                .series(Embalses.getSeries())
                .repeticiones(Embalses.getRepeticiones())
                .duracionMinutos(Embalses.getDuracionMinutos())
                .Embalses(toEmbalses(Embalses.getEmbalses(), idEntrenador))
                .build();
    }

    /**
     * Convierte una AguaAlmacenadaNuevaDTO a una AguaAlmacenada
     * @param AguaAlmacenadaNuevaDTO
     * @return AguaAlmacenada
     */
    public  static AguaAlmacenada toAguaAlmacenada(AguaAlmacenadaNuevaDTO AguaAlmacenadaNuevaDTO, Integer idEntrenador) {
        List<Embalses> Embalsess = null;
        if (AguaAlmacenadaNuevaDTO.getEmbalsess() != null)
            AguaAlmacenadaNuevaDTO.getEmbalsess().stream().map(e -> Mapper.toEmbalses(e,idEntrenador)).toList();
        else
            Embalsess = new ArrayList<>(5);
        return AguaAlmacenada.builder()
                .nombre(AguaAlmacenadaNuevaDTO.getNombre())
                .descripcion(AguaAlmacenadaNuevaDTO.getDescripcion())
                .observaciones(AguaAlmacenadaNuevaDTO.getObservaciones())
                .Embalsess(Embalsess)
                .build();
    }

    /**
     * Convierte un Embalses a un EmbalsesDTO
     * @param Embalses
     * @param uriBuilder
     * @return EmbalsesDTO
     */
    public static EmbalsesDTO toEmbalsesDTO(Embalses Embalses, Function<Long, URI> uriBuilder) {
        return EmbalsesDTO.builder()
                .id(Embalses.getId())
                .nombre(Embalses.getNombre())
                .descripcion(Embalses.getDescripcion())
                .observaciones(Embalses.getObservaciones())
                .tipo(Embalses.getTipo())
                .musculosTrabajados(Embalses.getMusculosTrabajados())
                .material(Embalses.getMaterial())
                .dificultad(Embalses.getDificultad())
                .multimedia(Embalses.getMultimedia())
                .links(Links.builder()
                            .self(uriBuilder.apply(Embalses.getId()))
                            .build())  //Basicamente crea un nuevo Links añadiendole la URI obtenido tras aplicar la funcion uriBuilder
                .build();
    }
    /**
     * Convierte un EmbalsesNuevoDTO a un Embalses
     * @param EmbalsesNuevoDTO
     * @return Embalses
     */
    public static Embalses toEmbalses(EmbalsesNuevoDTO EmbalsesNuevoDTO, Integer idEntrenador) {
        return Embalses.builder()
                .nombre(EmbalsesNuevoDTO.getNombre())
                .descripcion(EmbalsesNuevoDTO.getDescripcion())
                .observaciones(EmbalsesNuevoDTO.getObservaciones())
                .tipo(EmbalsesNuevoDTO.getTipo())
                .musculosTrabajados(EmbalsesNuevoDTO.getMusculosTrabajados())
                .material(EmbalsesNuevoDTO.getMaterial())
                .dificultad(EmbalsesNuevoDTO.getDificultad())
                .multimedia(EmbalsesNuevoDTO.getMultimedia())
                .entrenador(idEntrenador)
                .build();
    }
    public static Embalses toEmbalses(EmbalsesDTO EmbalsesNuevoDTO, Integer idEntrenador) {
        return Embalses.builder()
                .nombre(EmbalsesNuevoDTO.getNombre())
                .descripcion(EmbalsesNuevoDTO.getDescripcion())
                .observaciones(EmbalsesNuevoDTO.getObservaciones())
                .tipo(EmbalsesNuevoDTO.getTipo())
                .musculosTrabajados(EmbalsesNuevoDTO.getMusculosTrabajados())
                .material(EmbalsesNuevoDTO.getMaterial())
                .dificultad(EmbalsesNuevoDTO.getDificultad())
                .multimedia(EmbalsesNuevoDTO.getMultimedia())
                .entrenador(idEntrenador)
                .build();
    }

}
