package gr.hua.dit.fit_track_client.dto;

public record LoginRequest(
        String email,
        String password
) {}
