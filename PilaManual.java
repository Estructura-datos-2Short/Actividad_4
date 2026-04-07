public class PilaManual {
    private NodoPila cima; // Guarda el último elemento apilado.

    public PilaManual() {
        this.cima = null; // La pila empieza vacía.
    }

    public void push(String simbolo, int linea, int columna) {
        NodoPila nuevo = new NodoPila(simbolo, linea, columna); // Crea el nuevo nodo.
        nuevo.setSiguiente(cima); // El nuevo nodo apunta a la cima anterior.
        cima = nuevo; // Ahora la nueva cima es el nodo recién creado.
    }

    public NodoPila pop() {
        if (isEmpty()) {
            throw new PilaVaciaException("La pila está vacía"); // Evita sacar elementos de una pila vacía.
        }

        NodoPila nodoEliminado = cima; // Guarda el nodo que está arriba.
        cima = cima.getSiguiente(); // La cima baja al siguiente nodo.
        nodoEliminado.setSiguiente(null); // Rompe el enlace para dejar limpio el nodo.
        return nodoEliminado; // Devuelve el nodo que se sacó.
    }

    public NodoPila peek() {
        if (isEmpty()) {
            throw new PilaVaciaException("La pila está vacía"); // Evita consultar una cima inexistente.
        }

        return cima; // Devuelve el nodo de arriba sin quitarlo.
    }

    public boolean isEmpty() {
        return cima == null; // Si la cima es null, no hay elementos.
    }
}
