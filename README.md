# Actividad Evaluable 3


Nombre: Larry David Botia C.  
Asignatura: Estructura de Datos  
Profesor: Endi Jainner Romero  

## Descripción del proyecto
Este proyecto implementa un verificador de sintaxis avanzado en Java. El programa usa una pila construida manualmente para validar expresiones matemáticas con paréntesis, corchetes y llaves, y también para revisar etiquetas HTML anidadas.

El sistema detecta errores de balanceo, errores de jerarquía, cierres sin apertura y aperturas sin cierre. Además, informa la línea y la columna donde ocurre la inconsistencia.

## Estructura del proyecto
- `NodoPila.java`: representa cada nodo de la pila.
- `PilaManual.java`: implementa la pila desde cero.
- `PilaVaciaException.java`: maneja el caso de intentar sacar datos de una pila vacía.
- `VerificadorSintaxis.java`: contiene la lógica para validar expresiones y HTML.
- `Main.java`: ejecuta el programa y muestra un menú sencillo.

## Funcionamiento general
1. Cuando el programa encuentra un símbolo o etiqueta de apertura, lo apila.
2. Cuando encuentra un símbolo o etiqueta de cierre, compara con la cima.
3. Si coinciden, desapila.
4. Si no coinciden, reporta el error con línea y columna.
5. Si al final todavía quedan elementos en la pila, esos elementos quedaron sin cierre.

## Instrucciones de ejecución en Visual Studio Code
1. Guardar todos los archivos en una misma carpeta.
2. Abrir esa carpeta en Visual Studio Code.
3. Abrir la terminal en esa carpeta.
4. Compilar con el comando:

```bash
javac *.java
```

5. Ejecutar con el comando:

```bash
java Main
```

## Casos de prueba sugeridos
### Expresión correcta
```text
([a+b] * {c-d})
```

### Expresión incorrecta
```text
([a+b} * {c-d})
```

### HTML correcto
```html
<html>
  <body>
    <h1>Título</h1>
  </body>
</html>
```

### HTML incorrecto
```html
<html>
  <body>
    <h1>Título</body>
</html>
```

## Conclusión
El proyecto muestra cómo una pila permite controlar el orden de apertura y cierre de símbolos o etiquetas. Gracias a la estructura LIFO, se puede validar si el texto mantiene una jerarquía correcta y detectar con precisión el punto donde aparece un error.
