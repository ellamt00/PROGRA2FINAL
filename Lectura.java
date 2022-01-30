
package unileon.es;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lectura {

	private static String muro = "0";
	private static String finMuro = " ";
	private static int robots = 0;
	private static int cajas = 0;
	private static int objetivos = 0;
	private static boolean resultado = true;
	private static boolean solucion = true;

	public boolean leeArregla(String[][] matriz, int filas, int columnas) {

		for(int i=0;i<filas;i++) {
			for(int j=0;j<columnas;j++) {


				resultado = cerrando(matriz,i,j,filas,columnas);

				//Nos salimos del bucle por que ya nos ha dado un error
				if(resultado==false) {
					return false;
				}

				if(matriz[i][j].equals("@")) {

					robots++;

					//Tenemos mas robots de los permitidos
					if(robots>1) {
						resultado = false;
						return resultado;
					}
				}

				if(matriz[i][j].equals("#")) {
					cajas++;
				}

				if(matriz[i][j].equals("!")) {
					objetivos++;
				}


			}
		}

		if(cajas!=objetivos) {

			//No se puede resolver el ejercicio
			resultado = false;
			return resultado;
		}

		solucion = blancos(matriz,true);
		//El numero de cajas || objetivos sigue siendo 0
		if(cajas == 0) {
			resultado  = false;
			return resultado;
		}
		//Todo ha salido bien por que no hay fallos en el cierre de la habitacion ni en los espacios
		if(resultado == true && solucion == true) {
			return true;
		}else {

			//alguno de los dos valores ha sido = false
			return false;
		}
	}




	/*
	 * Metodo que comprueba que la habitacion esta bien cerrada
	 */
	private boolean cerrando(String[][] matriz, int i, int j, int filas, int columnas) {

		if (i == 0) {

			// Caso de la primera fila
			if (j == 0) {
				if (matriz[i][j].equals(muro)) {
					if (matriz[i + 1][j + 1].equals(muro)) {
						return false;
					}
				}
			}

			// El otro extremo de la primera fila
			else if (j == columnas - 1) {
				if (matriz[i][j].equals(muro) && matriz[i + 1][j - 1].equals(muro)) {
					return false;
				}
			}
			/*
			 * Comprobamos para los casos que esten en el medio con la estructura 0 000 y
			 * tambien los casos con la estructura 000 en los dos primeros if's 0
			 * 
			 */

			else {
				if (matriz[i][j].equals(muro)) {

					if (matriz[i + 1][j].equals(muro) && matriz[i + 1][j].equals(muro)
							&& matriz[i + 1][j - 1].equals(muro) && matriz[i + 1][j + 1].equals(muro)) {
						return false;
					}

					if (j > 1) {
						if (matriz[i][j - 1].equals(muro) && matriz[i][j - 2].equals(muro)
								&& matriz[i + 1][j - 1].equals(muro)) {
							return false;
						}
					}
				}
			}
		}

		else if (i == filas - 1) {

			// Mismos casos que i=0 pero cambiando + por - (triangulito)
			if (j == 0) {
				if (matriz[i][j].equals(muro) && matriz[i - 1][j + 1].equals(muro)) {

					return false;
				}
			}

			else if (j == columnas - 1) {
				if (matriz[i][j].equals(muro) && matriz[i - 1][j - 1].equals(muro)) {

					return false;
				}
			} else {

				if (matriz[i][j].equals(muro)) {
					if (matriz[i - 1][j].equals(muro) && matriz[i - 1][j + 1].equals(muro)
							&& matriz[i][j + 1].equals(muro)) {
						return false;
					}
				}
			}
		}

		// casos de en medio de la habitacion
		else {
			if (matriz[i][j].equals(muro)) {
				if (j == 0) {
					if (matriz[i - 1][j].equals(muro) && matriz[i - 1][j + 1].equals(muro)
							&& matriz[i][j + 1].equals(muro)) {

						return false;
					}
				} else if (j == columnas - 1) {
					if (matriz[i - 1][j].equals(muro) && matriz[i - 1][j - 1].equals(muro)
							&& matriz[i][j - 1].equals(muro)) {

						return false;
					}
				} else {

					// Tampoco podemos formar entradas de 0's cuadradas
					if (matriz[i - 1][j].equals(muro) && matriz[i - 1][j + 1].equals(muro)
							&& matriz[i][j + 1].equals(muro)) {
						return false;
					}
				}
			}
		}

		// Todo ha salido bien
		return true;

	}



	/*
	 * 
	 * Metodo que comprueba que los espcacios en blanco (finMuro) esten bien
	 * 
	 */
	private boolean blancos(String[][] matriz, boolean dentro) {

	

		for(int i = 0;i<matriz.length;i++) {
			int numCeros = 0;
			dentro = false;
			for(int j=0;j<matriz[0].length;j++) {

				
				//Primera o ultima fila
				if(i==0 || i==matriz.length-1) {
					if(matriz[i][j].equals(muro)) {
						numCeros++;
					}

					//Ultima columna y no hemos encontrado 0, entrada mal formada
					if(j==matriz[0].length-1 && numCeros==0	) {
						return false;
					}
				}else {
					if(matriz[i][j].equals(muro)) {

						//Comprobamos si podemos acceder a la matriz/entrar en la habitacion
						if(!dentro && j<matriz[0].length-1 && !matriz[i][j+1].equals(muro) && !matriz[i][j+1].equals(" ")) {
							dentro = true;
						}else {

							//seguimos fuera de la habitaion
							dentro = false;
						}
					}

					//caso en el que no es cero
					else {

						if(!dentro) {
							if(!matriz[i][j].equals(finMuro) && !matriz[i][j].equals(muro)) {
								return false;
							}
						}

						else {

							//estamos dentro
							Pattern objetos = Pattern.compile("[!%-@#1+]");

							//comparamos el patron con la cadena
							Matcher compara = objetos.matcher(matriz[i][j]);

							if(!compara.find()) {
								//No se cumple la expresion regular
								return false;
							}
						}
					}
				}
			}
		}

		return true;

	}
	
	

}