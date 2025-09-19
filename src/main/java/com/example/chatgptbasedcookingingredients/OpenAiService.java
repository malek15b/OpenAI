package com.example.chatgptbasedcookingingredients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OpenAiService {

    private static final String BASE_URL = "https://api.openai.com/v1/chat";
    private static final String COMPLETIONS_ENDPOINT = "/completions";
    private final RestClient restClient;
    private static final String MODEL_GPT5 = "gpt-5";

    public OpenAiService(RestClient.Builder restClientBuilder,
                         @Value("API_KEY") String apikey) {
        this.restClient = restClientBuilder
                .baseUrl(BASE_URL)
                .defaultHeader("Authorization", "Bearer " + apikey)
                .build();
    }

    public List<OpenAiChoice> categorizeIngredient(String ingredient) {
        String message = "Ist " + ingredient + " vegan, vegetarisch oder normal? Antwort JSON-Format.";
        System.out.println(message);
        OpenAiRequest request = this.getBody(message);
        System.out.println(request);
        return this.restClient.post()
                .uri(COMPLETIONS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(OpenAiResponse.class).choices();
    }

    private OpenAiRequest getBody(String message) {
        OpenAiMessage openAiMessage = new OpenAiMessage("user", message);
        List<OpenAiMessage> messages = List.of(openAiMessage);
        return new OpenAiRequest(MODEL_GPT5, messages);
    }
}
