package com.alura.challengeliteralura.model;

public enum Idiomas {

    PORTUGUES("pt","Português", "portugues"),
    INGLES("en", "Inglês", "ingles"),
    FRANCES("fr", "Frânces", "frances");

    private String idiomaAbreviado;
    private String idioma;
    private String idiomaSAcento;

    Idiomas(String idiomaAbreviado, String idioma, String idiomaSAcento) {
        this.idiomaAbreviado = idiomaAbreviado;
        this.idioma = idioma;
        this.idiomaSAcento = idiomaSAcento;
    }

    public static Idiomas idiomaAbreviado(String idioma) {
        for (Idiomas idiomas : Idiomas.values()) {
            if (idiomas.idioma.equalsIgnoreCase(idioma) ||
                idiomas.idiomaSAcento.equalsIgnoreCase(idioma) ||
                    idiomas.idiomaAbreviado.equalsIgnoreCase(idioma)
            ) {
                return (idiomas);
            }
        }
        throw new IllegalArgumentException("Idioma não encontrado");
    }

    public String getIdiomaAbreviado() {
        return (idiomaAbreviado);
    }
}
