package com.ceiba.clase.servicio.testdatabuilder;

import com.ceiba.clase.modelo.entidad.Clase;

public class ClaseTestDataBuilder {
    private Long id;
    private Long docente;
    private Long curso;
    public ClaseTestDataBuilder(){
        docente = 1L;
        curso = 1L;
    }
    public ClaseTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }
    public ClaseTestDataBuilder conDocente(Long docente){
        this.docente = docente;
        return this;
    }
    public ClaseTestDataBuilder conCurso(Long curso){
        this.curso = curso;
        return this;
    }
    public Clase build(){
        return new Clase(id, docente, curso);
    }
}
