
package unileon.es;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movimientos {

	private String[][] matriz;
	private String solucion;
	private final String robotito = "@";
	private final String robotSobreObjetivo = "+";
	private final String cajaSobreObjetivo = "*";
	private final String caja = "#";
	private final String guion = "-";
	private final String objetivo = "!";
	private int posicion = 0;

	/*
	 * Le paso la matriz leida en pantalla tras haber comprobado que esta bien
	 * formada
	 */

	public Movimientos(String[][] matriz) {

		this.matriz = matriz;
		solucion = "";

	}

	/*
	 * Metodo que recorre matriz hasta encontrar al robot
	 */
	public int[] buscaRobot(String[][] matriz) {

		int[] robot = new int[2];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {

				if (matriz[i][j].equals(robotito) || matriz[i][j].equals(robotSobreObjetivo)) {

					robot[0] = i;
					robot[1] = j;
					break;
				}
			}
		}
		return robot;
	}

	public String[][] realizaMovimiento(String[][] matriz, String adolfillo, boolean b) {

		String[][] aux = clonarHabitacion(matriz);
		int[] vector = buscaRobot(aux);

		if (adolfillo.equals("d")) {
			if (hayCaja(aux, vector[0], vector[1] + 1) && b) {
				if (hayObjetivo(aux, vector[0], vector[1] + 2)) {

					// Movemos una caja a un objetivo
					aux[vector[0]][vector[1] + 2] = cajaSobreObjetivo;
					aux[vector[0]][vector[1] + 1] = robotito;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;

				} else if (hayGuion(aux, vector[0], vector[1] + 2)) {

					aux[vector[0]][vector[1] + 2] = caja;
					aux[vector[0]][vector[1] + 1] = robotito;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;
				}

			} else if (hayCajaSobreObjetivo(aux, vector[0], vector[1] + 1) && b) {
				if (hayObjetivo(aux, vector[0], vector[1] + 2)) {

					// Movemos una caja a un objetivo
					aux[vector[0]][vector[1] + 2] = cajaSobreObjetivo;
					aux[vector[0]][vector[1] + 1] = robotSobreObjetivo;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;

				} else if (hayGuion(aux, vector[0], vector[1] + 2)) {

					aux[vector[0]][vector[1] + 2] = caja;
					aux[vector[0]][vector[1] + 1] = robotSobreObjetivo;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;
				}

			} else if (hayGuion(aux, vector[0], vector[1] + 1)) {

				aux[vector[0]][vector[1] + 1] = robotito;
				reseteaPostRobot(aux, vector[0], vector[1]);
				return aux;

			} else if (hayObjetivo(aux, vector[0], vector[1] + 1)) {

				aux[vector[0]][vector[1] + 1] = robotSobreObjetivo;
				reseteaPostRobot(aux, vector[0], vector[1]);
				return aux;
			}

		} else if (adolfillo.equals("i")) {
			if (hayCaja(aux, vector[0], vector[1] - 1) && b) {
				if (hayObjetivo(aux, vector[0], vector[1] - 2)) {

					// Movemos una caja a un objetivo
					aux[vector[0]][vector[1] - 2] = cajaSobreObjetivo;
					aux[vector[0]][vector[1] - 1] = robotito;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;

				} else if (hayGuion(aux, vector[0], vector[1] - 2)) {

					aux[vector[0]][vector[1] - 2] = caja;
					aux[vector[0]][vector[1] - 1] = robotito;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;
				}

			} else if (hayCajaSobreObjetivo(aux, vector[0], vector[1] - 1) && b) {
				if (hayObjetivo(aux, vector[0], vector[1] - 2)) {

					// Movemos una caja a un objetivo
					aux[vector[0]][vector[1] - 2] = cajaSobreObjetivo;
					aux[vector[0]][vector[1] - 1] = robotSobreObjetivo;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;

				} else if (hayGuion(aux, vector[0], vector[1] - 2)) {

					aux[vector[0]][vector[1] - 2] = caja;
					aux[vector[0]][vector[1] - 1] = robotSobreObjetivo;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;
				}

			} else if (hayGuion(aux, vector[0], vector[1] - 1)) {

				aux[vector[0]][vector[1] - 1] = robotito;
				reseteaPostRobot(aux, vector[0], vector[1]);
				return aux;

			} else if (hayObjetivo(aux, vector[0], vector[1] - 1)) {

				aux[vector[0]][vector[1] - 1] = robotSobreObjetivo;
				reseteaPostRobot(aux, vector[0], vector[1]);
				return aux;
			}
		} else if (adolfillo.equals("b")) {
			if (hayCaja(aux, vector[0] + 1, vector[1]) && b) {
				if (hayObjetivo(aux, vector[0] + 2, vector[1])) {

					// Movemos una caja a un objetivo
					aux[vector[0] + 2][vector[1]] = cajaSobreObjetivo;
					aux[vector[0] + 1][vector[1]] = robotito;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;

				} else if (hayGuion(aux, vector[0] + 2, vector[1])) {

					aux[vector[0] + 2][vector[1]] = caja;
					aux[vector[0] + 1][vector[1]] = robotito;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;
				}

			} else if (hayCajaSobreObjetivo(aux, vector[0] + 1, vector[1]) && b) {
				if (hayObjetivo(aux, vector[0] + 2, vector[1])) {

					// Movemos una caja a un objetivo
					aux[vector[0] + 2][vector[1]] = cajaSobreObjetivo;
					aux[vector[0] + 1][vector[1]] = robotSobreObjetivo;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;

				} else if (hayGuion(aux, vector[0] + 2, vector[1])) {

					aux[vector[0] + 2][vector[1]] = caja;
					aux[vector[0] + 1][vector[1]] = robotSobreObjetivo;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;
				}

			} else if (hayGuion(aux, vector[0] + 1, vector[1])) {

				aux[vector[0] + 1][vector[1]] = robotito;
				reseteaPostRobot(aux, vector[0], vector[1]);
				return aux;

			} else if (hayObjetivo(aux, vector[0] + 1, vector[1])) {

				aux[vector[0] + 1][vector[1]] = robotSobreObjetivo;
				reseteaPostRobot(aux, vector[0], vector[1]);
				return aux;
			}
		} else if (adolfillo.equals("a")) {
			if (hayCaja(aux, vector[0] - 1, vector[1]) && b) {
				if (hayObjetivo(aux, vector[0] - 2, vector[1])) {

					// Movemos una caja a un objetivo
					aux[vector[0] - 2][vector[1]] = cajaSobreObjetivo;
					aux[vector[0] - 1][vector[1]] = robotito;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;

				} else if (hayGuion(aux, vector[0] - 2, vector[1])) {

					aux[vector[0] - 2][vector[1]] = caja;
					aux[vector[0] - 1][vector[1]] = robotito;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;
				}

			} else if (hayCajaSobreObjetivo(aux, vector[0] - 1, vector[1]) && b) {
				if (hayObjetivo(aux, vector[0] - 2, vector[1])) {

					// Movemos una caja a un objetivo
					aux[vector[0] - 2][vector[1]] = cajaSobreObjetivo;
					aux[vector[0] - 1][vector[1]] = robotSobreObjetivo;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;

				} else if (hayGuion(aux, vector[0] - 2, vector[1])) {

					aux[vector[0] - 2][vector[1]] = caja;
					aux[vector[0] - 1][vector[1]] = robotSobreObjetivo;
					reseteaPostRobot(aux, vector[0], vector[1]);
					return aux;
				}

			} else if (hayGuion(aux, vector[0] - 1, vector[1])) {

				aux[vector[0] - 1][vector[1]] = robotito;
				reseteaPostRobot(aux, vector[0], vector[1]);
				return aux;

			} else if (hayObjetivo(aux, vector[0] - 1, vector[1])) {

				aux[vector[0] - 1][vector[1]] = robotSobreObjetivo;
				reseteaPostRobot(aux, vector[0], vector[1]);
				return aux;
			}
		}
		return null;
	}

	private boolean hayCajaSobreObjetivo(String[][] aux, int i, int j) {
		return aux[i][j].equals(cajaSobreObjetivo);
	}

	private boolean hayGuion(String[][] aux, int i, int j) {
		return aux[i][j].equals(guion);
	}

	private boolean hayObjetivo(String[][] aux, int i, int j) {
		return aux[i][j].equals(objetivo);
	}

	private boolean hayCaja(String[][] aux, int i, int j) {
		return aux[i][j].equals(caja);
	}

	/*
	 * Encuentra la caja
	 */
	public boolean esSolucion(String[][] matriz) {

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {

				if (matriz[i][j].equals(caja)) {
					return false;
				}
			}
		}

		return true;
	}

	public void setMatriz(String[][] matriz) {
		this.matriz = matriz;

	}

	/*
	 * Metodo que ejecuta
	 */

	public void run() {

		ArrayList<String[][]> habitaciones = new ArrayList<String[][]>();
		ArrayList<String> ruta = new ArrayList<String>();

		// Saber cuantas de posiciones de habitaciones he cogido ya
		ArrayList<Character> usadas = new ArrayList<Character>();
		habitaciones.add(matriz);
		ruta.add("");
		usadas.add('n');

		buscaSoluciones(habitaciones, ruta, usadas);

	}

	private void buscaSoluciones(ArrayList<String[][]> habitaciones, ArrayList<String> ruta,
			ArrayList<Character> usadas) {

		for (int m = 0; m < habitaciones.size(); m++) {

			// Buscamos la habitacion con el camino mas corto que no se haya usado
			int siguiente = buscaSiguienteRoom(ruta.size(), ruta, usadas);
			String[][] aux = habitaciones.get(siguiente);
			String caminoAux = ruta.get(siguiente);

			// Modificamos el valor de usadas para esta habitacion
			usadas.set(siguiente, 's');


			//Imprimimos solo el numero de mayusculas del resultado
			if (esSolucion(aux)) {

				this.solucion = caminoAux;

				int numerador = 0;

				for(int i = 0;i<solucion.length();i++) {

					if(this.solucion.substring(i,i+1).equals("A") || this.solucion.substring(i,i+1).equals("B") || this.solucion.substring(i,i+1).equals("D")
							|| this.solucion.substring(i,i+1).equals("I")) {

						numerador++;
					}
				}

				System.out.println(numerador);
				System.out.println(this.solucion);

				return;
			}


			// Localizamos la posicion de las cajas
			ArrayList<ArrayList<Integer>> posCajas = new ArrayList<ArrayList<Integer>>();
			buscaPosicionCajas(aux, posCajas);


			// Recorrer cajas buscando por donde empujar
			for (int i = 0; i < posCajas.size(); i++) {
				ArrayList<String[][]> pesadilla = new ArrayList<String[][]>();
				ArrayList<String> zanahoria = new ArrayList<String>();

				pesadilla.add(aux);
				zanahoria.add(caminoAux);

				// Intentar llegar a la poisicion de la caja por la izquierda
				if (puedoMover(posCajas.get(i), aux, 0, 1)) {
					posicion = busquedaAnchura(posCajas.get(i), 0, 1, pesadilla, zanahoria, 0);
					if (posicion != -1) {
						String[][] terceraHabitacion = mueveCaja(pesadilla.get(posicion), zanahoria.get(posicion),
								'i');
						if (terceraHabitacion != null && comparameHab(terceraHabitacion, habitaciones,
								zanahoria.get(posicion) + 'I', ruta, usadas) == false) {
							habitaciones.add(terceraHabitacion);
							ruta.add(zanahoria.get(posicion) + 'I');
							usadas.add('n');
						}

						//caminos y habitaciones en las que se han realizado arrastres
						ArrayList<String[][]> solucionesArrastres = new ArrayList<String[][]>();
						ArrayList<String> rutaArrastres = new ArrayList<String>();

						arratreCaja(pesadilla.get(posicion),zanahoria.get(posicion),solucionesArrastres,rutaArrastres);

						for(int s=0;s<solucionesArrastres.size();s++) {
							if(!comparameHab(solucionesArrastres.get(s),habitaciones,rutaArrastres.get(s),ruta,usadas)) {

								habitaciones.add(solucionesArrastres.get(s));
								ruta.add(rutaArrastres.get(s));
								usadas.add('n');
							}
						}
					}
				}

				pesadilla = new ArrayList<String[][]>();
				zanahoria = new ArrayList<String>();

				pesadilla.add(aux);
				zanahoria.add(caminoAux);

				// Intentar llegar a la poisicion de la caja por la derecha
				if (puedoMover(posCajas.get(i), aux, 0, -1)) {
					posicion = busquedaAnchura(posCajas.get(i), 0, -1, pesadilla, zanahoria, 0);
					if (posicion != -1) {
						String[][] terceraHabitacion = mueveCaja(pesadilla.get(posicion), zanahoria.get(posicion),
								'd');
						if (terceraHabitacion != null && comparameHab(terceraHabitacion, habitaciones,
								zanahoria.get(posicion) + 'D', ruta, usadas) == false) {
							habitaciones.add(terceraHabitacion);
							ruta.add(zanahoria.get(posicion) + 'D');
							usadas.add('n');
						}
						ArrayList<String[][]> solucionesArrastres = new ArrayList<String[][]>();
						ArrayList<String> rutaArrastres = new ArrayList<String>();

						arratreCaja(pesadilla.get(posicion),zanahoria.get(posicion),solucionesArrastres,rutaArrastres);

						for(int s=0;s<solucionesArrastres.size();s++) {
							if(!comparameHab(solucionesArrastres.get(s),habitaciones,rutaArrastres.get(s),ruta,usadas)) {

								habitaciones.add(solucionesArrastres.get(s));
								ruta.add(rutaArrastres.get(s));
								usadas.add('n');
							}
						}
					}

				}

				pesadilla = new ArrayList<String[][]>();
				zanahoria = new ArrayList<String>();

				pesadilla.add(aux);
				zanahoria.add(caminoAux);

				// Intentar llegar a la poisicion de la caja por arriba
				if (puedoMover(posCajas.get(i), aux, 1, 0)) {
					posicion = busquedaAnchura(posCajas.get(i), 1, 0, pesadilla, zanahoria, 0);
					if (posicion != -1) {
						String[][] terceraHabitacion = mueveCaja(pesadilla.get(posicion), zanahoria.get(posicion),
								'a');
						if (terceraHabitacion != null && comparameHab(terceraHabitacion, habitaciones,
								zanahoria.get(posicion) + 'A', ruta, usadas) == false) {
							habitaciones.add(terceraHabitacion);
							ruta.add(zanahoria.get(posicion) + 'A');
							usadas.add('n');
						}
						ArrayList<String[][]> solucionesArrastres = new ArrayList<String[][]>();
						ArrayList<String> rutaArrastres = new ArrayList<String>();

						arratreCaja(pesadilla.get(posicion),zanahoria.get(posicion),solucionesArrastres,rutaArrastres);

						for(int s=0;s<solucionesArrastres.size();s++) {
							if(!comparameHab(solucionesArrastres.get(s),habitaciones,rutaArrastres.get(s),ruta,usadas)) {

								habitaciones.add(solucionesArrastres.get(s));
								ruta.add(rutaArrastres.get(s));
								usadas.add('n');
							}
						}
					}
				}

				pesadilla = new ArrayList<String[][]>();
				zanahoria = new ArrayList<String>();

				pesadilla.add(aux);
				zanahoria.add(caminoAux);

				// Intentar llegar a la poisicion de la caja por abajo
				if (puedoMover(posCajas.get(i), aux, -1, 0)) {
					posicion = busquedaAnchura(posCajas.get(i), -1, 0, pesadilla, zanahoria, 0);
					if (posicion != -1) {
						String[][] terceraHabitacion = mueveCaja(pesadilla.get(posicion), zanahoria.get(posicion),
								'b');
						if (terceraHabitacion != null && comparameHab(terceraHabitacion, habitaciones,
								zanahoria.get(posicion) + 'B', ruta, usadas) == false) {
							habitaciones.add(terceraHabitacion);
							ruta.add(zanahoria.get(posicion) + 'B');
							usadas.add('n');
						}
						ArrayList<String[][]> solucionesArrastres = new ArrayList<String[][]>();
						ArrayList<String> rutaArrastres = new ArrayList<String>();

						arratreCaja(pesadilla.get(posicion),zanahoria.get(posicion),solucionesArrastres,rutaArrastres);

						for(int s=0;s<solucionesArrastres.size();s++) {
							if(!comparameHab(solucionesArrastres.get(s),habitaciones,rutaArrastres.get(s),ruta,usadas)) {

								habitaciones.add(solucionesArrastres.get(s));
								ruta.add(rutaArrastres.get(s));
								usadas.add('n');
							}
						}
					}
				}
			}


		}
		System.out.println("No hay solución.");

	}

	private void arratreCaja(String[][] pesadilla, String caminoAuxiliar, ArrayList<String[][]> solucionesArrastres,
			ArrayList<String> rutaArrastres) {

		int[] vector2 = buscaRobot(pesadilla);
		if(pesadilla[vector2[0]-1][vector2[1]].equals(guion) || pesadilla[vector2[0]-1][vector2[1]].equals(objetivo)) {
			String[][] nueva = clonarHabitacion(pesadilla);
			String[][] nueva2 = arratraCajasContiguas(nueva,caminoAuxiliar,"a");

			//Ha podido mover al menos una caja
			if(nueva2!=null) {
				solucionesArrastres.add(nueva2);
				rutaArrastres.add(caminoAuxiliar+"A");
			}
		}

		if(pesadilla[vector2[0]+1][vector2[1]].equals(guion) || pesadilla[vector2[0]+1][vector2[1]].equals(objetivo)) {
			String[][] nueva = clonarHabitacion(pesadilla);
			String[][] nueva2 = arratraCajasContiguas(nueva,caminoAuxiliar,"b");

			//Ha podido mover al menos una caja
			if(nueva2!=null) {
				solucionesArrastres.add(nueva2);
				rutaArrastres.add(caminoAuxiliar+"B");
			}
		}

		if(pesadilla[vector2[0]][vector2[1]+1].equals(guion) || pesadilla[vector2[0]][vector2[1]+1].equals(objetivo)) {
			String[][] nueva = clonarHabitacion(pesadilla);
			String[][] nueva2 = arratraCajasContiguas(nueva,caminoAuxiliar,"d");

			//Ha podido mover al menos una caja
			if(nueva2!=null) {
				solucionesArrastres.add(nueva2);
				rutaArrastres.add(caminoAuxiliar+"D");
			}
		}

		if(pesadilla[vector2[0]][vector2[1]-1].equals(guion) || pesadilla[vector2[0]][vector2[1]-1].equals(objetivo)) {
			String[][] nueva = clonarHabitacion(pesadilla);
			String[][] nueva2 = arratraCajasContiguas(nueva,caminoAuxiliar,"i");

			//Ha podido mover al menos una caja
			if(nueva2!=null) {
				solucionesArrastres.add(nueva2);
				rutaArrastres.add(caminoAuxiliar+"I");
			}
		}


	}

	public String[][] arratraCajasContiguas(String[][] nueva, String caminoAuxiliar, String movimiento) {

		int[] posiciones = buscaRobot(nueva);

		if(movimiento.equals("a")) {

			ArrayList<int[]> cajasContiguas = buscaCajasContiguas(nueva);

			//Lo primero que hacemos es mover al robot
			if(nueva[posiciones[0]-1][posiciones[1]].equals(guion)) {

				nueva[posiciones[0]-1][posiciones[1]] = robotito;
				reseteaPostRobot(nueva, posiciones[0], posiciones[1]);

			}else if(nueva[posiciones[0]-1][posiciones[1]].equals(objetivo)) {

				nueva[posiciones[0]-1][posiciones[1]] = robotSobreObjetivo;
				reseteaPostRobot(nueva, posiciones[0], posiciones[1]);

			}

			int cajasMovidas = 0;

			//Nos almacena en int caja cada objeto recorrido del ArrayList<int[]>
			for(int [] cajita : cajasContiguas) {

				int filita = cajita[0];
				int columnita = cajita[1];

				if(nueva[filita-1][columnita].equals(guion)) {

					cajasMovidas++;
					nueva[filita-1][columnita] = caja;
					reseteaPosCajita(nueva,filita,columnita);

				}else if(nueva[filita-1][columnita].equals(objetivo)) {

					cajasMovidas++;
					nueva[filita-1][columnita] = cajaSobreObjetivo;
					reseteaPosCajita(nueva,filita,columnita);
				}

			}

			if(cajasMovidas!=0) {
				return nueva;
			}

		}

		if(movimiento.equals("b")) {

			ArrayList<int[]> cajasContiguas = buscaCajasContiguas(nueva);

			//Lo primero que hacemos es mover al robot
			if(nueva[posiciones[0]+1][posiciones[1]].equals(guion)) {

				nueva[posiciones[0]+1][posiciones[1]] = robotito;
				reseteaPostRobot(nueva, posiciones[0], posiciones[1]);

			}else if(nueva[posiciones[0]+1][posiciones[1]].equals(objetivo)) {

				nueva[posiciones[0]+1][posiciones[1]] = robotSobreObjetivo;
				reseteaPostRobot(nueva, posiciones[0], posiciones[1]);

			}

			int cajasMovidas = 0;

			//Nos almacena en int caja cada objeto recorrido del ArrayList<int[]>
			for(int [] cajita : cajasContiguas) {

				int filita = cajita[0];
				int columnita = cajita[1];

				if(nueva[filita+1][columnita].equals(guion)) {

					cajasMovidas++;
					nueva[filita+1][columnita] = caja;
					reseteaPosCajita(nueva,filita,columnita);

				}else if(nueva[filita+1][columnita].equals(objetivo)) {

					cajasMovidas++;
					nueva[filita+1][columnita] = cajaSobreObjetivo;
					reseteaPosCajita(nueva,filita,columnita);
				}

			}

			if(cajasMovidas!=0) {
				return nueva;
			}

		}

		if(movimiento.equals("d")) {

			ArrayList<int[]> cajasContiguas = buscaCajasContiguas(nueva);

			//Lo primero que hacemos es mover al robot
			if(nueva[posiciones[0]][posiciones[1]+1].equals(guion)) {

				nueva[posiciones[0]][posiciones[1]+1] = robotito;
				reseteaPostRobot(nueva, posiciones[0], posiciones[1]);

			}else if(nueva[posiciones[0]][posiciones[1]+1].equals(objetivo)) {

				nueva[posiciones[0]][posiciones[1]+1] = robotSobreObjetivo;
				reseteaPostRobot(nueva, posiciones[0], posiciones[1]);

			}

			int cajasMovidas = 0;

			//Nos almacena en int caja cada objeto recorrido del ArrayList<int[]>
			for(int [] cajita : cajasContiguas) {

				int filita = cajita[0];
				int columnita = cajita[1];

				if(nueva[filita][columnita+1].equals(guion)) {

					cajasMovidas++;
					nueva[filita][columnita+1] = caja;
					reseteaPosCajita(nueva,filita,columnita);

				}else if(nueva[filita][columnita+1].equals(objetivo)) {

					cajasMovidas++;
					nueva[filita][columnita+1] = cajaSobreObjetivo;
					reseteaPosCajita(nueva,filita,columnita);
				}

			}

			if(cajasMovidas!=0) {
				return nueva;
			}

		}
		if(movimiento.equals("i")) {

			ArrayList<int[]> cajasContiguas = buscaCajasContiguas(nueva);

			//Lo primero que hacemos es mover al robot
			if(nueva[posiciones[0]][posiciones[1]-1].equals(guion)) {

				nueva[posiciones[0]][posiciones[1]-1] = robotito;
				reseteaPostRobot(nueva, posiciones[0], posiciones[1]);

			}else if(nueva[posiciones[0]][posiciones[1]-1].equals(objetivo)) {

				nueva[posiciones[0]][posiciones[1]-1] = robotSobreObjetivo;
				reseteaPostRobot(nueva, posiciones[0], posiciones[1]);

			}

			int cajasMovidas = 0;

			//Nos almacena en int caja cada objeto recorrido del ArrayList<int[]>
			for(int [] cajita : cajasContiguas) {

				int filita = cajita[0];
				int columnita = cajita[1];

				if(nueva[filita][columnita-1].equals(guion)) {

					cajasMovidas++;
					nueva[filita][columnita-1] = caja;
					reseteaPosCajita(nueva,filita,columnita);

				}else if(nueva[filita][columnita-1].equals(objetivo)) {

					cajasMovidas++;
					nueva[filita][columnita-1] = cajaSobreObjetivo;
					reseteaPosCajita(nueva,filita,columnita);
				}

			}

			if(cajasMovidas!=0) {
				return nueva;
			}

		}


		return null;
	}


	private ArrayList<int[]> buscaCajasContiguas(String[][] nueva) {

		int [] posiciones2 = buscaRobot(nueva);

		ArrayList<int[]>guardaPosiciones = new ArrayList<int[]>();
		ArrayList<ArrayList<Integer>> posCajitas = new ArrayList<ArrayList<Integer>>();

		buscaPosicionCajas(nueva, posCajitas);

		for(int i = 0;i<posCajitas.size();i++) {

			//IZQUIERDA
			if(posCajitas.get(i).get(0)==posiciones2[0] && posCajitas.get(i).get(1) == posiciones2[1]-1) {

				guardaPosiciones.add(new int[] {posiciones2[0],posiciones2[1]-1});

			}

			//ABAJO
			else if(posCajitas.get(i).get(0)==posiciones2[0]+1 && posCajitas.get(i).get(1) == posiciones2[1]) {

				guardaPosiciones.add(new int[] {posiciones2[0]+1,posiciones2[1]});

			}

			//ARRIBA
			else if(posCajitas.get(i).get(0)==posiciones2[0]-1 && posCajitas.get(i).get(1) == posiciones2[1]) {

				guardaPosiciones.add(new int[] {posiciones2[0]-1,posiciones2[1]});

			}

			//DERECHA
			else if(posCajitas.get(i).get(0)==posiciones2[0] && posCajitas.get(i).get(1) == posiciones2[1]+1) {

				guardaPosiciones.add(new int[] {posiciones2[0],posiciones2[1]+1});

			}
		}

		return guardaPosiciones;
	}

	private boolean comparameHab(String[][] terceraHabitacion, ArrayList<String[][]> habitaciones, String camino,
			ArrayList<String> ruta, ArrayList<Character> usadas) {

		for (int i = 0; i < habitaciones.size(); i++) {

			boolean iguales = true;
			String[][] matrizAux = habitaciones.get(i);

			for (int j = 0; j < matrizAux.length; j++) {
				for (int m = 0; m < matrizAux[0].length; m++) {
					if (!terceraHabitacion[j][m].equals(matrizAux[j][m])) {
						iguales = false;
						break;
					}
				}
			}

			if (iguales == true) {
				if (ruta.get(i).length() > camino.length()) {
					ruta.set(i, camino);
					usadas.set(i, 'n');
				}

				return true;
			}

		}

		return false;
	}

	private String[][] mueveCaja(String[][] habAux, String zanahoria, char c) {

		int[] posRobot = buscaRobot(habAux);

		if (c == 'd' && (habAux[posRobot[0]][posRobot[1] + 1].equals(caja)
				|| habAux[posRobot[0]][posRobot[1] + 1].equals(cajaSobreObjetivo))) {

			return realizaMovimiento(habAux, "d", true);
		}

		if (c == 'i' && (habAux[posRobot[0]][posRobot[1] - 1].equals(caja)
				|| habAux[posRobot[0]][posRobot[1] - 1].equals(cajaSobreObjetivo))) {

			return realizaMovimiento(habAux, "i", true);
		}

		if (c == 'a' && (habAux[posRobot[0] - 1][posRobot[1]].equals(caja)
				|| habAux[posRobot[0] - 1][posRobot[1]].equals(cajaSobreObjetivo))) {

			return realizaMovimiento(habAux, "a", true);
		}

		if (c == 'b' && (habAux[posRobot[0] + 1][posRobot[1]].equals(caja)
				|| habAux[posRobot[0] + 1][posRobot[1]].equals(cajaSobreObjetivo))) {

			return realizaMovimiento(habAux, "b", true);
		}

		return null;
	}

	private int busquedaAnchura(ArrayList<Integer> posCaja, int i, int j, ArrayList<String[][]> habAux,
			ArrayList<String> zanahoria, int k) {

		// No hemos encontrado un camino que lleve a la caja
		if (k >= habAux.size()) {
			return -1;
		} else if ((habAux.get(k)[posCaja.get(0) + i][posCaja.get(1) + j]).equals(robotito)
				|| (habAux.get(k)[posCaja.get(0) + i][posCaja.get(1) + j]).equals(robotSobreObjetivo)) {
			return k;
		} else {

			String[][] cuartaHabitacion = realizaMovimiento(habAux.get(k), "d", false);
			if (cuartaHabitacion != null && comparaAMayores(habAux, cuartaHabitacion) == false) {

				habAux.add(cuartaHabitacion);
				zanahoria.add(zanahoria.get(k) + 'd');
			}

			cuartaHabitacion = null;
			cuartaHabitacion = realizaMovimiento(habAux.get(k), "i", false);

			if (cuartaHabitacion != null && comparaAMayores(habAux, cuartaHabitacion) == false) {

				habAux.add(cuartaHabitacion);
				zanahoria.add(zanahoria.get(k) + 'i');
			}

			cuartaHabitacion = null;
			cuartaHabitacion = realizaMovimiento(habAux.get(k), "a", false);

			if (cuartaHabitacion != null && comparaAMayores(habAux, cuartaHabitacion) == false) {

				habAux.add(cuartaHabitacion);
				zanahoria.add(zanahoria.get(k) + 'a');
			}

			cuartaHabitacion = null;
			cuartaHabitacion = realizaMovimiento(habAux.get(k), "b", false);

			if (cuartaHabitacion != null && comparaAMayores(habAux, cuartaHabitacion) == false) {

				habAux.add(cuartaHabitacion);
				zanahoria.add(zanahoria.get(k) + 'b');
			}

			return busquedaAnchura(posCaja, i, j, habAux, zanahoria, ++k);
		}
	}

	private boolean comparaAMayores(ArrayList<String[][]> habAux, String[][] cuartaHabitacion) {

		for (int i = 0; i < habAux.size(); i++) {
			String[][] comparando = habAux.get(i);
			boolean iguales = true;

			for (int j = 0; j < comparando.length; j++) {
				for (int m = 0; m < comparando[0].length; m++) {

					if (!comparando[j][m].equals(cuartaHabitacion[j][m])) {
						iguales = false;
						break;
					}
				}
			}
			if (iguales == true) {
				return true;
			}
		}
		return false;
	}

	private boolean puedoMover(ArrayList<Integer> posCajas, String[][] aux, int filaLlegada, int columnaLlegada) {

		Pattern pLlegaRobot = Pattern.compile("[!%-@+]");
		Matcher mLlegaRobot = pLlegaRobot.matcher(aux[posCajas.get(0) + filaLlegada][posCajas.get(1) + columnaLlegada]);
		// Devuelve true si los dos lo encuentran
		return (mLlegaRobot.find());
	}



	private void buscaPosicionCajas(String[][] aux, ArrayList<ArrayList<Integer>> posCajas) {

		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux[0].length; j++) {

				if (aux[i][j].equals(caja) || aux[i][j].equals(cajaSobreObjetivo)) {
					ArrayList<Integer> posAux = new ArrayList<Integer>();
					posAux.add(i);
					posAux.add(j);
					posCajas.add(posAux);
				}

			}
		}
	}

	private int buscaSiguienteRoom(int tamano, ArrayList<String> ruta, ArrayList<Character> usadas) {

		int minimo = Integer.MAX_VALUE, pos = -1;

		for (int i = 0; i < tamano; i++) {
			if ((ruta.get(i).length()) < minimo && usadas.get(i) == ('n')) {

				// Hemos encontrado el camino mas corto
				minimo = ruta.get(i).length();
				pos = i;

			}
		}
		return pos;
	}

	public String getResult() {

		int numerador = 0;


		for(int i = 0;i<solucion.length();i++) {
			if(this.solucion.substring(i,i+1).equals("A") || this.solucion.substring(i,i+1).equals("B") || this.solucion.substring(i,i+1).equals("D")
					|| this.solucion.substring(i,i+1).equals("I")) {

				numerador++;
			}
		}
		return "Camino: "+solucion+"\t Movimientos realizados: "+ numerador;
	}

	public String[][] clonarHabitacion(String[][] matriz) {
		String[][] clonado = new String[matriz.length][matriz[0].length];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				clonado[i][j] = matriz[i][j];
			}
		}
		return clonado;
	}

	private void reseteaPosCajita(String[][] nueva, int filita, int columnita) {

		if(nueva[filita][columnita].equals(caja)) {
			nueva[filita][columnita] = guion;
		}else if(nueva[filita][columnita].equals(cajaSobreObjetivo)) {
			nueva[filita][columnita] = objetivo;
		}

	}

	private void reseteaPostRobot(String[][] matriz, int filaReseteo, int ColumnaReseteo) {

		if (matriz[filaReseteo][ColumnaReseteo].equals(robotito)) {
			matriz[filaReseteo][ColumnaReseteo] = guion;
		} else if (matriz[filaReseteo][ColumnaReseteo].equals(robotSobreObjetivo)) {
			matriz[filaReseteo][ColumnaReseteo] = objetivo;
		}
	}
}