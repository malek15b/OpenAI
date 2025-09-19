package com.example.chatgptbasedcookingingredients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OpenAiService {

    private static final String BASE_URL = "https://api.openai.com/v1/chat/completions";
    private final RestClient restClient;
    private static final String MODEL_GPT5 = "gpt-5";

    public OpenAiService(RestClient.Builder restClientBuilder,
                         @Value("${API_KEY}") String apikey) {
        this.restClient = restClientBuilder
                .baseUrl(BASE_URL)
                .defaultHeader("Authorization", "Bearer " + apikey)
                .build();
    }

    public List<OpenAiChoice> categorizeIngredient(String ingredient) {
        String message = "Ist " + ingredient + " vegan, vegetarisch oder normal? Antwort JSON-Format.";
        OpenAiRequest request = this.getBody(message);
        return this.restClient.post()
                .body(request)
                .retrieve()
                .body(OpenAiResponse.class).choices();
    }

    private OpenAiRequest getBody(String message) {
        return new OpenAiRequest(message);
    }
}
