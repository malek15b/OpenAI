package com.example.chatgptbasedcookingingredients;

import java.util.Collections;
import java.util.List;

/**
 * {
 *     "model": "gpt-5",
 *     "messages": [
 *       {
 *         "role": "developer",
 *         "content": "You are a helpful assistant."
 *       },
 *       {
 *         "role": "user",
 *         "content": "Hello!"
 *       }
 *     ]
 *   }
 */
public record OpenAiRequest(String model, List<OpenAiMessage> messages) {
    OpenAiRequest(String message) {
        this("gpt-4o-mini", Collections.singletonList(new OpenAiMessage("user", message)));
    }
}
