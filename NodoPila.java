public class NodoPila {
    private String simbolo; // Guarda el símbolo o etiqueta que se apila.
    private int linea; // Guarda la línea donde apareció.
    private int columna; // Guarda la columna donde apareció.
    private NodoPila siguiente; // Apunta al nodo que quedó debajo en la pila.

    public NodoPila(String simbolo, int linea, int columna) {
        this.simbolo = simbolo; // Asigna el símbolo recibido.
        this.linea = linea; // Asigna la línea recibida.
        this.columna = columna; // Asigna la columna recibida.
        this.siguiente = null; // Al crear el nodo aún no hay otro enlazado.
    }

    public String getSimbolo() {
        return simbolo; // Devuelve el símbolo guardado.
    }

    public int getLinea() {
        return linea; // Devuelve la línea guardada.
    }

    public int getColumna() {
        return columna; // Devuelve la columna guardada.
    }

    public NodoPila getSiguiente() {
        return siguiente; // Devuelve el nodo siguiente.
    }

    public void setSiguiente(NodoPila siguiente) {
        this.siguiente = siguiente; // Conecta este nodo con el siguiente nodo de la pila.
    }
}
