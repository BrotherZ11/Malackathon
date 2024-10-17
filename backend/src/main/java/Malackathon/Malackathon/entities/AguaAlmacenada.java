package Malackathon.Malackathon.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AguaAlmacenada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long agua_actual;
    private String fecha;

    // toString
    @Override
    public String toString() {
        return "AguaAlmacenada{" +
                "id=" + id +
                ", agua_actual='" + agua_actual + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        return o instanceof AguaAlmacenada r && r.getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

