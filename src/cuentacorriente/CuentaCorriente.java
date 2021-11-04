/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuentacorriente;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author arturv
 */
public class CuentaCorriente {
    private static ArrayList<Cuenta> cuentas;
    /**
     * @param args the command line arguments
     */
    
    //Consulta el saldo de un usuario
    private static void consultasaldo(int usuario)
    {
        double saldo = cuentas.get(usuario).getSaldo();
        System.out.println("Tiene " + saldo + " euros en la cuenta.");
    }
    
    //Ingresa o saca dinero de la cuenta de un usuario
    private static void ingresaosaca(int usuario,double dinero, boolean pon)
    {
        if(pon)
        {
            cuentas.get(usuario).setSaldo(cuentas.get(usuario).getSaldo()+dinero);
        }
        else
        {
            cuentas.get(usuario).setSaldo(cuentas.get(usuario).getSaldo()-dinero);
        }
    }
    
    //Pide al usuario para ingresar o sacar dinero de la cuenta de un usuario
    private static void ingresarosacardinero(int usuario, boolean pon)
    {
        if(pon)
        {
            System.out.println("Introduce cuanto dinero quieres ingresar en la cuenta.");
        }
        else
        {
            System.out.println("Cuanto dinero quieres retirar de la cuenta?");
        }
        
        Scanner entrada=new Scanner(System.in);
        double dinero;
        
        try
        {
            dinero = entrada.nextDouble();
            if(pon)
            {
                ingresaosaca(usuario,dinero,true);
                System.out.println("Dinero añadido.");
            }
            else
            {
                ingresaosaca(usuario,dinero,false);
                System.out.println("Dinero retirado.");
            }
            
            System.out.println("El total de dinero en la cuenta ahora es de " + cuentas.get(usuario).getSaldo());
        }
        catch(Exception ex)
        {
            System.out.println("Error");
        }
    }
    
    //Realiza transferencia desde un usuario al usuario 1. Pide los valores.
    private static void realizartransferencia(int usuario)
    {
        double dinero;
        int destinatario;
        System.out.println("La cuenta de origen es la que tiene iniciada sesión:" + cuentas.get(usuario).getTitular());
        System.out.println("Ahora debes introducir una cuenta de destino, y no puede ser la misma que de origen.");
        destinatario = iniciarsesion();
        if(destinatario!=-1 && destinatario!=usuario)
        {
            System.out.println("Se enviará el dinero a la cuenta de: " + cuentas.get(destinatario).getTitular());
            Scanner entrada=new Scanner(System.in);
            System.out.println("Introduce cuanto dinero vas a transferir.");
            try
            {
                dinero=entrada.nextDouble();                
                ingresaosaca(usuario,dinero,false);
                ingresaosaca(destinatario,dinero,true);
                System.out.println("Operación realizada.");
                System.out.println("Saldo del usuario " + cuentas.get(usuario).getSaldo() + " " + cuentas.get(usuario).getTitular());
                System.out.println("Saldo del destinatario " + cuentas.get(destinatario).getSaldo() + " " + cuentas.get(destinatario).getTitular());
            }
            catch(Exception ex)
            {
                System.out.println("Error");
            }
        }
        else
        {
            System.out.println("Error de cuenta.");
        }
    }
    
    private static int buscacuenta(int numerocuenta)
    {
        boolean sal=false, encontrado = false;
        int cont=0;
        while(!sal)
        {
            if(cont<cuentas.size())
            {
                if(cuentas.get(cont).getNumeroCuenta()==numerocuenta)
                {
                    encontrado = true;
                    sal = true;
                }
                else
                {
                    cont++;
                }
            }
            else
            {
                sal=true;
            }
        }
        if(encontrado)
        {
            return cont;
        }
        else
        {
            return -1;
        }
    }
    
    private static int iniciarsesion()
    {
        System.out.println("Introduce número de cuenta.");
        Scanner entrada=new Scanner(System.in);
        int numcuenta, poscuenta;
        try
        {
            numcuenta = entrada.nextInt();
            poscuenta = buscacuenta(numcuenta);
            return poscuenta;
        }
        catch(Exception ex)
        {
            return -1;
        }
    }
    
    
    public static void main(String[] args) {
        int opcion=0;
        int usuario;
        
        cuentas = new ArrayList<>();
        Cuenta cuentacreada = new Cuenta(1234,1000,"Artur");
        cuentas.add(cuentacreada);
        cuentacreada = new Cuenta(5678,2000,"Pedro");
        cuentas.add(cuentacreada);
        //Se establece por defecto el usuario 0 o primer usuario
        System.out.println("Primero se debe iniciar sesión con una cuenta.");
        usuario =iniciarsesion();
        if(usuario!=-1)
        {
            Scanner entrada=new Scanner(System.in);
            try
            {
                do
                {
                    System.out.println("Iniciada sesión usuario " + cuentas.get(usuario).getTitular());
                    System.out.println("Introduce una opción.");
                    System.out.println("1-Consultar saldo.");
                    System.out.println("2-Ingresar dinero.");
                    System.out.println("3-Sacar dinero.");
                    System.out.println("4-Realizar transferencia.");
                    System.out.println("5-Inciar sesión con otro usuario.");
                    System.out.println("0-Salir");
                    opcion=entrada.nextInt();
                    switch(opcion)
                    {
                        case 1:
                            consultasaldo(usuario);
                            break;
                        case 2:
                            ingresarosacardinero(usuario,true);
                            break;
                        case 3:
                            ingresarosacardinero(usuario,false);
                            break;
                        case 4:
                            realizartransferencia(usuario);
                            break;
                        case 5:
                            usuario=iniciarsesion();
                            if(usuario==-1)
                            {
                                System.out.println("Cuenta no válida.");
                                opcion=0; //Si el inicio de sesión no es válido se sale del programa.
                            }
                        break;
                        default:
                    }
                    
                }while(opcion!=0);
            }
            catch(Exception ex)
            {
                System.out.println("Error");
            }
        }
        else
        {
            System.out.println("Cuenta incorrecta.");
        }
    }   
}
