package Malackathon.Malackathon.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class EmbalsesDTO {
    private Long id;
    private String ambito_nombre;
    private String embalse_nombre;
    private Long agua_total;
    private String electrico_flag;
}
