package Requests;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.*;
import org.testng.Assert;

public class User {
    String UsersUrl = "https://gorest.co.in/public-api/users/";
    String UserUrl = "https://gorest.co.in/public-api/users/";
    String token = "5a891662a6908a75f745bde231a03bb90a9e3c975a24669c4c07d3a86b7f509e";

    public Response getUsersListResponse() {
        return get(UsersUrl);
    }
    public Response getUserResponse(int id) {
        return get(UserUrl+id);
    }

    public void checkStatusIs200 (Response res) {
        System.out.println("Status code: "+res.getStatusCode());
        Assert.assertEquals(200,res.getStatusCode(),"Status check failed");
    }

    public void updateUser(String newName, int id){
        String payload = String.format("{\"name\":\"%s\"}",newName);
        given()
                .header("Authorization","Bearer "+ token)
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .body(payload)
                .patch(UsersUrl+id).prettyPrint();
    }

    public JsonPath getJsonPath (Response res) {
        String json = res.asString();
        return new JsonPath(json);
    }

    public String getUserName (JsonPath jp) {
        String userName = jp.get("data.name");
        System.out.println("User name: "+userName);
        return userName;
    }

    public int getStatusCode (JsonPath jp) {
        int statusCode = jp.get("code");
        System.out.println("Status code: "+ statusCode);
        return statusCode;
    }

    public void deleteUser(int id){
        given()
                .header("Authorization","Bearer "+ token)
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .delete(UsersUrl+id).prettyPrint();
    }

    public void getUser(int id){
        given()
                .header("Authorization","Bearer "+ token)
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .get(UsersUrl+id).prettyPrint();
    }
}
