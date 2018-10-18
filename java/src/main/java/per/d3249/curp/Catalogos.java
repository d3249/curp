package per.d3249.curp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Catalogos {

	private static final String[] ARRAY_PRIMEROS_NOMBRES_IGNORADOS = { "MARIA", "MA.", "MA", "JOSE", "J", "J.", "DA",
			"DAS", "DE", "DEL", "DER", "DI", "DIE", "DD", "EL", "LA", "LOS", "LAS", "LE", "LES", "MAC", "MC", "VAN",
			"VON", "Y" };

	private static final String[] ARRAY_PALABRAS_IMPROPIAS = { "BACA", "BAKA", "BUEI", "BUEY", "CACA", "CACO", "CAGA",
			"CAGO", "COGI", "COJA", "COJE", "COJI", "COJO", "COLA", "CULO", "FALO", "FETO", "GETA", "GUEI", "GUEY",
			"JETA", "JOTO", "KACA", "KACO", "KAGA", "KAGO", "KAKA", "KAKO", "KOGE", "KOGI", "KOJA", "KOJE", "KOJI",
			"KOJO", "KOLA", "KULO", "LILO", "LOCA", "LOCO", "LOKA", "LOKO", "MAME", "MAMO", "MEAR", "MEAS", "MEON",
			"MIAR", "MION", "MOCO", "MOKO", "MULA", "MULO", "NACA", "NACO", "PEDA", "PEDO", "PENE", "PIPI", "PITO",
			"POPO", "PUTA", "PUTO", "QULO", "RATA", "ROBA", "ROBE", "ROBO", "RUIN", "SENO", "TETA", "VACA", "CAGA",
			"VAGO", "VAKA", "VUEI", "VUEY", "WUEI", "WUEY" };

	public static final List<String> LISTA_PRIMEROS_NOMBRES_IGNORADOS = Collections
			.unmodifiableList(Arrays.asList(ARRAY_PRIMEROS_NOMBRES_IGNORADOS));
	public static final List<String> LISTA_PALABRAS_IMPROPIAS = Collections
			.unmodifiableList(Arrays.asList(ARRAY_PALABRAS_IMPROPIAS));

}
