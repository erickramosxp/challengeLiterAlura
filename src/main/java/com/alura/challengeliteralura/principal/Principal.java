package com.alura.challengeliteralura.principal;

import com.alura.challengeliteralura.model.*;
import com.alura.challengeliteralura.repository.AutorRepository;
import com.alura.challengeliteralura.repository.LivrosRepository;
import com.alura.challengeliteralura.service.ConsumoAPI;
import com.alura.challengeliteralura.service.ConverteDados;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

public class Principal {

    private String endereco = "https://gutendex.com/books?search=";
    private Scanner leitura = new Scanner(System.in);
    private LivrosRepository resitorio;
    private AutorRepository autorRepositorio;
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConverteDados convesor = new ConverteDados();

    private List<Livro> livros = new ArrayList<>();

    private List<Autor> autores = new ArrayList<>();

    public Principal(LivrosRepository repositorio, AutorRepository autorRepositorio) {
        this.resitorio = repositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibirMenu() {
        Integer opcao = 1;
        String menu = """
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em determinado ano
                5 - Listar livros em determinado idioma
                0 - Sair
                """;
        while (opcao != 0) {
            System.out.println(menu);
            System.out.println("Selecione uma opção: ");
            try {
                opcao = leitura.nextInt();
                leitura.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Não é uma opção valida.");
                leitura.nextLine();
                opcao = 1;
                continue ;
            }

            switch (opcao) {
                case 1:
                    buscarLivroNaWeb();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    autoresVivosNoAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção invalida!");
                    break;
            }
        }
    }

    private void buscarLivroNaWeb(){
        System.out.println("Digite o nome do livro: ");
        var livroDigitado = leitura.nextLine();
        String resultado = consumoAPI.obterDados(endereco + livroDigitado.toLowerCase().replace(" ", "+"));
        Dados resul = convesor.obterDados(resultado, Dados.class);
        if (resul.livro().isEmpty()) {
            System.out.println("Livro não encontrado.");
            return ;
        }
        List<DadosLivro> livros = resul.livro();
        String nomeAutor = livros.get(0).autor().get(0).nome();
        Optional<Autor> encontrarAutor = resitorio.encontarAutor(nomeAutor);
        Autor autor;
        Livro novoLivro;
        try {
            if (encontrarAutor.isPresent()) {
                autor = encontrarAutor.get();
                novoLivro = new Livro(livros.get(0));
                autor.setLivros(novoLivro);
            } else {
                novoLivro = new Livro(livros.get(0));
                autor = new Autor(livros.get(0).autor().get(0));
                autor.setLivros(novoLivro);
            }
            System.out.println(novoLivro);
            autorRepositorio.save(autor);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Livro já cadastrado.");
        }
    }

    private void listarLivros() {
        livros = resitorio.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutores() {
        autores = autorRepositorio.findAll();
        autores.forEach(System.out::println);
    }

    private void autoresVivosNoAno() {
        System.out.println("Insira o ano que você deseja buscar: ");
        Integer ano = leitura.nextInt();
        leitura.nextLine();
        autores = autorRepositorio.procurarAutoresNoAno(ano);
        autores.forEach(System.out::println);
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Selecione um idioma: ");
        var idioma = leitura.nextLine();
        Idiomas idiomaEscolhido = Idiomas.idiomaAbreviado(idioma);
        livros = resitorio.procurarLivroPorIdioma(idiomaEscolhido.getIdiomaAbreviado());
        if (livros.isEmpty()) {
            System.out.println("Nada Encontrado");
        } else {
            livros.forEach(System.out::println);
        }
    }
}
