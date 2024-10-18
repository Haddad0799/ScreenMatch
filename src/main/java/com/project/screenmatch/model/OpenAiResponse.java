package com.project.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class OpenAiResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;



    @Getter
    public static class Choice {
        private Message message;
        private String finish_reason;
        private int index;

    }

    @Getter
    public static class Message {
        private String role;
        private String content;

    }
    @Getter
    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;
        @JsonProperty("completion_tokens")
        private int completionTokens;
        @JsonProperty("total_tokens")
        private int totalTokens;

    }
}

