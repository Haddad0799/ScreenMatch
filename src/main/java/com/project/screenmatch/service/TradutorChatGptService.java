package com.project.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class TradutorChatGptService {

    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService(System.getenv("OPENAI_APIKEY"));

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-4")
                .prompt("Please translate the following text into Portuguese: \n\n" + texto)
                .maxTokens(1000)
                .temperature(0.5) //
                .build();

        var resposta = service.createCompletion(requisicao);
        if (resposta.getChoices() != null && !resposta.getChoices().isEmpty()) {
            return resposta.getChoices().get(0).getText().trim();
        } else {
            return "Falha na tradução!";
        }
    }
}
