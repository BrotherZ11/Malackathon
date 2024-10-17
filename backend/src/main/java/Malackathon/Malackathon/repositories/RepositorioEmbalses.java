package Malackathon.Malackathon.repositories;

import java.util.List;
import java.util.Optional;

import Malackathon.Malackathon.entities.Embalses;
import org.springframework.data.jpa.repository.JpaRepository;
import Malackathon.Malackathon.entities.Embalses;

public interface RepositorioEmbalses extends JpaRepository<Embalses, Long> {
    
}
