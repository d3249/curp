package per.d3249.curp;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CURPGeneratorExcepcionesTest {

	@Test
	@DisplayName("Valida nulos")
	public void validaNulos() {

		CURPGeneratorException excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar(null, "González", "González", "1992", "1", "1", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});
		assertThat(excepcion.getMessage(), StringContains.containsString("Todos los campos son obligatorios"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", null, "González", "1992", "1", "1", Sexo.HOMBRE, Entidad.DISTRITO_FEDERAL);
		});
		assertThat(excepcion.getMessage(), StringContains.containsString("Todos los campos son obligatorios"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", null, "1992", "1", "1", Sexo.HOMBRE, Entidad.DISTRITO_FEDERAL);
		});
		assertThat(excepcion.getMessage(), StringContains.containsString("Todos los campos son obligatorios"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", null, "1", "1", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});
		assertThat(excepcion.getMessage(), StringContains.containsString("Todos los campos son obligatorios"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1992", null, "1", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});
		assertThat(excepcion.getMessage(), StringContains.containsString("Todos los campos son obligatorios"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1992", "1", null, Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});
		assertThat(excepcion.getMessage(), StringContains.containsString("Todos los campos son obligatorios"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1992", "1", "1", null, Entidad.DISTRITO_FEDERAL);
		});
		assertThat(excepcion.getMessage(), StringContains.containsString("Todos los campos son obligatorios"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1992", "1", "1", Sexo.HOMBRE, null);
		});
		assertThat(excepcion.getMessage(), StringContains.containsString("Todos los campos son obligatorios"));

	}

	@Test
	@DisplayName("El año debe ser entre 1900 y 2099")
	public void errorPorSiglo() {

		CURPGeneratorException excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1899", "1", "1", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});

		assertThat(excepcion.getMessage(),
				StringContains.containsString("No se pudo determinar el identificador de siglo"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "2100", "1", "1", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});

		assertThat(excepcion.getMessage(),
				StringContains.containsString("No se pudo determinar el identificador de siglo"));
	}

	@Test
	@DisplayName("No se puede dar un valor de día de más de dos caracteres")
	public void longitudMesAno() {

		CURPGeneratorException excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1999", "111", "1", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});

		assertThat(excepcion.getMessage(), StringContains.containsString("Longitud inválida para día/mes"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1999", "1", "111", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});

		assertThat(excepcion.getMessage(), StringContains.containsString("Longitud inválida para día/mes"));

	}

	@Test
	@DisplayName("Año més y día deben ser enteros")
	public void validaNumeros() {

		CURPGeneratorException excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "x", "1", "1", Sexo.HOMBRE, Entidad.DISTRITO_FEDERAL);
		});

		assertThat(excepcion.getMessage(), StringContains.containsString("El valor de día/mes/año debe ser entero"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1999", "x", "1", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});

		assertThat(excepcion.getMessage(), StringContains.containsString("El valor de día/mes/año debe ser entero"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1999", "1", "x", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});

		assertThat(excepcion.getMessage(), StringContains.containsString("El valor de día/mes/año debe ser entero"));

	}

	@Test
	@DisplayName("El día debe ser un número entero entre 1 y 31 y el mes entre 1 y 12")
	public void valorDiaMes() {

		CURPGeneratorException excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1999", "1", "0", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});

		assertThat(excepcion.getMessage(), StringContains.containsString("Valor inválido [00]. Debe ser entre 1 y 31"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1999", "1", "32", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});

		assertThat(excepcion.getMessage(), StringContains.containsString("Valor inválido [32]. Debe ser entre 1 y 31"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1999", "0", "5", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});

		assertThat(excepcion.getMessage(), StringContains.containsString("Valor inválido [00]. Debe ser entre 1 y 12"));

		excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("Juan", "González", "González", "1999", "13", "5", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});

		assertThat(excepcion.getMessage(), StringContains.containsString("Valor inválido [13]. Debe ser entre 1 y 12"));
	}

	@Test
	@DisplayName("Error si elimna todos los elementos de un nombre compuesto")
	public void errorSiEliminaTodosApellidos() {

		CURPGeneratorException excepcion = assertThrows(CURPGeneratorException.class, () -> {
			CURPGenerator.generar("J Ma", "González", "González", "1999", "13", "5", Sexo.HOMBRE,
					Entidad.DISTRITO_FEDERAL);
		});

		assertThat(excepcion.getMessage(),
				StringContains.containsString("Error al eliminar elementos compuestos de nombre/apellido"));
	}

}
