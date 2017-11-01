/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.transferObject;

import java.sql.Time;

/**
 *
 * @author Nicolas
 */
public class HorarioTO {
	private int id;
	private Time horaInicio;
	private Time horaFin;
	private int cantMaxRaciones;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public int getCantMaxRaciones() {
		return cantMaxRaciones;
	}

	public void setCantMaxRaciones(int cantMaxRaciones) {
		this.cantMaxRaciones = cantMaxRaciones;
	}

}
