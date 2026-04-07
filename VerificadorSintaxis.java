public class VerificadorSintaxis {

    public String verificarExpresion(String texto) {
        PilaManual pila = new PilaManual(); // Crea una pila nueva para revisar la expresión.
        int linea = 1; // La lectura inicia en la línea 1.
        int columna = 0; // La columna inicia en 0 y se incrementa al leer.

        for (int i = 0; i < texto.length(); i++) {
            char actual = texto.charAt(i); // Toma el carácter actual del texto.

            if (actual == '\n') {
                linea++; // Si hay salto de línea, pasa a la siguiente línea.
                columna = 0; // La columna vuelve a empezar.
                continue; // Sigue con el siguiente carácter.
            }

            columna++; // Suma una columna por cada carácter normal leído.

            if (esApertura(actual)) {
                pila.push(String.valueOf(actual), linea, columna); // Guarda el símbolo de apertura en la pila.
            } else if (esCierre(actual)) {
                if (pila.isEmpty()) {
                    return "Error: cierre sin apertura '" + actual + "' en línea " + linea + ", columna " + columna + ".";
                }

                NodoPila cima = pila.peek(); // Mira el último símbolo de apertura.
                char apertura = cima.getSimbolo().charAt(0); // Toma el carácter que está arriba.

                if (!coincide(apertura, actual)) {
                    return "Error: se encontró '" + actual + "' en línea " + linea + ", columna " + columna
                            + ", pero arriba de la pila estaba '" + apertura + "' abierto en línea "
                            + cima.getLinea() + ", columna " + cima.getColumna() + ".";
                }

                pila.pop(); // Si coincide, saca el símbolo porque quedó balanceado.
            }
        }

        if (!pila.isEmpty()) {
            NodoPila huérfano = pila.pop(); // Toma el símbolo que quedó sin cerrar.
            return "Error: apertura sin cierre '" + huérfano.getSimbolo() + "' en línea " + huérfano.getLinea()
                    + ", columna " + huérfano.getColumna() + ".";
        }

        return "Expresión correcta: todos los símbolos están balanceados.";
    }

    public String verificarHTML(String texto) {
        PilaManual pila = new PilaManual(); // Crea una pila nueva para revisar las etiquetas.
        int linea = 1; // La lectura inicia en la línea 1.
        int columna = 0; // La columna se actualiza mientras avanza el texto.
        int i = 0; // Índice para recorrer el texto.

        while (i < texto.length()) {
            char actual = texto.charAt(i); // Toma el carácter actual.

            if (actual == '<') {
                int lineaEtiqueta = linea; // Guarda la línea exacta donde inicia la etiqueta.
                int columnaEtiqueta = columna + 1; // Guarda la columna exacta donde inicia la etiqueta.
                int finEtiqueta = texto.indexOf('>', i); // Busca el cierre de la etiqueta.

                if (finEtiqueta == -1) {
                    return "Error: etiqueta sin cierre '>' en línea " + lineaEtiqueta + ", columna "
                            + columnaEtiqueta + ".";
                }

                String contenido = texto.substring(i + 1, finEtiqueta).trim(); // Saca el texto interno de la etiqueta.

                if (!contenido.isEmpty() && !contenido.startsWith("!") && !contenido.startsWith("?")) {
                    if (contenido.endsWith("/")) {
                        // Si la etiqueta se cierra sola, no se apila ni se compara.
                    } else if (contenido.startsWith("/")) {
                        String nombreCierre = limpiarNombreEtiqueta(contenido.substring(1)); // Obtiene el nombre del cierre.

                        if (pila.isEmpty()) {
                            return "Error: etiqueta de cierre </" + nombreCierre + "> sin apertura en línea "
                                    + lineaEtiqueta + ", columna " + columnaEtiqueta + ".";
                        }

                        NodoPila cima = pila.peek(); // Revisa la última etiqueta abierta.
                        String nombreApertura = cima.getSimbolo(); // Toma el nombre guardado arriba.

                        if (!nombreApertura.equals(nombreCierre)) {
                            return "Error: se encontró </" + nombreCierre + "> en línea " + lineaEtiqueta
                                    + ", columna " + columnaEtiqueta + ", pero se esperaba cerrar <"
                                    + nombreApertura + "> abierta en línea " + cima.getLinea() + ", columna "
                                    + cima.getColumna() + ".";
                        }

                        pila.pop(); // Si coincide, saca la etiqueta porque ya cerró bien.
                    } else {
                        String nombreApertura = limpiarNombreEtiqueta(contenido); // Obtiene el nombre de la apertura.
                        pila.push(nombreApertura, lineaEtiqueta, columnaEtiqueta); // Guarda la etiqueta abierta.
                    }
                }

                int[] posicion = avanzarPosicion(texto, i, finEtiqueta, linea, columna); // Actualiza línea y columna.
                linea = posicion[0]; // Guarda la nueva línea.
                columna = posicion[1]; // Guarda la nueva columna.
                i = finEtiqueta + 1; // Salta al carácter siguiente a la etiqueta.
            } else {
                if (actual == '\n') {
                    linea++; // Si hay salto, pasa a la siguiente línea.
                    columna = 0; // Reinicia la columna.
                } else {
                    columna++; // Si no hay salto, solo avanza una columna.
                }

                i++; // Avanza al siguiente carácter del texto.
            }
        }

        if (!pila.isEmpty()) {
            NodoPila huérfana = pila.pop(); // Toma la etiqueta que quedó abierta.
            return "Error: etiqueta <" + huérfana.getSimbolo() + "> sin cierre, abierta en línea "
                    + huérfana.getLinea() + ", columna " + huérfana.getColumna() + ".";
        }

        return "HTML correcto: todas las etiquetas están bien anidadas.";
    }

    private boolean esApertura(char simbolo) {
        return simbolo == '(' || simbolo == '[' || simbolo == '{'; // Define símbolos de apertura.
    }

    private boolean esCierre(char simbolo) {
        return simbolo == ')' || simbolo == ']' || simbolo == '}'; // Define símbolos de cierre.
    }

    private boolean coincide(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')')
                || (apertura == '[' && cierre == ']')
                || (apertura == '{' && cierre == '}'); // Compara si la pareja sí corresponde.
    }

    private String limpiarNombreEtiqueta(String contenido) {
        String limpio = contenido.trim(); // Quita espacios extras.
        int espacio = limpio.indexOf(' '); // Busca si la etiqueta trae atributos.

        if (espacio != -1) {
            limpio = limpio.substring(0, espacio); // Si hay atributos, conserva solo el nombre.
        }

        limpio = limpio.replace("/", ""); // Elimina una barra final si existe.
        return limpio.toLowerCase(); // Devuelve el nombre en minúscula para comparar mejor.
    }

    private int[] avanzarPosicion(String texto, int inicio, int fin, int linea, int columna) {
        for (int j = inicio; j <= fin; j++) {
            if (texto.charAt(j) == '\n') {
                linea++; // Si aparece un salto, aumenta la línea.
                columna = 0; // Reinicia la columna después del salto.
            } else {
                columna++; // Si no hay salto, avanza una columna.
            }
        }

        return new int[] { linea, columna }; // Devuelve la nueva posición en el texto.
    }
}
