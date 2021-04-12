import Requests.User;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class UserTest {
    Response res = null;
    JsonPath jp = null;
    User us;

    @BeforeTest
    public void setup(){
        us = new User();
    }

    @Test
    public void getUsers(){
        res = us.getUsersListResponse();
        us.checkStatusIs200(res);
    }

    @Test
    public void updateUser() {
        int id = 1749;
        //Please change user name to another value
        String newName = "test1";
        us.updateUser(newName,id);
        res = us.getUserResponse(id);
        jp = us.getJsonPath(res);
        String updatedName = us.getUserName(jp);
        Assert.assertEquals(updatedName,newName,"Names are not match: ");
    }

    @Test
    public void deleteUser(){
        int userId = 161;
        us.getUser(userId);
        res = us.getUserResponse(userId);
        jp = us.getJsonPath(res);
        String userName = us.getUserName(jp);
        Assert.assertNotNull(userName,"Please insert another userId:");

        us.deleteUser(userId);
        res = us.getUserResponse(userId);
        jp = us.getJsonPath(res);
        int stasusCode = us.getStatusCode(jp);
        Assert.assertEquals(stasusCode,404,"The user has been deleted");

    }
}
