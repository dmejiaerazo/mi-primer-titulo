package com.ceiba.horario.modelo.entidad;

import com.ceiba.dominio.utiles.DiasSemana;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Horario {

    private static final String SE_DEBE_INGRESAR_LA_CLASE = "Se debe ingresar la clase";
    private static final String SE_DEBE_INGRESAR_EL_DIA = "Se debe ingresar el día";
    private static final String SE_DEBE_INGRESAR_LA_HORA_DE_INICIO_DE_LA_CLASE = "Se debe ingresar la hora de inicio de la clase";
    private static final String SE_DEBE_INGRESAR_LA_CANTIDAD_DE_HORAS = "Se debe ingresar la cantidad de horas de la clase";
    private static final String LA_CANTIDAD_HORAS_DEBE_TENER_UN_VALOR_MENOR_O_IGUAL_A = "La cantidad de horas debe tener una valor menor o igual a 4";
    private static final String HORA_INICIO_FUERA_DEL_RANGO_PERMITIDO = "Hora inicio fuera del rango permitido";
    private static final int LONGITUD_MAXIMA_HORAS = 4;
    private static final int INICIA_JORNADA = 8;
    private static final int INICIA_DESCANSO = 12;
    private static final int FINZALIZA_DESCANSO = 14;
    private static final int FINALIZA_JORNADA = 18;

    private Long id;
    private Long clase;
    private DiasSemana dia;
    private LocalDateTime horaInicio;
    private int cantidadHoras;

    public Horario(Long id, Long clase, DiasSemana dia, LocalDateTime horaInicio, int cantidadHoras){
        validarObligatorio(clase, SE_DEBE_INGRESAR_LA_CLASE);
        validarObligatorio(dia, SE_DEBE_INGRESAR_EL_DIA);
        validarObligatorio(horaInicio, SE_DEBE_INGRESAR_LA_HORA_DE_INICIO_DE_LA_CLASE);
        validarObligatorio(cantidadHoras, SE_DEBE_INGRESAR_LA_CANTIDAD_DE_HORAS);
        validarMenor(cantidadHoras, LONGITUD_MAXIMA_HORAS, LA_CANTIDAD_HORAS_DEBE_TENER_UN_VALOR_MENOR_O_IGUAL_A);
        validarRangoFecha(horaInicio, INICIA_JORNADA, (INICIA_DESCANSO-cantidadHoras), FINZALIZA_DESCANSO,(FINALIZA_JORNADA-cantidadHoras), HORA_INICIO_FUERA_DEL_RANGO_PERMITIDO);

        this.id = id;
        this.clase = clase;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.cantidadHoras = cantidadHoras;
    }

}