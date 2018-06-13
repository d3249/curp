'use strict';

var file = './curp.js';


var expect = require('chai').expect;

// Grupo 1: Primeras cuatro letras
// Grupo 2: Fecha de nacimiento
// Grupo 3: Sexo
// Grupo 4: Estado
// Grupo 5: Letras Internas
// Grupo 6: Homoclave
var estructuraCURP = /^([A-Z]{4})(\d{6})([HM])([A-Z]{2})([A-Z]{3})([A-Z0-9]{2})$/;

var Generador = require(file);

describe('Caso normal (nacido antes del año 2000)', function(){
	it('Debe generar la CURP según la norma general', function(){
		var esperado = 'RARE851023HZSMMD03';

		var nombre = 'edgar arturo';
		var apPat = 'ramos';
		var apMat = 'rambaud';
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		expect(Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad )).to.equal(esperado);

	});

});

describe('Caso normal (nacido después del año 2000)', function(){
	it('Debe generar la CURP según la norma general', function(){
		var esperado = 'JOVO000409HDFQZSA8';

                var nombre = 'oscar';
	        var apPat = 'joaquin';
	        var apMat = 'vazquez';
	        var ano = '2000';
       	        var mes = '4';
	        var dia = '9'
	        var entidad = 'distrito federal'
	        var sexo = 'H';

		expect(Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad )).to.equal(esperado);

	});

});


describe('Primer caso de excepción', function(){
	it('Si la primer letra de algún apellido es Ñ debe reemplazarse por X', function(){

		var esperado = 'XARA';

		//Valores de prueba
		var nombre = 'Alberto';
		var apPat = 'Ñanando';
		var apMat = 'Rodriguez';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[1]).to.equal(esperado);


	});
});

describe("Segundo caso de excepción, caso 1", function(){

	it("Cuando el nombre sea compuesto, se usa el primero a menos que no sea uno de MARIA, MA, MA., JOSE, J, J., en cuyo caso, se usa el segundo",function(){

		var esperado = 'PEHL';

		//Valores de prueba
		var nombre = 'Maria Luisa';
		var apPat = 'Perez';
		var apMat = 'Hernandez';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[1]).to.equal(esperado);
	});

});

describe("Segundo caso de excepción, caso 2", function(){

	it("Cuando el nombre sea compuesto, se usa el primero a menos que no sea uno de MARIA, MA, MA., JOSE, J, J., en cuyo caso, se usa el segundo",function(){

		var esperado = 'ROPL';

		//Valores de prueba
		var nombre = 'Luis Enrique';
		var apPat = 'Romero';
		var apMat = 'Palazuelos';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[1]).to.equal(esperado);
	});

});

describe("Segundo caso de excepción, caso 3", function(){

	it("Cuando el nombre sea compuesto, se usa el primero a menos que no sea uno de MARIA, MA, MA., JOSE, J, J., en cuyo caso, se usa el segundo",function(){

		var esperado = 'ROPJ';

		//Valores de prueba
		var nombre = 'Jose';
		var apPat = 'Romero';
		var apMat = 'Palazuelos';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[1]).to.equal(esperado);
	});

});

describe('Tercer caso de excepción', function(){
	it('Si en el nombre o apellido aparece un caracter especial que será asignado al CURP, este se reemplaza con X', function(){
		var esperado = 'DXAJ';

		//Valores de prueba
		var nombre = 'Jan Jose';
		var apPat = 'D/amico';
		var apMat = 'Alvarez';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[1]).to.equal(esperado);

	});
});

describe('Cuarto caso de excepción', function(){
	it('Cuando se presenten diéresis (ü) en el nombre o apellidos, habrá que omitirlas usando sólo la letra u', function(){
		var esperado = "AUAJ";

		//Valores de prueba
		var nombre = 'Jan Jose';
		var apPat = 'Argüello';
		var apMat = 'Alvarez';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[1]).to.equal(esperado);

	});
});

describe('Quinto caso de excepción', function(){
	it('Cuando es un apellido compuesto, se usa la primera palabra del mismo', function(){
		var esperado = "RICR";

		//Valores de prueba
		var nombre = 'Rocio';
		var apPat = 'Riva Palacio';
		var apMat = 'Cruz';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[1]).to.equal(esperado);

	});
});

describe('Sexto caso de excepción', function(){
	it('Cuando es un apellido compuesto y la primera palabra es una preposición, conjunción o contraccion, se usa la siguiente', function(){
		var esperado = "GELC";

		//Valores de prueba
		var nombre = 'Carlos';
		var apPat = 'Mc Gregor';
		var apMat = 'Lopez';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[1]).to.equal(esperado);

	});

});

describe('Séptimo caso de excepción', function(){
	it('Si las cuatro letras forman una palabra altisonante, la segunda letra se reemplaza por X', function(){
		var esperado = "PXDO";

		//Valores de prueba
		var nombre = 'Ofelia';
		var apPat = 'Pedrero';
		var apMat = 'Dominguez';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[1]).to.equal(esperado);

	});
});

describe('Octavo caso de excepción', function(){
	it('Con dos apellidos, si el primero no tiene vocal interna, se asigna X', function(){

		var esperado = "IXRA";

		//Valores de prueba
		var nombre = 'Andres';
		var apPat = 'Ich';
		var apMat = 'Rodriguez';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[1]).to.equal(esperado);
	});
});

describe('Noveno caso de excepción', function(){
	it('Si sólo existe un apellido, se asigna X', function(){

		var esperado = "PEXL";

		//Valores de prueba
		var nombre = 'Luis';
		var apPat = 'Perez';
		var apMat = '';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[1]).to.equal(esperado);
	});
});

describe('Décimo caso de excepción', function(){
	it('Cuando la primera consonante interna es Ñ se asigna X', function(){

		var esperado = "XDL";

		//Valores de prueba
		var nombre = 'Alberto';
		var apPat = 'Oñate';
		var apMat = 'Rodriguez';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[5]).to.equal(esperado);
	});
});

describe('Decimoprimer caso de excepción, caso 1', function(){
	it('Cuando no existen consonantes internas en nombre o apellido se asigna X', function(){

		var esperado = "XRN";

		//Valores de prueba
		var nombre = 'Andres';
		var apPat = 'Po';
		var apMat = 'Barrios';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[5]).to.equal(esperado);
	});
});

describe('Decimoprimer caso de excepción, caso 2', function(){
	it('Cuando no existen consonantes internas en nombre o apellido se asigna X', function(){

		var esperado = "RXN";

		//Valores de prueba
		var nombre = 'Andres';
		var apPat = 'Barrios';
		var apMat = 'Po';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[5]).to.equal(esperado);
	});
});

describe('Decimoprimer caso de excepción, caso 3', function(){
	it('Cuando no existen consonantes internas en nombre o apellido se asigna X', function(){

		var esperado = "RRX";

		//Valores de prueba
		var nombre = 'Aaa';
		var apPat = 'Barrios';
		var apMat = 'Barrios';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[5]).to.equal(esperado);
	});
});

describe('Decimosegundo caso de excepción', function(){
	it('Con un solo apellido el sistema asigna X', function(){

		var esperado = "NXT";

		//Valores de prueba
		var nombre = 'Leticia';
		var apPat = 'Luna';
		var apMat = '';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[5]).to.equal(esperado);

	});
});

describe('Decimotercer caso de excepción', function(){
	it('Con un nombre compuesto, se usa el con el primer nombre excepto si es Maria o Jose', function(){

		var esperado = "RNN";

		//Valores de prueba
		var nombre = 'Ma. de los angeles';
		var apPat = 'Moreno';
		var apMat = 'Sanchez';
		//Valores fuera de la prueba
		var ano = '1985';
		var mes = '10';
		var dia = '23'
		var entidad = 'Zacatecas'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto.match(estructuraCURP)[5]).to.equal(esperado);

	});
});

//
// Casos reales
//
describe('Caso real 1', function(){
	it('Debe generar el curp CUCA891203MDFRLN05', function(){

		var esperado = "CUCA891203MDFRLN05";

		//Valores de prueba
		var nombre = 'Maria de los angeles';
		var apPat = 'Cruz';
		var apMat = 'Colon';
		//Valores fuera de la prueba
		var ano = '1989';
		var mes = '12';
		var dia = '3'
		var entidad = 'Distrito Federal'
		var sexo = 'M';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto).to.equal(esperado);

	});
});

describe('Caso real 2', function(){
	it('Debe generar el curp ', function(){

		var esperado = "AAAP790505HDFMGD05";

		//Valores de prueba
		var nombre = 'Pedro Jesus';
		var apPat = 'Amador';
		var apMat = 'Aguilera';
		//Valores fuera de la prueba
		var ano = '1979';
		var mes = '5';
		var dia = '5'
		var entidad = 'Distrito Federal'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto).to.equal(esperado);

	});
});

describe('Caso real 3', function(){
	it('Debe generar el curp ', function(){

		var esperado = "AERS840919MMCMMS08";

		//Valores de prueba
		var nombre = 'Susana Alejandra';
		var apPat = 'Amezcua';
		var apMat = 'Ramirez';
		//Valores fuera de la prueba
		var ano = '1984';
		var mes = '9';
		var dia = '19'
		var entidad = 'Mexico'
		var sexo = 'M';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto).to.equal(esperado);

	});
});

describe('Caso real 4', function(){
	it('Debe generar el curp ', function(){

		var esperado = "AUOK930510MOCGRR04";

		//Valores de prueba
		var nombre = 'Karla Itzel';
		var apPat = 'Aguilar';
		var apMat = 'Ortiz';
		//Valores fuera de la prueba
		var ano = '1993';
		var mes = '5';
		var dia = '10'
		var entidad = 'Oaxaca'
		var sexo = 'M';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto).to.equal(esperado);

	});
});

describe('Caso real 5', function(){
	it('Debe generar el curp ', function(){

		var esperado = "PXNE660720HMCXTN06";

		//Valores de prueba
		var nombre = 'Enrique';
		var apPat = 'Peña';
		var apMat = 'Nieto';
		//Valores fuera de la prueba
		var ano = '1966';
		var mes = '7';
		var dia = '20'
		var entidad = 'Mexico'
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto).to.equal(esperado);

	});
});

describe('Caso real 6', function(){
	it('Debe generar el curp ', function(){

		var esperado = "CXCA770207HNENSL05";

		//Valores de prueba
		var nombre = 'Alex Humberto';
		var apPat = 'Canizalez';
		var apMat = 'Castro';
		//Valores fuera de la prueba
		var ano = '1977';
		var mes = '2';
		var dia = '7'
		var entidad = 'Nacido en el extranjero';
		var sexo = 'H';

		var curpCompleto = Generador.generar(nombre, apPat, apMat,ano, mes, dia, sexo, entidad );

		expect(curpCompleto).to.equal(esperado);

	});
});

