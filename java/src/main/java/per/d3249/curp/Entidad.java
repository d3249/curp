package per.d3249.curp;

public enum Entidad {

    AGUASCALIENTES("AS"), MORELOS("MS"), BAJA_CALIFORNIA("BC"), NAYARIT("NT"), BAJA_CALIFORNIA_SUR("BS"), NUEVO_LEON(
            "NL"), CAMPECHE("CC"), OAXACA("OC"), COAHUILA("CL"), PUEBLA("PL"), COLIMA("CM"), QUERETARO("QT"), CHIAPAS(
                    "CS"), QUINTANA_ROO("QR"), CHIHUHUA("CH"), SAN_LUIS_POTOSI("SP"), DISTRITO_FEDERAL("DF"), SINALOA(
                            "SL"), DURANGO("DG"), SONORA("SR"), GUANAJUATO("GT"), TABASCO("TC"), GUERRERO(
                                    "GR"), TAMAULIPAS("TS"), HIDALGO("HG"), TLAXCALA("TL"), JALISCO("JC"), VERACRUZ(
                                            "VZ"), MEXICO("MC"), YUCATAN("YN"), MICHOACAN(
                                                    "MN"), ZACATECAS("ZS"), NACIDO_EN_EL_EXTRANJERO("NE");

    public final String CLAVE;

    private Entidad(String clave) {
        this.CLAVE = clave;
    }

}
