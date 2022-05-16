package com.ceiba.horario.comando.fabrica;

import com.ceiba.horario.comando.ComandoHorario;
import com.ceiba.horario.modelo.entidad.Horario;
import org.springframework.stereotype.Component;

@Component
public class FabricaHorario {

    public Horario crear(ComandoHorario comandoHorario){
        return new Horario(comandoHorario.getId(),
                comandoHorario.getClase(),
                comandoHorario.getDia(),
                comandoHorario.getHoraInicio(),
                comandoHorario.getCantidadHoras());
    }
}
