package Malackathon.Malackathon.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import Malackathon.Malackathon.entities.Embalse;

public interface RepositorioEmbalse extends JpaRepository<Embalse, Long> {
    
}
