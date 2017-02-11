package com.dnarvaez27.calendario;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

import com.dnarvaez27.calendario.recursos.CalendarioUtilities.Colores;
import com.dnarvaez27.calendario.recursos.CalendarioUtilities.Media;
import com.dnarvaez27.calendario.recursos.Meses;
import com.dnarvaez27.componentes.buttons.ButtonTip;
import com.dnarvaez27.componentes.containers.ComboBoxColor;
import com.dnarvaez27.componentes.containers.dialogs.DialogoInput;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

public class Calendario extends JPanel
{
	public class DiaCalendario
	{
		private int dia;
		
		private int eventos;
		
		public DiaCalendario( int dia )
		{
			this.dia = dia;
			eventos = 0;
		}
		
		public void agregarEvento( )
		{
			eventos++;
		}
		
		public int getDia( )
		{
			return dia;
		}
		
		public int getEventos( )
		{
			return eventos;
		}
		
		@Override
		public String toString( )
		{
			return String.valueOf( dia );
		}
	}
	
	public class PanelDiaCalendario extends JPanel implements MouseListener
	{
		private static final long serialVersionUID = -8981765311395745058L;
		
		private DiaCalendario diaCalendario;
		
		private boolean hoy;
		
		private JLabel labelDia;
		
		private JPanel panelEventos;
		
		private JPanel panelSuperior;
		
		public PanelDiaCalendario( DiaCalendario dia )
		{
			diaCalendario = dia;
			
			setLayout( new BorderLayout( ) );
			setBorder( BorderFactory.createLineBorder( colorBackground != null ? colorBackground : Colores.GRIS ) );
			setBackground( dia != null ? Colores.BLANCO : ( colorBackground != null ? colorBackground : Colores.GRIS ) );
			
			panelSuperior = new JPanel( );
			panelSuperior.setBackground( colorBackground != null ? colorBackground : Colores.GRIS );
			labelDia = new JLabel( dia != null ? String.valueOf( dia.getDia( ) ) : " " );
			labelDia.setForeground( Colores.BLANCO );
			labelDia.setHorizontalAlignment( SwingConstants.CENTER );
			panelSuperior.add( labelDia );
			
			panelEventos = new JPanel( );
			panelEventos.setLayout( new BorderLayout( ) );
			panelEventos.setBackground( null );
			
			if( diaCalendario != null )
			{
				setCursor( Media.HAND );
				addMouseListener( this );
			}
			
			add( panelSuperior, BorderLayout.NORTH );
			add( panelEventos, BorderLayout.CENTER );
		}
		
		public void actualizar( )
		{
			actualizar( diaCalendario );
		}
		
		public void actualizar( DiaCalendario diaCalendario )
		{
			this.diaCalendario = diaCalendario;
			
			setBorder( BorderFactory.createLineBorder( colorBackground != null ? colorBackground : Colores.GRIS ) );
			setBackground( this.diaCalendario != null ? Colores.BLANCO : ( colorBackground != null ? colorBackground : Colores.GRIS ) );
			panelSuperior.setBackground( colorBackground != null ? colorBackground : Colores.GRIS );
			setBackground( this.diaCalendario != null ? Colores.BLANCO : ( colorBackground != null ? colorBackground : Colores.GRIS ) );
			
			if( this.diaCalendario != null )
			{
				boolean diaHoy = this.diaCalendario.getDia( ) == Calendar.getInstance( ).get( Calendar.DAY_OF_MONTH );
				boolean mesHoy = mesLocal == Calendar.getInstance( ).get( Calendar.MONTH );
				boolean yearHoy = yearLocal == Calendar.getInstance( ).get( Calendar.YEAR );
				
				if( diaHoy && mesHoy && yearHoy )
				{
					hoy = true;
					setBackground( colorNotification );
					panelSuperior.setBackground( colorNotification );
				}
				
				if( this.diaCalendario.eventos != 0 )
				{
					panelEventos.removeAll( );
					JLabel num = new JLabel( "" + ( diaCalendario.getEventos( ) != 0 ? diaCalendario.getEventos( ) : "" ) )
					{
						private static final long serialVersionUID = -6415049802198157128L;
						
						@Override
						public void paint( Graphics g )
						{
							int w = getWidth( ) / 2;
							int x = ( getWidth( ) / 2 ) - ( w / 2 );
							int y = ( getHeight( ) / 2 ) - ( w / 2 );
							
							Ellipse2D.Double e = new Ellipse2D.Double( x, y, w, w );
							
							if( hoy )
							{
								g.setColor( colorBackground != null ? colorBackground : Colores.GRIS );
							}
							else
							{
								g.setColor( colorNotification != null ? colorNotification : Colores.ROJO );
							}
							( ( Graphics2D ) g ).fill( e );
							
							super.paint( g );
						}
					};
					num.setFont( getFont( ).deriveFont( Font.BOLD ) );
					num.setHorizontalAlignment( SwingConstants.CENTER );
					num.setForeground( Colores.BLANCO );
					panelEventos.add( num, BorderLayout.CENTER );
				}
			}
		}
		
		public DiaCalendario getDiaCalendario( )
		{
			return diaCalendario;
		}
		
		@Override
		public void mouseClicked( MouseEvent e )
		{
		}
		
		@Override
		public void mouseEntered( MouseEvent e )
		{
		}
		
		@Override
		public void mouseExited( MouseEvent e )
		{
		}
		
		@Override
		public void mousePressed( MouseEvent e )
		{
		}
		
		@Override
		public void mouseReleased( MouseEvent e )
		{
			diaLocal = diaCalendario.getDia( );
		}
		
		public void setDia( DiaCalendario diaCalendario )
		{
			this.diaCalendario = diaCalendario;
		}
		
		public void setHoverColors( Color bg )
		{
			setBackground( bg );
			labelDia.setForeground( bg );
		}
	}
	
	private class MouseListenerShadow implements MouseListener
	{
		@Override
		public void mouseClicked( MouseEvent e )
		{
			
		}
		
		@Override
		public void mouseEntered( MouseEvent e )
		{
			
		}
		
		@Override
		public void mouseExited( MouseEvent e )
		{
			
		}
		
		@Override
		public void mousePressed( MouseEvent e )
		{
			
		}
		
		@Override
		public void mouseReleased( MouseEvent e )
		{
			for( PanelDiaCalendario[ ] pDC : panelCalendario )
			{
				for( PanelDiaCalendario panelDiaCalendario : pDC )
				{
					customPanelDiaCalendario( panelDiaCalendario, panelDiaCalendario.equals( e.getSource( ) ) );
				}
			}
		}
	}
	
	private class PanelNavegacion extends JPanel
	{
		private class ActionListenerNavegacion implements ActionListener
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				String comando = e.getActionCommand( );
				if( comando.equals( SIGUIENTE ) )
				{
					if( mesLocal < Calendar.DECEMBER )
					{
						configDate( yearLocal, ++mesLocal );
					}
					else
					{
						configDate( ++yearLocal, ( mesLocal = 0 ) );
					}
					actualizar( );
				}
				else
				{
					if( mesLocal > 0 )
					{
						configDate( yearLocal, --mesLocal );
					}
					else
					{
						configDate( --yearLocal, ( mesLocal = 11 ) );
					}
					actualizar( );
				}
			}
		}
		
		private static final long serialVersionUID = -8286161843571423109L;
		
		private ActionListenerNavegacion actionListenerNavegacion;
		
		private ButtonTip btnAnterior;
		
		private ButtonTip btnMes;
		
		private ButtonTip btnSiguiente;
		
		private Popup popup;
		
		public PanelNavegacion( )
		{
			setBackground( null );
			setLayout( new BorderLayout( ) );
			
			btnMes = new ButtonTip( Meses.values( )[ mesLocal ].getName( ) );
			btnMes.setBackground( null );
			btnMes.setOpaque( true );
			btnMes.setForeground( Colores.BLANCO );
			btnMes.setFocusPainted( false );
			btnMes.setBorderPainted( false );
			btnMes.setContentAreaFilled( false );
			btnMes.setOpaque( true );
			btnMes.addActionListener( new ActionListener( )
			{
				@Override
				public void actionPerformed( ActionEvent e )
				{
					// showPopup( );
				}
			} );
			
			actionListenerNavegacion = new ActionListenerNavegacion( );
			
			btnSiguiente = new ButtonTip( Media.NEXT_ARROW );
			btnSiguiente.setBackground( null );
			btnSiguiente.setFocusPainted( false );
			btnSiguiente.setContentAreaFilled( false );
			btnSiguiente.setOpaque( true );
			btnSiguiente.setBorderPainted( false );
			btnSiguiente.setActionCommand( SIGUIENTE );
			btnSiguiente.addActionListener( actionListenerNavegacion );
			
			btnAnterior = new ButtonTip( Media.LAST_ARROW );
			btnAnterior.setBackground( null );
			btnAnterior.setFocusPainted( false );
			btnAnterior.setContentAreaFilled( false );
			btnAnterior.setOpaque( true );
			btnAnterior.setBorderPainted( false );
			btnAnterior.setActionCommand( ANTERIOR );
			btnAnterior.addActionListener( actionListenerNavegacion );
			
			add( btnAnterior, BorderLayout.WEST );
			add( btnMes, BorderLayout.CENTER );
			add( btnSiguiente, BorderLayout.EAST );
		}
		
		public void removeActionPerformedNav( ActionListener listener )
		{
			btnAnterior.removeActionListener( listener );
			btnSiguiente.removeActionListener( listener );
		}
		
		public void setActionPerformedNav( ActionListener list, boolean removeSuper )
		{
			btnMes.addActionListener( new ActionListener( )
			{
				@Override
				public void actionPerformed( ActionEvent e )
				{
					// showPopup( );
				}
			} );
			btnAnterior.addActionListener( list );
			btnSiguiente.addActionListener( list );
			
			if( removeSuper )
			{
				removeActionPerformedNav( actionListenerNavegacion );
			}
		}
		
		@SuppressWarnings( "unused" )
		@Deprecated
		public void showPopupi( )
		{
			JPanel panelPopup = new JPanel( );
			panelPopup.setLayout( new BorderLayout( ) );
			
			if( popup == null )
			{
				ComboBoxColor<Meses> boxColor = new ComboBoxColor<>( Meses.values( ) );
				boxColor.setPreferredSize( new Dimension( btnMes.getWidth( ), 50 ) );
				
				panelPopup.add( boxColor );
			}
			Point point = btnMes.getLocationOnScreen( );
			int x = point.x /* + ( ( int ) btnMes.getPreferredSize( ).getWidth( ) / 2 ) */;
			int y = point.y + ( int ) btnMes.getPreferredSize( ).getHeight( );
			popup = new PopupFactory( ).getPopup( this, panelPopup, x, y );
			popup.show( );
		}
	}
	
	private class PanelYear extends JPanel implements ActionListener
	{
		private static final long serialVersionUID = 2106311395003057516L;
		
		private ButtonTip btnYear;
		
		public PanelYear( )
		{
			btnYear = new ButtonTip( String.valueOf( yearLocal ) );
			btnYear.addActionListener( this );
			btnYear.setFont( getFont( ).deriveFont( Font.BOLD, ( float ) ( getFont( ).getSize( ) * 1.5 ) ) );
			btnYear.setBackground( null );
			btnYear.setForeground( colorForeground );
			
			add( btnYear );
			
			setBackground( null );
		}
		
		@Override
		public void actionPerformed( ActionEvent e )
		{
			DialogoInput dInput = new DialogoInput( "Año", "Ingrese el año", colorBackground, true );
			dInput.setNumeric( true, false );
			if( dInput.mostrarDialogo( ) )
			{
				btnYear.setText( dInput.getInput( ) );
				configDate( Integer.parseInt( dInput.getInput( ) ), mesLocal );
				actualizar( );
			}
		}
		
		public void actualizarYear( )
		{
			btnYear.setText( String.valueOf( yearLocal ) );
		}
	}
	
	private class SemanaCalendario
	{
		private DiaCalendario[ ] diasSemana;
		
		public SemanaCalendario( DiaCalendario[ ] diasSemana )
		{
			this.diasSemana = diasSemana;
		}
		
		public void actualizar( )
		{
			for( PanelDiaCalendario panelDiaCalendario : diasCalendario )
			{
				panelDiaCalendario.actualizar( );
			}
		}
	}
	
	public static final String ANTERIOR = "Anterior";
	
	public static final String SIGUIENTE = "Siguiente";
	
	private static final long serialVersionUID = -3058531262436690733L;
	
	private Color colorBackground;
	
	private Color colorForeground;
	
	private Color colorNotification;
	
	private JPanel contenedor;
	
	private int diaLocal;
	
	private ArrayList<PanelDiaCalendario> diasCalendario;
	
	private String[ ] labels;
	
	private int mesLocal;
	
	private MouseListener mouseListener;
	
	private MouseListenerShadow mouseListenerShadow;
	
	private PanelDiaCalendario[ ][ ] panelCalendario;
	
	private JPanel panelLabels;
	
	private PanelNavegacion panelNavegacion;
	
	private PanelYear panelYear;
	
	private SemanaCalendario[ ] semanasCalendario;
	
	private int yearLocal;
	
	public Calendario( )
	{
		this( false );
	}
	
	public Calendario( boolean wYear )
	{
		setLayout( new BorderLayout( ) );
		setBackground( null );
		
		Calendar c = Calendar.getInstance( );
		mesLocal = c.get( Calendar.MONTH );
		yearLocal = c.get( Calendar.YEAR );
		
		diasCalendario = new ArrayList<>( );
		
		mouseListenerShadow = new MouseListenerShadow( );
		
		contenedor = new JPanel( );
		contenedor.setBorder( null );
		contenedor.setBackground( null );
		
		panelLabels = new JPanel( );
		panelLabels.setLayout( new GridLayout( 1, 7 ) );
		panelLabels.setBackground( null );
		
		labels = new String[ ]
		{
				"D",
				"L",
				"M",
				"X",
				"J",
				"V",
				"S"
		};
		
		// for( String string : labels )
		// {
		// JLabel label = new JLabel( string );
		// label.setHorizontalAlignment( SwingConstants.CENTER );
		// if( colorForeground != null )
		// {
		// label.setForeground( colorForeground );
		// }
		// panelLabels.add( label );
		// }
		
		JPanel panelSuperior = new JPanel( );
		panelSuperior.setBackground( null );
		panelSuperior.setLayout( new BorderLayout( ) );
		panelSuperior.setBackground( null );
		
		panelNavegacion = new PanelNavegacion( );
		
		panelSuperior.add( panelNavegacion, BorderLayout.CENTER );
		panelSuperior.add( panelLabels, BorderLayout.SOUTH );
		if( wYear )
		{
			panelYear = new PanelYear( );
			panelSuperior.add( panelYear, BorderLayout.NORTH );
		}
		
		add( panelSuperior, BorderLayout.NORTH );
		add( contenedor, BorderLayout.CENTER );
		
		configDate( yearLocal, mesLocal );
	}
	
	public void actualizar( )
	{
		panelYear.actualizarYear( );
		for( SemanaCalendario semanaCalendario : semanasCalendario )
		{
			if( semanaCalendario != null )
			{
				semanaCalendario.actualizar( );
			}
		}
	}
	
	public void agregarEvento( IEvento evento )
	{
		for( PanelDiaCalendario panelDiaCalendario : diasCalendario )
		{
			if( ( panelDiaCalendario.diaCalendario != null ) && ( panelDiaCalendario.diaCalendario.dia == evento.getFecha( ).get( Calendar.DAY_OF_MONTH ) ) )
			{
				panelDiaCalendario.diaCalendario.agregarEvento( );
				panelDiaCalendario.setDia( panelDiaCalendario.diaCalendario );
			}
		}
	}
	
	public void eventListener( MouseListener mouseListener )
	{
		this.mouseListener = mouseListener;
		
		for( PanelDiaCalendario[ ] pDC : panelCalendario )
		{
			for( PanelDiaCalendario panelDiaCalendario : pDC )
			{
				if( panelDiaCalendario != null )
				{
					panelDiaCalendario.addMouseListener( mouseListener );
					panelDiaCalendario.addMouseListener( mouseListenerShadow );
				}
			}
		}
	}
	
	public int getDay( )
	{
		return diaLocal;
	}
	
	public int getMonth( )
	{
		return mesLocal;
	}
	
	public Calendar getSelectedDate( )
	{
		Calendar c = Calendar.getInstance( );
		c.set( Calendar.DAY_OF_MONTH, diaLocal );
		c.set( Calendar.MONTH, mesLocal );
		c.set( Calendar.YEAR, yearLocal );
		return c;
	}
	
	public int getYear( )
	{
		return yearLocal;
	}
	
	public void removeActionListenerNavegacion( ActionListener listener )
	{
		panelNavegacion.removeActionPerformedNav( listener );
	}
	
	public void removeMouseListenerShadow( )
	{
		for( PanelDiaCalendario[ ] pDC : panelCalendario )
		{
			for( PanelDiaCalendario panelDiaCalendario : pDC )
			{
				if( panelDiaCalendario != null )
				{
					panelDiaCalendario.removeMouseListener( mouseListenerShadow );
				}
			}
		}
	}
	
	public void selectDay( int day )
	{
		Calendar c = Calendar.getInstance( );
		c.set( Calendar.MONTH, mesLocal );
		c.set( Calendar.YEAR, yearLocal );
		c.set( Calendar.DAY_OF_MONTH, 1 );
		double fD = ( c.get( Calendar.DAY_OF_WEEK ) ) - 1;
		c.set( Calendar.DAY_OF_MONTH, day );
		int week = ( int ) Math.ceil( ( day + fD ) / 7.0 ) - 1;
		customPanelDiaCalendario( panelCalendario[ week ][ c.get( Calendar.DAY_OF_WEEK ) - 1 ], true );
	}
	
	public void setActionListenerNavegacion( ActionListener listener, boolean removeSuper )
	{
		panelNavegacion.setActionPerformedNav( listener, removeSuper );
	}
	
	public void setBackgroundColor( Color color )
	{
		colorBackground = color;
	}
	
	public void setColorNotification( Color color )
	{
		colorNotification = color;
	}
	
	public void setDate( int year, Meses month )
	{
		configDate( year, month.getNum( ) );
	}
	
	public void setForegroundColor( Color color )
	{
		colorForeground = color;
		
		panelLabels.removeAll( );
		for( String string : labels )
		{
			JLabel label = new JLabel( string );
			label.setHorizontalAlignment( SwingConstants.CENTER );
			if( colorForeground != null )
			{
				label.setForeground( colorForeground );
			}
			panelLabels.add( label );
		}
	}
	
	public void setMonth( Meses mes )
	{
		setDate( Calendar.getInstance( ).get( Calendar.YEAR ), mes );
	}
	
	public void setYear( int year )
	{
		setDate( year, Meses.values( )[ Calendar.getInstance( ).get( Calendar.MONTH ) ] );
	}
	
	private void configDate( int year, int mes )
	{
		mesLocal = mes;
		yearLocal = year;
		
		Calendar test = Calendar.getInstance( );
		test.set( Calendar.YEAR, year );
		test.set( Calendar.MONTH, mes );
		test.set( Calendar.DAY_OF_MONTH, 1 );
		
		int diaIni = test.get( Calendar.DAY_OF_WEEK );
		int maxDay = test.getActualMaximum( Calendar.DAY_OF_MONTH );
		double div = ( ( maxDay + diaIni ) - 1 ) / 7.0;
		int semanas = ( int ) Math.ceil( div );
		// System.out.println( "MAX: " + maxDay + " SEM: " + semanas + " INI: " + diaIni + " DIV: " + div );
		
		semanasCalendario = new SemanaCalendario[ semanas ];
		
		panelCalendario = new PanelDiaCalendario[ semanas ][ 7 ];
		
		// Sunday = 0
		int diaActual = 1;
		boolean inicio = false;
		boolean termino = false;
		for( int i = 0; ( i < semanas ) && !termino; i++ )
		{
			DiaCalendario[ ] diasSemana = new DiaCalendario[ 7 ];
			for( int j = 0; ( j < diasSemana.length ) && !termino; j++ )
			{
				if( diaActual == ( maxDay + 1 ) )
				{
					termino = true;
				}
				else
				{
					if( ( j == ( diaIni - 1 ) ) && !inicio )
					{
						inicio = true;
						diasSemana[ j ] = new DiaCalendario( diaActual );
						diaActual++;
					}
					else if( !inicio )
					{
						diasSemana[ j ] = null;
					}
					else
					{
						diasSemana[ j ] = new DiaCalendario( diaActual );
						diaActual++;
					}
				}
			}
			semanasCalendario[ i ] = new SemanaCalendario( diasSemana );
		}
		
		contenedor.removeAll( );
		contenedor.setLayout( new GridLayout( semanas, 7 ) );
		
		for( int i = 0; i < semanasCalendario.length; i++ )
		{
			for( int j = 0; j < semanasCalendario[ i ].diasSemana.length; j++ )
			{
				DiaCalendario diaCalendario = semanasCalendario[ i ].diasSemana[ j ];
				panelCalendario[ i ][ j ] = new PanelDiaCalendario( diaCalendario );
				diasCalendario.add( panelCalendario[ i ][ j ] );
				contenedor.add( panelCalendario[ i ][ j ] );
			}
		}
		
		panelNavegacion.btnMes.setText( Meses.values( )[ mes ].getName( ) );
		
		eventListener( mouseListener );
	}
	
	private void customPanelDiaCalendario( PanelDiaCalendario panelDiaCalendario, boolean on )
	{
		boolean source = on;
		boolean notNull = panelDiaCalendario.diaCalendario != null;
		boolean today = panelDiaCalendario.hoy;
		if( source && notNull && !today )
		{
			if( panelDiaCalendario.getBackground( ) != Colores.GRIS )
			{
				panelDiaCalendario.setHoverColors( Colores.GRIS );
			}
		}
		else if( notNull && !today )
		{
			if( panelDiaCalendario.getBackground( ) != Colores.BLANCO )
			{
				panelDiaCalendario.setHoverColors( Colores.BLANCO );
			}
		}
	}
}