package br.com.aula10.service;

/**
 * Servico de operacoes aritmeticas para validacao de testes unitarios e cobertura JaCoCo.
 */
public class Calculadora {

    public int somar(int a, int b) {
        return a + b;
    }

    public int subtrair(int a, int b) {
        return a - b;
    }

    public int multiplicar(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divisor nao pode ser zero");
        }
        return a / b;
    }
}
