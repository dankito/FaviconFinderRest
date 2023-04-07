package net.dankito.utils.favicon.rest

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test

@QuarkusTest
class FaviconFinderResourceTest {

  @Test
  fun findFavicons() {
    RestAssured.given()
      .`when`().get("/favicon-finder?url=codinux.net")
      .then()
        .statusCode(200)
        .body(containsString("https://www.codinux.net"))
  }
}