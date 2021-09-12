package commons;

import sun.misc.JavaAWTAccess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

public class Data {

    //Funcao para ler arquivo Json
    public String ler_json(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //Funcao para ler um arquivo CSV
    public Collection<String[]> lerCsv(String caminhoCsv){

        return null
    }
}






