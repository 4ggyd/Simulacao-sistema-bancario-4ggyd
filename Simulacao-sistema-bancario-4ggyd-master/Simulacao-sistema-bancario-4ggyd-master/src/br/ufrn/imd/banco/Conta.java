package br.ufrn.imd.banco;

public abstract class Conta {
    protected int numero;
    protected String cliente;
    protected double saldo;

    public Conta(int numero, String cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public abstract boolean sacar(double valor);
    public abstract boolean transferir(Conta destino, double valor);

    public int getNumero() { return numero; }
    public String getCliente() { return cliente; }
    public double getSaldo() { return saldo; }
}