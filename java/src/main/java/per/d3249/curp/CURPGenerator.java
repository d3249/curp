package per.d3249.curp;

import static per.d3249.curp.CalculadorDigitoVerificador.calcularDigitoVerificador;
import static per.d3249.curp.ExpresionesRegulares.primerConsonanteInterna;
import static per.d3249.curp.ExpresionesRegulares.primeraLetra;
import static per.d3249.curp.ExpresionesRegulares.primeraVocalInterna;
import static per.d3249.curp.ExpresionesRegulares.reemplazarCaracteresEspeciales;

public class CURPGenerator {

	private static final int MINIMO_VALOR_RANGOS = 1;
	private static final int MAXIMO_VALOR_DIA = 31;
	private static final int MAXIMO_VALOR_MES = 12;
	private static final String PLANTILLA_ERROR_ELIMINAR_COMPUESTOS = "Error al eliminar elementos compuestos de nombre/apellido [%s]";
	private static final String PLANTILLA_ERROR_LONGITUD_FECHA = "Longitud inválida para día/mes [%s]";
	private static final String PLANTILLA_MENSAJE_IDENTIFICADOR = "No se pudo determinar el identificador de siglo [%s]";

	private CURPGenerator() {
	}

	public static String generar(String nombres, String apellidoPaterno, String apellidoMaterno, String ano, String mes,
			String dia, Sexo sexo, Entidad entidad) {

		validarNulos(nombres, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad);

		StringBuilder curpStringBuilder = new StringBuilder();

		// Convertir a mayúsculas
		nombres = limpiar(nombres);
		apellidoPaterno = limpiar(apellidoPaterno);
		apellidoMaterno = limpiar(apellidoMaterno);

		apellidoPaterno = limpiarApellido(apellidoPaterno);
		apellidoMaterno = limpiarApellido(apellidoMaterno);

		curpStringBuilder.append(primeraLetra(apellidoPaterno)).append(primeraVocalInterna(apellidoPaterno))
				.append(primeraLetra(apellidoMaterno)).append(primeraLetra(nombres));

		curpStringBuilder = reemplazarCaracteresEspeciales(curpStringBuilder);
		curpStringBuilder = eliminarPalabrasAltisonantes(curpStringBuilder);

		// Corrección de longitudes
		mes = corregirLongitud(mes);
		dia = corregirLongitud(dia);

		validaNumero(ano);
		validaNumero(mes);
		validaNumero(dia);

		validarRango(dia, MAXIMO_VALOR_DIA);
		validarRango(mes, MAXIMO_VALOR_MES);

		//@formatter:off
		// dos dígitos para el año
		curpStringBuilder.append(ano.substring(2, 4))
		.append(mes)
		.append(dia)
		.append(sexo.caracter)
		.append(entidad.clave)
		.append(primerConsonanteInterna(apellidoPaterno))
		.append(primerConsonanteInterna(apellidoMaterno))
		.append(primerConsonanteInterna(nombres))
		.append(identificadorSiglo(ano))
		.append(calcularDigitoVerificador(curpStringBuilder));
		//@formatter:on

		return curpStringBuilder.toString();
	}

	private static void validarRango(String valor, int maximo) {
		int valorNumero = Integer.parseInt(valor);

		if (valorNumero < MINIMO_VALOR_RANGOS || valorNumero > maximo) {
			throw new CURPGeneratorException(
					String.format("Valor inválido [%s]. Debe ser entre 1 y %d", valor, maximo));
		}
	}

	private static void validarNulos(Object... objects) {

		for (Object object : objects) {
			if (object == null) {
				throw new CURPGeneratorException("Todos los campos son obligatorios");
			}
		}
	}

	private static void validaNumero(String numero) {
		try {
			Integer.valueOf(numero);
		} catch (NumberFormatException e) {
			throw new CURPGeneratorException(String.format("El valor de día/mes/año debe ser entero [%s]", numero), e);
		}

	}

	private static String limpiarApellido(String apellido) {

		if (apellido.isEmpty()) {
			return apellido;
		}

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
			throw new CURPGeneratorException(String.format(PLANTILLA_MENSAJE_IDENTIFICADOR, ano));
		}
	}

	private static String corregirLongitud(String valor) {
		int longitud = valor.length();

		if (longitud == 2) {
			return valor;
		} else if (longitud > 2) {
			throw new CURPGeneratorException(String.format(PLANTILLA_ERROR_LONGITUD_FECHA, valor));
		}

		return "0" + valor;
	}

	private static String limpiar(String palabra) {
		// Se convierte a mayúsculas
		String palabraCorregida = palabra.toUpperCase();

		palabraCorregida = reemplazarAcentos(palabraCorregida);

		return eliminarCompuestos(palabraCorregida);

	}

	private static String eliminarCompuestos(String palabra) {
		String[] componentes = palabra.split("\\s");

		if (componentes.length == 1) {
			return componentes[0];
		}

		for (String nombre : componentes) {
			if (!Catalogos.LISTA_PRIMEROS_NOMBRES_IGNORADOS.contains(nombre)) {
				return nombre;
			}
		}
		throw new CURPGeneratorException(String.format(PLANTILLA_ERROR_ELIMINAR_COMPUESTOS, palabra));

	}

	private static String reemplazarAcentos(String palabraCorregida) {
		palabraCorregida = palabraCorregida.replaceAll("Á", "A");
		palabraCorregida = palabraCorregida.replaceAll("É", "E");
		palabraCorregida = palabraCorregida.replaceAll("Í", "I");
		palabraCorregida = palabraCorregida.replaceAll("Ó", "O");
		palabraCorregida = palabraCorregida.replaceAll("(?:Ú|Ü)", "U");
		return palabraCorregida;
	}

	private static StringBuilder eliminarPalabrasAltisonantes(StringBuilder palabra) {

		if (Catalogos.LISTA_PALABRAS_IMPROPIAS.contains(palabra.toString())) {
			return palabra.replace(1, 2, ExpresionesRegulares.CARACTER_DEFAULT);
		} else {
			return palabra;
		}

	}

}
