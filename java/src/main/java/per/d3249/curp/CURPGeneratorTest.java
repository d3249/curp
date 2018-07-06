package per.d3249.curp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CURPGeneratorTest {

    @Test
    @DisplayName("Caso básico nacido antes del año 2000")
    public void casoBasico() throws Exception {
        // Datos de entrada
        String nombre = "Edgar Arturo";
        String apellidoPaterno = "Ramos";
        String apellidoMaterno = "Rambaud";
        String ano = "1985";
        String mes = "10";
        String dia = "23";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.ZACATECAS;

        // CURP esperado
        String curpEsperado = "RARE851023HZSMMD03";

        assertEquals(curpEsperado,
                CURPGenerator.generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad));
    }

    @Test
    @DisplayName("Caso básico nacido después del año 2000")
    void casoBasico2() throws Exception {
        // Datos de entrada
        String nombre = "Oscar";
        String apellidoPaterno = "Joaquín";
        String apellidoMaterno = "Vázquez";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String curpEsperado = "JOVO000409HDFQZSA8";

        assertEquals(curpEsperado,
                CURPGenerator.generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad));
    }

    @Test
    @DisplayName("Si la primera letra de un apellido es Ñ debe reemplazarse por X")
    void primerCasoExcepcion() throws Exception {

        // Datos de entrada
        String nombre = "Alberto";
        String apellidoPaterno = "Ñanando";
        String apellidoMaterno = "Rodríguez";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "XARA";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(0, 4));
    }

    @Test
    @DisplayName("En caso de nombre compuesto se usa el primero a menos que esté en la lista de ignorados")
    void segundoCasoExcepcionEjemplo1() throws Exception {

        // Datos de entrada
        String nombre = "María Luisa";
        String apellidoPaterno = "Pérez";
        String apellidoMaterno = "Hernández";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.MUJER;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "PEHL";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(0, 4));
    }

    @Test
    @DisplayName("En caso de nombre compuesto se usa el primero a menos que esté en la lista de ignorados")
    void segundoCasoExcepcionEjemplo2() throws Exception {

        // Datos de entrada
        String nombre = "Luis Enrique";
        String apellidoPaterno = "Romero";
        String apellidoMaterno = "Palazuelos";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "ROPL";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(0, 4));
    }

    @Test
    @DisplayName("En caso de nombre compuesto se usa el primero a menos que esté en la lista de ignorados")
    void segundoCasoExcepcionEjemplo3() throws Exception {

        // Datos de entrada
        String nombre = "José";
        String apellidoPaterno = "Romero";
        String apellidoMaterno = "Palazuelos";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "ROPJ";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(0, 4));
    }

    @Test
    @DisplayName("Si en el nombre o apellido aparece un caracter especial asignable al CURP, se reemplaza por X")
    void tercerCasoExcepcionEjemplo() throws Exception {

        // Datos de entrada
        String nombre = "Juan José";
        String apellidoPaterno = "D/Amico";
        String apellidoMaterno = "Álvarez";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "DXAJ";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(0, 4));
    }
}
