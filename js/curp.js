(function(){

	var primerVocalInternaRgx = /^.(?:[B-DF-HJ-NP-TV-ZÑ]*)([^B-DF-HJ-NP-TV-ZÑ]{1}).*$/;

	var ultimaConsonanteRgx = /([B-DF-HJ-NP-TV-ZÑ]{1})(?:[^B-DF-HJ-NP-TV-ZÑ]*)$/;

	var primerConsonanteInternaRgx = /^.(?:[^B-DF-HJ-NP-TV-ZÑ]*)([B-DF-HJ-NP-TV-ZÑ]{1})/;

	var caracteresEspecialesRgx = /(?:[^A-Z]|[Ñ])/;

	var reemplazarCaracteres = function reemplazarCaracteres(letra) {

		if (letra.match(caracteresEspecialesRgx)) {
			letra = 'X';
		};

		return letra;
	};

	var matchOrDefault = function matchOrDefault(string, regex, def) {

		if (string == '') {
			return def;
		}

		if (( match = string.match(regex))) {
			return match[1];
		}

		return def;

	};

	var esPrimerNombreValido = function esPrimerNombreValido(string) {
		var listaPrimerNombreInvalido = ['MARIA', 'MA.', 'MA', 'JOSE', 'J', 'J.', 'DA', 'DAS', 'DE', 'DEL', 'DER', 'DI', 'DIE', 'DD', 'EL', 'LA', 'LOS', 'LAS', 'LE', 'LES', 'MAC', 'MC', 'VAN', 'VON', 'Y'];

		var valido = 'true';

		listaPrimerNombreInvalido.forEach(function(n) {

			if (string == n) {
				valido = false;
				return;
			}
		});

		return valido;
	};

	var primerNombreValido = function primerNombreValido(string) {

		chunks = string.split(/\s/);

		if(chunks.length == 1){
			return string;
		}

		for ( i = 0; i < chunks.length; i++) {
			if (esPrimerNombreValido(chunks[i])) {
				return chunks[i];
			}
		}

		return null;
	};

	var letraInicial = function letraInicial(string) {

		if (string == '') {
			return 'X';
		}

		return string[0];
	};

	var pad = function pad(str) {

		str += '';

		if (str.length == 1) {
			str = '0' + str;
		}

		return str;
	};

	var limpiar = function limpiar(string) {
		string = string.toUpperCase();
		string = string.replace('Ü', 'U');
		string = primerNombreValido(string);

		return string;
	};

	var replaceCharacterAt = function replaceCharacterAt(original, index, replace) {

		return original.substring(0, index) + replace + original.substring(index + 1);
	};




	var limpiarAltizonante = function limpiarAltizonante(curp) {

		var palabrasInconvenientes = ['BACA', 'BAKA', 'BUEI', 'BUEY', 'CACA', 'CACO', 'CAGA', 'CAGO', 'COGI', 'COJA', 'COJE', 'COJI', 'COJO', 'COLA', 'CULO', 'FALO', 'FETO', 'GETA', 'GUEI', 'GUEY', 'JETA', 'JOTO', 'KACA', 'KACO', 'KAGA', 'KAGO', 'KAKA', 'KAKO', 'KOGE', 'KOGI', 'KOJA', 'KOJE', 'KOJI', 'KOJO', 'KOLA', 'KULO', 'LILO', 'LOCA', 'LOCO', 'LOKA', 'LOKO', 'MAME', 'MAMO', 'MEAR', 'MEAS', 'MEON', 'MIAR', 'MION', 'MOCO', 'MOKO', 'MULA', 'MULO', 'NACA', 'NACO', 'PEDA', 'PEDO', 'PENE', 'PIPI', 'PITO', 'POPO', 'PUTA', 'PUTO', 'QULO', 'RATA', 'ROBA', 'ROBE', 'ROBO', 'RUIN', 'SENO', 'TETA', 'VACA', 'CAGA', 'VAGO', 'VAKA', 'VUEI', 'VUEY', 'WUEI', 'WUEY'];

		palabrasInconvenientes.forEach(function(palabraInc) {

			if (curp == palabraInc) {
				curp = replaceCharacterAt(curp, 1, 'X');
				return;
			}

		});

		return curp;
	};

	var identificadorEntidadFederativa = function identificadorEntidadFederativa(entidad) {


    var clavesEstados =  {'AGUASCALIENTES':'AS',
        'MORELOS':'MS',
        'BAJA CALIFORNIA':'BC',
        'NAYARIT':'NT',
        'BAJA CALIFORNIA SUR':'BS',
        'NUEVO LEON':'NL',
        'CAMPECHE':'CC',
        'OAXACA':'OC',
        'COAHUILA':'CL',
        'PUEBLA':'PL',
        'COLIMA':'CM',
        'QUERETARO':'QT',
        'CHIAPAS':'CS',
        'QUINTANA ROO':'QR',
        'CHIHUHUA':'CH',
        'SAN LUIS POTOSI':'SP',
        'DISTRITO FEDERAL':'DF',
        'SINALOA':'SL',
        'DURANGO':'DG',
        'SONORA':'SR',
        'GUANAJUATO':'GT',
        'TABASCO':'TC',
        'GUERRERO':'GR',
        'TAMAULIPAS':'TS',
        'HIDALGO ':'HG',
        'TLAXCALA':'TL',
        'JALISCO':'JC',
        'VERACRUZ':'VZ',
        'MEXICO':'MC',
        'YUCATAN':'YN',
        'MICHOACAN':'MN',
        'ZACATECAS':'ZS',
        'NACIDO EN EL EXTRANJERO':'NE' };

        return clavesEstados[entidad];
	};

	var calcularHomoclave = function calculaHomoclave(c) {
		//Está función fue tomada del script que se carga en el sitio
		//https://consultas.curp.gob.mx/CurpSP/gobmx/inicio.jsp
		//
		//Existe un bug, ya que no diferencia entre A y 0 (cero) en el
		//primer dígito de verificación v.g. AX = 0X
		//
		//Mayo de 2018

		var segRaiz = c.substring(0, 17),
		    chrCaracter = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		intFactor = new Array(17),
		lngSuma = 0.0,
		lngDigito = 0.0;

		for (var i = 0; i < 17; i++) {
			for (var j = 0; j < 37; j++) {
				if (segRaiz.substring(i, i + 1) == chrCaracter.substring(j, j + 1)) {
					intFactor[i] = j;
				}
			}

		}
		for (var k = 0; k < 17; k++) {

			lngSuma = lngSuma + ((intFactor[k]) * (18 - k));
		}

		lngDigito = (10 - (lngSuma % 10));
		if (lngDigito == 10) {
			lngDigito = 0;
		}

		return lngDigito;
	};


	var identificadorDeSiglo = function identificadorDeSiglo(ano){
		//El penúltimo dígito de la CURP es llamado identificador de sigo
		//Tiene un valor de 0 (cero) si el año de nacimiento es entre 1900 y 1999
		// y de A entre 2000 y 2099
		if(ano < 2000){
		  return '0';
		}

		return 'A';
	};

	var calculaCURP = {};

	calculaCURP.generar = function generar(nombre, primerApellido, segundoApellido, ano, mes, dia, sexo, entidad) {

		var curp = '';

		nombre = limpiar(nombre);
		primerApellido = limpiar(primerApellido);
		segundoApellido = limpiar(segundoApellido);
		sexo = sexo.toUpperCase();
		entidad = entidad.toUpperCase();

		//Letra inicial del primer apellido
		curp += reemplazarCaracteres(letraInicial(primerApellido));
		//Primera vocal interna del primer apellido
		curp += reemplazarCaracteres(matchOrDefault(primerApellido, primerVocalInternaRgx, 'X'));
		//Letra inicial del segundo apellido
		curp += reemplazarCaracteres(letraInicial(segundoApellido));
		//Primera letra del nombre
		curp += reemplazarCaracteres(letraInicial(nombre));

		//evalúa palabras altizonante
		curp = limpiarAltizonante(curp);

		//Dos dígitos del año
		curp += ano.substring(2);
		//Dos dígitos del mes
		curp += pad(mes);
		//Dos dígitos del día
		curp += pad(dia);
		//el caracter de género
		curp += sexo;

		//Dos letras de la entidad federativa
		curp += identificadorEntidadFederativa(entidad);

		//Primer consonante interna del primer apellido
		curp += reemplazarCaracteres(matchOrDefault(primerApellido, primerConsonanteInternaRgx, 'X'));
		//Primer consonane interna del segundo apellido
		curp += reemplazarCaracteres(matchOrDefault(segundoApellido, primerConsonanteInternaRgx, 'X'));

		//Primer consonane interna del nombre
		curp += reemplazarCaracteres(matchOrDefault(nombre, primerConsonanteInternaRgx, 'X'));

		//Consecutivo
		curp += identificadorDeSiglo(ano);

		//Digito verificador
		curp += calcularHomoclave(curp);

		return curp;
	};

	module.exports = calculaCURP;


})();



