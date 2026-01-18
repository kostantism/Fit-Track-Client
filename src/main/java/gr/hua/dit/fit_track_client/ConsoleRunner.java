package gr.hua.dit.fit_track_client;

import gr.hua.dit.fit_track_client.client.FitTrackApiClient;
import gr.hua.dit.fit_track_client.dto.PersonType;
import gr.hua.dit.fit_track_client.dto.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final FitTrackApiClient api;

    public ConsoleRunner(FitTrackApiClient api) {
        this.api = api;
    }

    @Override
    public void run(String... args) {

        api.register(new RegisterRequest(
                "Kostas",
                "Test",
                "+306900000000",
                "kostas@test.com",
                "1234",
                PersonType.CUSTOMER
        ));

        api.login("kostas@test.com", "1234");

    }
}
