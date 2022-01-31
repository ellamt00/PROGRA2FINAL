
package unileon.es;

public class JuegoManual {

	private  String[][] matriz;
	private  String movimiento;
	private  Movimientos adolfito;

	public JuegoManual(String[][] matriz) {

		adolfito = new Movimientos(matriz);
		this.matriz = matriz;

	}

	public String[][] mueve(int fila, int columna) {

		String[][]prueba;

		//Posicion robot
		int[]vector = adolfito.buscaRobot(matriz);
		if(fila==vector[0]) {

			//mov, dech o izq
			if(columna-1==vector[1]) {
				prueba=adolfito.realizaMovimiento(matriz,"d",false);

				if(prueba!=null) {

					//Movemos robot DERE
					this.movimiento="d";
					return prueba;

				}

				prueba=adolfito.realizaMovimiento(matriz,"d",true);

				if(prueba!=null) {

					//Movemos caja DERE
					this.movimiento="D";
					return prueba;

				}


			}else if(columna+1==vector[1]) {
				prueba=adolfito.realizaMovimiento(matriz,"i",false);

				if(prueba!=null) {

					//Movemos robot IZQ
					this.movimiento="i";
					return prueba;

				}

				prueba=adolfito.realizaMovimiento(matriz,"i",true);

				if(prueba!=null) {

					//Movemos caja IZQ
					this.movimiento="I";
					return prueba;

				}
			}

		}else if(columna==vector[1]) {

			if(fila-1==vector[0]) {
				prueba=adolfito.realizaMovimiento(matriz,"b",false);

				if(prueba!=null) {

					//Movemos robot AB
					this.movimiento="b";
					return prueba;

				}

				prueba=adolfito.realizaMovimiento(matriz,"b",true);

				if(prueba!=null) {

					//Movemos caja AB
					this.movimiento="B";
					return prueba;

				}


			}else if(fila+1==vector[0]) {
				prueba=adolfito.realizaMovimiento(matriz,"a",false);

				if(prueba!=null) {

					//Movemos robot ARRI
					this.movimiento="a";
					return prueba;

				}

				prueba=adolfito.realizaMovimiento(matriz,"a",true);

				if(prueba!=null) {

					//Movemos caja ARRI
					this.movimiento="A";
					return prueba;

				}
			}

		}

		return null;
	}



	public String getMovimiento() {
		return movimiento;
	}

	public boolean esSolucion(String[][] matriz) {
		return adolfito.esSolucion(matriz);
	}

	public String getResult() {
		return this.adolfito.getResult();
	}

	public String[][] arrastrar( int Filas, int Columnas) {
		int[] vectorcito = buscaRobot(matriz);

		if(Filas==vectorcito[0]) {
			if(Columnas-1 == vectorcito[1]) {
				String [][] matrizAux = adolfito.clonarHabitacion(matriz);
				adolfito.setMatriz(matrizAux);
				this.movimiento = "D";
				this.matriz=matrizAux;
				return adolfito.arratraCajasContiguas(matriz,"","d");

			}else if(Columnas+1==vectorcito[1]) {
				String[][] matrizAux = adolfito.clonarHabitacion(matriz);
				adolfito.setMatriz(matrizAux);
				this.movimiento = "I";
				this.matriz=matrizAux;
				return adolfito.arratraCajasContiguas(matriz,"","i");
			}
		}else if(Columnas==vectorcito[1]) {
			if(Filas+1 == vectorcito[0]){
				String[][]matrizAux = adolfito.clonarHabitacion(matriz);
				adolfito.setMatriz(matrizAux);
				this.movimiento = "A";
				this.matriz=matrizAux;
				return adolfito.arratraCajasContiguas(matriz,"","a");

			}else if(Filas+1 == vectorcito[0]) {
				String[][] matrizAux = adolfito.clonarHabitacion(matriz);
				adolfito.setMatriz(matrizAux);
				this.movimiento = "B";
				this.matriz=matrizAux;
				return adolfito.arratraCajasContiguas(matriz,"","b");
			}
		}
		return null;
	}

	public int[] buscaRobot(String[][] matriz) {
		return adolfito.buscaRobot(matriz);
	}

}