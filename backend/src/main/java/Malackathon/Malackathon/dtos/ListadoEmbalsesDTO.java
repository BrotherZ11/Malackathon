package Malackathon.Malackathon.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class ListadoEmbalsesDTO {
    private Long codigo;
    private String nombre;
    private String x;
    private String y;
    private String Demarc;
    private String Google;
    private String Openstreetmap;
    private String wikidata;
    private String provincia;
    private String ccaa;
    private String tipo;
    private String titular;
    private String uso;
    private String cota_coron;
    private String alt_cimien;
    private String informe;
}
