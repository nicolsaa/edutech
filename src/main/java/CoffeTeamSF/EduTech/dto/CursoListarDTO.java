package CoffeTeamSF.EduTech.dto;

import java.util.List;

import CoffeTeamSF.EduTech.model.Contenido;



//Made by Vicente Aravena
//DTO específico para listar Cursos

public class CursoListarDTO {
    private String sigla;
    private String nombre;
    private String descripcion;
    private String profesor;
    private List<Contenido> contenidos;
    private List<InscripcionDTO> inscripciones;
    private List<EvaluacionDTO> evaluaciones;
    private List<ResenaListarDTO> resenas;

    public CursoListarDTO() {}

    public CursoListarDTO(
        String sigla,
        String nombre,
        String descripcion,
        String profesor,
        List<Contenido> contenidos,
        List<InscripcionDTO> inscripciones,
        List<EvaluacionDTO> evaluaciones,
        List<ResenaListarDTO> resenas
    ) {
        this.sigla = sigla;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesor = profesor;
        this.contenidos = contenidos;
        this.inscripciones = inscripciones;
        this.evaluaciones = evaluaciones;
        this.resenas = resenas;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    public List<ResenaListarDTO> getResenas() {
        return resenas;
    }

    public void setResenas(List<ResenaListarDTO> resenas) {
        this.resenas = resenas;
    }

    public List<EvaluacionDTO> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<EvaluacionDTO> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public List<InscripcionDTO> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionDTO> inscripciones) {
        this.inscripciones = inscripciones;
    }
}
