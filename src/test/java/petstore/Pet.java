//1 - Pacote
package petstore;

//2 - Bibliotecas


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

//3 - Classe
public class Pet {
    //3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet";
    private Object IOException;

    // 3.2 - Métodos e Funções
    public String ler_json(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Post
    @Test(priority = 1)
    public void incluir_pet() throws IOException {
        String jsonBody = ler_json("db/pet1.json");

        //Sintaxe Gherkin - Estrutura Rest-Assured
        //Dado - Quando - Então
        //Given - When - Then

        String path;
        given()
                .contentType("application/json") //comum em APIs REST - antigas era text/xml
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)

        .then()
                .log().all()
                .statusCode(200)
                .body("name", is ("klaus"))
                .body( "status", is ("available"))
                .body ("category.name", is ("132NC456DP")) //usar IS quando não tem colchetes dentro do principal
                .body ("tags.name", contains("token")) //usar CONTAINS quando tem colchetes dentro do principal
        ;



    }

    //GET
@Test(priority = 2)
    public void consultarPet(){
        String petId = "1991030830";
        String token =
            given()
                    .contentType("application/json")
                    .log().all()

                    .when()
                            .get(uri + "/" + petId)

                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("name", is ("klaus"))
                    .body("status", is ("available"))
                    .body("category.name", is ("132NC456DP"))


                    .extract()
                .path("category.name")

           ;

    System.out.println("O token é " + token);


    }

    }

