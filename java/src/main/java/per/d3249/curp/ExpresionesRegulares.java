package per.d3249.curp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExpresionesRegulares {

	private static final Pattern PRIMER_VOCAL_INTERNA_PATTERN = Pattern
			.compile("^.[B-DF-HJ-NP-TV-ZÑ]*([^B-DF-HJ-NP-TV-ZÑ])");
	private static final Pattern PRIMER_CONSONANTE_INTERNA_PATTERN = Pattern
			.compile("^.[^B-DF-HJ-NP-TV-ZÑ]*([B-DF-HJ-NP-TV-ZÑ])");
	private static final Pattern CARACTER_ESPECIAL_PATTERN = Pattern.compile("[^A-ZÑ\\s]");
	public static final String CARACTER_DEFAULT = "X";

	public static String primeraLetra(String palabra) {
		if (palabra.isEmpty()) {
			return CARACTER_DEFAULT;
		}
		return palabra.substring(0, 1);
	}

	public static String primerConsonanteInterna(String palabra) {
		Matcher matcher = PRIMER_CONSONANTE_INTERNA_PATTERN.matcher(palabra);

		if (matcher.find()) {
			String valor = matcher.group(1);
			if (valor.equals("Ñ")) {
				return CARACTER_DEFAULT;
			} else {
				return matcher.group(1);
			}
		} else {
			return CARACTER_DEFAULT;
		}

	}

	public static String primeraVocalInterna(String palabra) {
		Matcher matcher = PRIMER_VOCAL_INTERNA_PATTERN.matcher(palabra);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return CARACTER_DEFAULT;
		}

	}

	public static StringBuilder reemplazarCaracteresEspeciales(StringBuilder palabra) {

		Matcher matcher = CARACTER_ESPECIAL_PATTERN.matcher(palabra);
		return new StringBuilder(matcher.replaceAll(CARACTER_DEFAULT));

	}
}
