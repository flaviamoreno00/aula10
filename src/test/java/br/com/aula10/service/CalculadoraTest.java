package br.com.aula10.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraTest {

    private final Calculadora calculadora = new Calculadora();

    @Test
    void deveExecutarOperacoesBasicasCorretamente() {
        assertEquals(15, calculadora.somar(10, 5));
        assertEquals(5, calculadora.subtrair(10, 5));
        assertEquals(50, calculadora.multiplicar(10, 5));
        assertEquals(5, calculadora.divide(10, 2));
        assertEquals(-3, calculadora.divide(-9, 3));
    }

    @Test
    void deveLancarArithmeticExceptionQuandoDivisorForZero() {
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculadora.divide(10, 0)
        );
        assertEquals("Divisor nao pode ser zero", exception.getMessage());
    }
}
