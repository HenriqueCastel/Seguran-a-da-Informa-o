import java.util.Scanner;

public class CifraDeCesar {

    public static void main(String[] args) {
    	Scanner ler = new Scanner(System.in);

    	String s, sc, sd;
	    int cifra;
	
	    System.out.printf("Informe um texto:\n");
	    s = ler.nextLine();
	
	    System.out.printf("\nInforme o valor da cifra:\n");
	    cifra = ler.nextInt();
	
	    sc = criptografa(s, cifra);
	    sd = descriptografa(sc, cifra);
	
	    System.out.printf("\nTexto criptografado:\n");
	    System.out.printf("%s", sc);
	
	    System.out.printf("\n\nTexto descriptografado:\n");
	    System.out.printf("%s\n", sd);
    }

    public static String criptografa(String s, int cifra) {
    	int i, n = s.length();
    	String saux = "";

    	for (i=0; i<n; i++) {
    		saux = saux + (char)(s.charAt(i) + cifra);
    	}
    	return(saux);
    }

    public static String descriptografa(String s, int cifra) {
    	int i, n = s.length();
    	String saux = "";

    	for (i=0; i<n; i++) {
    		saux = saux + (char)(s.charAt(i) - cifra);
    	}
    	return(saux);
    }

}
