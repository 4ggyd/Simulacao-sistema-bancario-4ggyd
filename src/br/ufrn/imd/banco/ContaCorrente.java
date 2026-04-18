package br.ufrn.imd.banco;

public class ContaCorrente extends Conta implements ITributavel {

    public ContaCorrente(int numero, String cliente) {
        super(numero, cliente);
    }

    @Override
    public boolean sacar(double valor) {
        double valorComTaxa = valor * 1.05;
        if (this.saldo >= valorComTaxa) {
            this.saldo -= valorComTaxa;
            return true;
        }
        return false;
    }

    @Override
    public boolean transferir(Conta destino, double valor) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    @Override
    public double calculaTributos() {
        return this.saldo * 0.01;
    }
}