import java.util.Scanner;
import java.util.regex.*;


public class main {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserte cadena:");
		String frase = sc.nextLine();
		int valor, valor2, valor3, valor4;
		String hint;
		System.out.println("Inserte 1 para cifrar o 2 para descifrar:");


		switch (sc.nextInt()) {

		case 1:
			System.out.println("Inserte cuatro valores enteros:");
			do {
				System.out.println("Valor:");
				valor = sc.nextInt();
				if (valor < 0 || valor > 99) System.err.println("Valor fuera de rango, vuelva a intentarlo: ");
			} while(valor < 0 || valor > 99);
			do {
				System.out.println("Valor:");
				valor2 = sc.nextInt();
				if (valor2 < 0 || valor2 > 99) System.err.println("Valor fuera de rango, vuelva a intentarlo: ");
			} while(valor2 < 0 || valor2 > 99);
			do {
				System.out.println("Valor:");
				valor3 = sc.nextInt();
				if (valor3 < 0 || valor3 > 99) System.err.println("Valor fuera de rango, vuelva a intentarlo: ");
			} while(valor3 < 0 || valor3 > 99);
			do {
				System.out.println("Valor:");
				valor4 = sc.nextInt();
				if (valor4 < 0 || valor4 > 99) System.err.println("Valor fuera de rango, vuelva a intentarlo: ");
			} while(valor4 < 0 || valor4 > 99);
			System.out.println(RotorD((RotorC((RotorB((RotorA(frase, valor)), valor2)), valor3)), valor4));
		break;
		case 2:
			
			System.out.println("Inserte 1 si tiene la clave o 2 si no:");
			if (sc.nextInt() == 1) {
				System.out.println("Inserte los cuatro valores enteros:");
				do {
					System.out.println("Valor:");
					valor = sc.nextInt();
					if (valor < 0 || valor > 99) System.err.println("Valor fuera de rango, vuelva a intentarlo: ");
				} while(valor < 0 || valor > 99);
				do {
					System.out.println("Valor:");
					valor2 = sc.nextInt();
					if (valor2 < 0 || valor2 > 99) System.err.println("Valor fuera de rango, vuelva a intentarlo: ");
				} while(valor2 < 0 || valor2 > 99);
				do {
					System.out.println("Valor:");
					valor3 = sc.nextInt();
					if (valor3 < 0 || valor3 > 99) System.err.println("Valor fuera de rango, vuelva a intentarlo: ");
				} while(valor3 < 0 || valor3 > 99);
				do {
					System.out.println("Valor:");
					valor4 = sc.nextInt();
					if (valor4 < 0 || valor4 > 99) System.err.println("Valor fuera de rango, vuelva a intentarlo: ");
				} while(valor4 < 0 || valor4 > 99);
				System.out.println(RotorAD((RotorBD((RotorCD((RotorDD(frase, valor4)), valor3)), valor2)), valor));
			}
			else {
				System.out.println("Inserte una concidencia:");
				hint = sc.next();
				System.out.println(BruteForce(frase, hint));
			}
		break;
		
		default:
			System.out.println("Error, indice fuera del dominio.");
		}
	} 
	public static String BruteForce(String frase, String hint) {
		String resultado = ""; String termino; int selector;
		
		for(int i=0; i<=99; i++) {
			for(int j=0; j<=99; j++) {
				for(int k=0; k<=99; k++) {
					for(int h=0; h<=99; h++) {
						System.out.println(i+" "+j+" "+k+" "+h);
						resultado = RotorAD(RotorBD(RotorCD(RotorDD(frase, h), k), j), i);
						if(resultado.toLowerCase().contains(hint.toLowerCase())) {
							System.out.println("Los rotores actuales son "+i+" "+j+" "+k+" "+h);
							return resultado;

						}
					}
				}
			}
		}
		return "No se ha encontrado";
	}
	
	

	// Informe 345/32 Buque de Carga en el Atlantico.

	public static String RotorA(String frase, int valor) {
		String newCad = "";
		int cont = 0;
		for (int i = 0; i < frase.length(); i++) {
			
			if (cont >= 0 && cont < 5 && frase.charAt(i) >= 32 && frase.charAt(i) <= 126) newCad = newCad + Cifrar(frase.charAt(i), valor);
			else if (cont >= 5  && cont < 10) newCad = newCad + frase.charAt(i);
			else System.err.println("no condition A");
			cont++;
			if (cont > 9) cont = 0;
		}
	return newCad;
	}
	
	public static String RotorAD(String frase, int valor) {
		String newCad = "";
		int cont = 0;
		for (int i = 0; i < frase.length(); i++) {
			if (cont >= 0 && cont < 5) newCad = newCad + Descifrar(frase.charAt(i), valor);
			else if (cont >= 5  && cont < 10) newCad = newCad + frase.charAt(i);
			else System.err.println("no condition AD");
			cont++;
			if (cont >= 10) cont = 0;
		}
	return newCad;
	}
	
	public static String RotorB(String frase, int valor2) {
		
		String newCad = "";
		
		for (int i = 0; i < frase.length(); i++) {
			
			if (esPar(i)) {
				newCad = newCad + Cifrar(frase.charAt(i), valor2); 
				valor2++;
			}else newCad = newCad + frase.charAt(i); 
		}
		return newCad;
	}

	public static String RotorBD(String frase, int valor2) {
		
		String newCad = "";
		
		for (int i = 0; i < frase.length(); i++) {
			
			if (esPar(i)) {
				newCad = newCad + Descifrar(frase.charAt(i), valor2); 
				valor2++;
			}else newCad = newCad + frase.charAt(i); 
		}
		return newCad;
	}		 
	
	public static String RotorC(String frase, int valor3) {
		
		String newCad = "";
		
		for(int i = frase.length()-1; i >= 0; i--) {
			if (!esPar(i) && frase.charAt(i) > 31 && frase.charAt(i) <= 126) {
				newCad = newCad + Cifrar(frase.charAt(i), valor3);
				valor3++;
			}else newCad = newCad + frase.charAt(i);
		}
		return newCad;
	}
	
	public static String RotorCD(String frase, int valor3) {
			
			String res = "", newCad = "";
	
			if (esPar(frase.length())) {
				for(int i = 0; i < frase.length(); i++) {
					if (esPar(i) && frase.charAt(i) > 31 && frase.charAt(i) <= 126) {
						newCad = newCad + Descifrar(frase.charAt(i), valor3);
						valor3++;
					}else newCad = newCad + frase.charAt(i);
				}
			}else {
				for(int i = 0; i < frase.length(); i++) {
					if (!esPar(i) && frase.charAt(i) > 31 && frase.charAt(i) <= 126) {
						newCad = newCad + Descifrar(frase.charAt(i), valor3);
						valor3++;
					}else newCad = newCad + frase.charAt(i);
				}
			}
			
			for (int i = newCad.length()-1; i >= 0; i--) res = res + newCad.charAt(i);
			return res;
	}
	
	public static String RotorD(String frase, int valor4) {
		
		String newCad = "";
		int cont = 0;
		
		for (int i = frase.length()-1; i >= 0; i--) {
			
			if (cont >= 0 && cont < 11) newCad = newCad + Cifrar(frase.charAt(i), valor4);
			else if (cont >= 11  && cont < 23) newCad = newCad + frase.charAt(i);
			else System.err.println("no condition D");
			cont++;
			if (cont >= 22) cont = 0;
		}
		
		return newCad;
	}
	
	public static String RotorDD(String frase, int valor4) {
		
		String newCad = "";
		String corregido = "";
		int cont = 0;

		for(int i = 0; i < frase.length(); i++) {
			
			if (cont >= 0 && cont < 11) newCad = newCad + Descifrar(frase.charAt(i), valor4);
			else if (cont >= 11  && cont < 23) newCad = newCad + frase.charAt(i);
			else System.err.println("no condition DD");
			cont++;
			if (cont >= 22) cont = 0;
		}
		
		for(int i = newCad.length()-1; i >= 0; i--) corregido = corregido + newCad.charAt(i);
		
		return corregido;
	}
	
	
	public static boolean esPar(int indice) {
		if (indice % 2 == 0) return true;
		else return false;
	}
	

	public static char Cifrar (char letra, int valor) {
			
			int codigo=(int)letra;
			int desplazamiento = codigo+valor%95;
			int resultado;
			if (desplazamiento>126) resultado=desplazamiento-95;
			else resultado=desplazamiento;
			return (char)resultado;
	}
	
	public static char Descifrar (char letra, int valor) {
		
		int codigo=(int)letra;
		int desplazamiento = codigo-valor%95;
		int resultado;
		if (desplazamiento<32) resultado=desplazamiento+95;
		else resultado=desplazamiento;
		return (char)resultado;
		
	}
}

/**
 * 
 Rotores 23 81 93 13
 
 	1= U-Boat U235K. En ruta hacia coordenadas 51.089446100151406, -11.956882669426218 frente costa irlandesa. Visibilidad baja. Inmersión. 20A2?BW6H'F X,ID%BG0`hp
	2 = Martes 22/11/1941. Informe Climatológico. U-Boat U256N. Nubosidad baja frente a costa inglesa. Visibilidad 100 m. Movimiento barcas de pesca y transportes pequeños. Sin actividad militar.
	3 = MaRteS 22/11/1941. Informe Climatológico. Cielos despejados hacia el sur. Estación de Cuxhaven reporta lluvias sobre la cosa Danesa.
	4 = Coronel Werner Mölders as de la aviación, derribado a la altura de Breslau. Se desconoce paradero.
	5 = Crucero Auxiliar Atlantis cercano a rendevouz con U-Boats U-68 y U-126. Costa sur St Helena. Aprox 5 millas para rendevouz.
	
 */
