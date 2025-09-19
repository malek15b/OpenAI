package com.example.chatgptbasedcookingingredients;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final OpenAiService openAiService;

    public IngredientController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping
    public List<OpenAiChoice> categorizeIngredient(@RequestBody String ingredient) {
        return this.openAiService.categorizeIngredient(ingredient);
    }

}
