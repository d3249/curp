package per.d3249.curp;

import static per.d3249.curp.ExpresionesRegulares.primerConsonanteInterna;
import static per.d3249.curp.ExpresionesRegulares.primeraLetra;
import static per.d3249.curp.ExpresionesRegulares.primeraVocalInterna;
import static per.d3249.curp.ExpresionesRegulares.reemplazarCaracteresEspeciales;
import static per.d3249.curp.CalculadorDigitoVerificador.calcularDigitoVerificador;

public class CURPGenerator {

    public static final String DEFAULT_CHARACTER = "X";

    public static String generar(String nombres, String apellidoPaterno, String apellidoMaterno, String ano, String mes,
            String dia, Sexo sexo, Entidad entidad) {
        StringBuilder curpStringBuilder = new StringBuilder();

        // Convertir todo a mayúsculas
        nombres = limpiar(nombres);
        apellidoPaterno = limpiar(apellidoPaterno);
        apellidoMaterno = limpiar(apellidoMaterno);

        apellidoPaterno = limpiarApellido(apellidoPaterno);
        apellidoMaterno = limpiarApellido(apellidoMaterno);

        nombres = limpiarNombres(nombres);

        // Corregir mes y año en caso de tener un sólo dígito
        mes = corregirLongitud(mes);
        dia = corregirLongitud(dia);

        // Letra inicial del primer apellido
        curpStringBuilder.append(primeraLetra(apellidoPaterno))
                // primera vocal interna del primer apellido
                .append(primeraVocalInterna(apellidoPaterno))
                // primera letra del segundo apellido
                .append(primeraLetra(apellidoMaterno))
                // primera letra del nombre
                .append(primeraLetra(nombres));

        // Se hacen las validaciones hasta este punto
        curpStringBuilder = reemplazarCaracteresEspeciales(curpStringBuilder);

        // dos dígitos para el año
        curpStringBuilder.append(ano.substring(2, 4))
                // dos dígitos para el mes
                .append(mes)
                // dos dígitos para el día
                .append(dia)
                // caracter de sexo
                .append(sexo.CARACTER)
                // código de entidad
                .append(entidad.CLAVE)
                // primera consonante interna del primer apellido
                .append(primerConsonanteInterna(apellidoPaterno))
                // primera consonante interna del segundo apellido
                .append(primerConsonanteInterna(apellidoMaterno))
                // primera consonante interna del nombre
                .append(primerConsonanteInterna(nombres))
                // identificador de siglo
                .append(identificadorSiglo(ano))
                // dígito verificador
                .append(calcularDigitoVerificador(curpStringBuilder));

        return curpStringBuilder.toString();
    }

    private static String limpiarNombres(String nombres) {

        String[] nombresArray = nombres.split("\\s");

        if (nombresArray.length == 1) {
            return nombresArray[0];
        }

        for (String nombre : nombresArray) {
            if (!Catalogos.LISTA_PRIMEROS_NOMBRES_IGNORADOS.contains(nombre)) {
                return nombre;
            }
        }

        return null;

    }

    private static String limpiarApellido(String apellido) {
        if (apellido.charAt(0) == 'Ñ') {
            apellido = apellido.replaceFirst("Ñ", "X");
        }
        return apellido;
    }

    private static String identificadorSiglo(String ano) {
        String siglo = ano.substring(0, 2);

        switch (siglo) {
        case "19":
            return "0";
        case "20":
            return "A";
        default:
            return null;
        }
    }

    private static String corregirLongitud(String valor) {
        if (valor.length() == 2) {
            return valor;
        }

        return "0" + valor;
    }

    private static String limpiar(String palabra) {
        // Se convierte a mayúsculas
        palabra = palabra.toUpperCase();

        // Se eliminan acentos y diéresis
        palabra = palabra.replaceAll("Á", "A");
        palabra = palabra.replaceAll("É", "E");
        palabra = palabra.replaceAll("Í", "I");
        palabra = palabra.replaceAll("Ó", "O");
        palabra = palabra.replaceAll("(?:Ú|Ü)", "U");

        return palabra;

    }

}
