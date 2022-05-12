package com.ceiba.curso.puerto.dao;

import com.ceiba.curso.modelo.dto.DtoCurso;

import java.util.List;

public interface DaoCurso {

    /**
     * Permite listar cursos
     * @return los cursos
     */
    List<DtoCurso> listar();
}
