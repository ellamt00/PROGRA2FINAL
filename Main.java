package unileon.es;

import java.util.Scanner;

//import java.util.Scanner;

//import unileon.es.Pantalla;

public class Main {

	private static String[][] matriz;
	private static int Filas;
	private static int Columnas; 

	public static void main (String[] args) {
		
		//scnr comentar interm
		Pantalla pantalla = new Pantalla();
		pantalla.setVisible(true);

		//practicaIntermedia();
		
	}
	public static void practicaIntermedia() {
		
		//intermedia
		Scanner scan = new Scanner (System.in);
		String fyc = scan.nextLine();
		
		String[]linea = fyc.split(" ");
		Filas = Integer.parseInt(linea[0]);
		Columnas = Integer.parseInt(linea[1]);
		
		matriz = new String[Filas][Columnas];

		//Almacenamos la entrada en una matriz
		for(int i = 0;i<Filas;i++) {

			//Recojo las filas  de una en una.
			String f = scan.nextLine();

			//Separo elemento a elemento 
			String[] vector = f.split("");

			for(int j = 0;j<Columnas;j++) {

				//Añado elemento a elemento en la matriz
				matriz[i][j]=vector[j];
			}
		}
		

		//Termino el scan
		scan.close();
		//intermedia
		
		Lectura leyendo = new Lectura();
		boolean solucion = leyendo.leeArregla(matriz,Filas,Columnas);
		if(solucion==true) {
			Movimientos adolfito = new Movimientos(matriz);
			adolfito.run();
			
		
		}else {
			
			System.out.println("Entrada mal formada.");
		
		}
		
	}
}