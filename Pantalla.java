package unileon.es;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pantalla extends JFrame implements ActionListener {

	private BorderLayout border;
	private JMenuBar barra;
	private JMenu Fichero;
	private JMenu Herramientas;
	private JMenu Jugar;
	private JMenu Ayuda;
	private JMenuItem Crear;
	private JMenuItem Guardar;
	private JMenuItem GuardarComo;
	private JMenuItem CargarArchivo;
	private JMenuItem JugarManual;
	private JMenuItem JugarAutom;
	private JMenuItem Rehacer;
	private JMenuItem Deshacer;
	private JMenuItem ResumenJuego;
	private JMenuItem EmpezarAJugar;
	private String name = "";
	private int Filas;
	private int Columnas;
	private String[][] matriz;
	private String[][] matrizOriginal;
	private JPanel matrizGrafica;
	private String camino = "";
	private ArrayList<String[][]> habitacionHecha;
	private ArrayList<String> caminos;
	private int posicionAccion;
	private boolean estoyJugando;
	public boolean actualizado =true;

	@SuppressWarnings("static-access")
	public Pantalla() {

		this.setTitle("Moviendo Cajas");
		this.setBackground(Color.gray);
		this.setSize(500, 600);

		// Para darle funcionalidad al boton de cerrar.
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		habitacionHecha = new ArrayList<String[][]>();
		caminos = new ArrayList<String>();
		border = new BorderLayout();
		this.setLayout(border);

		barra = new JMenuBar();
		this.setJMenuBar(barra);

		matrizGrafica = new JPanel();

		// Inicializamos las Variables
		Fichero = new JMenu("Fichero");
		Jugar = new JMenu("Jugar");
		Ayuda = new JMenu("Ayuda");
		Herramientas = new JMenu("Herramientas");
		Crear = new JMenuItem("Crear");
		Guardar = new JMenuItem("Guardar");
		GuardarComo = new JMenuItem("Guardar Como");
		CargarArchivo = new JMenuItem("Cargar Archivo");
		JugarManual = new JMenuItem("Jugar Manual");
		Rehacer = new JMenuItem("Hacer");
		Deshacer = new JMenuItem("Deshacer");
		JugarAutom = new JMenuItem("Jugar Automatico");
		ResumenJuego = new JMenuItem("Resumen_Juego");
		EmpezarAJugar = new JMenuItem("Empezar_a_Jugar");

		// AÃ±adimos cada opcion a su Menu.
		barra.add(Fichero);
		barra.add(Jugar);
		barra.add(Ayuda);
		barra.add(Herramientas);

		Fichero.add(Crear);
		Fichero.add(Guardar);
		Fichero.add(GuardarComo);
		Fichero.add(CargarArchivo);
		Jugar.add(JugarManual);
		Jugar.add(JugarAutom);
		Herramientas.add(Rehacer);
		Herramientas.add(Deshacer);
		Ayuda.add(ResumenJuego);
		Ayuda.add(EmpezarAJugar);

		// Damos funcionalidad a los botones.
		Crear.addActionListener(this);
		Guardar.addActionListener(this);
		GuardarComo.addActionListener(this);
		CargarArchivo.addActionListener(this);
		Ayuda.addActionListener(this);
		JugarManual.addActionListener(this);
		JugarAutom.addActionListener(this);
		Rehacer.addActionListener(this);
		Deshacer.addActionListener(this);
		ResumenJuego.addActionListener(this);
		EmpezarAJugar.addActionListener(this);

		// Para poder cambiar el color de los mensajes
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", Color.yellow);
		UI.put("Panel.background", Color.cyan);

		// Para no guardar los paneles que se crean
		this.setLocationRelativeTo(null);
	}

	/*
	 * Metodo para darle funcionalidad a los botones
	 */
	public void actionPerformed(ActionEvent arg0) {

		// Para dar color a los botones
		Container fondos = this.getContentPane();

		// Boton para pedir tanto filas como columnas
		if (arg0.getSource() == Crear) {

			fondos.setBackground(new Color(200, 150, 100));

			// Pedimos Filas
			String filas = JOptionPane.showInputDialog("Introduzca Filas: ");

			try {

				Filas = Integer.parseInt(filas);

			} catch (Exception e) {

				System.out.println("No se han introducido las filas");

			}

			// Pedimos Columnas
			String columnas = JOptionPane.showInputDialog("Introduzca 	Columnas : ");
			try {

				Columnas = Integer.parseInt(columnas);

			} catch (Exception e) {

				System.out.println("No se han introducido las columnas");

			}

			// Controlamos que filas y columnas cumplan el rango
			if (Filas >= 20 || Columnas >= 20) {

				// lanzamos excpetion
			} else {
				inicializarMatriz();
			}

		}

		// Boton para guardar el fichero
		if (arg0.getSource() == Guardar) {
			fondos.setBackground(new Color(200, 150, 100));
			guardar();
		}

		if (arg0.getSource() == GuardarComo) {
			fondos.setBackground(new Color(200, 150, 100));
			guardarComo();
		}

		if (arg0.getSource() == CargarArchivo) {
			fondos.setBackground(new Color(200, 150, 100));
			this.matriz = leer();
			muestraMatriz();
		}

		if (arg0.getSource() == ResumenJuego) {
			JOptionPane.showMessageDialog(null,
					"Juego 'Moviendo Cajas'. \n Juego interactivo que trata de llevar una caja(#) a traves "
							+ "de una habitacion y mediante un robot(@), a su estado objetivo(!).\n "
							+ "Cuando todas las cajas esten encima de sus objetivos(+) , el juego habrÃ¡ terminado y habrÃ¡s ganado.\n"
							+ "Â¡OJO!, se trata de buscar el camino mas corto para llevar las cajas a sus objetivos.\n"
							+ "Y cuidado que no es tan facil... En la habitacion te podrÃ¡s encontrar con obstaculos tales como muros(1).\n"
							+ "Espero que este mensaje te haya sido de ayuda."
							+ " Comienza a jugar creando una habitacion o cargando una "
							+ "ya establecida.\n \n MUCHA SUERTE!!");
		}

		if (arg0.getSource() == EmpezarAJugar) {
			JOptionPane.showMessageDialog(null,
					"Una vez hayas considerado que tu habitacion esta creada o hayas cargado una: \n "
							+ "Seleccione la opcion de Juego Manual o Juego Automatico para comenzar a jugar");
		}

		if (arg0.getSource() == JugarManual) {

			if (matriz == null) {
				JOptionPane.showMessageDialog(null, "Para jugar primero crea/carga habitacion");
			} else {
				jugarManu();
			}

		}

		if (arg0.getSource() == JugarAutom) {

			if (matriz == null) {
				JOptionPane.showMessageDialog(null, "Para jugar primero crea/carga habitacion");
			} else {
				jugarAut();
			}
		}

		if (arg0.getSource() == Rehacer) {
			reHacer();

		}

		if (arg0.getSource() == Deshacer) {
			deshacer();
		}
	}

	private void reHacer() {
		if (posicionAccion == habitacionHecha.size()) {
			JOptionPane.showMessageDialog(null, "No se puede rehacer la accion");
		} else {
			posicionAccion++;
			matriz = habitacionHecha.get(posicionAccion);
			if (estoyJugando) {
				camino = caminos.get(posicionAccion);
				cargaPanelJuego();
			} else {
				muestraMatriz();
			}
		}
	}

	private void deshacer() {

		if (posicionAccion == 0) {
			JOptionPane.showMessageDialog(null, "No se puede Deshacer la accion");
		}
		if(!actualizado) {
			actualizaHabitacionesHechas();
			posicionAccion--;
			actualizado = true;
		}
		posicionAccion--;
		matriz = habitacionHecha.get(posicionAccion);

		if (estoyJugando) {
			cargaPanelJuego();
			camino = caminos.get(posicionAccion);
		} else {
			muestraMatriz();
		}

	}

	public void actualizaHabitacionesHechas() {
		if (posicionAccion != habitacionHecha.size()) {

			borrarHabitaciones(posicionAccion);
			if (estoyJugando) {
				borraCamino(posicionAccion);
			}
		}

		habitacionHecha.add(clonarHabitacion(matriz));
		if (estoyJugando) {
			caminos.add(camino);
		}
		posicionAccion++;

	}

	private void borraCamino(int posicionAccion) {
		for (int i = posicionAccion; i <= caminos.size(); i++) {
			caminos.remove(posicionAccion);
		}
	}

	private void borrarHabitaciones(int posicion) {

		for (int i = posicion; i <= habitacionHecha.size(); i++) {
			habitacionHecha.remove(posicion);
		}
	}

	private String[][] clonarHabitacion(String[][] matriz) {
		String[][] clonado = new String[Filas][Columnas];

		for (int i = 0; i < Filas; i++) {
			for (int j = 0; j < Columnas; j++) {
				clonado[i][j] = matriz[i][j];
			}
		}
		return clonado;
	}

	/*
	 * 
	 * Metodo que juega de manera automatica
	 * 
	 */
	private void jugarAut() {

		// Pedimos el tiempo de ejecucion
		long time = Long.parseLong(JOptionPane.showInputDialog("El tiempo en segundos: "));

		// AÃ±adimos funciones de hilos
		ScheduledExecutorService funcionalidades = Executors.newSingleThreadScheduledExecutor();

		// Controla los hilos de la interfaz grafica EDT
		@SuppressWarnings("rawtypes")
		SwingWorker worker = new SwingWorker<String, Void>() {

			// Ejecutamos en un hilo secundario
			protected String doInBackground() throws Exception {

				Movimientos adolfito = new Movimientos(matriz);
				adolfito.setMatriz(matriz);
				Lectura l = new Lectura();
				if(l.leeArregla(matriz, matriz.length, matriz[0].length)) {
					// Simulamos la ejecucion de la habitacion
					adolfito.run();
					JOptionPane.showMessageDialog(null, adolfito.getResult());
				}else {
					JOptionPane.showMessageDialog(null, "Entrada mal formada");
				}

				return adolfito.getResult();
			}
		};

		worker.execute();

		Runnable tarea = () -> {

			if (!worker.isDone()) {
				worker.cancel(true);
				JOptionPane.showMessageDialog(null, "Se ha excedido el tiempo de ejecución.");
			}

			funcionalidades.shutdown();
		};

		funcionalidades.schedule(tarea, time, TimeUnit.SECONDS);
	}

	// Pantalla adicional para el caso de jugar de manera Manual
	private void jugarManu() {

		Pantalla VentanaJuego = new Pantalla();

		VentanaJuego.setMatriz(clonarHabitacion(matriz));
		VentanaJuego.setMatrizOriginal(clonarHabitacion(matriz));
		VentanaJuego.inicializarJuego(camino);

	}

	private void inicializarJuego(String camino) {

		for(int i = 0;i<camino.length();i++) {
			JuegoManual juguete = new JuegoManual(matriz);
			String[][] matrizAux = null; 
			int[] vectorcito = juguete.buscaRobot(matriz);
			int[] destino = calculadestino(vectorcito,matriz,camino.charAt(i)+"");

			Pattern mayusculas = Pattern.compile("[ADIB]");
			Matcher matcher = mayusculas.matcher(camino.charAt(i)+"");

			if(matcher.find()) {

				//Tengo que mover una caja
				if(matriz[destino[0]][destino[1]].equals("#") || matriz[destino[0]][destino[1]].equals("!")) {
					matrizAux = juguete.mueve(destino[0],destino[1]);
				}else {
					matrizAux = juguete.arrastrar(matriz,destino[0],destino[1]);
				}

			}else {
				matrizAux = juguete.mueve(destino[0],destino[1]);
			}

			if(matrizAux==null) {
				JOptionPane.showMessageDialog(null,"ERROR.Camino incorrecto");
			}else {
				matriz = matrizAux;
			}

		}

		this.cargaPanelJuego();

	}


	private int[] calculadestino(int[] vectorcito, String[][] matriz, String movimiento) {
		if(movimiento.toLowerCase().equals("a")) {

			vectorcito[0] -= 1;

		}else if(movimiento.toLowerCase().equals("b")) {

			vectorcito[0] += 1;

		}else if(movimiento.toLowerCase().equals("d")) {

			vectorcito[1] += 1;

		}else if(movimiento.toLowerCase().equals("i")) {

			vectorcito[1] -= 1;

		}
		return vectorcito;
	}

	private void setMatrizOriginal(String[][] matrizOriginal) {

		this.matrizOriginal = matrizOriginal;

	}

	private void setMatriz(String[][] matriz) {

		this.matriz = matriz;
	}

	/*
	 * 
	 * Metodo que realiza el movimiento manualmente
	 * 
	 */

	public boolean realizarMovimiento(int fila, int columna) {

		JuegoManual movimiento = new JuegoManual(matriz);

		String[][] prueba = movimiento.mueve(fila, columna);

		// Comprobamos si se ha realizado el movimiento o no
		if (prueba == null) {

			// No se ha realizado el movimiento
			return false;
		} else {
			matriz = prueba;
			this.cargaPanelJuego();
			camino += movimiento.getMovimiento();

			if (movimiento.esSolucion(matriz)) {
				JOptionPane.showMessageDialog(null, "Se ha resulto el ta blero, enhorabuena"+ getResult());
			}
			// Se ha realizado el movimiento
			return true;
		}
	}

	private String getResult() {

		//Mostramos solo los movimientos de la caja (letras mayúsculas)
		int numerador = 0;
		for(int i = 0;i<camino.length();i++) {
			if(this.camino.substring(i,i+1).equals("A") || this.camino.substring(i,i+1).equals("B") || this.camino.substring(i,i+1).equals("D")
					|| this.camino.substring(i,i+1).equals("I")) {

				numerador++;

			}
		}

		return "Camino: "+camino+".\t Movimientos hechos: "+numerador;
	}

	// Metodo que me carga los paneles como hilos que lleva la propia clase
	private void cargaPanelJuego() {
		estoyJugando = true;
		// Cargamos los datos
		this.Filas = matriz.length;
		this.Columnas = matriz[0].length;

		GridLayout mG = new GridLayout(Filas, Columnas);

		// Eliminamos lo que tenemos en nuestro panel anterior
		matrizGrafica.removeAll();
		matrizGrafica.setLayout(mG);

		for (int i = 0; i < Filas; i++) {
			for (int j = 0; j < Columnas; j++) {

				String mueble = matriz[i][j];
				BotonJuega jugando = new BotonJuega(mueble, i, j, this);
				matrizGrafica.add(jugando);
			}
		}

		this.getContentPane().add(matrizGrafica, BorderLayout.CENTER);
		this.setVisible(true);
	}

	private void inicializarMatriz() {

		// Borramos lo anterior
		this.getContentPane().removeAll();
		matriz = new String[Filas][Columnas];

		// Matriz grafica swing
		GridLayout mG = new GridLayout(Filas, Columnas);

		// Eliminamos la habitacion anterior
		matrizGrafica.removeAll();
		matrizGrafica.setLayout(mG);

		for (int i = 0; i < Filas; i++) {

			for (int j = 0; j < Columnas; j++) {

				// Etiqueta editable
				JTextField etiqueta = new JTextField();

				// Da funcionalidad a nuestra etiqueta
				addFoco(etiqueta, i, j);

				matrizGrafica.add(etiqueta);
			}

		}

		// Anadir al getContentPane para que sea visible
		this.getContentPane().add(matrizGrafica, BorderLayout.CENTER);
		this.setVisible(true);

	}

	private void addFoco(JTextField etiqueta, int i, int j) {

		// Escucha los eventos sobre cada etiqueta
		etiqueta.addFocusListener(new FocusAdapter() {
			private String contenidoActual = "";

			public void focusGained(FocusEvent evento) {
				// Cuando la etiqueta gane el foco (se pinche encima)
				contenidoActual = etiqueta.getText();
			}

			public void focusLost(FocusEvent e) {
				if (etiqueta.getText().length() > 1) {
					etiqueta.setText(contenidoActual);
					JOptionPane.showMessageDialog(null, "No se pueden introducir más de dos caracteres");
				} else {
					// Comprobar si se ha cambiado el contenido
					boolean cambiado = esCambiado(etiqueta.getText(), i, j);
					if (cambiado) {
						if(!camino.equals("")) {

							//Muestra mensaje
							int res = JOptionPane.showConfirmDialog(null,"Esta habitacion es de juego, si la modifica se perderá el camino almacenado ");

							//Hemos Aceptado
							if(res==0) {

								camino = "";
								actualizaHabitacionesHechas();
								// Propiedad de hacer y deshacer
								matriz[i][j] = etiqueta.getText();
							}else {
								etiqueta.setText(contenidoActual);
							}
						}else {
							actualizaHabitacionesHechas();
							// Propiedad de hacer y deshacer
							matriz[i][j] = etiqueta.getText();
						}

					}
				}
			}

		});

	}

	private boolean esCambiado(String etiqueta, int Filas, int Columnas) {
		return !etiqueta.equals(matriz[Filas][Columnas]);
	}

	private void guardar() {

		if (name.equals("")) {
			guardarComo();
		}

		try {

			// Archivo a escribir
			FileWriter archivo = new FileWriter(name);

			// Contenido a escribir en el archivo
			PrintWriter contenido = new PrintWriter(archivo);

			contenido.println(Filas + " " + Columnas);

			String[][] matrix = matriz;

			if(estoyJugando) {
				matrix = matrizOriginal;
			}

			// Recorremos la matriz e imprimimos cada valor
			for (int i = 0; i < Filas; i++) {
				for (int j = 0; j < Columnas; j++) {

					if (matrix[i][j] == null) {
						contenido.print("x");
					} else if(matrix[i][j].equals("")){
						contenido.print("x");
					}else {
						contenido.print(matrix[i][j]);
					}

				}

				contenido.println();
			}

			//Guardar camino (Mod)
			if(estoyJugando) {
				contenido.println(camino);
			}


			if (archivo != null) {

				// Si todo sale bien cerramos el archivo
				archivo.close();
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error al guardar");

		}

	}

	private void guardarComo() {

		JFileChooser jc = new JFileChooser();

		// devStringuelve 0 si se ha pulsado el ok

		int respuesta = jc.showSaveDialog(null);

		if (respuesta == 0) {

			File ficherito = jc.getSelectedFile();

			// Cogemos la ruta absoluta del archivo
			name = ficherito.getAbsolutePath();

			// Si no contiene la exception se la aÃ±adimos
			if (!name.contains(".txt")) {
				name = name + ".txt";
			}
		} else {

			// hemos pulsado cancelar y por lo tanto terminamos el metodo
			return;

		}

		// Creamos archivo File

		File ficherito = new File(name);

		// Comprueba si existe en la ruta
		if (ficherito.exists()) {

			int confirmacion = JOptionPane.showConfirmDialog(null, "Â¿Estas seguro de que quiere sobreescribir?");

			if (confirmacion == 0) {
				// Hemos aceptado
				guardar();
			}

		} else {

			guardar();
		}
	}

	/*
	 * 
	 * Metodos relacionados con el boton de CargarArchivo
	 * 
	 */

	public String[][] leer() {

		// Creamos la matriz
		String[][] matriz = null;

		// Nos da la ruta del fichero
		name = obtenRuta();

		// No se ha seleccionado archivo
		if (name.equals("")) {

			System.out.println("No se ha seleccionado ningun archivo");

		} else {

			ArrayList<String> lineas = leerFichero();
			matriz = tratarLineas(lineas);

		}
		return matriz;
	}

	// Se encarga de buscar el fichero
	private String obtenRuta() {

		JFileChooser jc = new JFileChooser();
		int respuesta = jc.showSaveDialog(null);

		// Hemos pulsado ACEPTAR,obtenemos el archivo seleccionado
		if (respuesta == 0) {
			File ficherito = jc.getSelectedFile();
			name = ficherito.getAbsolutePath();
			return name;
		}
		return name;
	}

	private ArrayList<String> leerFichero() {

		FileReader jR = null;

		try {

			// Necesitamos algo que lea
			jR = new FileReader(name);

		} catch (FileNotFoundException e) {

			System.out.println("No se encuentra el fichero");
		}

		BufferedReader lector = new BufferedReader(jR);
		String lineillas = " ";
		ArrayList<String> lineas = new ArrayList<String>();

		try {

			while ((lineillas = lector.readLine()) != null) {
				lineas.add(lineillas);
			}

		} catch (IOException e) {
			// Tratar excepcion
			System.out.println("No se puede leer el fichero");
		}

		return lineas;
	}

	private String[][] tratarLineas(ArrayList<String> lineas) {

		// Lo primero es obtener nuestras Lineas y Columnas

		String dimensiones = lineas.get(0);
		Filas = Integer.parseInt(dimensiones.substring(0, dimensiones.indexOf(" ")));
		Columnas = Integer.parseInt(dimensiones.substring(dimensiones.indexOf(" ") + 1));

		if (Filas <= 0 || Columnas <= 0) {
			// para poder salir del metodo
			System.out.println("Habitacion invalida");
			return null;
		}

		matriz = new String[Filas][Columnas];

		// i=1 debido a que nuestra habitacion comienza en la segunda linea
		for (int i = 1; i <= Filas; i++) {
			String l = lineas.get(i);

			for (int j = 0; j < Columnas; j++) {

				matriz[i - 1][j] = l.substring(0, 1);

				// Para cortar l
				l = l.substring(1);
			}
		}

		if(lineas.size() == Filas+2) {
			//Que tenemos la linea de camino
			camino = lineas.get(lineas.size()-1);
			JOptionPane.showMessageDialog(null,camino);
		}



		return matriz;
	}

	public void muestraMatriz() {

		this.getContentPane().removeAll();

		// Reseteamos el panel
		matrizGrafica.removeAll();

		GridLayout g = new GridLayout(Filas, Columnas);
		matrizGrafica.setLayout(g);

		for (int i = 0; i < Filas; i++) {
			for (int j = 0; j < Columnas; j++) {

				JTextField etiqueta = new JTextField(matriz[i][j]);

				addFoco(etiqueta, i, j);
				matrizGrafica.add(etiqueta);

			}
		}

		this.getContentPane().add(matrizGrafica, BorderLayout.CENTER);
		this.setVisible(true);

	}

	public void eliminaUltimaHabitacion() {
		habitacionHecha.remove(habitacionHecha.size() - 1);

	}

	//CLASE DE LAS TECLAS (KEYLISTENER)


	class KeyListen implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

	}




















}