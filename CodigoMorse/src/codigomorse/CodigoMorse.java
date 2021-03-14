package codigomorse;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CodigoMorse {

    public static void main(String[] args) {
        String[] palabras ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        
        boolean salir = false;
        Scanner s = new Scanner(System.in);
        int opcion = 0;
        System.out.println("TRADUCTOR MORSE");
        do {            
            System.out.println("Ingrese Opción");
            System.out.println("1. Traducir español a morse");
            System.out.println("2. Traducir morse a español");
            System.out.println("3. Salir");
            opcion = comprobaropcion( s, 1, 3);
            s.nextLine();
            salir = proccesOpcion(opcion, s, palabras, morse);
        } while (!salir);
    }
    
    public static boolean proccesOpcion(int opcion, Scanner s, String[] palabras, String[] morse){
        boolean terminar = false;
        if(opcion==1){
            System.out.println("Ingrese frase para traducir");
            String frase = s.nextLine();
            String fraseTraducir = frase.toUpperCase();
            String mensajeTraducido = fraseMorse(fraseTraducir, palabras, morse);
            System.out.println("Traduccion del Mensaje");
            System.out.println(mensajeTraducido);
        }else if(opcion==2){
             System.out.println("Ingrese frase para traducir en morse");
            String frase = s.nextLine();
            String mensajeTraducido = morseFrase(frase, palabras, morse);
            System.out.println("Traduccion al español");
            System.out.println(mensajeTraducido);
        }else if(opcion==3){
            terminar = true;
        }
        return terminar;
    }
    
    public static int comprobaropcion(Scanner s, int min, int max){
        int valor = 0;
        boolean error = false;
        do {            
            error = false;
            try {
                valor = s.nextInt();
                if (!((valor>=min)&&(valor<=max))) {
                    System.out.println("Valor mal ingresado");
                    System.out.println("El valor debe entre "+ min + " y" + max);
                    error = true;
                    s.nextLine();
                }
            }catch(InputMismatchException e){
                System.out.println("Tipo de dato mal ingresado, Se esperaba un número");
                error = true;
                s.nextLine();
            }catch (Exception e) {
                System.out.println("Error inesperado. "+e);
                error=true;
            }
        } while (error);
        return valor;
    }

    public static String fraseMorse(String fraseTraducir, String[] palabras, String[] morse) {
        String mensajeMorse = "";
        for (int i = 0; i < fraseTraducir.length(); i++) {
            int j = 0;
            boolean salir = false;
            if (Character.toString(fraseTraducir.charAt(i)).equals(" ")) {
                mensajeMorse += "  ";
            }else{
                do {
                    if (Character.toString(fraseTraducir.charAt(i)).equals(palabras[j])) {
                        mensajeMorse+=morse[j] + " ";
                        salir = true;
                }
                j++;
                    if (j==palabras.length) {
                        if (!salir) {
                            salir=true;
                        }
                    }
                } while (!salir);
            }
        }
        return mensajeMorse;
    }

    public static String morseFrase(String fraseTraducir, String[] palabras, String[] morse) {
        String mensajeEspa = "";
        String caracterMorse = "";
        int i = 0;
        do {
            boolean terminaCaracter = false;
            do {
                if (Character.toString(fraseTraducir.charAt(i)).equals(" ")) {
                    terminaCaracter = true;
                }else{
                    caracterMorse+=Character.toString(fraseTraducir.charAt(i));
                    i++;
                }
            } while ((!terminaCaracter)&&(i<fraseTraducir.length()));
            
            int j = 0;
            boolean salir = false;
                do {
                    if (caracterMorse.equals(morse[j])) {
                        mensajeEspa+=palabras[j];
                        salir = true;
                }
                j++;
                    if (j==morse.length) {
                        if (!salir) {
                            salir=true;
                        }
                    }
                } while (!salir);
                caracterMorse="";
            
            i++;
            try {
                 if (Character.toString(fraseTraducir.charAt(i)).equals(" ")) {
                if (Character.toString(fraseTraducir.charAt(i+1)).equals(" ")){
                    mensajeEspa += " ";
                    i+=2;
                }
            }
            }catch(StringIndexOutOfBoundsException e){
                
            }catch (Exception e) {
            }
           
        } while (i<fraseTraducir.length());
        
        return mensajeEspa;
    }
    
}
