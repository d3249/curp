package per.d3249.curp;

public class CalculadorDigitoVerificador {

	// Está función fue tomada del script que se carga en el sitio
	// https://consultas.curp.gob.mx/CurpSP/gobmx/inicio.jsp
	//
	// Existe un bug, ya que no diferencia entre A y 0 (cero) en el
	// primer dígito de verificación v.g. AX = 0X
	//
	// Mayo de 2018

	private CalculadorDigitoVerificador() {
	}

	public static String calcularDigitoVerificador(String curp) {
		return calcularDigitoVerificador(new StringBuilder(curp));
	}

	public static String calcularDigitoVerificador(StringBuilder curp) {

		String chrCaracter = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

		Integer[] intFactor = new Integer[17];
		Integer lngSuma = 0;
		Integer lngDigito = 0;

		for (int i = 0; i < curp.length(); i++) {
			intFactor[i] = chrCaracter.indexOf(curp.charAt(i));
		}

		for (int k = 0; k < curp.length(); k++) {

			lngSuma = lngSuma + ((intFactor[k]) * (18 - k));
		}

		lngDigito = (10 - (lngSuma % 10));

		if (lngDigito == 10) {
			lngDigito = 0;
		}

		return lngDigito.toString();

	}

}
