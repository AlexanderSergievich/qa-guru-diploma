package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;

public class UserSpecs {
    public static RequestSpecification SignInPostRequestSpecification = with()
            .header("Content-Type", "application/json; charset=utf-8")
            .log().all()
            .filter(withCustomTemplates());
    public static ResponseSpecification PostResponseSpecification = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();
    public static RequestSpecification GetRequestSpecification = with()
            .header("Content-Type", "application/vnd.api+json")
            .log().all()
            .filter(withCustomTemplates());
    public static ResponseSpecification GetResponseSpecification = new ResponseSpecBuilder()
            .log(BODY)
            .log(STATUS)
            .expectStatusCode(200)
            .build();
    public static RequestSpecification SignUpPostRequestSpecification = with()
            .header("Content-Type", "application/vnd.api+json")
            .log().all()
            .filter(withCustomTemplates());
//    public static ResponseSpecification createUserResponseSpec = new ResponseSpecBuilder()
//            .log(ALL)
//            .expectStatusCode(201)
//            .build();
//    public static ResponseSpecification deleteUserResponseSpec = new ResponseSpecBuilder()
//            .log(ALL)
//            .expectStatusCode(204)
//            .build();
//    public static ResponseSpecification notFoundUserResponseSpec = new ResponseSpecBuilder()
//            .log(ALL)
//            .expectStatusCode(404)
//            .build();
}

