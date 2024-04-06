package com.miguelneto.demo2;

import com.miguelneto.demo2.entidade.Camisa;
import com.miguelneto.demo2.repositorio.CamisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
public class Demo2Application implements CommandLineRunner {
    @Autowired
    private CamisaRepository camisaRepository;

    public void run(String... args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        List<Camisa> carrinho = new ArrayList<>();

        boolean continuar = true;

        while (continuar) {
            System.out.println("╔══════════════════════════════════╗");
            System.out.println("║         MENU DE OPÇÕES           ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║ 1. Adicionar Camisa ao Carrinho  ║");
            System.out.println("║ 2. Listar Carrinho               ║");
            System.out.println("║ 3. Finalizar Compra              ║");
            System.out.println("║ 4. Alterar Valor de Camisa       ║");
            System.out.println("║ 5. Retirar Item do Carrinho      ║");
            System.out.println("║ 6. Sair                          ║");
            System.out.println("╚══════════════════════════════════╝");;
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║   Digite a opção desejada:     ║");
            System.out.println("╚════════════════════════════════╝");

            int opcao = Integer.parseInt(teclado.nextLine());

            if (opcao == 1) {
                while (true) {
                    System.out.println("Digite o nome da camisa: ");
                    String nome = teclado.nextLine();
                    System.out.println("Digite o tamanho da camisa: ");
                    String tamanho = teclado.nextLine();
                    System.out.println("Digite a cor da camisa: ");
                    String cor = teclado.nextLine();
                    System.out.println("Digite o preço da camisa: ");
                    double preco = Double.parseDouble(teclado.nextLine());

                    Camisa novaCamisa = new Camisa();
                    novaCamisa.setNome(nome);
                    novaCamisa.setTamanho(tamanho);
                    novaCamisa.setCor(cor);
                    novaCamisa.setPreco(preco);

                    carrinho.add(novaCamisa);
                    System.out.println("Camisa adicionada ao carrinho.");

                    System.out.println("Deseja adicionar mais uma camisa? (s/n)");
                    String continuarAdicionando = teclado.nextLine();
                    if (!continuarAdicionando.equalsIgnoreCase("s")) {
                        break;
                    }
                }
            } else if (opcao == 2) {
                if (carrinho.isEmpty()) {
                    System.out.println("Carrinho vazio.");
                } else {
                    System.out.println("Carrinho:");
                    int contador = 1;
                    for (Camisa camisa : carrinho) {
                        System.out.println(contador + ". " + camisa.getNome() + ", " + camisa.getTamanho() + ", " + camisa.getCor() + ", R$" + camisa.getPreco());
                        contador++;
                    }
                }
            } else if (opcao == 3) {
                double total = 0;
                for (Camisa camisa : carrinho) {
                    total += camisa.getPreco();
                }
                System.out.println("Valor total da compra: R$" + total);
                System.out.println("Compra finalizada!");
                carrinho.clear();
            } else if (opcao == 4) {
                if (carrinho.isEmpty()) {
                    System.out.println("Carrinho vazio.");
                } else {
                    System.out.println("Digite o número do item a ser alterado:");
                    int itemIndex = Integer.parseInt(teclado.nextLine()) - 1;
                    if (itemIndex >= 0 && itemIndex < carrinho.size()) {
                        Camisa camisa = carrinho.get(itemIndex);
                        System.out.println("Digite o novo preço para " + camisa.getNome() + ":");
                        double novoPreco = Double.parseDouble(teclado.nextLine());
                        camisa.setPreco(novoPreco);
                        System.out.println("Preço alterado com sucesso!");
                    } else {
                        System.out.println("Número de item inválido.");
                    }
                }
            } else if (opcao == 5) {
                if (carrinho.isEmpty()) {
                    System.out.println("Carrinho vazio.");
                } else {
                    System.out.println("Digite o número do item a ser retirado:");
                    int itemIndex = Integer.parseInt(teclado.nextLine()) - 1;
                    if (itemIndex >= 0 && itemIndex < carrinho.size()) {
                        carrinho.remove(itemIndex);
                        System.out.println("Item removido do carrinho.");
                    } else {
                        System.out.println("Número de item inválido.");
                    }
                }
            } else if (opcao == 6) {
                System.out.println("╔═════════════════════════════════════╗");
                System.out.println("║ Obrigado por utilizar a aplicação ! ║");
                System.out.println("╚═════════════════════════════════════╝");
                continuar = false;
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }
}

