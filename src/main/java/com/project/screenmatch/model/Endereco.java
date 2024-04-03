package com.project.screenmatch.model;




public class Endereco {

        public static String montaEnderecoTitulo(String pesquisaDoUsuario) {
            String ENDERECO_BASE = "https://www.omdbapi.com/?t=";
            String API_KEY_ENDPOINT = "&apikey=";
            //String TEMPORADA_ENDPOINT = "&season=";

            return ENDERECO_BASE + pesquisaDoUsuario.replace(" ", "+").toLowerCase()
                    + API_KEY_ENDPOINT + System.getenv("OMDB_APIKEY");
        }

        public static String montaEnderecoTemporada(String pesquisaDoUsuario, Integer temporada) {
            String ENDERECO_BASE = "https://www.omdbapi.com/?t=";
            String API_KEY_ENDPOINT = "&apikey=";
            String TEMPORADA_ENDPOINT = "&season=";

            return ENDERECO_BASE + pesquisaDoUsuario.replace(" ", "+").toLowerCase()
                    + TEMPORADA_ENDPOINT + temporada +  API_KEY_ENDPOINT + System.getenv("OMDB_APIKEY");
        }
    }


