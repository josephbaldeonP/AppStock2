package com.example.sem04_lab2.recycler;

public class Titular {

        private String titulo;
        private String subtitulo;
        public Titular(String tit, String sub){
            titulo = tit;
            subtitulo = sub;
        }
        public String getTitulo(){
            return titulo;
        }
        public String getSubtitulo(){
            return subtitulo;
        }

}
