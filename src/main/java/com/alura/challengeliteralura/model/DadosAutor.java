package com.alura.challengeliteralura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAutor(@JsonAlias("results") String resultado) {
}