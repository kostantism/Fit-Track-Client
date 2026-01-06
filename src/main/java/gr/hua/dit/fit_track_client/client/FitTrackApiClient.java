package gr.hua.dit.fit_track_client.client;

import gr.hua.dit.fit_track_client.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FitTrackApiClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    private String jwt;

    public FitTrackApiClient(
            RestTemplate restTemplate,
            @Value("${fittrack.api.base-url}") String baseUrl
    ) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    // 🔐 LOGIN
    public void login(String username, String password) {

        LoginRequest request = new LoginRequest(username, password);

        ResponseEntity<LoginResponse> response =
                restTemplate.postForEntity(
                        baseUrl + "/api/v1/auth/login",
                        request,
                        LoginResponse.class
                );

        if (!response.getStatusCode().is2xxSuccessful()
                || response.getBody() == null) {
            throw new RuntimeException("Login failed");
        }

        this.jwt = response.getBody().accessToken();
        System.out.println("Logged in, JWT acquired");
    }

    // 🆕 REGISTER
    public void register(RegisterRequest request) {

        ResponseEntity<RegisterResponse> response =
                restTemplate.postForEntity(
                        baseUrl + "/api/v1/auth/register",
                        request,
                        RegisterResponse.class
                );

        if (!response.getStatusCode().is2xxSuccessful()
                || response.getBody() == null
                || !response.getBody().created()) {
            throw new RuntimeException(
                    "Registration failed: "
                            + (response.getBody() != null
                            ? response.getBody().reason()
                            : "unknown error")
            );
        }

        System.out.println("User registered successfully: "
                + response.getBody().personView().emailAddress());
    }

    // 🔒 AUTH HEADER
    private HttpHeaders authHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwt);
        return headers;
    }

//    // 📌 Secured GET
//    public void getMyAppointments() {
//
//        HttpEntity<Void> entity =
//                new HttpEntity<>(authHeaders());
//
//        ResponseEntity<String> response =
//                restTemplate.exchange(
//                        baseUrl + "/api/customer/appointments",
//                        HttpMethod.GET,
//                        entity,
//                        String.class
//                );
//
//        System.out.println("Appointments:");
//        System.out.println(response.getBody());
//    }
}