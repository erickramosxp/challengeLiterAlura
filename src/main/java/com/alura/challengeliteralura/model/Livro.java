package com.alura.challengeliteralura.model;

import java.util.List;

public class Livro {
    private String titulo;
    private List<Autor> autor;
    private List<String> idiomas;
    private Integer downloads;

    public Livro(DadosLivro livro) {
        this.titulo = livro.titulo();
        this.idiomas = livro.idiomas();
        this.downloads = livro.downloads();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "Livro --" +
                "titulo: " + titulo +
                ", autor: " + autor +
                ", idiomas: " + idiomas +
                ", downloads: " + downloads;
    }
}
