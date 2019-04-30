
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class qaLoginAndFullWorkFlow extends Simulation {

	val httpProtocol = http
		.baseUrl("https://qa.cs-notprod.homeoffice.gov.uk")
		.inferHtmlResources()

	val headers_0 = Map(
		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"upgrade-insecure-requests" -> "1",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_1 = Map("User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_12 = Map(
		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"cache-control" -> "max-age=0",
		"origin" -> "https://sso-dev.notprod.homeoffice.gov.uk",
		"upgrade-insecure-requests" -> "1",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_20 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_22 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryZor7H3gxiHzLMZUe",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_25 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryLq1lzcbdpiHyjWBO",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_26 = Map(
		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"cache-control" -> "max-age=0",
		"content-type" -> "multipart/form-data; boundary=----WebKitFormBoundaryJ84mXsQBAAPAlj9W",
		"origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"upgrade-insecure-requests" -> "1",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_41 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryAU2HyNuj7JL0z1Bf",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_50 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryENcUCwIkZWRZ9NRi",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_53 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryevpTJgQOJUPiVKHM",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_56 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryYzXlA8qUOeOF3BAJ",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_62 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryB4KJJCrQUAs4IjCB",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_65 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryrOEBlcAt8HAXkWZh",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_74 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryYCAhVNcHLXukDMuO",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_81 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryKJSBBt3zRaCBPhSE",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_86 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryyyx35kM4mbmovDFd",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_88 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundarypm4xaQK3ZAis9Rca",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_102 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundarykxMNTUj9PzFZe7ya",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_107 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryKRbiGK6WHBeSzJVd",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_112 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryVjbbfTJllSeqpYs6",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_117 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryUeXpo2Dz9UUW9bvM",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_120 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryJnZoIa9ZyBeaK3yQ",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_134 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundarye8iTBy9HtMbRAY9B",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_153 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryHwZKAsnBox8rkNZl",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_167 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryohs4AAc05KSmdfBz",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_170 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryrVA28jReqpcldbAc",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_179 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryvr6ZJ3RxwVp1rFNR",
		"Origin" -> "https://qa.cs-notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

	val headers_195 = Map(
		"Origin" -> "https://sso-dev.notprod.homeoffice.gov.uk",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

    val uri1 = "https://sso-dev.notprod.homeoffice.gov.uk/auth"
    val uri2 = "https://www.google-analytics.com"

	val scn = scenario("qaLoginAndFullWorkFlow")
		.exec(http("request_0")
			.get("/")
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
			.get(uri1 + "/resources/4.5.0.final/login/govuk-internal/vendor/govuk_template/stylesheets/images/gov.uk_logotype_crown.png?0.22.1")))
		.pause(2)
		.exec(http("request_12")
			.post(uri1 + "/realms/hocs-notprod/login-actions/authenticate?session_code=mWezuaAzpydziYSAZAC0JDk2BJU2zVYqDqnd8wPot7A&execution=6495391f-0073-4c59-9dbb-0551f0a91a83&client_id=hocs-frontend-dev&tab_id=5EDlQL8uWcA")
			.headers(headers_12)
			.formParam("username", "danny.large@ten10.com")
			.formParam("password", "Password1!")
			.formParam("login", "Continue")
			.resources(http("request_13")
			.get("/public/styles/main-dafc2aa265b99781bc06.css")
			.headers(headers_1),
            http("request_14")
			.get("/public/js/runtime-dafc2aa265b99781bc06.js")
			.headers(headers_1),
            http("request_15")
			.get("/public/js/main-1696f7c8ab8749f24928.js")
			.headers(headers_1),
            http("request_16")
			.get("/public/all.js")
			.headers(headers_1),
            http("request_17")
			.get("/public/js/vendor-6806be88446432f69a81.js")
			.headers(headers_1),
            http("request_18")
			.get(uri2 + "/analytics.js")
			.headers(headers_1),
            http("request_19")
			.get(uri2 + "/r/collect?v=1&_v=j73&a=1709419062&t=pageview&_s=1&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2F&dr=https%3A%2F%2Fsso-dev.notprod.homeoffice.gov.uk%2Fauth%2Frealms%2Fhocs-notprod%2Fprotocol%2Fopenid-connect%2Fauth%3Fclient_id%3Dhocs-frontend-dev%26redirect_uri%3Dhttps%253A%252F%252Fqa.cs-notprod.homeoffice.gov.uk%252Foauth%252Fcallback%26response_type%3Dcode%26scope%3Dopenid%2Bemail%2Bprofile%26state%3DLw%253D%253D&dp=%2F&ul=en-gb&de=UTF-8&dt=Dashboard&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=AACAAAAB~&jid=1175409931&gjid=255583834&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&_r=1&z=234420508")
			.headers(headers_1)))
		.pause(2)
		.exec(http("request_20")
			.get("/api/form/action/create/workflow")
			.headers(headers_20)
			.resources(http("request_21")
			.get(uri2 + "/collect?v=1&_v=j73&a=1709419062&t=pageview&_s=2&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2F&dr=https%3A%2F%2Fsso-dev.notprod.homeoffice.gov.uk%2Fauth%2Frealms%2Fhocs-notprod%2Fprotocol%2Fopenid-connect%2Fauth%3Fclient_id%3Dhocs-frontend-dev%26redirect_uri%3Dhttps%253A%252F%252Fqa.cs-notprod.homeoffice.gov.uk%252Foauth%252Fcallback%26response_type%3Dcode%26scope%3Dopenid%2Bemail%2Bprofile%26state%3DLw%253D%253D&dp=%2Faction%2Fcreate%2Fworkflow&ul=en-gb&de=UTF-8&dt=Create%20Single%20Case&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1749026294")
			.headers(headers_1)))
		.pause(2)
		.exec(http("request_22")
			.post("/api/action/create/workflow")
			.headers(headers_22)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0022_request.txt"))
			.resources(http("request_23")
			.get("/api/form/action/create/MIN/DOCUMENT")
			.headers(headers_20),
            http("request_24")
			.get(uri2 + "/collect?v=1&_v=j73&a=1709419062&t=pageview&_s=3&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2F&dr=https%3A%2F%2Fsso-dev.notprod.homeoffice.gov.uk%2Fauth%2Frealms%2Fhocs-notprod%2Fprotocol%2Fopenid-connect%2Fauth%3Fclient_id%3Dhocs-frontend-dev%26redirect_uri%3Dhttps%253A%252F%252Fqa.cs-notprod.homeoffice.gov.uk%252Foauth%252Fcallback%26response_type%3Dcode%26scope%3Dopenid%2Bemail%2Bprofile%26state%3DLw%253D%253D&dp=%2Faction%2Fcreate%2FMIN%2FDOCUMENT&ul=en-gb&de=UTF-8&dt=Create%20Single%20Case&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1432092099")
			.headers(headers_1)))
		.pause(10)
		.exec(http("request_25")
			.post("/api/action/create/MIN/DOCUMENT")
			.headers(headers_25))
		.pause(10)
		.exec(http("request_26")
			.post("/search/reference")
			.headers(headers_26)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0026_request.txt"))
			.resources(http("request_27")
			.get("/public/js/runtime-dafc2aa265b99781bc06.js")
			.headers(headers_1),
            http("request_28")
			.get("/public/js/vendor-6806be88446432f69a81.js")
			.headers(headers_1),
            http("request_29")
			.get("/public/js/main-1696f7c8ab8749f24928.js")
			.headers(headers_1),
            http("request_30")
			.get("/public/all.js")
			.headers(headers_1),
            http("request_31")
			.get("/public/styles/main-dafc2aa265b99781bc06.css")
			.headers(headers_1),
            http("request_32")
			.get(uri2 + "/analytics.js")
			.headers(headers_1),
            http("request_33")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500)),
            http("request_34")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=1&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&ul=en-gb&de=UTF-8&dt=MIN%2F0120297%2F19&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=AACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1289580733")
			.headers(headers_1)))
		.pause(8)
		.exec(http("request_35")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/55e5150a-94df-4758-93cd-1ed55c3ff4da/allocate")
			.headers(headers_20)
			.resources(http("request_36")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=2&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da%2Fallocate&ul=en-gb&de=UTF-8&dt=Data%20Input&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=860834271")
			.headers(headers_1),
            http("request_37")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_38")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(4)
		.exec(http("request_39")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20))
		.pause(4)
		.exec(http("request_40")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20))
		.pause(3)
		.exec(http("request_41")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/55e5150a-94df-4758-93cd-1ed55c3ff4da/allocate")
			.headers(headers_41)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0041_request.txt"))
			.resources(http("request_42")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/55e5150a-94df-4758-93cd-1ed55c3ff4da")
			.headers(headers_20),
            http("request_43")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=3&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&ul=en-gb&de=UTF-8&dt=Data%20Input&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=405468805")
			.headers(headers_1),
            http("request_44")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_45")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_46")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/55e5150a-94df-4758-93cd-1ed55c3ff4da/entity/correspondent/add")
			.headers(headers_20)
			.resources(http("request_47")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=4&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da%2Fentity%2Fcorrespondent%2Fadd&ul=en-gb&de=UTF-8&dt=Add%20Correspondent&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=259502328")
			.headers(headers_1),
            http("request_48")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_49")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_50")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/55e5150a-94df-4758-93cd-1ed55c3ff4da/entity/correspondent/add")
			.headers(headers_50)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0050_request.txt"))
			.resources(http("request_51")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/55e5150a-94df-4758-93cd-1ed55c3ff4da/entity/member/add")
			.headers(headers_20),
            http("request_52")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=5&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da%2Fentity%2Fmember%2Fadd&ul=en-gb&de=UTF-8&dt=Add%20member%20of%20parliament&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1229064853")
			.headers(headers_1)))
		.pause(7)
		.exec(http("request_53")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/55e5150a-94df-4758-93cd-1ed55c3ff4da/entity/member/add")
			.headers(headers_53)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0053_request.txt"))
			.resources(http("request_54")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/55e5150a-94df-4758-93cd-1ed55c3ff4da/entity/member/58c8e3e3-53bd-4e4b-9600-6d7b2dd36c30/details")
			.headers(headers_20),
            http("request_55")
			.get(uri2 + "/r/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=6&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da%2Fentity%2Fmember%2F58c8e3e3-53bd-4e4b-9600-6d7b2dd36c30%2Fdetails&ul=en-gb&de=UTF-8&dt=Member%20Details&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=1007762534&gjid=1374665337&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&_r=1&z=540901042")
			.headers(headers_1)))
		.pause(5)
		.exec(http("request_56")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/55e5150a-94df-4758-93cd-1ed55c3ff4da/entity/member/58c8e3e3-53bd-4e4b-9600-6d7b2dd36c30/details")
			.headers(headers_56)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0056_request.txt"))
			.resources(http("request_57")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/55e5150a-94df-4758-93cd-1ed55c3ff4da")
			.headers(headers_20),
            http("request_58")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=7&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&ul=en-gb&de=UTF-8&dt=Data%20Input&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=299770881")
			.headers(headers_1),
            http("request_59")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_60")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(4)
		.exec(http("request_61")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20)
			.resources(http("request_62")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/55e5150a-94df-4758-93cd-1ed55c3ff4da")
			.headers(headers_62)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0062_request.txt")),
            http("request_63")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=8&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2F&ul=en-gb&de=UTF-8&dt=Dashboard&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1698822042")
			.headers(headers_1),
            http("request_64")
			.get("/api/form")
			.headers(headers_20)))
		.pause(3)
		.exec(http("request_65")
			.post("/api/search/reference")
			.headers(headers_65)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0065_request.txt"))
			.resources(http("request_66")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/bae7bfa5-abfc-4c35-8a36-d688beabce65")
			.headers(headers_20),
            http("request_67")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=9&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Fbae7bfa5-abfc-4c35-8a36-d688beabce65&ul=en-gb&de=UTF-8&dt=MIN%2F0120297%2F19&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=2129702401")
			.headers(headers_1),
            http("request_68")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_69")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_70")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/bae7bfa5-abfc-4c35-8a36-d688beabce65/allocate")
			.headers(headers_20)
			.resources(http("request_71")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=10&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Fbae7bfa5-abfc-4c35-8a36-d688beabce65%2Fallocate&ul=en-gb&de=UTF-8&dt=Markup&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=601156921")
			.headers(headers_1),
            http("request_72")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_73")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_74")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/bae7bfa5-abfc-4c35-8a36-d688beabce65/allocate")
			.headers(headers_74)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0074_request.txt"))
			.resources(http("request_75")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/bae7bfa5-abfc-4c35-8a36-d688beabce65")
			.headers(headers_20),
            http("request_76")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=11&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Fbae7bfa5-abfc-4c35-8a36-d688beabce65&ul=en-gb&de=UTF-8&dt=Markup&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1522469062")
			.headers(headers_1),
            http("request_77")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_78")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_79")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/bae7bfa5-abfc-4c35-8a36-d688beabce65/entity/topic/add")
			.headers(headers_20)
			.resources(http("request_80")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=12&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Fbae7bfa5-abfc-4c35-8a36-d688beabce65%2Fentity%2Ftopic%2Fadd&ul=en-gb&de=UTF-8&dt=Add%20Topic&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=268022703")
			.headers(headers_1)))
		.pause(3)
		.exec(http("request_81")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/bae7bfa5-abfc-4c35-8a36-d688beabce65/entity/topic/add")
			.headers(headers_81)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0081_request.txt"))
			.resources(http("request_82")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/bae7bfa5-abfc-4c35-8a36-d688beabce65")
			.headers(headers_20),
            http("request_83")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=13&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Fbae7bfa5-abfc-4c35-8a36-d688beabce65&ul=en-gb&de=UTF-8&dt=Markup&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=2037757776")
			.headers(headers_1),
            http("request_84")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_85")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_86")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/bae7bfa5-abfc-4c35-8a36-d688beabce65")
			.headers(headers_86)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0086_request.txt"))
			.resources(http("request_87")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/bae7bfa5-abfc-4c35-8a36-d688beabce65")
			.headers(headers_20)))
		.pause(1)
		.exec(http("request_88")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/bae7bfa5-abfc-4c35-8a36-d688beabce65")
			.headers(headers_88)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0088_request.txt"))
			.resources(http("request_89")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=14&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2F&ul=en-gb&de=UTF-8&dt=Dashboard&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1378153037")
			.headers(headers_1),
            http("request_90")
			.get("/api/form")
			.headers(headers_20)))
		.pause(2)
		.exec(http("request_91")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=15&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fworkstack%2Fteam%2F183c522c-e3d8-454f-8061-881c9c2b1e28&ul=en-gb&de=UTF-8&dt=Team%20Workstack&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1607786875")
			.headers(headers_1)
			.resources(http("request_92")
			.get("/api/workstack/team/183c522c-e3d8-454f-8061-881c9c2b1e28")
			.headers(headers_20)))
		.pause(5)
		.exec(http("request_93")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=event&_s=16&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&ul=en-gb&de=UTF-8&dt=Correspondence%20System&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&ec=Workstack&ea=Filter&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1703884922")
			.headers(headers_1)
			.resources(http("request_94")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/d156f13b-82f6-4cd3-9db7-84531021c300")
			.headers(headers_20),
            http("request_95")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=17&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Fd156f13b-82f6-4cd3-9db7-84531021c300&ul=en-gb&de=UTF-8&dt=MIN%2F0120297%2F19&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=898034911")
			.headers(headers_1),
            http("request_96")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_97")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_98")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=18&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Fd156f13b-82f6-4cd3-9db7-84531021c300%2Fallocate&ul=en-gb&de=UTF-8&dt=Initial%20Draft&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=902151031")
			.headers(headers_1)
			.resources(http("request_99")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/d156f13b-82f6-4cd3-9db7-84531021c300/allocate")
			.headers(headers_20),
            http("request_100")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_101")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(2)
		.exec(http("request_102")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/d156f13b-82f6-4cd3-9db7-84531021c300/allocate")
			.headers(headers_102)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0102_request.txt"))
			.resources(http("request_103")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/d156f13b-82f6-4cd3-9db7-84531021c300")
			.headers(headers_20),
            http("request_104")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=19&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Fd156f13b-82f6-4cd3-9db7-84531021c300&ul=en-gb&de=UTF-8&dt=Initial%20Draft&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1847542855")
			.headers(headers_1),
            http("request_105")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_106")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(2)
		.exec(http("request_107")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/d156f13b-82f6-4cd3-9db7-84531021c300")
			.headers(headers_107)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0107_request.txt")))
		.pause(2)
		.exec(http("request_108")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20)
			.resources(http("request_109")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/d156f13b-82f6-4cd3-9db7-84531021c300")
			.headers(headers_20)))
		.pause(2)
		.exec(http("request_110")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/d156f13b-82f6-4cd3-9db7-84531021c300/entity/document/add")
			.headers(headers_20)
			.resources(http("request_111")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=20&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Fd156f13b-82f6-4cd3-9db7-84531021c300%2Fentity%2Fdocument%2Fadd&ul=en-gb&de=UTF-8&dt=Add%20Documents&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=718681594")
			.headers(headers_1)))
		.pause(16)
		.exec(http("request_112")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/d156f13b-82f6-4cd3-9db7-84531021c300/entity/document/add")
			.headers(headers_112)
			.resources(http("request_113")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/d156f13b-82f6-4cd3-9db7-84531021c300")
			.headers(headers_20),
            http("request_114")
			.get(uri2 + "/r/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=21&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Fd156f13b-82f6-4cd3-9db7-84531021c300&ul=en-gb&de=UTF-8&dt=Initial%20Draft&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=2137078134&gjid=1547170663&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&_r=1&z=1015429764")
			.headers(headers_1),
            http("request_115")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_116")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(2)
		.exec(http("request_117")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/d156f13b-82f6-4cd3-9db7-84531021c300")
			.headers(headers_117)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0117_request.txt"))
			.resources(http("request_118")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/d156f13b-82f6-4cd3-9db7-84531021c300")
			.headers(headers_20)))
		.pause(2)
		.exec(http("request_119")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20)
			.resources(http("request_120")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/d156f13b-82f6-4cd3-9db7-84531021c300")
			.headers(headers_120)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0120_request.txt")),
            http("request_121")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=22&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2F&ul=en-gb&de=UTF-8&dt=Dashboard&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1019493975")
			.headers(headers_1),
            http("request_122")
			.get("/api/form")
			.headers(headers_20)))
		.pause(4)
		.exec(http("request_123")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=23&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fworkstack%2Fteam%2F183c522c-e3d8-454f-8061-881c9c2b1e28&ul=en-gb&de=UTF-8&dt=Team%20Workstack&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1507072267")
			.headers(headers_1)
			.resources(http("request_124")
			.get("/api/workstack/team/183c522c-e3d8-454f-8061-881c9c2b1e28")
			.headers(headers_20)))
		.pause(4)
		.exec(http("request_125")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=event&_s=24&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&ul=en-gb&de=UTF-8&dt=Correspondence%20System&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&ec=Workstack&ea=Filter&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=239151029")
			.headers(headers_1)
			.resources(http("request_126")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/498f3c4c-8cac-416d-86b5-9308c13ef9cf")
			.headers(headers_20),
            http("request_127")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=25&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F498f3c4c-8cac-416d-86b5-9308c13ef9cf&ul=en-gb&de=UTF-8&dt=MIN%2F0120297%2F19&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1040045529")
			.headers(headers_1),
            http("request_128")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_129")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_130")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/498f3c4c-8cac-416d-86b5-9308c13ef9cf/allocate")
			.headers(headers_20)
			.resources(http("request_131")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=26&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F498f3c4c-8cac-416d-86b5-9308c13ef9cf%2Fallocate&ul=en-gb&de=UTF-8&dt=QA%20Response&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=2000703075")
			.headers(headers_1),
            http("request_132")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_133")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_134")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/498f3c4c-8cac-416d-86b5-9308c13ef9cf/allocate")
			.headers(headers_134)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0134_request.txt"))
			.resources(http("request_135")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=27&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2F&ul=en-gb&de=UTF-8&dt=Dashboard&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=338932359")
			.headers(headers_1),
            http("request_136")
			.get("/api/form")
			.headers(headers_20)))
		.pause(5)
		.exec(http("request_137")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=28&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fworkstack%2Fteam%2F183c522c-e3d8-454f-8061-881c9c2b1e28&ul=en-gb&de=UTF-8&dt=Team%20Workstack&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=2118016997")
			.headers(headers_1)
			.resources(http("request_138")
			.get("/api/workstack/team/183c522c-e3d8-454f-8061-881c9c2b1e28")
			.headers(headers_20)))
		.pause(2)
		.exec(http("request_139")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=event&_s=29&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&ul=en-gb&de=UTF-8&dt=Correspondence%20System&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&ec=Workstack&ea=Filter&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=104896170")
			.headers(headers_1)
			.resources(http("request_140")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=30&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2F&ul=en-gb&de=UTF-8&dt=Dashboard&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=349143949")
			.headers(headers_1),
            http("request_141")
			.get("/api/form")
			.headers(headers_20)))
		.pause(3)
		.exec(http("request_142")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=31&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fworkstack%2Fteam%2F1102b26b-06ed-4247-a1b3-699167f2dbcd&ul=en-gb&de=UTF-8&dt=Team%20Workstack&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1616261814")
			.headers(headers_1)
			.resources(http("request_143")
			.get("/api/workstack/team/1102b26b-06ed-4247-a1b3-699167f2dbcd")
			.headers(headers_20)))
		.pause(2)
		.exec(http("request_144")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=event&_s=32&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&ul=en-gb&de=UTF-8&dt=Correspondence%20System&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&ec=Workstack&ea=Filter&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=184259269")
			.headers(headers_1)
			.resources(http("request_145")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/f5714faf-a7c1-4352-ad05-f7b462d686b5")
			.headers(headers_20),
            http("request_146")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=33&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Ff5714faf-a7c1-4352-ad05-f7b462d686b5&ul=en-gb&de=UTF-8&dt=MIN%2F0120297%2F19&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1762993649")
			.headers(headers_1),
            http("request_147")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_148")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_149")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/f5714faf-a7c1-4352-ad05-f7b462d686b5/allocate")
			.headers(headers_20)
			.resources(http("request_150")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=34&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Ff5714faf-a7c1-4352-ad05-f7b462d686b5%2Fallocate&ul=en-gb&de=UTF-8&dt=Private%20Office%20Sign%20Off&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=2024403301")
			.headers(headers_1),
            http("request_151")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_152")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(2)
		.exec(http("request_153")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/f5714faf-a7c1-4352-ad05-f7b462d686b5/allocate")
			.headers(headers_153)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0153_request.txt"))
			.resources(http("request_154")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=35&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2F&ul=en-gb&de=UTF-8&dt=Dashboard&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=914838986")
			.headers(headers_1),
            http("request_155")
			.get("/api/form")
			.headers(headers_20)))
		.pause(3)
		.exec(http("request_156")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=36&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fworkstack%2Fteam%2F5311138e-33bc-434d-9bce-a933a51a3146&ul=en-gb&de=UTF-8&dt=Team%20Workstack&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1845648445")
			.headers(headers_1)
			.resources(http("request_157")
			.get("/api/workstack/team/5311138e-33bc-434d-9bce-a933a51a3146")
			.headers(headers_20)))
		.pause(1)
		.exec(http("request_158")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=event&_s=37&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&ul=en-gb&de=UTF-8&dt=Correspondence%20System&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&ec=Workstack&ea=Filter&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1275861767")
			.headers(headers_1)
			.resources(http("request_159")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/3e5a23ea-e087-4bce-9014-9452fa4ab86c")
			.headers(headers_20),
            http("request_160")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=38&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F3e5a23ea-e087-4bce-9014-9452fa4ab86c&ul=en-gb&de=UTF-8&dt=MIN%2F0120297%2F19&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=157056658")
			.headers(headers_1),
            http("request_161")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_162")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_163")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/3e5a23ea-e087-4bce-9014-9452fa4ab86c/allocate")
			.headers(headers_20)
			.resources(http("request_164")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=39&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F3e5a23ea-e087-4bce-9014-9452fa4ab86c%2Fallocate&ul=en-gb&de=UTF-8&dt=Minister%20Sign%20Off&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=2048834688")
			.headers(headers_1),
            http("request_165")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_166")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_167")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/3e5a23ea-e087-4bce-9014-9452fa4ab86c/allocate")
			.headers(headers_167)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0167_request.txt"))
			.resources(http("request_168")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=40&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2F&ul=en-gb&de=UTF-8&dt=Dashboard&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1249952199")
			.headers(headers_1),
            http("request_169")
			.get("/api/form")
			.headers(headers_20)))
		.pause(9)
		.exec(http("request_170")
			.post("/api/search/reference")
			.headers(headers_170)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0170_request.txt"))
			.resources(http("request_171")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/ca8dbdee-2e6d-4808-949b-48ac69601a08")
			.headers(headers_20),
            http("request_172")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=41&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Fca8dbdee-2e6d-4808-949b-48ac69601a08&ul=en-gb&de=UTF-8&dt=MIN%2F0120297%2F19&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=71187998")
			.headers(headers_1),
            http("request_173")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_174")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(1)
		.exec(http("request_175")
			.get("/api/form/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/ca8dbdee-2e6d-4808-949b-48ac69601a08/allocate")
			.headers(headers_20)
			.resources(http("request_176")
			.get(uri2 + "/r/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=42&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2Fca8dbdee-2e6d-4808-949b-48ac69601a08%2Fallocate&ul=en-gb&de=UTF-8&dt=Dispatch&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=1779729828&gjid=773662721&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&_r=1&z=1010045533")
			.headers(headers_1),
            http("request_177")
			.get("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document")
			.headers(headers_20),
            http("request_178")
			.get("/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/document/5a844774-4ef8-4cac-90cd-317cc6dc8333/preview")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(2)
		.exec(http("request_179")
			.post("/api/case/3ab762fe-0340-4d10-a11c-07b5aee35ca1/stage/ca8dbdee-2e6d-4808-949b-48ac69601a08/allocate")
			.headers(headers_179)
			.body(RawFileBody("qaLoginAndFullWorkFlow_0179_request.txt"))
			.resources(http("request_180")
			.get(uri2 + "/collect?v=1&_v=j73&a=2081185987&t=pageview&_s=43&dl=https%3A%2F%2Fqa.cs-notprod.homeoffice.gov.uk%2Fcase%2F3ab762fe-0340-4d10-a11c-07b5aee35ca1%2Fstage%2F55e5150a-94df-4758-93cd-1ed55c3ff4da&dp=%2F&ul=en-gb&de=UTF-8&dt=Dashboard&sd=24-bit&sr=1920x1080&vp=1039x888&je=0&_u=CACAAAAB~&jid=&gjid=&cid=838258612.1548761863&uid=d403b609-0dce-4aaa-8c85-850b6c12fe8c&tid=UNSET&_gid=510478268.1556009177&z=1788241449")
			.headers(headers_1),
            http("request_181")
			.get("/api/form")
			.headers(headers_20)))
		.pause(14)
		.exec(http("request_182")
			.get("/oauth/logout?redirect=/")
			.headers(headers_0)
			.resources(http("request_183")
			.get(uri1 + "/realms/hocs-notprod/protocol/openid-connect/logout?redirect_uri=%2F")
			.headers(headers_0),
            http("request_184")
			.get(uri1 + "/resources/4.5.0.final/welcome/keycloak/css/welcome.css")
			.headers(headers_1),
            http("request_185")
			.get(uri1 + "/welcome-content/keycloak-project.png")
			.headers(headers_1),
            http("request_186")
			.get(uri1 + "/resources/4.5.0.final/welcome/keycloak/node_modules/patternfly/dist/css/patternfly-additions.css")
			.headers(headers_1),
            http("request_187")
			.get(uri1 + "/resources/4.5.0.final/welcome/keycloak/logo.png")
			.headers(headers_1),
            http("request_188")
			.get(uri1 + "/welcome-content/user.png")
			.headers(headers_1),
            http("request_189")
			.get(uri1 + "/welcome-content/admin-console.png")
			.headers(headers_1),
            http("request_190")
			.get(uri1 + "/welcome-content/mail.png")
			.headers(headers_1),
            http("request_191")
			.get(uri1 + "/welcome-content/bug.png")
			.headers(headers_1),
            http("request_192")
			.get(uri1 + "/welcome-content/jboss_community.png")
			.headers(headers_1),
            http("request_193")
			.get(uri1 + "/resources/4.5.0.final/welcome/keycloak/node_modules/patternfly/dist/css/patternfly.css")
			.headers(headers_1),
            http("request_194")
			.get(uri1 + "/resources/4.5.0.final/welcome/keycloak/bg.png")
			.headers(headers_1),
            http("request_195")
			.get(uri1 + "/resources/4.5.0.final/welcome/keycloak/node_modules/patternfly/dist/fonts/OpenSans-Light-webfont.woff2")
			.headers(headers_195),
            http("request_196")
			.get(uri1 + "/resources/4.5.0.final/welcome/keycloak/node_modules/patternfly/dist/fonts/OpenSans-Bold-webfont.woff2")
			.headers(headers_195),
            http("request_197")
			.get(uri1 + "/resources/4.5.0.final/welcome/keycloak/node_modules/patternfly/dist/fonts/OpenSans-Semibold-webfont.woff2")
			.headers(headers_195),
            http("request_198")
			.get(uri1 + "/resources/4.5.0.final/welcome/keycloak/node_modules/patternfly/dist/fonts/fontawesome-webfont.woff2?v=4.7.0")
			.headers(headers_195)))

	setUp(scn.inject(atOnceUsers(50))).protocols(httpProtocol)
}