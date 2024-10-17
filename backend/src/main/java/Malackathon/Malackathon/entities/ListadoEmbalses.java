package Malackathon.Malackathon.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ListadoEmbalses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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



    // toString
    @Override
    public String toString() {
        return "";
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        return o instanceof ListadoEmbalses e && e.getCodigo().equals(this.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}

