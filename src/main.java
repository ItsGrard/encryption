import java.util.Scanner;

public class main {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		System.out.println("Dame cadena makinote:");
		String frase = sc.nextLine();
	
	
	System.out.println(RotorA(frase, 0));
		
		//System.out.println(RotorAD(RotorA(frase, 54), 54));
		
		System.out.println(RotorB(frase, 73));
		System.out.println(RotorBD(RotorB(frase,73), 73));
	}
	
	
	public static String RotorA(String frase, int valor) {
		String newCad = "";
		int cont = 0;
		for (int i = 0; i < frase.length(); i++) {
			
			if (cont >= 0 && cont < 5 && frase.charAt(i) >= 32 && frase.charAt(i) <= 126) newCad = newCad + Cifrar(frase.charAt(i), valor);
			else if (cont >= 5  && cont < 10) newCad = newCad + frase.charAt(i);
			else System.err.println("no condition");
			cont++;
			if (cont > 9) cont = 0;
		}
	return newCad;
	}
	
	public static String RotorAD(String frase, int valor) {
		String newCad = "";
		int cont = 0;
		for (int i = 0; i < frase.length(); i++) {
			if (cont >= 0 && cont < 5 && frase.charAt(i) >= 32 && frase.charAt(i) <= 126) newCad = newCad + Descifrar(frase.charAt(i), valor);
			else if (cont >= 5  && cont < 10) newCad = newCad + frase.charAt(i);
			else System.err.println("no condition");
			cont++;
			if (cont > 9) cont = 0;
		}
	return newCad;
	}
	

	/*
	public static String RotorB (String frase, int valor) {
		String newCad ="";
		for (int i = 0; i < frase.length(); i++) {
			if (esPar(i)) valor++;
			if (frase.charAt(i) >= 32 && frase.charAt(i) <= 126) newCad = newCad + Cifrar(frase.charAt(i), valor);
			else newCad = newCad + frase.charAt(i);
			
		}
	return newCad;
	}
	*/
	public static String RotorBD (String frase, int valor) {
		String newCad ="";
		valor = valor + frase.length() / 2;
		for (int i = 0; i < frase.length(); i++) {
			newCad = newCad + Descifrar(frase.charAt(i), valor);
			if (esPar(i)) valor--;
		}
	return newCad;
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
