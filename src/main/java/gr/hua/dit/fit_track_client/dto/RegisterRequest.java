package gr.hua.dit.fit_track_client.dto;

public record RegisterRequest(
        String firstName,
        String lastName,
        String mobilePhoneNumber,
        String emailAddress,
        String password,
        PersonType type
) {}