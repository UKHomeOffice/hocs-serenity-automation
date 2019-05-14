
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class loginAndUpload1 extends Simulation {

	val httpProtocol = http
		.baseUrl("https://qa.cs-notprod.homeoffice.gov.uk")
		.inferHtmlResources()
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36")

	val headers_0 = Map(
		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"if-none-match" -> """W/"1d51-SK9mirCcHJRM/W5++qUYI7GcH3U"""",
		"upgrade-insecure-requests" -> "1")

	val headers_11 = Map(
		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"cache-control" -> "max-age=0",
		"origin" -> "https://sso-dev.notprod.homeoffice.gov.uk",
		"upgrade-insecure-requests" -> "1")

	val headers_14 = Map("Accept" -> "application/json, text/plain, */*")

	val headers_16 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryimdLGAEzQLvVjW4Y",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk")

	val SubmitButton = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundarybrlXnReFYkNzQQRI",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk")

    val uri1 = "https://sso-dev.notprod.homeoffice.gov.uk/auth"
    val uri2 = "https://www.google-analytics.com"

	val scn = scenario("loginAndUpload1")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/govuk-template.css?0.22.1"),
            http("request_2")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/css/govuk_elements.css"),
            http("request_3")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/css/govuk-internal.css"),
            http("request_4")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/govuk-template-print.css?0.22.1"),
            http("request_5")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/javascripts/govuk-template.js?0.22.1"),
            http("request_6")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/fonts.css?0.22.1"),
            http("request_7")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/images/gov.uk_logotype_crown.png?0.22.1"),
            http("request_8")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/images/gov.uk_logotype_crown_invert_trans.png?0.19.2"),
            http("request_9")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/images/open-government-licence.png?0.22.1"),
            http("request_10")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/images/govuk-crest.png?0.22.1")))
		.pause(1)
		.exec(http("request_11")
			.post(uri1 + "/realms/hocs-notprod/login-actions/authenticate?session_code=xbzqWPDNBQVJyXD60dUZ0mq7BFC3KfhV1sIIzCpxVrk&execution=6495391f-0073-4c59-9dbb-0551f0a91a83&client_id=hocs-frontend-dev&tab_id=UUcZDiQ4Y2M")
			.headers(headers_11)
			.formParam("username", "danny.large@ten10.com")
			.formParam("password", "Password1!")
			.formParam("login", "Continue")
			.resources(http("request_12")
			.get(uri2 + "/analytics.js"),
            http("request_13")
			.get(uri2 + "/collect?v=1&_v=j73&a=965691092&t=pageview&_s=1&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2F&dr=https%3A%2F%2Fsso-dev.notprod.homeoffice.gov.uk%2Fauth%2Frealms%2Fhocs-notprod%2Fprotocol%2Fopenid-connect%2Fauth%3Fclient_id%3Dhocs-frontend-dev%26redirect_uri%3Dhttps%253A%252F%252Fqa.cs-notprod.homeoffice.gov.uk%252Foauth%252Fcallback%26response_type%3Dcode%26scope%3Dopenid%2Bemail%2Bprofile%26state%3DLw%253D%253D&dp=%2F&ul=en-gb&de=UTF-8&dt=Dashboard&sd=24-bit&sr=1920x1080&vp=396x928&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=2004296316.1557733763&z=954140426")))
		.pause(7)
		.exec(http("request_14")
			.get("/api/form/action/create/workflow")
			.headers(headers_14)
			.resources(http("request_15")
			.get(uri2 + "/collect?v=1&_v=j73&a=965691092&t=pageview&_s=2&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2F&dr=https%3A%2F%2Fsso-dev.notprod.homeoffice.gov.uk%2Fauth%2Frealms%2Fhocs-notprod%2Fprotocol%2Fopenid-connect%2Fauth%3Fclient_id%3Dhocs-frontend-dev%26redirect_uri%3Dhttps%253A%252F%252Fqa.cs-notprod.homeoffice.gov.uk%252Foauth%252Fcallback%26response_type%3Dcode%26scope%3Dopenid%2Bemail%2Bprofile%26state%3DLw%253D%253D&dp=%2Faction%2Fcreate%2Fworkflow&ul=en-gb&de=UTF-8&dt=Create%20Single%20Case&sd=24-bit&sr=1920x1080&vp=396x928&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=2004296316.1557733763&z=407115411")))
		.pause(1)
		.exec(http("request_16")
			.post("/api/action/create/workflow")
			.headers(headers_16)
			.body(RawFileBody("test2.docx"))
			.resources(http("request_17")
			.get("/action/create/MIN/DOCUMENT")
			.headers(headers_14),
            http("request_18")
			.get(uri2 + "/collect?v=1&_v=j73&a=965691092&t=pageview&_s=3&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2F&dr=https%3A%2F%2Fsso-dev.notprod.homeoffice.gov.uk%2Fauth%2Frealms%2Fhocs-notprod%2Fprotocol%2Fopenid-connect%2Fauth%3Fclient_id%3Dhocs-frontend-dev%26redirect_uri%3Dhttps%253A%252F%252Fqa.cs-notprod.homeoffice.gov.uk%252Foauth%252Fcallback%26response_type%3Dcode%26scope%3Dopenid%2Bemail%2Bprofile%26state%3DLw%253D%253D&dp=%2Faction%2Fcreate%2FMIN%2FDOCUMENT&ul=en-gb&de=UTF-8&dt=Create%20Single%20Case&sd=24-bit&sr=1920x1080&vp=396x928&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=2004296316.1557733763&z=451829051")))
		.pause(7)
		.exec(http("request_19")
			.post("/action/create/MIN/DOCUMENT")
			.headers(SubmitButton)
			.formParam("DateReceived", "2019-01-01")
			.formParam("DateReceived-day", "01")
			.formParam("DateReceived-month", "01")
			.formParam("DateReceived-year", "2019")
			.formUpload("add_document", "src/test/resources/performanceTextFile.txt"))
//			.check(css("h1").is("Success")))

	setUp(scn.inject(atOnceUsers(100))).protocols(httpProtocol)
}