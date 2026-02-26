import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase principal que analiza y compara el rendimiento de un participante de referencia
 * frente a un grupo de participantes, a partir de datos leídos desde un archivo de texto.
 * <p>
 * El archivo {@code participantes.txt} debe tener un participante por línea, con el formato:
 * <pre>
 *   nombre, M: medallas, P: participaciones, C: cinta
 * </pre>
 * La primera línea corresponde al participante de referencia (alumno A) y el resto
 * conforman el grupo de comparación.
 */
public class AdivinadorResultados{

	/**
	 * Devuelve el subconjunto de participantes del grupo {@code g} a quienes el
	 * participante {@code a} supera en índice de victoria.
	 *
	 * @param a el participante de referencia
	 * @param g el arreglo de participantes del grupo
	 * @return un arreglo con los participantes cuyo índice de victoria es
	 *         estrictamente menor que el de {@code a}
	 */
     public static Participante[] alumnoALesGana(Participante a, Participante[] g) {
    	int alumnoGana = 0;
    	for (int i = 0; i < g.length; i++) {
        	if (a.indiceVic() > g[i].indiceVic()) {
            	alumnoGana++;
        	}
    	}

    	Participante[] ganados = new Participante[alumnoGana];
    	int posicion = 0;
    	for (int i = 0; i < g.length; i++) {
        	if (a.indiceVic() > g[i].indiceVic()) {
            	ganados[posicion] = g[i];
            	posicion++;
        	}
    	}
    	return ganados;
	}

	/**
	 * Devuelve el subconjunto de participantes del grupo {@code g} que superan en
	 * índice de victoria al participante de referencia {@code a}.
	 *
	 * @param a el participante de referencia
	 * @param g el arreglo de participantes del grupo
	 * @return un arreglo con los participantes cuyo índice de victoria es
	 *         estrictamente mayor que el de {@code a}
	 */
    public static Participante[] alumnoAPierde (Participante a, Participante[] g){
	int alumnoPierde = 0;
	for(int i = 0; i < g.length; i++){
	    if (a.indiceVic() < g[i].indiceVic()){
		alumnoPierde++;
	    }
	}

	Participante[] perdidos = new Participante[alumnoPierde];
	int posicion2 = 0;
	for(int i = 0; i < g.length; i++){

	    if (a.indiceVic() < g[i].indiceVic()){
		perdidos[posicion2] = g[i];
		posicion2++;
	    }
	}
	return perdidos;
    }

	/**
	 * Calcula el promedio de los índices de victoria de todos los participantes
	 * del grupo {@code g}.
	 *
	 * @param g el arreglo de participantes del grupo
	 * @return un arreglo de un único elemento donde {@code resultado[0]} contiene
	 *         el promedio de los índices de victoria del grupo
	 */
    public static double[] indicesVictoria(Participante[] g){
	double promedio = 0;
	for(int i = 0; i < g.length; i++){
	   promedio += g[i].indiceVic();
	}

	double[] promedioVic = new double[1];
	promedioVic[0] = promedio / g.length;
	return promedioVic;
	

    }

	/**
	 * Devuelve los participantes del grupo {@code g} que tienen la misma cinta
	 * que el participante de referencia {@code a}.
	 *
	 * @param a el participante de referencia
	 * @param g el arreglo de participantes del grupo
	 * @return un arreglo con los participantes cuya cinta coincide con la de {@code a}
	 */
	public static Participante[] mismaCinta(Participante a, Participante[] g){
	    int mismaCinta = 0;
	    for (int i = 0; i < g.length; i++){
		if (a.getCinta().equals(g[i].getCinta())){
		    mismaCinta++;
		}
	    }

	    Participante[]  misma = new Participante[mismaCinta];
	    int participantes = 0;
	    for (int i = 0; i < g.length; i++){
		if (a.getCinta().equals(g[i].getCinta())){
		    misma[participantes] = g[i];
		    participantes++;
		}
	    }
    
    return misma;
}

	/**
	 * Devuelve los participantes del grupo {@code g} cuyo índice de victoria es
	 * similar al del participante de referencia {@code a}, con una tolerancia de ±0.10.
	 * <p>
	 * Se considera similar si la diferencia absoluta entre los índices de victoria
	 * satisface: {@code |a.indiceVic() - g[i].indiceVic()| <= 0.10}
	 *
	 * @param a el participante de referencia
	 * @param g el arreglo de participantes del grupo
	 * @return un arreglo con los participantes cuyo índice de victoria se encuentra
	 *         dentro del rango [{@code a.indiceVic() - 0.10}, {@code a.indiceVic() + 0.10}]
	 */
    public static Participante[] indicesimilarAlAlumnoA(Participante a, Participante[] g){
	int similar = 0;
	for (int i = 0; i < g.length; i ++){
	    if ((a.indiceVic() - g[i].indiceVic()) <= 0.10 && (a.indiceVic() - g[i].indiceVic()) >= -0.10){
		similar++;
	    }
	}

	Participante[] similares = new Participante[similar];
	int indicesSimilares = 0;
	for (int i = 0; i < g.length; i++){
	    if ((a.indiceVic() - g[i].indiceVic()) <= 0.10 && (a.indiceVic() - g[i].indiceVic()) >= -0.10){
		similares[indicesSimilares] = g[i];
		indicesSimilares++;
	    }
	}

	return similares;
		
    }

	/**
	 * Método auxiliar que parsea una línea del archivo de texto y construye un objeto
	 * {@code Participante} a partir de ella.
	 * <p>
	 * El formato esperado de la línea es:
	 * <pre>
	 *   nombre, M: medallas, P: participaciones, C: cinta
	 * </pre>
	 *
	 * @param linea la cadena de texto con los datos del participante
	 * @return un nuevo objeto {@code Participante} con los datos extraídos de la línea
	 */
    private static Participante obtenerDatos(String linea){
    int c1 = linea.indexOf(",");
    int c2 = linea.indexOf(",", c1 + 1);
    int c3 = linea.indexOf(",", c2 + 1);

    String nombre = linea.substring(0, c1);
    int medallas = Integer.parseInt(linea.substring(c1 + 3, c2));
    int participaciones = Integer.parseInt(linea.substring(c2 + 3, c3));
    String cinta = linea.substring(c3 + 3);

    return new Participante(nombre, medallas, participaciones, cinta);
}

	/**
	 * Método principal que lee el archivo {@code participantes.txt}, construye los
	 * objetos {@code Participante} y ejecuta los distintos análisis comparativos,
	 * imprimiendo los resultados por consola.
	 * <p>
	 * El primer participante del archivo actúa como alumno de referencia.
	 * El resto conforman el grupo de comparación.
	 *
	 * @param args argumentos de línea de comandos (no se utilizan)
	 */
    public static void main(String[] args){
	 try {
            BufferedReader br = new BufferedReader(new FileReader("participantes.txt"));

            
            int numLineas = 0;
            while (br.readLine() != null) {
                numLineas++;
            }
            br.close();

            BufferedReader br2 = new BufferedReader(new FileReader("participantes.txt"));
            Participante[] g = new Participante[numLineas - 1];

            String lineaPrimera = br2.readLine();
            Participante a = obtenerDatos(lineaPrimera);

            int pos = 0;
            String linea;
            while ((linea = br2.readLine()) != null) {
                g[pos] = obtenerDatos(linea);
                pos++;
            }
            br2.close();

            System.out.println("Alumno de referencia: " + a.nombre);

            System.out.println("\nA quienes les gana:");
            Participante[] gana = alumnoALesGana(a, g);
            for (int i = 0; i < gana.length; i++) {
                System.out.println(gana[i].nombre + ",M:" + gana[i].numMedallas + ",P:" + gana[i].numParticipaciones + ",C:" + gana[i].nombreCinta);
            }

            System.out.println("\nA quienes pierde:");
            Participante[] pierde = alumnoAPierde(a, g);
            for (int i = 0; i < pierde.length; i++) {
                System.out.println(pierde[i].nombre + ",M:" + pierde[i].numMedallas + ",P:" + pierde[i].numParticipaciones + ",C:" + pierde[i].nombreCinta);
            }

            System.out.println("\nIndices de victoria:");
            double[] indices = indicesVictoria(g);
            for (int i = 0; i < indices.length; i++) {
                System.out.println(g[i].nombre + ": " + indices[i]);
            }

            System.out.println("\nMisma cinta que " + a.nombre + ":");
            Participante[] misma = mismaCinta(a, g);
            for (int i = 0; i < misma.length; i++) {
                System.out.println(misma[i].nombre + ",M:" + misma[i].numMedallas + ",P:" + misma[i].numParticipaciones + ",C:" + misma[i].nombreCinta);
            }

            System.out.println("\nIndice similar a " + a.nombre + ":");
            Participante[] similares = indicesimilarAlAlumnoA(a, g);
            for (int i = 0; i < similares.length; i++) {
                System.out.println(similares[i].nombre + ",M:" + similares[i].numMedallas + ",P:" + similares[i].numParticipaciones + ",C:" + similares[i].nombreCinta);
            }

	    
	    
	    
	} catch (IOException e){
	    e.printStackTrace();
	}
    }
}
