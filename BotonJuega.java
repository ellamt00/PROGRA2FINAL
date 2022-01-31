package unileon.es;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class BotonJuega extends JButton implements ActionListener{

	private int fila;
	private int columna;
	private Pantalla pantalla;

	public BotonJuega(String mueble,int fila,int columna, Pantalla pantalla) {

		//LLamamos al constructor de nuestros papa 
		super(mueble);

		this.fila = fila;
		this.columna = columna;

		//añadimos la escucha
		addActionListener(this);

		this.pantalla=pantalla;

	}


	public void actionPerformed(ActionEvent arg0) {

		//pantalla.actualizaHabitacionesHechas();

		//llamamos a un metodo que compruebe si podemos movernos
		if(pantalla.realizarMovimiento(fila,columna,true)) {
		}else {

			//pantalla.eliminaUltimaHabitacion();
			JOptionPane.showInputDialog(null,"Movimiento no valido");
		}

	}


}