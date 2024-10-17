package Malackathon.Malackathon.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Embalses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ambito_nombre;
    private String embalse_nombre;
    private Long agua_total;
    private String electrico_flag;



    // toString
    @Override
    public String toString() {
        return "Embalses{" +
                "id=" + id +
                ", ambito='" + ambito_nombre + '\'' +
                ", nombre_embalse='" + embalse_nombre + '\'' +
                ", agua_total='" + agua_total + '\'' +
                ", electrico_flag='" + electrico_flag + '\'' +
                '}';
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        return o instanceof Embalses e && e.getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

