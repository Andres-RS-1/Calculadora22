/*
APACHE NETBEANS IDE 20
RINCON SANCHEZ
25 DE FEBRERO DE 2024
*/

// La línea a continuación indica que el archivo pertenece al paquete com.mycompany.calculadora22
package com.mycompany.calculadora22;

// Importación de la clase Arrays desde el paquete java.util. 
// Esta importación permite utilizar funcionalidades relacionadas con arrays en el código.
import java.util.Arrays;

// Importación de la clase Scanner desde el paquete java.util. 
// Esta importación permite utilizar la clase Scanner para la entrada de datos desde la consola.
import java.util.Scanner;


// La declaración de la clase principal llamada Calculadora22.
public class Calculadora22 {
    // Comentario indicando el autor o información adicional asociada al código.
    

    // Declaración de constantes que representan los pesos de cada corte para calcular la definitiva.
    private static final double PESO_CORTE_1 = 0.30;
    private static final double PESO_CORTE_2 = 0.20;
    private static final double PESO_CORTE_3 = 0.20;
    private static final double PESO_CORTE_4 = 0.30;

    // Declaración de variables estáticas que almacenarán información sobre los estudiantes.
    private static int numEstudiantes;
    private static int[] codigosEstudiantes;
    private static String[] nombresEstudiantes;
    private static String[] apellidosEstudiantes;
    private static long[] documentosEstudiantes;
    private static double[][] notasCortes;


    // Método principal que inicia la ejecución del programa.
    public static void main(String[] args) {
    // Creación de un objeto Scanner para la entrada de datos desde la consola.
    Scanner scanner = new Scanner(System.in);

    // Declaración de una variable para almacenar la opción del usuario.
    int opcion = 0;

    // Bucle infinito que representa el menú principal del programa.
    while (true) {
        // Mostrar las opciones disponibles al usuario.
        System.out.println("Seleccione una opción:");
        System.out.println("1. Cargar");
        System.out.println("2. Ordenar (de menor a mayor)");
        System.out.println("3. Calcular definitiva");
        System.out.println("4. Salir");

        // Leer la opción ingresada por el usuario.
        opcion = scanner.nextInt();

        // Evaluar la opción seleccionada por el usuario mediante un switch.
        switch (opcion) {
            case 1:
                // Llamar al método cargarInformacionEstudiantes, pasando el objeto Scanner como parámetro.
                cargarInformacionEstudiantes(scanner);
                break;
            case 2:
                // Preguntar al usuario el criterio de ordenamiento.
                System.out.println("Seleccione el criterio de ordenamiento:");
                System.out.println("1. Por código");
                System.out.println("2. Por identificación (NUIP)");
                int criterioOrdenamiento = scanner.nextInt();

                // Llamar al método ordenarVectores, pasando el criterio de ordenamiento como parámetro.
                ordenarVectores(criterioOrdenamiento);
                break;
            case 3:
                // Llamar al método calcularDefinitivaYPromedio.
                calcularDefinitivaYPromedio();
                break;
            case 4:
                // Mostrar mensaje de salida y finalizar el programa.
                System.out.println("Saliendo del programa.");
                System.exit(0);
        }
    }
}

    // Método privado utilizado para cargar la información de los estudiantes.
    private static void cargarInformacionEstudiantes(Scanner scanner) {
    // Bucle que se ejecutará hasta que se ingrese un número válido de estudiantes.
        while (true) {
        System.out.println("Ingrese el número de estudiantes:");

        try {
            // Intentar leer un entero.
            numEstudiantes = scanner.nextInt();

            if (numEstudiantes <= 0) {
                // Mostrar mensaje de error si el número de estudiantes es no válido.
                System.out.println("Error: El número de estudiantes debe ser mayor que cero. Intente de nuevo.");
            } else {
                // Salir del bucle si la entrada es válida.
                break;
            }
        } catch (java.util.InputMismatchException e) {
            // Capturar la excepción si no se ingresa un entero.
            System.out.println("Error: Ingrese un número entero. Intente de nuevo.");
            scanner.nextLine(); // Limpiar el buffer del scanner para evitar bucle infinito.
        }
    }


        
        // Inicialización de los arreglos para almacenar la información de los estudiantes.
    codigosEstudiantes = new int[numEstudiantes];
    nombresEstudiantes = new String[numEstudiantes];
    apellidosEstudiantes = new String[numEstudiantes];
    documentosEstudiantes = new long[numEstudiantes];
    notasCortes = new double[numEstudiantes][4];

    // Bucle que recorre cada estudiante para obtener y almacenar su información.
    for (int i = 0; i < numEstudiantes; i++) {
        System.out.println("Ingrese solo un nombre del estudiante " + (i + 1) + ":");
        scanner.nextLine(); // Consumir la línea en blanco después de nextInt()
        nombresEstudiantes[i] = obtenerNombreValido(scanner);

        System.out.println("Ingrese el primer apellido del estudiante " + (i + 1) + ":");
        apellidosEstudiantes[i] = obtenerNombreValido(scanner);

        System.out.println("Ingrese el segundo apellido del estudiante " + (i + 1) + ":");
        apellidosEstudiantes[i] += " " + obtenerNombreValido(scanner);

    // Validación del NUIP (Número Único de Identificación Personal).
    boolean documentoValido = false;

    // Se utiliza un bucle while para asegurarse de que se ingrese un NUIP válido.
    while (!documentoValido) {
    // Se muestra un mensaje solicitando el ingreso del NUIP del estudiante actual.
        System.out.println("Ingrese el NUIP (10 números, sin puntos ni comas) del estudiante " + (i + 1) + ":");
    
    // Se llama al método validarNUIP para obtener el NUIP ingresado por el usuario.
        documentosEstudiantes[i] = validarNUIP(scanner);

    // Se verifica si el valor devuelto por validarNUIP es -1, indicando un error en la validación.
    if (documentosEstudiantes[i] == -1) {
        // Se muestra un mensaje de error y se solicita que se ingrese un NUIP válido.
        System.out.println("Error: Ingrese un NUIP válido. Intente de nuevo.");
    } else {
        // Si el NUIP es válido, se sale del bucle while estableciendo documentoValido en true.
        documentoValido = true;
    }
}

            
            
// Validación del código del estudiante.
    boolean codigoValido = false;

// Bucle que se ejecuta mientras el código no sea válido.
    while (!codigoValido) {
    // Se muestra un mensaje para que el usuario ingrese el código del estudiante.
        System.out.println("Ingrese el código del estudiante (6 números) " + (i + 1) + ":");

    // Se inicializa la variable 'codigo' en 0.
    int codigo = 0;

    try {
        // Intentar leer un entero desde la entrada del usuario.
        codigo = scanner.nextInt();

        if (String.valueOf(codigo).length() == 6) {
            // Si el código tiene exactamente 6 dígitos, se considera válido.
            codigosEstudiantes[i] = codigo;
            codigoValido = true;
        } else {
            // Mostrar mensaje de error si el código no tiene la longitud correcta.
            System.out.println("Error: Ingrese un código de 6 números. Intente de nuevo.");
        }
    } catch (java.util.InputMismatchException e) {
        // Capturar la excepción si no se ingresa un entero.
        System.out.println("Error: Ingrese un número entero. Intente de nuevo.");
        scanner.nextLine(); // Limpiar el buffer del scanner para evitar bucle infinito.
    }
}

            // Bucle que solicita y valida las notas de los cuatro cortes para cada estudiante.
    for (int j = 0; j < 4; j++) {
        System.out.println("Ingrese la nota del corte " + (j + 1) + " para el estudiante " + (i + 1) + ":");
        double nota = scanner.nextDouble();

    if (nota < 0 || nota > 100) {
        // Mostrar mensaje de error si la nota está fuera del rango permitido.
        System.out.println("Error: Ingrese una nota entre 0 y 100. Intente de nuevo.");
        j--; // Decrementar la variable de control para repetir la solicitud de la misma nota.
    } else {
        // Almacenar la nota en el arreglo de notasCortes si es válida.
        notasCortes[i][j] = nota;
    }
}

// Mensaje indicando que la información de estudiantes ha sido cargada exitosamente.
        System.out.println("Información de estudiantes cargada exitosamente.");
    }
}
    
    // Método privado utilizado para validar el NUIP (Número Único de Identificación Personal).
    private static long validarNUIP(Scanner scanner) {
    try {
        // Intentar leer un long desde la entrada del usuario.
        long documento = scanner.nextLong();

        // Verificar que la longitud del NUIP sea 10.
        if (String.valueOf(documento).length() == 10) {
            return documento;
        } else {
            // Indicar que hubo un error en la validación.
            return -1;
        }
    } catch (java.util.InputMismatchException e) {
        // Capturar la excepción si no se ingresa un long.
        scanner.nextLine(); // Limpiar el buffer del scanner.
        // Indicar que hubo un error en la validación.
        return -1;
    }
}
    
    // Método privado utilizado para obtener un nombre válido (que solo contenga letras).
    private static String obtenerNombreValido(Scanner scanner) {
    // Declaración de variables locales.
    String nombre;
    boolean esValido;

    // Bucle do-while para repetir la solicitud hasta que se ingrese un nombre válido.
    do {
        // Leer una línea de la entrada del usuario y eliminar espacios en blanco al inicio y al final.
        nombre = scanner.nextLine().trim();

        // Verificar que la cadena solo contenga letras.
        esValido = !nombre.isEmpty() && nombre.matches("^[a-zA-Z]+$");

        if (!esValido) {
            // Mostrar mensaje de error si el nombre no es válido.
            System.out.println("Error: Ingrese solo letras. Intente de nuevo.");
        }

    } while (!esValido);

    // Devolver el nombre válido.
    return nombre;
}


// Método privado utilizado para ordenar los vectores según el criterio de ordenamiento especificado.
// Método que realiza la ordenación de los vectores de estudiantes.
    private static void ordenarVectores(int criterioOrdenamiento) {
    // Verificar si se han cargado datos antes de intentar ordenar.
    if (numEstudiantes <= 0) {
        // Si no se han cargado datos, muestra un mensaje de error y retorna sin intentar ordenar.
        System.out.println("Error: No se han cargado datos. Cargue datos primero antes de intentar ordenar.");
        return;
    }

    // Implementar la lógica para ordenar los vectores por el código del estudiante de menor a mayor.
    for (int i = 0; i < numEstudiantes - 1; i++) {
        for (int j = 0; j < numEstudiantes - i - 1; j++) {
            // Variable para almacenar el resultado de la comparación.
            int comparacion;

            if (criterioOrdenamiento == 1) {
    // Si el criterio de ordenamiento es 1 (por código):
    // Utilizamos Integer.compare para comparar los códigos de dos estudiantes consecutivos.
    comparacion = Integer.compare(codigosEstudiantes[j], codigosEstudiantes[j + 1]);
} else {
    // Si el criterio de ordenamiento no es 1 (por identificación/NUIP u otro):
    // Utilizamos Long.compare para comparar las identificaciones (NUIP) de dos estudiantes consecutivos.
    comparacion = Long.compare(documentosEstudiantes[j], documentosEstudiantes[j + 1]);
}


            // Si la comparación es mayor que 0, significa que el código o NUIP es mayor.
            if (comparacion > 0) {
                // Intercambiar información de estudiantes si el código o NUIP es mayor.

                // Intercambiar codigosEstudiantes
// Almacenar temporalmente el código del estudiante en una variable temporal.
    int tempCodigo = codigosEstudiantes[j];

// Asignar el código del estudiante siguiente al lugar actual.
    codigosEstudiantes[j] = codigosEstudiantes[j + 1];

// Asignar el código almacenado temporalmente al lugar siguiente.
    codigosEstudiantes[j + 1] = tempCodigo;

// Intercambiar nombresEstudiantes
// Almacenar temporalmente el nombre del estudiante en una variable temporal.
    String tempNombre = nombresEstudiantes[j];

// Asignar el nombre del estudiante siguiente al lugar actual.
    nombresEstudiantes[j] = nombresEstudiantes[j + 1];

// Asignar el nombre almacenado temporalmente al lugar siguiente.
    nombresEstudiantes[j + 1] = tempNombre;

// Intercambiar apellidosEstudiantes
// Almacenar temporalmente el apellido del estudiante en una variable temporal.
    String tempApellido = apellidosEstudiantes[j];

// Asignar el apellido del estudiante siguiente al lugar actual.
    apellidosEstudiantes[j] = apellidosEstudiantes[j + 1];

// Asignar el apellido almacenado temporalmente al lugar siguiente.
    apellidosEstudiantes[j + 1] = tempApellido;

// Intercambiar documentosEstudiantes
// Almacenar temporalmente el documento del estudiante en una variable temporal.
    long tempDocumento = documentosEstudiantes[j];

// Asignar el documento del estudiante siguiente al lugar actual.
    documentosEstudiantes[j] = documentosEstudiantes[j + 1];

// Asignar el documento almacenado temporalmente al lugar siguiente.
    documentosEstudiantes[j + 1] = tempDocumento;

// Intercambiar notasCortes
// Almacenar temporalmente las notas de cortes del estudiante en una variable temporal.
    double[] tempNotas = notasCortes[j];

// Asignar las notas de cortes del estudiante siguiente al lugar actual.
    notasCortes[j] = notasCortes[j + 1];

// Asignar las notas de cortes almacenadas temporalmente al lugar siguiente.
    notasCortes[j + 1] = tempNotas;

            }
        }
    }
    // Mensaje indicando que los vectores han sido ordenados según el criterio seleccionado.
    System.out.println("Vectores ordenados según el criterio seleccionado.");
}


        // Método privado utilizado para calcular la definitiva y mostrar los resultados en una tabla ordenada.
    private static void calcularDefinitivaYPromedio() {
    // Imprimir encabezado del cálculo.
    System.out.println("Cálculo de definitiva y promedio del curso:");

    // Imprimir encabezados de la tabla en un formato ordenado.
    System.out.printf("%-15s %-25s %-25s %-20s %-15s %-15s %-15s %-15s %-15s%n",
            "Código", "Nombre", "Apellidos", "Documento", "Corte 1", "Corte 2", "Corte 3", "Corte 4", "Definitiva");

    // Variable para almacenar la suma de definitivas para calcular el promedio.
    double promedioCurso = 0;

    // Bucle para recorrer cada estudiante y calcular su definitiva.
    for (int i = 0; i < numEstudiantes; i++) {
        // Calcular la definitiva del estudiante actual.
        double definitiva = calcularNotaDefinitiva(
                notasCortes[i][0], notasCortes[i][1], notasCortes[i][2], notasCortes[i][3]);

        // Sumar la definitiva al promedio del curso.
        promedioCurso += definitiva;

        // Imprimir los datos del estudiante en la tabla.
        System.out.printf("%-15d %-25s %-25s %-20d %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f%n",
                codigosEstudiantes[i], nombresEstudiantes[i], apellidosEstudiantes[i],
                documentosEstudiantes[i], notasCortes[i][0], notasCortes[i][1],
                notasCortes[i][2], notasCortes[i][3], definitiva);
    }

    // Calcular y mostrar el promedio del curso si hay estudiantes.
    if (numEstudiantes > 0) {
        promedioCurso /= numEstudiantes;
        System.out.println("Promedio del curso: " + promedioCurso);
    } else {
        // Mensaje indicando que no hay estudiantes para calcular el promedio.
        System.out.println("No hay estudiantes para calcular el promedio del curso.");
    }
}

    // Método privado utilizado para calcular la nota definitiva de un estudiante.
    private static double calcularNotaDefinitiva(double corte1, double corte2, double corte3, double corte4) {
    // Calcular la nota definitiva utilizando los pesos asignados a cada corte.
    double definitiva = (corte1 * PESO_CORTE_1) + (corte2 * PESO_CORTE_2) + (corte3 * PESO_CORTE_3) + (corte4 * PESO_CORTE_4);
    
    // Redondear la definitiva a dos decimales utilizando el método redondearADosDecimales.
    return redondearADosDecimales(definitiva);
}

// Método privado utilizado para redondear un valor a dos decimales.
    private static double redondearADosDecimales(double valor) {
        // Implementar la lógica para redondear un valor a dos decimales.
    return Math.round(valor * 100.0) / 100.0;
    }
}
