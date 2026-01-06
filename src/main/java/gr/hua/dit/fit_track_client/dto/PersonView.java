package gr.hua.dit.fit_track_client.dto;

public record PersonView(
        long id,
        String firstName,
        String lastName,
        String mobilePhoneNumber,
        String emailAddress,
        PersonType type
) {}