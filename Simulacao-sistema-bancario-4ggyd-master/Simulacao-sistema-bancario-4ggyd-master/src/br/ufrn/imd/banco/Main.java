//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
package br.ufrn.imd.banco;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Conta> contas = new ArrayList<>();
        int proximoNumero = 101;
        int opcao = 0;

        while (opcao != 7) {
            System.out.println("\n--- MENU BANCO ---");
            System.out.println("1. Criar Conta\n2. Depósito\n3. Saque\n4. Transferência\n5. Listar\n6. Tributos\n7. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Tipo (1-Corrente, 2-Poupança): ");
                    int tipo = scanner.nextInt();
                    if (tipo == 1) {
                        contas.add(new ContaCorrente(proximoNumero++, nome));
                    } else {
                        contas.add(new ContaPoupanca(proximoNumero++, nome));
                    }
                    System.out.println("Conta criada!");
                    break;
                case 2:
                    System.out.print("Número: ");
                    int nDep = scanner.nextInt();
                    System.out.print("Valor: ");
                    double vDep = scanner.nextDouble();
                    for (Conta c : contas) {
                        if (c.getNumero() == nDep) c.depositar(vDep);
                    }
                    break;
                case 3:
                    System.out.print("Número: ");
                    int nSaq = scanner.nextInt();
                    System.out.print("Valor: ");
                    double vSaq = scanner.nextDouble();
                    for (Conta c : contas) {
                        if (c.getNumero() == nSaq) {
                            if (c.sacar(vSaq)) System.out.println("Sucesso!");
                            else System.out.println("Saldo insuficiente!");
                        }
                    }
                    break;
                case 4:
                    System.out.print("Origem: ");
                    int orig = scanner.nextInt();
                    System.out.print("Destino: ");
                    int dest = scanner.nextInt();
                    System.out.print("Valor: ");
                    double vTrans = scanner.nextDouble();
                    Conta cO = null, cD = null;
                    for (Conta c : contas) {
                        if (c.getNumero() == orig) cO = c;
                        if (c.getNumero() == dest) cD = c;
                    }
                    if (cO != null && cD != null) cO.transferir(cD, vTrans);
                    break;
                case 5:
                    for (Conta c : contas) {
                        String t = (c instanceof ContaCorrente) ? "Corrente" : "Poupança";
                        System.out.println("Nº: " + c.getNumero() + " | Cliente: " + c.getCliente() + " | Saldo: R$ " + c.getSaldo() + " | Tipo: " + t);
                    }
                    break;
                case 6:
                    double totalTributos = 0.0;
                    for (Conta c : contas) {
                        if (c instanceof ITributavel) {
                            totalTributos += ((ITributavel) c).calculaTributos();
                        }
                    }
                    System.out.println("Total de tributos a recolher: R$ " + totalTributos);
                    break;
            }
        }
        scanner.close();
    }
}