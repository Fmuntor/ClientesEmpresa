package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente {
    private final String ER_CORREO = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private final String ER_DNI = "^(\\d{8})([-]?)([A-Za-z])$";
    private final String ER_TELEFONO = "^\\d{9}$";
    public final String FORMATO_FECHA = "^\\d{2}/\\d{2}/\\d{4}$";
    public final String FORMATO_NOMBRE = "^[a-zA-Z\\s]*$";
    private String nombre;
    private String DNI;
    private String correo;
    private String telefono;
    private LocalDate fechaNacimiento;

    public Cliente(String nombre, String DNI, String correo, String telefono, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDNI(DNI);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }


    public Cliente(Cliente cliente) {
        if(cliente == null) {
            throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
        }
        setNombre(cliente.getNombre());
        setDNI(cliente.getDni());
        setCorreo(cliente.getCorreo());
        setTelefono(cliente.getTelefono());
        setFechaNacimiento(cliente.getFechaNacimiento());
    }

    public Cliente() {
    }

    private String formateaNombre(String nombre) {
        
        // Eliminar caracteres en blanco al inicio y al final del nombre
        try{
            nombre = nombre.trim();
            }catch (IllegalArgumentException e){
                System.out.println("No.");
            }
    
        // Separar el nombre en palabras
        String[] palabras = nombre.split("\\s+");
    
        // Formatear cada palabra
        for (int i = 0; i < palabras.length; i++) {
            // Poner la primera letra de la palabra en mayúsculas
            palabras[i] = palabras[i].substring(0, 1).toUpperCase() + palabras[i].substring(1).toLowerCase();
        }
    
        // Unir las palabras formateadas en un solo nombre
        nombre = String.join(" ", palabras);
        nombre = nombre.trim();
    
        return nombre;
    }

    public boolean comprobarLetraDNI(String DNI){
        // Definir las posibles letras posibles
        String mapaLetraDni = "TRWAGMYFPDXBNJZSQVHLCKE";

        // Comprobar si la cadena de entrada coincide con la expresión regular
        if (!DNI.matches(ER_DNI)) {
            return false;
        }

         // Extraer las partes número y letra del DNI asignarlas a dos variables
         String numero = DNI.substring(0, 8);
         String letra = DNI.substring(8, 9);
        
        // Calcular la letra correcta para el número de DNI
        int numeroDni = Integer.parseInt(numero);
        char letraEsperada = mapaLetraDni.charAt(numeroDni % 23);

        // Compara la letra calculada con la proporcionada en la cadena de entrada
        return letra.equalsIgnoreCase(String.valueOf(letraEsperada));
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return DNI;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    private String getIniciales(){
        // Crear cadena vacia
        String iniciales="";
        //Recorrer cada carácter de la cadena
        for(int i=0;i<nombre.length();i++){
            char letra=nombre.charAt(i);
            //Si es mayuscula, la añade a la cadena. Como lo hemos normalizado previamente, las mayúsculas son las iniciales del nombre.
            if(Character.isUpperCase(letra)){
                iniciales += letra;
            }
        }
        return iniciales;
    }

    public void setNombre(String nombre) {
        if (nombre == null){
            throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
        }
        if(nombre.trim().isEmpty()){
            throw new IllegalArgumentException("ERROR: El nombre de un cliente no puede estar vacío.");
        }
        this.nombre = formateaNombre(nombre);
    }

    public void setDNI(String DNI) {
        if (DNI == null){
            throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
        }
        if(DNI.trim().isEmpty() || DNI.length() != 9){
            throw new IllegalArgumentException("ERROR: El dni del cliente no tiene un formato válido.");
        }
        if(!comprobarLetraDNI(DNI)){
            throw new IllegalArgumentException("ERROR: La letra del dni del cliente no es correcta.");
        }
        this.DNI = DNI;
    }

    public void setCorreo(String correo) {
        // Define la expresión regular para comprobar el formato del correo.
        // El primer grupo (^[a-zA-Z0-9._%+-]+) coincide con una o más letras, dígitos, puntos, guiones bajos, porcentajes, signos más o signos menos.
        // El segundo grupo (@) coincide con el símbolo "arroba".
        // El tercer grupo ([a-zA-Z0-9.-]+) coincide con una o más letras, dígitos, puntos o guiones.
        // El cuarto grupo (\\.) coincide con un punto.
        // El quinto grupo ([a-zA-Z]{2,6}) coincide con dos a seis letras.
        // El carácter $ al final de la expresión regular indica que la cadena debe terminar aquí.

        // Comprueba si el correo no coincide con la expresión regular,, en ese caso lanza una excepcion
        if (correo == null){
            throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
        }
        if(correo.trim().isEmpty() || !correo.matches(ER_CORREO)){
            throw new IllegalArgumentException("ERROR: El correo del cliente no tiene un formato válido.");
        }
    


        this.correo = correo;
   }


    public void setTelefono(String telefono) {
        if (telefono == null){
            throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
        }
        if(telefono.trim().isEmpty() || !telefono.matches(ER_TELEFONO)){
            throw new IllegalArgumentException("ERROR: El teléfono del cliente no tiene un formato válido.");
        }

        this.telefono = telefono;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null){
            throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public boolean equals(Object obj) {
        // Si el objeto es null, no es igual
        if (obj == null) {
            return false;
        }
        // Si el objeto es de otra clase, no es igual
        if (getClass() != obj.getClass()) {
            return false;
        }
        // Si el objeto es de la clase Cliente, se convierte a Cliente
        Cliente otro = (Cliente) obj;
        // Si el DNI es el mismo, son iguales
        return DNI.equals(otro.DNI);
    }

    @Override
    public int hashCode() {
        // Obtiene el hash del DNI
        int hash = DNI.hashCode();
        // Devuelve el hash
        return hash;
    }

    @Override
    public String toString() {
        return "nombre="+ nombre +" ("+getIniciales()+")"+ 
            ", DNI=" + DNI + 
            ", correo=" + correo + 
            ", teléfono=" + telefono +
            ", fecha nacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
    }
}

