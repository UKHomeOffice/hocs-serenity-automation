
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HocsDocument extends Simulation {

	val httpProtocol = http
		.baseUrl("http://detectportal.firefox.com")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-GB,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0")

	val headers_0 = Map(
		"Accept-Encoding" -> "gzip, deflate",
		"Pragma" -> "no-cache")

	val headers_9 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_11 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_17 = Map("Accept" -> "image/webp,*/*")

	val headers_24 = Map("Accept" -> "application/json, text/plain, */*")

	val headers_25 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=---------------------------18467633426500")

	val headers_28 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=---------------------------191691572411478")

    val uri1 = "https://sso-dev.notprod.homeoffice.gov.uk:443/auth"
    val uri2 = "https://qa.cs-notprod.homeoffice.gov.uk:443"
    val uri3 = "http://detectportal.firefox.com/success.txt"

	val scn = scenario("HocsDocument")
		// Login
		.exec(http("request_22")
			.post(uri1 + "/realms/hocs-notprod/login-actions/authenticate?session_code=bVqY7iRgij-7AO-GOPfnvTZRMFV15nkHiFlsdxvz6C0&execution=6495391f-0073-4c59-9dbb-0551f0a91a83&client_id=hocs-frontend-dev&tab_id=1u4-RsnHc98")
			.headers(headers_9)
			.formParam("username", "danny.large@ten10.com")
			.formParam("password", "Password1!")
			.formParam("login", "Continue")
  		.check(currentLocation.saveAs("postLoginLocation"))
			.resources(http("request_23")
			.get("/success.txt")
			.headers(headers_0)))
		.pause(17)

	println("postLoginLocation")

//		// CreateSingleCase
//		.exec(http("request_24")
//			.get(uri2 + "/api/form/action/create/workflow")
//			.headers(headers_24))
//		.pause(9)
//		// Select
//		.exec(http("request_25")
//			.post(uri2 + "/api/action/create/workflow")
//			.headers(headers_25)
//			.body(RawFileBody("HocsDocument_0025_request.txt"))
//			.resources(http("request_26")
//			.get(uri2 + "/api/form/action/create/MIN/DOCUMENT")
//			.headers(headers_24)))
//		.pause(35)
//		// Browse
//		.exec(http("request_27")
//			.get("/success.txt")
//			.headers(headers_0))
//		.pause(6)
//		// Finish
//		.exec(http("request_28")
//			.post(uri2 + "/api/action/create/MIN/DOCUMENT")
//			.headers(headers_28)
//			.body(RawFileBody("HocsDocument_0028_request.txt")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}