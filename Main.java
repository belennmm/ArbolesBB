import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    /**
     * Clase principal del programa.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BinaryTree<Association<String, String>> diccionario = new BinaryTree<>(new AssociationComparator());
        System.out.println("\nÁRBOLES BINARIOS DE BÚSQUEDA:");

        // leer el diccionario de palabras
        cargarDiccionario("diccionario.txt", diccionario);

        //mostrar el diccionario ordenado
        System.out.println("\nDiccionario ordenado:");
        diccionario.inOrder();

        // texto traducido
        translateText("texto.txt", diccionario);
    }

    // ------------------------------------------------------
    /**
     * Se encarga de cargar el diccionario al programa.
     * @param nombreFile
     * @param diccionario
     */
    private static void cargarDiccionario( String nombreFile, BinaryTree<Association<String, String>> diccionario){
        try{
            File file = new File(nombreFile);
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()){

                String line = scan.nextLine().trim();

                if(!line.isEmpty()){
                    // separar por coma y quitar los ()
                   
                    line = line.substring(1, line.length() - 1);

                    String[] parts = line.split(",");

                    if(parts.length == 2){
                        String inglesWord = parts[0].trim().toLowerCase(); // para pasar a minúscula las palabras
                        String spanishWord = parts[1].trim();

                        Association<String, String> association = new Association<>(inglesWord, spanishWord);
                        diccionario.insertar(association);
                    }
                }
            }

         
        } 
        catch (FileNotFoundException e){
            System.err.println("No se ha encontrado el Archivo: " + e.getMessage());
        }
    }

    // ------------ TRADUCIR -----------------------------------
    /**
     * Representa el traductor del protrama.
     * @param nombreFile
     * @param diccionario
     */
    private static void translateText(String nombreFile, BinaryTree<Association<String, String>> diccionario) {
        try {

            File file = new File(nombreFile);
            Scanner scan = new Scanner(file);

            System.out.println("\n\n --- Texto traducido: ---");

            while (scan.hasNext()) {
                String wordWithPunctuation = scan.next(); // la otra palabra

                StringBuilder palabra = new StringBuilder();
                StringBuilder signo = new StringBuilder();

                // separar los signos de las letras
                for(char x : wordWithPunctuation.toCharArray()){

                    if(Character.isLetter(x)) {
                        palabra.append(x);
                    } 
                    else{
                        signo.append(x);
                    }
                }

                String word = palabra.toString();
                String punctuation = signo.toString();

                if(!word.isEmpty()){
                    Association<String, String> searchResult = diccionario.buscar(new Association<>(word.toLowerCase(), null));
                   
                    if (searchResult != null) {
                        // mostrar la palabra traducida añade el signo si hay
                        System.out.print(searchResult.getValue() + punctuation + " "); 
                    } 
                    else {
                        // muestra la palabra original entre **
                        System.out.print("*" + word + "*" + punctuation + " "); 
                    }
                } 
                else {
                   // si la palabra está vacía solo los signos de puntuación se muestran
                    System.out.print(punctuation + " "); 
                }
            }

            scan.close();

        } 
        catch (FileNotFoundException e) {
            System.err.println("El archivo no se ha encontrado: " + e.getMessage());
        }
    }
}