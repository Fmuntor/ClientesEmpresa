package org.iesalandalus.programacion.clientesempresa.vista;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

    private Consola(){
    }

    public static void mostrarMenu(){

        System.out.println("\n********** - MENU - **********\n");
        System.out.println("1: Insertar.");
        System.out.println("2: Buscar.");
        System.out.println("3: Borrar.");
        System.out.println("4: Mostrar todos los clientes.");
        System.out.println("5: Mostrar los clientes según su fecha de nacimiento.");
        System.out.println("6: Salir.\n");
        System.out.println("------------------------------\n");
    }

    public static Opcion elegirOpcion(){
        System.out.print("ELIGE UNA OPCION: ");

        int opcion=Entrada.entero();
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
                return Opcion.NO_VALIDO;
        }
    }

    public static Cliente leerCliente(){
        Cliente cliente = new Cliente();

        String nombre = null;
        while(nombre == null){
            System.out.print("Introduce el nombre del cliente:");
            nombre = Entrada.cadena();

            try{
                cliente.setNombre(nombre);
            }catch(Exception e){
                System.out.print(e.getMessage());
                nombre = null;
            }
        }

        String DNI = null;
        while(DNI == null){
            System.out.print("Introduce el DNI del cliente:");
            DNI = Entrada.cadena();

            try{
                cliente.setDNI(DNI);
            }catch(Exception e){
                System.out.print(e.getMessage());
                DNI = null;
            }
        }

        String correo = null;
        while(correo == null){
            System.out.print("Introduce el correo del cliente:");
            correo = Entrada.cadena();

            try{
                cliente.setCorreo(correo);
            }catch(Exception e){
                System.out.print(e.getMessage());
                correo = null;
            }
        }

        String telefono = null;
        while(telefono == null){
            System.out.print("Introduce el télefono del cliente:");
            telefono = Entrada.cadena();

            try{
                cliente.setTelefono(telefono);
            }catch(Exception e){
                System.out.print(e.getMessage());
                telefono = null;
            }
        }

        cliente.setFechaNacimiento(Consola.leerFechaNacimiento());

        return cliente;
    } 

    public static Cliente leerClienteDNI(Clientes clientes){;
        String DNI = null;
        while(DNI == null){            
            System.out.print("Introduce el DNI del cliente:");
            DNI = Entrada.cadena();
            if(DNI.trim().isEmpty()){
                System.out.println("No se ha introducido un DNI.");
                DNI = null;
            }else{
                Cliente clienteDNI = new Cliente();       
                if(clienteDNI.comprobarLetraDNI(DNI)==false){
                    System.out.println("El DNI introducido es incorrecto.");
                    DNI = null;
                } 
            }
        }

        int encontrado=-1;       
        try{
            for(int i=0;i<clientes.getTamano();i++){
                // Si el DNI introducido es igual que el DNI de algun cliente existente, aumenta las vueltas y guarda el cliente.
                if(DNI.equals(clientes.coleccionClientes[i].getDni())){
                    encontrado=i;
                    break;
                }
            }

            if (encontrado == -1){
                // Si no lo ha encontrado, lanzo excepcion
                throw new Exception("Cliente no encontrado");
            } else {
                // Si lo ha encontrado y no hay clientes con el mismo DNI, devuelve el cliente
                return clientes.coleccionClientes[encontrado];
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static LocalDate leerFechaNacimiento() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        // Desactiva la funcion que suma días si la fecha es incorrecta, como el 31/02/2022
        formatoFecha.setLenient(false);

        LocalDate fechaNacimiento = null;
        String fechaString = null;
        while (fechaNacimiento == null){
            System.out.print("Introduce fecha de nacimiento (dd/MM/yyyy):");
            fechaString = Entrada.cadena();
            if(fechaString.trim().isEmpty()){
                System.out.println("No se ha introducido una fecha.");
            }else{
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    fechaNacimiento = LocalDate.parse(fechaString, formatter);
                } catch (DateTimeParseException  e) {
                    System.out.println("Fecha introducida no es válida, vuelve a intentarlo.\n");
                }
            }
        }
        return fechaNacimiento;
    }

    public static void mostrarMensaje(Exception e){
        System.err.println(e.getMessage());
    }

    public static void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
}
