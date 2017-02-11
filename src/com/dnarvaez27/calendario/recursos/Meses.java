package com.dnarvaez27.calendario.recursos;

public enum Meses
{
	ENERO( 0, "ENERO" ),
	FEBRERO( 1, "FEBRERO" ),
	MARZO( 2, "MARZO" ),
	ABRIL( 3, "ABRIL" ),
	MAYO( 4, "MAYO" ),
	JUNIO( 5, "JUNIO" ),
	JULIO( 6, "JULIO" ),
	AGOSTO( 7, "AGOSTO" ),
	SEPTIEMBRE( 8, "SEPTIEMBRE" ),
	OCTUBRE( 9, "OCTUBRE" ),
	NOVIEMBRE( 10, "NOVIEMBRE" ),
	DICIEMBRE( 11, "DICIEMBRE" );
	
	private int num;
	
	private String name;
	
	private Meses( int num, String name )
	{
		this.num = num;
		this.name = name;
	}
	
	public String getName( )
	{
		return name;
	}
	
	public int getNum( )
	{
		return num;
	}
	
	@Override
	public String toString( )
	{
		return name;
	}
}