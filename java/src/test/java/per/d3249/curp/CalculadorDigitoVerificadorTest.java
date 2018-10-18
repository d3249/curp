package per.d3249.curp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadorDigitoVerificadorTest {
	private static final String DUMMY_CURP = "ABCD123456EFGHIJA";
	private static final String CHECKSUM = "5";

	@Test
	@DisplayName("Digito verificador con un string")
	void metodoString() {
		String digitoVerificador = CalculadorDigitoVerificador.calcularDigitoVerificador(DUMMY_CURP);

		assertThat(digitoVerificador, equalTo(CHECKSUM));
	}

	@Test
	@DisplayName("Digito verificador con un string builder")
	void metodoStringBuilder() {
		String digitoVerificador = CalculadorDigitoVerificador.calcularDigitoVerificador(new StringBuilder(DUMMY_CURP));

		assertThat(digitoVerificador, equalTo(CHECKSUM));
	}

	@Test
	@DisplayName("Ignora el identificador de siglo")
	void ignoraIdentificadorDeSiglo() {
		String modificado = DUMMY_CURP.substring(0, DUMMY_CURP.length() - 1) + "0";

		String digitoVerificador = CalculadorDigitoVerificador.calcularDigitoVerificador(modificado);

		assertThat(digitoVerificador, equalTo(CHECKSUM));
	}

}
