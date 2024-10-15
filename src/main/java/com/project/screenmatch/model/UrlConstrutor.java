package com.project.screenmatch.model;


import org.springframework.stereotype.Component;

@Component
public class UrlConstrutor {

    String ENDERECO_BASE = "https://www.omdbapi.com/?t=";
    String API_KEY_ENDPOINT = "&apikey=";
    String TEMPORADA_ENDPOINT = "&season=";

        public String construirUrl(String tituloPesquisado) {

            return ENDERECO_BASE + tituloPesquisado.replace(" ", "+").toLowerCase()
                    + API_KEY_ENDPOINT + System.getenv("OMDB_APIKEY");
        }

        public String construirUrl(String tituloPesquisado, Integer temporada) {

            return ENDERECO_BASE + tituloPesquisado.replace(" ", "+").toLowerCase()
                    + TEMPORADA_ENDPOINT + temporada +  API_KEY_ENDPOINT + System.getenv("OMDB_APIKEY");
        }
    }


