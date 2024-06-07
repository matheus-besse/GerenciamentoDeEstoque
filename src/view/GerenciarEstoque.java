package view;

import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciarEstoque {
    private List<Produto> produtos;
    private Scanner scanner;

    public GerenciarEstoque() {
        produtos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        GerenciarEstoque gerenciarEstoque = new GerenciarEstoque();
        gerenciarEstoque.menu();
    }

    private void menu() {
        int opcao;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Alterar dados do produto");
            System.out.println("3. Excluir produto");
            System.out.println("4. Entrada no estoque");
            System.out.println("5. Saída no estoque");
            System.out.println("6. Saldo atual no estoque");
            System.out.println("7. Listar produtos");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    alterarProduto();
                    break;
                case 3:
                    excluirProduto();
                    break;
                case 4:
                    entradaEstoque();
                    break;
                case 5:
                    saidaEstoque();
                    break;
                case 6:
                    saldoAtual();
                    break;
                case 7:
                    listarProdutos();
                    break;
                case 8:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 8);
    }

    private void cadastrarProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha

        Produto produto = new Produto(nome, quantidade, preco);
        produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private void alterarProduto() {
        System.out.print("Nome do produto a alterar: ");
        String nome = scanner.nextLine();
        Produto produto = buscarProduto(nome);

        if (produto != null) {
            System.out.print("Novo nome: ");
            produto.setNome(scanner.nextLine());
            System.out.print("Nova quantidade: ");
            produto.setQuantidade(scanner.nextInt());
            System.out.print("Novo preço: ");
            produto.setPreco(scanner.nextDouble());
            scanner.nextLine(); // Consumir a nova linha
            System.out.println("Produto alterado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void excluirProduto() {
        System.out.print("Nome do produto a excluir: ");
        String nome = scanner.nextLine();
        Produto produto = buscarProduto(nome);

        if (produto != null) {
            produtos.remove(produto);
            System.out.println("Produto excluído com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void entradaEstoque() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        Produto produto = buscarProduto(nome);

        if (produto != null) {
            System.out.print("Quantidade a adicionar: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            produto.setQuantidade(produto.getQuantidade() + quantidade);
            System.out.println("Quantidade adicionada com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void saidaEstoque() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        Produto produto = buscarProduto(nome);

        if (produto != null) {
            System.out.print("Quantidade a remover: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            if (quantidade <= produto.getQuantidade()) {
                produto.setQuantidade(produto.getQuantidade() - quantidade);
                System.out.println("Quantidade removida com sucesso!");
            } else {
                System.out.println("Quantidade insuficiente em estoque.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void saldoAtual() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        Produto produto = buscarProduto(nome);

        if (produto != null) {
            System.out.println("Saldo atual: " + produto.getQuantidade());
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    private Produto buscarProduto(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null;
    }
}