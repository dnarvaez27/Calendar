package com.dnarvaez27.calendario;

import java.util.Calendar;

public interface IEvento
{
	public abstract String getNombre( );

	public abstract Calendar getFecha( );
}