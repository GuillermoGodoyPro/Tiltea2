package juego;


/**
 * Juego Cuatro en Lí­nea
 * 
 * Reglas:
 * 
 * 		...
 *
 */
public class CuatroEnLinea {
	
	private String jugador1;
	private String jugador2;
	private Casillero[][] tablero;
	private Casillero jugadorActual = Casillero.ROJO;

	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4 y filas son menores a 8 y columnas menores a 16.
	 * post: empieza el juego entre el jugador que tiene fichas rojas, identificado como 
	 * 		 'jugadorRojo' y el jugador que tiene fichas amarillas, identificado como
	 * 		 'jugadorAmarillo'. 
	 * 		 Todo el tablero está vacío.
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
			throw new Error("Medidas del tablero invalidas, el tablero debe ser 4x4 como mínimo y 8x16 como maximo." );
		}
		tablero = new Casillero[filas][columnas];
		
		for(int i=0;i<filas;i++){
			for(int j=0;j<columnas;j++){
				tablero[i][j] = Casillero.VACIO;
			}
		}	
	}
	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden apilar en el tablero.
	 */
	public int contarFilas() {

		return tablero.length;
	}
	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden alinear en el tablero.
	 */
	public int contarColumnas() {
		
		return tablero[0].length;
	}

	/**
	 * pre : fila está en el intervalo [1, contarFilas()],
	 * 		 columnas está en el intervalo [1, contarColumnas()].
	 * post: indica qué ocupa el casillero en la posición dada por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		
		return 	tablero[fila-1][columna-1];
	}
	
	/**
	 * pre : el juego no terminó, columna está en el intervalo [1, contarColumnas()]
	 * 		 y aún queda un Casillero.VACIO en la columna indicada. 
	 * post: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFichaEnColumna(int columna) {
		
		int ultimaFila = tablero.length -1;

		while(ultimaFila >0 && tablero[ultimaFila][columna-1] != Casillero.VACIO){
			ultimaFila--;
		}
		if (tablero[0][columna-1] != Casillero.VACIO){
			throw new Error ("Columna llena");
		}

		tablero [ultimaFila][columna-1] = jugadorActual;

		if (!esFichaGanadora(jugadorActual,ultimaFila,columna-1)){
			if (jugadorActual == Casillero.ROJO){
				jugadorActual = Casillero.AMARILLO;	
			}
			else if (jugadorActual == Casillero.AMARILLO){
				jugadorActual = Casillero.ROJO;	
			}
		}
	}


	/**
	 * post: indica si el juego terminó porque uno de los jugadores
	 * 		 ganó o no existen casilleros vacíos.
	 */
	public boolean termino() {
		
		boolean juegoFinalizado = false;
		int casillerosPintados = 0 ;
		
			for (int i = 0 ; i < tablero.length; i++ ){
				for (int j = 0; j < (tablero[i].length) ; j++){
					if(tablero[i][j] != Casillero.VACIO){
					casillerosPintados++;
					}				
					if(casillerosPintados == ((tablero.length)*(tablero[i].length) )){
					juegoFinalizado = true;
					}
				}	
			}
		
		return juegoFinalizado;
	}	
	/**
	 * post: indica si el juego terminó y tiene un ganador.
	 */
	
	public boolean hayGanador() {
		boolean ganador = false;
					
		return ganador;
	}

	/**
	 * pre : el juego terminó.
	 * post: devuelve el nombre del jugador que ganó el juego.
	 */
	public String obtenerGanador() {
		
		return jugador1;
	}
	private boolean esFichaGanadora (Casillero c,int fila,int columna){
		int cantidadDeColores = 1;
		int j = columna+1;

		while (tablero[fila][j] == c && cantidadDeColores <4 && j < tablero[0].length){
			cantidadDeColores++;
			j++;
		}
		j = columna-1;
		while (tablero[fila][j] == c && cantidadDeColores <4 && j >=0){
			cantidadDeColores++;
			j--;
		}
		if (cantidadDeColores == 4){
			System.out.println("Gano");
			return true;
		}	
		else{	
			return false;
		}
	}
}	
