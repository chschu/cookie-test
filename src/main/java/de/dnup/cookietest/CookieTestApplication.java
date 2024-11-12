package de.dnup.cookietest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@SpringBootApplication
public class CookieTestApplication {

	private static final String MAX_AGE_TEST_COOKIE = "max-age-test-cookie";

	private static final String REDIRECT_TO_START = "redirect:/";

	public static void main(final String[] args) {
		SpringApplication.run(CookieTestApplication.class, args);
	}

	@GetMapping("/")
	public String getStart() {
		return "index";
	}

	@PostMapping("/set-cookie-standard-attributes")
	public String postSetCookieStandardAttributes(final HttpServletRequest request, final HttpServletResponse response) {
		final Cookie cookie = new Cookie("cookie-with-standard-attributes", "cookie-value-1");
		cookie.setHttpOnly(true);
		cookie.setSecure(request.isSecure());
		cookie.setMaxAge(86400);
		cookie.setAttribute("Same-Site", "None");
		response.addCookie(cookie);
		return REDIRECT_TO_START;
	}

	@PostMapping("/set-cookie-custom-attributes")
	public String postSetCookieCustomAttributes(final HttpServletRequest request, final HttpServletResponse response) {
		final Cookie cookie = new Cookie("cookie-with-custom-attributes", "cookie-value-2");
		cookie.setHttpOnly(true);
		cookie.setSecure(request.isSecure());
		cookie.setMaxAge(86400);
		cookie.setAttribute("Same-Site", "None");
		cookie.setAttribute("Hello", "World");
		response.addCookie(cookie);
		return REDIRECT_TO_START;
	}

	@PostMapping("/set-cookie-no-max-age")
	public String postSetCookieNoMaxAge(final HttpServletResponse response) {
		final Cookie cookie = new Cookie(MAX_AGE_TEST_COOKIE, "cookie-value-3");
		response.addCookie(cookie);
		return REDIRECT_TO_START;
	}

	@PostMapping("/set-cookie-negative-max-age")
	public String postSetCookieNegativeMaxAge(final HttpServletResponse response) {
		final Cookie cookie = new Cookie(MAX_AGE_TEST_COOKIE, "cookie-value-4");
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
		return REDIRECT_TO_START;
	}

	@PostMapping("/set-cookie-zero-max-age")
	public String postSetCookieZeroMaxAge(final HttpServletResponse response) {
		final Cookie cookie = new Cookie(MAX_AGE_TEST_COOKIE, "cookie-value-5");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return REDIRECT_TO_START;
	}

	@PostMapping("/set-cookie-positive-max-age")
	public String postSetCookiePositiveMaxAge(final HttpServletResponse response) {
		final Cookie cookie = new Cookie(MAX_AGE_TEST_COOKIE, "cookie-value-6");
		cookie.setMaxAge(3600);
		response.addCookie(cookie);
		return REDIRECT_TO_START;
	}
}
