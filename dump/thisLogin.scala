
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class thisLogin extends Simulation {

	val httpProtocol = http
		.baseUrl("https://sso-dev.notprod.homeoffice.gov.uk")
		.inferHtmlResources()

	val headers_1 = Map(
		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"upgrade-insecure-requests" -> "1",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_2 = Map("User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_13 = Map(
		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"cache-control" -> "max-age=0",
		"origin" -> "https://sso-dev.notprod.homeoffice.gov.uk",
		"upgrade-insecure-requests" -> "1",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

    val uri3 = "https://qa.cs-notprod.homeoffice.gov.uk"

	val scn = scenario("Login")
		.exec(http("request_13")
			.post("/auth/realms/hocs-notprod/login-actions/authenticate?session_code=nqBV6eIVQUADidb8SmbsadDmyThSuMLxoG4m1eYjCxA&execution=6495391f-0073-4c59-9dbb-0551f0a91a83&client_id=hocs-frontend-dev&tab_id=hTJJ9XIOC7s")
			.headers(headers_13)
			.formParam("username", "danny.large@ten10.com")
			.formParam("password", "Password1!")
			.formParam("login", "Continue")
			.resources(http("request_14"))
  			.check(status.in(200,201,202,304))
  			.check(status.not(404)))
			.pause(12)

	setUp(scn.inject(atOnceUsers(300))).protocols(httpProtocol)
}