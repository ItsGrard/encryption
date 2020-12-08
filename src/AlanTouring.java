import java.util.Scanner;
public class AlanTouring {

	public static void main(String[] args) {
		//Comenzamos creando un Scanner y una salida para pedir la frase al usuario
		//Posteriormente, declaramos nuestra 1era variable, que será procesada
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca la frase a cifrar, el tiempo vuela");
		String frase = sc.nextLine();
		//A medida que resolvemos el 1er rotor sacamos en esta clase la salida
		//La salida consta de el rotor (A o AD), frase y numero rotor	
		System.out.println(RotorA(frase, 54));
		//Con esta última acción llamamos a la función RotorA desde el main para que se ejecute tras la inserción del código a cifrar
		//Seguídamente llamamos a la función RotorAD desde el main también para que se ejecute tras cifrarse el código
		System.out.println(RotorAD(RotorA(frase, 54), 54));
		System.out.println(RotorB(frase,73));
		System.out.println(RotorBD(RotorB(frase, 73), 73));
		System.out.println(RotorC(frase,91));
		System.out.println(RotorCD(RotorC(frase, 91), 91));
		sc.close();
	}
	public static String RotorA(String frase, int valor) {
		//Hemos creado una función en la cual daremos forma al rotorA de CIFRADO
		String fraseCod = "";
		int contador = 0;
		//Declaramos como variable la frase codificada y un contador
		//Al contador le damos valor 0, para definir un punto de partida
		for (int i = 0; i<frase.length(); i++) {
			if (contador >= 0 && contador < 5 && frase.charAt(i) >= 32 && frase.charAt(i) <= 126)
				fraseCod = fraseCod + Cifrar(frase.charAt(i), valor);
			else if 
			(contador >= 5 && contador < 10)
			fraseCod = fraseCod + frase.charAt(i);
			else System.err.println("Aquí no voy a cifrar amigo");
			contador++;
			if (contador > 9) contador = 0;
		}
		return fraseCod;
		//En el for anidamos un if, en el cual el contador debe codificar del 1 al 5
		//Solo cifrará caracteres ASCI entre el 32 y el 126
		//Pero no lo hará en los índices comprendidos entre el 6 y el 10		
	}
	public static String RotorAD(String frase, int valor) {
		//Hemos creado el descifrador del rotor A
		String fraseCod = "";
		int contador = 0;
		//A partir de aquí hemos declarado las mismas variables y con el mismo valor que antes
		for (int i = 0; i<frase.length(); i++) {
			if (contador >= 0 && contador < 5 && frase.charAt(i) >= 32 && frase.charAt(i) <= 126)
				fraseCod = fraseCod + Descifrar(frase.charAt(i), valor);
			else if
			(contador >= 5 && contador < 10)
			fraseCod = fraseCod + frase.charAt(i);
			else
			System.err.println("Aquí no voy a descifrar nada amigo");
			contador++;
			if (contador > 9) contador = 0;
		}
		return fraseCod;
	}
	
	public static boolean esPar (int numero) {
		if (numero % 2 == 0) return true;
		else return false;
	}
	
	public static String RotorB(String frase, int valor) {
		String fraseCod = "";
		for (int i = 0; i < frase.length(); i++) {
			if (esPar(i)) {
				fraseCod = fraseCod + Cifrar(frase.charAt(i), valor);
				valor++;
			} else 
				fraseCod = fraseCod + frase.charAt(i);
			}
			return fraseCod;
		}
	public static String RotorBD(String frase, int valor) {
		String fraseCod = "";
		for (int i = 0; i < frase.length(); i++) {
			if (esPar(i)) {
				fraseCod = fraseCod + Descifrar(frase.charAt(i), valor);
				valor++;
			} else 
				fraseCod = fraseCod + frase.charAt(i);
			}
			return fraseCod;
		}
		
	public static boolean esImpar (int numero) {
		if (numero % 2 != 0) {
			return true;
		} else {
			return false;
		}
	}
	public static String RotorC(String frase, int valor) {
		String fraseCod = "";
		
		for(int i = frase.length(); i<frase.length(); i--) {
			if (esImpar(i)) {
				fraseCod = Cifrar(frase.charAt(i), valor) + fraseCod;
				valor++;
			} else {
				fraseCod = fraseCod + frase.charAt(i);
			}	
		}
		return fraseCod;
	}
	
	public static String RotorCD(String frase, int valor) {
		String fraseCod = "";
		
		for(int i = frase.length(); i<frase.length(); i--) {
			if (esImpar(i)) {
				fraseCod = fraseCod + Descifrar(frase.charAt(i), valor);
				valor++;
			} else {
				fraseCod = fraseCod + frase.charAt(i);
			}
		}
		return fraseCod;
	}
	
	public static char Cifrar(char letra, int valor) {
		
		int codigo = (int)letra;
		int desplazamiento = codigo + valor %95;
		int resultado;
		if (desplazamiento>126) resultado = desplazamiento - 95;
		else resultado = desplazamiento;
		return (char)resultado;
	}
	public static char Descifrar(char letra, int valor) {
		
		int codigo = (int)letra;
		int desplazamiento = codigo - valor %95;
		int resultado;
		if (desplazamiento<32) resultado = desplazamiento + 95;
		else resultado = desplazamiento;
		return (char)resultado;
	}
}
