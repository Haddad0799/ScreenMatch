package com.project.screenmatch.model;




public class Endereco {

        public static String montaEnderecoTitulo(String pesquisaDoUsuario) {
            String ENDERECO_BASE = "https://www.omdbapi.com/?t=";
            //String TEMPORADA_ENDPOINT = "&season=";

            return ENDERECO_BASE + pesquisaDoUsuario.replace(" ", "+").toLowerCase() + System.getenv("OMDB_APIKEY");
        }
    }


