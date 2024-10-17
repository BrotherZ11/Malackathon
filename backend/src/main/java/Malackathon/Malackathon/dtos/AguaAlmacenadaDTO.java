package Malackathon.Malackathon.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class AguaAlmacenadaDTO {
    private Long id;
    private Long agua_actual;
    private String fecha;
}
