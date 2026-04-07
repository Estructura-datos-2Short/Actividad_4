import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in); // Lee lo que escriba el usuario.
        VerificadorSintaxis verificador = new VerificadorSintaxis(); // Conecta con la clase que hace las validaciones.
        int opcion; // Guarda la opción elegida en el menú.

        do {
            System.out.println("\nVERIFICADOR DE SINTAXIS AVANZADO");
            System.out.println("1. Verificar expresión matemática");
            System.out.println("2. Verificar HTML");
            System.out.println("3. Ejecutar pruebas rápidas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            while (!entrada.hasNextInt()) {
                System.out.print("Ingrese un número válido: ");
                entrada.next(); // Limpia lo que no sea número.
            }

            opcion = entrada.nextInt(); // Toma la opción elegida.
            entrada.nextLine(); // Limpia el salto de línea pendiente.

            switch (opcion) {
                case 1:
                    System.out.println("Escriba la expresión en varias líneas si quiere.");
                    System.out.println("Cuando termine, escriba FIN en una línea sola.");
                    String expresion = leerTexto(entrada); // Lee el texto completo de la expresión.
                    System.out.println(verificador.verificarExpresion(expresion)); // Envía el texto al verificador.
                    break;

                case 2:
                    System.out.println("Escriba el HTML en varias líneas si quiere.");
                    System.out.println("Cuando termine, escriba FIN en una línea sola.");
                    String html = leerTexto(entrada); // Lee el texto completo del HTML.
                    System.out.println(verificador.verificarHTML(html)); // Envía el texto al verificador.
                    break;

                case 3:
                    ejecutarPruebasRapidas(verificador); // Muestra ejemplos correctos e incorrectos.
                    break;

                case 4:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 4);

        entrada.close(); // Cierra el lector al terminar el programa.
    }

    public static String leerTexto(Scanner entrada) {
        StringBuilder texto = new StringBuilder(); // Acumula todas las líneas escritas.

        while (true) {
            String linea = entrada.nextLine(); // Lee una línea completa.

            if (linea.equals("FIN")) {
                break; // Si el usuario escribe FIN, termina la captura.
            }

            texto.append(linea).append("\n"); // Agrega la línea y conserva el salto.
        }

        return texto.toString(); // Devuelve el texto completo armado.
    }

    public static void ejecutarPruebasRapidas(VerificadorSintaxis verificador) {
        String expresionBuena = "([a+b] * {c-d})"; // Caso correcto de símbolos.
        String expresionMala = "([a+b} * {c-d})"; // Caso con error de jerarquía.
        String htmlBueno = "<html>\n<body>\n<h1>Título</h1>\n</body>\n</html>"; // Caso correcto de HTML.
        String htmlMalo = "<html>\n<body>\n<h1>Título</body>\n</html>"; // Caso con cierre incorrecto.

        System.out.println("\nPrueba 1 - Expresión correcta:");
        System.out.println(verificador.verificarExpresion(expresionBuena));

        System.out.println("\nPrueba 2 - Expresión incorrecta:");
        System.out.println(verificador.verificarExpresion(expresionMala));

        System.out.println("\nPrueba 3 - HTML correcto:");
        System.out.println(verificador.verificarHTML(htmlBueno));

        System.out.println("\nPrueba 4 - HTML incorrecto:");
        System.out.println(verificador.verificarHTML(htmlMalo));
    }
}
