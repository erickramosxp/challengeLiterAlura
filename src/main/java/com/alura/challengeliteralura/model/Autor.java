package com.alura.challengeliteralura.model;

import java.util.ArrayList;
import java.util.List;

public class Autor {
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    private List<Livro> livros = new ArrayList<>();

    public Autor(String nome, Integer anoNascimento, Integer anoFalecimento) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        livros.forEach(l -> l.getAutor().add(this));
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Autor -- nome: " + nome +
                "\nanoNascimento: " + anoNascimento +
                ", anoFalecimento: " + anoFalecimento +
                ", livros: " + livros;
    }
}
