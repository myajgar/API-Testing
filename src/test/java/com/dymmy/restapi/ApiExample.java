package com.dymmy.restapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;


public class ApiExample {
    private Logger logger = Logger.getLogger(ApiExample.class);
    String baseURI = RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";//common things for all .so that wekeep it as here.
    private String employeesEndpoint = "employees";
    private String createAnEmployee = "create";
    private String updatesemp = "update/5";
    private String deleteEmp = "delete/6";

    //it gonna same as for one employee.
    @Test
    public void getAllEmployees() {
        Response response = RestAssured.given().when().get(employeesEndpoint)
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(response.asString());//asString gives u all data.
        System.out.println(response.getStatusCode());
        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("Successfully! All records has been fetched."), true, "the strings are not equal");
        logger.info("our request is passe we get all the employess");

    }


    @Test
    public void createAnEployee() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "karim");
        jsonObject.put("salary", 10000);
        jsonObject.put("age", 25);
        System.out.println(jsonObject);
        Response response = RestAssured.given().contentType(ContentType.JSON).body(jsonObject).when().post(createAnEmployee)
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());
        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("Successfully! Record has been added."),
                true, "the strings are not equal");
        logger.info("the employee added sucefully and ");

    }

    @Test
    public void updatesEmployees() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("salary", "12000");
        System.out.println(jsonObject);
        Response response = RestAssured.given().body(jsonObject).when().put(updatesemp)
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(response.asString());//asString gives u all data.
        System.out.println(response.getStatusCode());
        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("Successfully! Record has been updated."),
                true, "the strings are not equal");
        logger.info("we can update employee");

    }

    @Test
    public void deleteAnEmployees() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("salary", "12000");
        System.out.println(jsonObject);
        Response response = RestAssured.given().when().delete(deleteEmp)
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());
        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("Successfully! Record has been deleted"),
                true, "the strings are not equal");
        logger.info("we can update employee");
    }
}











