package per.d3249.curp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CURPGeneratorCasosRealesTest {

    @Test
    @DisplayName("Se espera el CURP CUCA891203MDFRLN05")
    void caso1() {

        // Datos de entrada
        String nombre = "María de los Ángeles";
        String apellidoPaterno = "Cruz";
        String apellidoMaterno = "Colón";
        String ano = "1989";
        String mes = "12";
        String dia = "3";
        Sexo sexo = Sexo.MUJER;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String curpEsperado = "CUCA891203MDFRLN05";

        assertEquals(curpEsperado,
                CURPGenerator.generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad));
    }

    @Test
    @DisplayName("Se espera el CURP AAAP790505HDFMGD05")
    void caso2() {

        // Datos de entrada
        String nombre = "Pedro Jesús";
        String apellidoPaterno = "Amador";
        String apellidoMaterno = "Aguilera";
        String ano = "1979";
        String mes = "5";
        String dia = "5";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.DISTRITO_FEDERAL;

        // CURP esperado
        String curpEsperado = "AAAP790505HDFMGD05";

        assertEquals(curpEsperado,
                CURPGenerator.generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad));
    }

    @Test
    @DisplayName("Se espera el CURP AERS840919MMCMMS08")
    void caso3() {

        // Datos de entrada
        String nombre = "Susana Alejandra";
        String apellidoPaterno = "Amezcua";
        String apellidoMaterno = "Ramírez";
        String ano = "1984";
        String mes = "9";
        String dia = "19";
        Sexo sexo = Sexo.MUJER;
        Entidad entidad = Entidad.MEXICO;

        // CURP esperado
        String curpEsperado = "AERS840919MMCMMS08";

        assertEquals(curpEsperado,
                CURPGenerator.generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad));
    }

    @Test
    @DisplayName("Se espera el CURP PXNE660720HMCXTN06")
    void caso5() {

        // Datos de entrada
        String nombre = "Enrique";
        String apellidoPaterno = "Peña";
        String apellidoMaterno = "Nieto";
        String ano = "1966";
        String mes = "7";

        String dia = "20";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.MEXICO;

        // CURP esperado
        String curpEsperado = "PXNE660720HMCXTN06";

        assertEquals(curpEsperado,
                CURPGenerator.generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad));
    }

    @Test
    @DisplayName("Se espera el CURP CXCA770207HNENSL05")
    void caso6() {

        // Datos de entrada
        String nombre = "Alex Humberto";
        String apellidoPaterno = "Canizales";
        String apellidoMaterno = "Castro";
        String ano = "1977";
        String mes = "2";
        String dia = "7";
        Sexo sexo = Sexo.HOMBRE;
        Entidad entidad = Entidad.NACIDO_EN_EL_EXTRANJERO;

        // CURP esperado
        String curpEsperado = "CXCA770207HNENSL05";

        assertEquals(curpEsperado,
                CURPGenerator.generar(nombre, apellidoPaterno, apellidoMaterno, ano, mes, dia, sexo, entidad));
    }
}
