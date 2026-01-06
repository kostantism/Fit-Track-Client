package gr.hua.dit.fit_track_client.dto;

public record RegisterResponse(
        boolean created,
        String reason,
        PersonView personView
) {}