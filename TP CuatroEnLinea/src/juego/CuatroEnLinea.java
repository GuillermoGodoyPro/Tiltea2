package juego;


/**
 * Juego Cuatro en L��nea
 * 
 * Reglas:
 * 
 * 		...
 *
 */
public class CuatroEnLinea {
	
	private String jugadorRojo;
	private String jugadorAmarillo;
	private Casillero[][] tablero;
	private boolean primerJugador = true;
	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4 y filas son menores a 8 y columnas menores a 16.
	 * post: empieza el juego entre el jugador que tiene fichas rojas, identificado como 
	 * 		 'jugadorRojo' y el jugador que tiene fichas amarillas, identificado como
	 * 		 'jugadorAmarillo'. 
	 * 		 Todo el tablero est� vac�o.
	 * 
	 * @param filas : cantidad de filas que tiene el tablero.
	 * @param columnas : cantidad de columnas que tiene el tablero.
	 * @param jugadorRojo : nombre del jugador con fichas rojas.
	 * @param jugadorAmarillo : nombre del jugador con fichas amarillas.
	 */
	public CuatroEnLinea(int filas, int columnas, String jugadorRojo, String jugadorAmarillo) {
	
		if (jugadorRojo.equals("")||jugadorAmarillo.equals("")){
			throw new Error ("Ambos jugadores deben tener un nombre.");
		}
		if((filas<4 || columnas<4)||(filas > 8 || columnas > 16)){
			throw new Error("Medidas del tablero invalidas, el tablero debe ser 4x4 como m�nimo y 8x16 como maximo." );
		}
		tablero = new Casillero[filas][columnas];
		
		for(int i=0;i<filas;i++){
			for(int j=0;j<columnas;j++){
				tablero[i][j] = Casillero.VACIO;
			}
		}
		this.jugadorRojo = jugadorRojo;
		this.jugadorAmarillo = jugadorAmarillo;

	}
	/**
	 * post: devuelve la cantidad m�xima de fichas que se pueden apilar en el tablero.
	 */
	public int contarFilas() {

		return tablero.length;
	}
	/**
	 * post: devuelve la cantidad m�xima de fichas que se pueden alinear en el tablero.
	 */
	public int contarColumnas() {
		
		return tablero[0].length;
	}

	/**
	 * pre : fila est� en el intervalo [1, contarFilas()],
	 * 		 columnas est� en el intervalo [1, contarColumnas()].
	 * post: indica qu� ocupa el casillero en la posici�n dada por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		
		return 	tablero[fila-1][columna-1];
	}
	
	/**
	 * pre : el juego no termin�, columna est� en el intervalo [1, contarColumnas()]
	 * 		 y a�n queda un Casillero.VACIO en la columna indicada. 
	 * post: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFichaEnColumna(int columna) {
		int ultimaFila = tablero.length -1;
		
		if (!termino()){
			while(ultimaFila >0 && tablero[ultimaFila][columna-1] != Casillero.VACIO){
			ultimaFila--;
			}
			if (tablero[0][columna-1] != Casillero.VACIO){
			throw new Error ("Columna llena");
			}
			if (primerJugador == true){
			tablero [ultimaFila][columna-1] = Casillero.ROJO;
			primerJugador = false;
			}
			else {tablero [ultimaFila][columna-1] = Casillero.AMARILLO;
			primerJugador = true;
			}
		}
	}
	/**
	 * post: indica si el juego termin� porque uno de los jugadores
	 * 		 gan� o no existen casilleros vac�os.
	 */
	public boolean termino() {
		boolean juegoFinalizado = false;
		
		for (int i = 0;i < tablero.length;i++){
			for (int j = 0;j < tablero[i].length;j++){
				if(tablero[i][j] == Casillero.VACIO){
					juegoFinalizado = false;
				}
				else {
					juegoFinalizado = true;
				}
			}	
		}	
		return juegoFinalizado;
	}

	/**
	 * post: indica si el juego termin� y tiene un ganador.
	 */
	public boolean hayGanador() {
		
		return false;
	}

	/**
	 * pre : el juego termin�.
	 * post: devuelve el nombre del jugador que gan� el juego.
	 */
	public String obtenerGanador() {
		
		return null;
	}
}