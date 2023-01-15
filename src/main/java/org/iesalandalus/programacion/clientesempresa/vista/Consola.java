package org.iesalandalus.programacion.clientesempresa.vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;


import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;

public class Consola {
    static Scanner sc = new Scanner(System.in);

    private Consola(){
    }

    public static void mostrarMenu(){

        System.out.println("********** - MENU - **********");
        System.out.println("");
        System.out.println("1: Insertar.");
        System.out.println("2: Buscar.");
        System.out.println("3: Borrar.");
        System.out.println("4: Mostrar todos los clientes.");
        System.out.println("5: Mostrar los clientes según su fecha de nacimiento.");
        System.out.println("6: Salir.");
        System.out.println("");
        System.out.println("------------------------------");
    }

    public static Opcion elegirOpcion(){
        System.out.println("ELIGE UNA OPCION:");

        int opcion=sc.nextInt();
        switch (opcion) {
            case 1:
                return Opcion.INSERTAR_CLIENTE;
            case 2:
                return Opcion.BUSCAR_CLIENTE;
            case 3:
                return Opcion.BORRAR_CLIENTE;
            case 4:
                return Opcion.MOSTRAR_CLIENTE;
            case 5:
                return Opcion.MOSTRAR_CLIENTES_FECHA;
            case 6:
                return Opcion.SALIR;
            default:
            System.out.println("Opción inválida. Intente de nuevo.");
            return elegirOpcion();
        }
    }

    public static Cliente leerCliente(){
        
        System.out.print("Introduce el nombre del cliente:");
        String nombre = sc.nextLine();
        System.out.print("Introduce el DNI del cliente:");
        String DNI = sc.nextLine();
        System.out.print("Introduce el correo del cliente:");
        String correo = sc.nextLine();
        System.out.print("Introduce el telefono del cliente:");
        String telefono = sc.nextLine();
        System.out.print("Introduce la fecha de nacimiento del cliente:");
        String fechaintroducida = sc.nextLine();
        // Pasar el dato introducido a formato Date
        Date fecha=null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fecha = formatter.parse(fechaintroducida);
        } catch (ParseException e) {
            System.out.println("Fecha incorrecta");
        }
        // Crear el constructor con los parametros introducidos
        Cliente clienteLeido= new Cliente(nombre, DNI, correo, telefono, fecha);

        // Crear objeto clientes y array coleccion para buscar en los clientes creados
        Clientes clientes = new Clientes(1);
        // Buscar el cliente introducido
        int indice=0;
        boolean encontrado=false;
        for(int i=0;i<clientes.coleccionClientes.length;i++){
            if(clienteLeido.equals(clientes.coleccionClientes[i])){
                indice=i;
                encontrado=true;
            }
            if (encontrado==false) {
                throw new IllegalArgumentException("El cliente no se encuentra en la colección");
            }
        }return clientes.coleccionClientes[indice];
    } 

    public static Cliente leerClienteDNI(){
        System.out.print("Introduce el DNI del cliente:");
        String DNI = sc.nextLine();
        int vueltas=0, encontrado=0;
        Cliente clienteDNI = new Cliente(null, DNI, null, null, null);
        // Comprobar que el DNI introducido es correcto
        while(clienteDNI.comprobarLetraDNI(DNI)==false){
            System.out.println("El DNI introducido es incorrecto.");
            leerClienteDNI();
        } 
        try{
            Clientes clientes = new Clientes(1); 
            Cliente[] coleccion = clientes.get();
            for(int i=0;i<coleccion.length;i++){
                // Si el DNI introducido es igual que el DNI de algun cliente existente, aumenta las vueltas y guarda el cliente.
                if(clienteDNI.getDNI().equals(clientes.coleccionClientes[i].getDNI())){
                encontrado=i;
                vueltas++;
                }
            }
            // Si lo ha encontrado y no hay clientes con el mismo DNI, devuelve el cliente
            if (vueltas == 1){
                return clientes.coleccionClientes[encontrado];
            }
            if (vueltas == 0) {
                throw new Exception("Cliente no encontrado");
            } else if (vueltas > 1) {
                throw new Exception("Hay " + vueltas + " clientes con el mismo DNI.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static LocalDate leerFechaNacimiento() {
        Scanner sc = new Scanner(System.in);

        // Definicion del patron de fecha
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = null;
        while (fecha == null) {
            System.out.print("Introduce la fecha de nacimiento (dd/MM/yyyy): ");
            String fechaIntroducida = sc.nextLine();
            try {
                fecha = LocalDate.parse(fechaIntroducida, formato);
            } catch (DateTimeParseException e) {
                System.out.println("La fecha introducida no es válida. Por favor, introduce una fecha en el formato adecuado (dd/MM/yyyy).");
            }
        }
        return fecha;
    }
}
