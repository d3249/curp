package per.d3249.curp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpresionesRegulares {

    private static final Pattern PRIMER_VOCAL_INTERNA_PATTERN = Pattern
            .compile("^.[B-DF-HJ-NP-TV-ZÑ]*([^B-DF-HJ-NP-TV-ZÑ])");
    private static final Pattern PRIMER_CONSONANTE_INTERNA_PATTERN = Pattern
            .compile("^.[^B-DF-HJ-NP-TV-ZÑ]*([B-DF-HJ-NP-TV-ZÑ])");
    private static final Pattern CARACTER_ESPECIAL_PATTERN = Pattern.compile("[^A-ZÑ\\s]");
    private static final String CARACTER_DEFAULT = "X";

    public static String primeraLetra(String palabra) {
        return palabra.substring(0, 1);
    }

    public static String primerConsonanteInterna(String palabra) {
        Matcher matcher = PRIMER_CONSONANTE_INTERNA_PATTERN.matcher(palabra);

        matcher.find();

        String vocal = matcher.group(1);

        return vocal;
    }

    public static String primeraVocalInterna(String palabra) {
        Matcher matcher = PRIMER_VOCAL_INTERNA_PATTERN.matcher(palabra);

        matcher.find();

        String vocal = matcher.group(1);

        return vocal;

    }

    public static StringBuilder reemplazarCaracteresEspeciales(StringBuilder palabra) {

        Matcher matcher = CARACTER_ESPECIAL_PATTERN.matcher(palabra);
        return new StringBuilder(matcher.replaceAll(CARACTER_DEFAULT));

    };
}
