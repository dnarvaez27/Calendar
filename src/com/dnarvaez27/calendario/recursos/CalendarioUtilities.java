package com.dnarvaez27.calendario.recursos;

import javax.swing.ImageIcon;

import com.dnarvaez27.calendario.Calendario;
import com.dnarvaez27.resources.Resources;

import java.awt.Color;
import java.awt.Cursor;

public class CalendarioUtilities
{
	public static class Colores
	{
		public static final Color BLANCO = new Color( 255, 255, 255 );
		
		public static final Color GRIS = new Color( 33, 33, 33 );
		
		public static final Color ROJO = new Color( 250, 0, 0 );
	}
	
	public static class Media
	{
		public static final Cursor DEFAULT = new Cursor( Cursor.DEFAULT_CURSOR );
		
		public static final Cursor HAND = new Cursor( Cursor.HAND_CURSOR );
		
		public static final ImageIcon LAST_ARROW = new ImageIcon( CalendarioUtilities.getResourceInstance( ).getPathFile( "Last.png" ) );
		
		public static final ImageIcon NEXT_ARROW = new ImageIcon( CalendarioUtilities.getResourceInstance( ).getPathFile( "Next.png" ) );
	}
	
	private static Resources resource;
	
	public static Resources getResourceInstance( )
	{
		if( resource == null )
		{
			resource = new Resources( Calendario.class, "recursos" );
		}
		return resource;
	}
}