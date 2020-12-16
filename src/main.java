import java.util.Scanner;
import java.util.regex.*;


public class main {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserte cadena:");
		String frase = sc.nextLine();
		int valor = 0;
		String hint;
		System.out.println("Inserte 1 para cifrar o 2 para descifrar:");


		switch (sc.nextInt()) {

		case 1:
			System.out.println("Inserte un valor entero:");
			do {
				valor = sc.nextInt();
				if (valor < 0 || valor > 99) System.err.println("Valor fuera de rango, vuelva a intentarlo: ");
			} while(valor < 0 || valor > 99);
			System.out.println(RotorD((RotorC((RotorB((RotorA(frase, valor)), valor)), valor)), valor));
		break;
		case 2:
			
			System.out.println("Inserte 1 si tiene la clave o 2 si no:");
			if (sc.nextInt() == 1) {
				System.out.println("Inserte el valor:");
				valor = sc.nextInt();
				System.out.println(RotorAD((RotorBD((RotorCD((RotorDD(frase, valor)), valor)), valor)), valor));
			}
			else {
				System.out.println("Inserte una concidencia:");
				hint = sc.next();
				BruteForce(frase, hint);
			}
		break;
		
		default:
			System.out.println("Error, indice fuera del dominio.");
		}
	} 

	// Informe 345/32 Buque de Carga en el Atlantico.
	public static void BruteForce(String frase, String hint) {
	
		boolean flag = false;
		int i = 0;
		
		while (!flag) {
			i++;
			System.out.println(RotorAD((RotorBD((RotorCD((RotorDD(frase, i)), i)), i)), i));
			
			if (Pattern.compile(Pattern.quote(hint), Pattern.CASE_INSENSITIVE).matcher((RotorAD((RotorBD((RotorCD((RotorDD(frase, i)), i)), i)), i))).find()){//(RotorAD((RotorBD((RotorCD((RotorDD(frase, i)), i)), i)), i).contains(hint)) {
				flag = true;
				System.out.println(RotorAD((RotorBD((RotorCD((RotorDD(frase, i)), i)), i)), i));
				System.out.println("Rotor: " +i + ".");
			}
			System.out.println(flag);
		
		}
	}
	
	
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
	
	public static String RotorB(String frase, int valor) {
		
		String newCad = "";
		
		for (int i = 0; i < frase.length(); i++) {
			
			if (esPar(i)) {
				newCad = newCad + Cifrar(frase.charAt(i), valor); 
				valor++;
			}else newCad = newCad + frase.charAt(i); 
		}
		return newCad;
	}

	public static String RotorBD(String frase, int valor) {
		
		String newCad = "";
		
		for (int i = 0; i < frase.length(); i++) {
			
			if (esPar(i)) {
				newCad = newCad + Descifrar(frase.charAt(i), valor); 
				valor++;
			}else newCad = newCad + frase.charAt(i); 
		}
		return newCad;
	}		 
	
	public static String RotorC(String frase, int valor) {
		
		String newCad = "";
		
		for(int i = frase.length()-1; i >= 0; i--) {
			if (!esPar(i) && frase.charAt(i) > 31 && frase.charAt(i) <= 126) {
				newCad = newCad + Cifrar(frase.charAt(i), valor);
				valor++;
			}else newCad = newCad + frase.charAt(i);
		}
		return newCad;
	}
	
	public static String RotorCD(String frase, int valor) {
			
			String res = "", newCad = "";
	
			if (esPar(frase.length())) {
				for(int i = 0; i < frase.length(); i++) {
					if (esPar(i) && frase.charAt(i) > 31 && frase.charAt(i) <= 126) {
						newCad = newCad + Descifrar(frase.charAt(i), valor);
						valor++;
					}else newCad = newCad + frase.charAt(i);
				}
			}else {
				for(int i = 0; i < frase.length(); i++) {
					if (!esPar(i) && frase.charAt(i) > 31 && frase.charAt(i) <= 126) {
						newCad = newCad + Descifrar(frase.charAt(i), valor);
						valor++;
					}else newCad = newCad + frase.charAt(i);
				}
			}
			
			for (int i = newCad.length()-1; i >= 0; i--) res = res + newCad.charAt(i);
			return res;
	}
	
	public static String RotorD(String frase, int valor) {
		
		String newCad = "";
		int cont = 0;
		
		for (int i = frase.length()-1; i >= 0; i--) {
			
			if (cont >= 0 && cont < 11) newCad = newCad + Cifrar(frase.charAt(i), valor);
			else if (cont >= 11  && cont < 23) newCad = newCad + frase.charAt(i);
			else System.err.println("no condition D");
			cont++;
			if (cont >= 22) cont = 0;
		}
		
		return newCad;
	}
	
	public static String RotorDD(String frase, int valor) {
		
		String newCad = "";
		String corregido = "";
		int cont = 0;

		for(int i = 0; i < frase.length(); i++) {
			
			if (cont >= 0 && cont < 11) newCad = newCad + Descifrar(frase.charAt(i), valor);
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
	
	// Informe 345/32 Buque de Carga en el Atlantico.
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
