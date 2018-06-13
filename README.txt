In this repository I will provide some different implementations to compute the mexican CURP (Clave Única de Registro de Población).

Because of the national scope, the rest of this documentation will be in spanish.

En este repositorio publicaré algunas implementaciones para calcular el CURP mexicano (Clave Única de Registro de Población).

El cálculo se hace basado, principalmente, en el documento INSTRUCTIVO NORMATIVO PARA LA ASIGNACIÓN DE LA CLAVE ÚNICA DE REGISTRO DE POBLACIÓN tomado de http://www.ordenjuridico.gob.mx/Federal/PE/APF/APC/SEGOB/Instructivos/InstructivoNormativo.pdf

El cálculo del dígito verificador se tomó del archivo javascript del sitio https://consultas.curp.gob.mx/CurpSP/gobmx/inicio.jsp, script CurpSP/gobmx/js/validar/curp.js, específicamente la función calculaDigito.

Actualmente la implemtación se encuentra sólo en javascript, pero espero pronto publicar versiones en Ruby y en Java.


