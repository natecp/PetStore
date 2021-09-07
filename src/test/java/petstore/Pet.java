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
import static org.hamcrest.Matchers.containsInAnyOrder;

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
                .body("name", is ("zelda"))
                .body( "status", is ("available"))
                .body ("category.name", is ("555888")) //usar IS quando não tem colchetes dentro do principal
                .body ("tags.name", contains("teste")) //usar CONTAINS quando tem colchetes dentro do principal
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

    //PUT
    @Test(priority = 3)
    public void alterarPet() throws IOException {
        String jsonBody = ler_json("db/pet2.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .put(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is ("klaus"))
                .body("status", is("unavailable"))


        ;



    }

    //DELETE
    @Test(priority = 4)
    public void excluirPet(){
        String petId = "1991030830";

        given()
                .contentType("application/json")
                .log().all()

                .when()
                        .delete(uri + "/" + petId)

                .then()
                        .log().all()
                        .statusCode(200)
                        .body("code", is (200)) //está olhando a estrutura de dados que retorna do json
                        .body("type", is("unknown"))
                        .body("message", is("1991030830"));

    }

//GET POR STATUS
@Test(priority = 5)
    public void consultarPetPorStatus(){
    String status = "available";
            given()
                    .contentType("application/json")
                    .log().all()

            .when()
                    .get(uri + "/findByStatus?status=" + status)
            .then()
                    .log().all()
                    .statusCode(200)





            ;




}


    }

