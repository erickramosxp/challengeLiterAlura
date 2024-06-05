package com.alura.challengeliteralura.principal;

import com.alura.challengeliteralura.model.Autor;
import com.alura.challengeliteralura.model.Dados;
import com.alura.challengeliteralura.model.DadosLivro;
import com.alura.challengeliteralura.model.Livro;
import com.alura.challengeliteralura.service.ConsumoAPI;
import com.alura.challengeliteralura.service.ConverteDados;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private String endereco = "https://gutendex.com/books?search=";
    private Scanner leitura = new Scanner(System.in);
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConverteDados convesor = new ConverteDados();

    public void exibirMenu() {
        Integer opcao = 1;
        String menu = """
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em determinado ano
                5 - Listar livros em determinado idioma
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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
        for (DadosLivro livro : livros) {
            Livro novoLivro = new Livro(livro);
            novoLivro.setAutor(livro.autor().stream()
                    .map(a -> new Autor(a.nome(),
                                a.anoNascimento()
                                ,a.anoNascimento()))
                    .collect(Collectors.toList()));
            System.out.println(novoLivro);
        }
    }
}
