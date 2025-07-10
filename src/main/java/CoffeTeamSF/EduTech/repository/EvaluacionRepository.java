package CoffeTeamSF.EduTech.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import CoffeTeamSF.EduTech.model.Evaluacion;


public interface EvaluacionRepository extends JpaRepository<Evaluacion, String> {


    /*METODO QUE BUSCA POR TITULO
     * BY NICOL SAAVEDRA
     */
    Evaluacion findByTitulo(String titulo);



}
