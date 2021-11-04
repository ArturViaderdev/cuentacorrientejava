package cuentacorriente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arturv
 */
public class Cuenta {
    //Atributos
    private final int numero;
    private double saldo;
    private String titular;
    
    //Métodos
    public Cuenta(int numero, double saldo, String titular)
    {
        this.numero = numero;
        this.saldo = saldo;
        this.titular = titular;
    }
    
    public int getNumeroCuenta()
    {
        return numero;
    }
    
    public double getSaldo()
    {
        return saldo;
    }
    
    public String getTitular()
    {
        return titular;
    }
    
    public void setSaldo(double saldo)
    {
        this.saldo=saldo;
    }
    
    public void setTitular(String titular)
    {
        this.titular = titular;
    }   
}
