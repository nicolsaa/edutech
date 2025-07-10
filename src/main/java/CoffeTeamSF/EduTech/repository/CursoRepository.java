package CoffeTeamSF.EduTech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import CoffeTeamSF.EduTech.model.Curso;


//MADE BY VICENTE ARAVENA

public interface CursoRepository extends JpaRepository<Curso, String > {
    Optional<Curso> findByNombre(String nombre);

    
}
