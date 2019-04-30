package qaLoginAndCreateMinCase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import io.gatling.jdbc.Predef._

class qaLoginAndCreateMinCase extends Simulation {

	val httpProtocol = http
		.baseUrl("https://qa.cs-notprod.homeoffice.gov.uk")
		.inferHtmlResources()

	val headers_0 = Map(
		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"cache-control" -> "max-age=0",
		"upgrade-insecure-requests" -> "1",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_1 = Map("User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_11 = Map(
		"accept" -> "image/webp,image/apng,image/*,*/*;q=0.8",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"cache-control" -> "no-cache",
		"pragma" -> "no-cache",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_13 = Map(
		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"cache-control" -> "max-age=0",
		"origin" -> "https://sso-dev.notprod.homeoffice.gov.uk",
		"upgrade-insecure-requests" -> "1",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_16 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_18 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryz6KFdPQVP0BfhmmK",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_21 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryOp067WIOTkELXfxu",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

    val uri1 = "https://sso-dev.notprod.homeoffice.gov.uk/auth"
    val uri2 = "https://www.google-analytics.com"

	val scn = scenario("qaLoginAndCreateMinCase")
		.exec(http("request_0")
			.get(uri1 + "/realms/hocs-notprod/protocol/openid-connect/auth?client_id=hocs-frontend-dev&redirect_uri=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Foauth%2Fcallback&response_type=code&scope=openid+email+profile&state=Lw%3D%3D")
			.headers(headers_0)
			.resources(http("request_1")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/govuk-template.css?0.22.1")
			.headers(headers_1),
            http("request_2")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/fonts.css?0.22.1")
			.headers(headers_1),
            http("request_3")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/css/govuk_elements.css")
			.headers(headers_1),
            http("request_4")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/css/govuk-internal.css")
			.headers(headers_1),
            http("request_5")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/javascripts/govuk-template.js?0.22.1")
			.headers(headers_1),
            http("request_6")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/govuk-template-print.css?0.22.1")
			.headers(headers_1),
            http("request_7")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/images/gov.uk_logotype_crown.png?0.22.1"),
            http("request_8")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/images/gov.uk_logotype_crown_invert_trans.png?0.19.2"),
            http("request_9")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/images/open-government-licence.png?0.22.1"),
            http("request_10")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/images/govuk-crest.png?0.22.1"),
            http("request_11")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/images/favicon.ico?0.22.1")
			.headers(headers_11),
            http("request_12")
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/images/gov.uk_logotype_crown.png?0.22.1")))
		.pause(2)
		.exec(http("request_13")
			.post(uri1 + "/realms/hocs-notprod/login-actions/authenticate?session_code=FfenDub7wVEdqxyfoHVXk4iOnDnXoLtB6_-uyH3H3Dc&execution=6495391f-0073-4c59-9dbb-0551f0a91a83&client_id=hocs-frontend-dev&tab_id=CAwd7vFRxSU")
			.headers(headers_13)
			.formParam("username", "danny.large@ten10.com")
			.formParam("password", "Password1!")
			.formParam("login", "Continue")
			.resources(http("request_14")
			.get(uri2 + "/analytics.js")
			.headers(headers_1),
            http("request_15")
			.get(uri2 + "/r/collect?v=1&_v=j73&a=783994054&t=pageview&_s=1&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2F&dr=https%3A%2F%2Fsso-dev.notprod.homeoffice.gov.uk%2Fauth%2Frealms%2Fhocs-notprod%2Fprotocol%2Fopenid-connect%2Fauth%3Fclient_id%3Dhocs-frontend-dev%26redirect_uri%3Dhttps%253A%252F%252Fqa.cs-notprod.homeoffice.gov.uk%252Foauth%252Fcallback%26response_type%3Dcode%26scope%3Dopenid%2Bemail%2Bprofile%26state%3DLw%253D%253D&dp=%2F&ul=en-gb&de=UTF-8&dt=Dashboard&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=AACAAAAB~&jid=1049157790&gjid=1978944971&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&_r=1&z=2059853204")
			.headers(headers_1)))
		.pause(3)
		.exec(http("request_16")
			.get("/api/form/action/create/workflow")
			.headers(headers_16)
			.resources(http("request_17")
			.get(uri2 + "/collect?v=1&_v=j73&a=783994054&t=pageview&_s=2&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2F&dr=https%3A%2F%2Fsso-dev.notprod.homeoffice.gov.uk%2Fauth%2Frealms%2Fhocs-notprod%2Fprotocol%2Fopenid-connect%2Fauth%3Fclient_id%3Dhocs-frontend-dev%26redirect_uri%3Dhttps%253A%252F%252Fqa.cs-notprod.homeoffice.gov.uk%252Foauth%252Fcallback%26response_type%3Dcode%26scope%3Dopenid%2Bemail%2Bprofile%26state%3DLw%253D%253D&dp=%2Faction%2Fcreate%2Fworkflow&ul=en-gb&de=UTF-8&dt=Create%20Single%20Case&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=994116018")
			.headers(headers_1)))
		.pause(5)
		.exec(http("request_18")
			.post("/api/action/create/workflow")
			.headers(headers_18)
			.body(RawFileBody("/src/test/resources/performanceTextFile.txt"))
			.resources(http("request_19")
			.get("/api/form/action/create/MIN/DOCUMENT")
			.headers(headers_16),
            http("request_20")
			.get(uri2 + "/collect?v=1&_v=j73&a=783994054&t=pageview&_s=3&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2F&dr=https%3A%2F%2Fsso-dev.notprod.homeoffice.gov.uk%2Fauth%2Frealms%2Fhocs-notprod%2Fprotocol%2Fopenid-connect%2Fauth%3Fclient_id%3Dhocs-frontend-dev%26redirect_uri%3Dhttps%253A%252F%252Fqa.cs-notprod.homeoffice.gov.uk%252Foauth%252Fcallback%26response_type%3Dcode%26scope%3Dopenid%2Bemail%2Bprofile%26state%3DLw%253D%253D&dp=%2Faction%2Fcreate%2FMIN%2FDOCUMENT&ul=en-gb&de=UTF-8&dt=Create%20Single%20Case&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=343814147")
			.headers(headers_1)))
		.pause(14)
		.exec(http("request_21")
			.post("/api/action/create/MIN/DOCUMENT")
			.headers(headers_21)
			.body(RawFileBody("/src/test/resources/performanceTextFile.txt")))
		.pause(5)
		.exec(http("request_22")
			.get(uri2 + "/collect?v=1&_v=j73&a=783994054&t=pageview&_s=4&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2F&dr=https%3A%2F%2Fsso-dev.notprod.homeoffice.gov.uk%2Fauth%2Frealms%2Fhocs-notprod%2Fprotocol%2Fopenid-connect%2Fauth%3Fclient_id%3Dhocs-frontend-dev%26redirect_uri%3Dhttps%253A%252F%252Fqa.cs-notprod.homeoffice.gov.uk%252Foauth%252Fcallback%26response_type%3Dcode%26scope%3Dopenid%2Bemail%2Bprofile%26state%3DLw%253D%253D&dp=%2F&ul=en-gb&de=UTF-8&dt=Dashboard&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1343498969")
			.headers(headers_1)
			.resources(http("request_23")
			.get("/api/form")
			.headers(headers_16)))

	setUp(scn.inject(atOnceUsers(500))).protocols(httpProtocol)
}