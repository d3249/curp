package per.d3249.curp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CURPGeneratorEspecificacionesTest {

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

    @Test
    @DisplayName("Se debe reemplazar el uso de diéresis por U")
    void cuartoCasoDeExepcion() throws Exception {
        // Datos de entrada
        String nombre = "Juan José";
        String apellidoPaterno = "Argüello";
        String apellidoMaterno = "Álvarez";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "AUAJ";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(0, 4));

    }

    @Test
    @DisplayName("Cuando hay apellidos compuestos se usa la primera palabra del mismo")
    void quintoCasoDeExepcion() throws Exception {
        // Datos de entrada
        String nombre = "Rocío";
        String apellidoPaterno = "Rivapalacio";
        String apellidoMaterno = "Cruz";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "RICR";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(0, 4));

    }

    @Test
    @DisplayName("Cuando es apellido compuesto y la primera palabra es una preposición, conjuncuón o contracción, se usa la siguiente")
    void sextoCasoDeExepcion() throws Exception {
        // Datos de entrada
        String nombre = "Carlos";
        String apellidoPaterno = "Mc Gregor";
        String apellidoMaterno = "López";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "GELC";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(0, 4));

    }

    @Test
    @DisplayName("Si las cuatro primeras letras forman palabas altisonantes, se reemplaza la segunda letra por X")
    void septimoCasoDeExcepcion() throws Exception {

        // Datos de entrada
        String nombre = "Ofelia";
        String apellidoPaterno = "Pedrero";
        String apellidoMaterno = "Dominguez";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "PXDO";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(0, 4));

    }

    @Test
    @DisplayName("Con dos apellidos, si el primero no tiene vocal interna, se usa X")
    void octavoCasoDeExcepcion() throws Exception {

        // Datos de entrada
        String nombre = "Andres";
        String apellidoPaterno = "Ich";
        String apellidoMaterno = "Rodríguez";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "IXRA";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(0, 4));

    }

    @Test
    @DisplayName("Si sólo existe un apellido se asigna X en el tercer caracter")
    void novenoCasoDeExcepcion() throws Exception {

        // Datos de entrada
        String nombre = "Luis";
        String apellidoPaterno = "Pérez";
        String apellidoMaterno = "";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "PEXL";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(0, 4));

    }

    @Test
    @DisplayName("Cuando la primer consonante interna es Ñ se asigna X")
    void decimoCasoDeExcepcion() throws Exception {

        // Datos de entrada
        String nombre = "Alberto";
        String apellidoPaterno = "Oñate";
        String apellidoMaterno = "Rodríguez";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "XDL";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(13, 16));

    }

    @Test
    @DisplayName("Cuando no hay consonantes internas, se asigna X")
    void decimoprimerCasoDeExcepcion1() throws Exception {

        // Datos de entrada
        String nombre = "Andrés";
        String apellidoPaterno = "Po";
        String apellidoMaterno = "Barrios";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "XRN";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(13, 16));

    }

    @Test
    @DisplayName("Cuando no hay consonantes internas, se asigna X")
    void decimoprimerCasoDeExcepcion2() throws Exception {

        // Datos de entrada
        String nombre = "Andrés";
        String apellidoPaterno = "Barrios";
        String apellidoMaterno = "Po";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "RXN";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(13, 16));

    }

    @Test
    @DisplayName("Cuando no hay consonantes internas, se asigna X")
    void decimoprimerCasoDeExcepcion3() throws Exception {

        // Datos de entrada
        String nombre = "Aaa";
        String apellidoPaterno = "Barrios";
        String apellidoMaterno = "Barrios";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "RRX";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(13, 16));

    }

    @Test
    @DisplayName("Cuando hay un único apellido, se asigna X")
    void decimosegundoCasoDeExcepcion() throws Exception {

        // Datos de entrada
        String nombre = "Leticia";
        String apellidoPaterno = "Luna";
        String apellidoMaterno = "";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "NXT";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(13, 16));

    }

    @Test
    @DisplayName("Con un nombre compuesto se usa el primer nombre excepto si es María o José (u otro de los ignorados)")
    void decimotercerCasoDeExcepcion() throws Exception {

        // Datos de entrada
        String nombre = "Ma. de los Ángeles";
        String apellidoPaterno = "Moreno";
        String apellidoMaterno = "Sánchez";
        String ano = "2000";
        String mes = "4";
        String dia = "9";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String valorEsperado = "RNN";

        assertEquals(valorEsperado, CURPGenerator
                .generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad).substring(13, 16));

    }
}
