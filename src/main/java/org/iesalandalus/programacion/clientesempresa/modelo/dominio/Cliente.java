package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    private final String ER_CORREO = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private final String ER_DNI = "^(\\d{8})([-]?)([A-Za-z])$";
    private final String ER_TELEFONO = "^\\d{9}$";
    public final String FORMATO_FECHA = "^\\d{2}/\\d{2}/\\d{4}$";
    private String nombre;
    private String DNI;
    private String correo;
    private String telefono;
    private Date fechaNacimiento;

    private String formateaNombre(String nombre) {
        // Eliminar caracteres en blanco al inicio y al final del nombre
        nombre = nombre.trim();
    
        // Separar el nombre en palabras
        String[] palabras = nombre.split("\\s+");
    
        // Formatear cada palabra
        for (int i = 0; i < palabras.length; i++) {
            // Poner la primera letra de la palabra en mayúsculas
            palabras[i] = palabras[i].substring(0, 1).toUpperCase() + palabras[i].substring(1).toLowerCase();
        }
    
        // Unir las palabras formateadas en un solo nombre
        nombre = String.join(" ", palabras);
    
        return nombre;
    }

    private boolean comprobarLetraDNI(String DNI){
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

    public String getDNI() {
        return DNI;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public Date getFechaNacimiento() {
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
        // Comprobacion de que el nombre no sea nulo ni este vacio
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni estar vacío");
        }
        // Realizar el formateo y asignarlo a la variable de la clase
        this.nombre = formateaNombre(nombre);
    }

    public void setDNI(String DNI) {
        if(comprobarLetraDNI(DNI)==false){
            throw new IllegalArgumentException("El DNI es incorrecto. Por favor, vuelva a introducirlo.");
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
        if (!correo.matches(ER_CORREO)) {
           throw new IllegalArgumentException("El formato del correo es incorrecto");
        }

        this.correo = correo;
   }

    public void setTelefono(String telefono) {
        // Comprueba si el teléfono no coincide con la expresión regular, lanzando una nueva excepcion.
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("El formato del teléfono es incorrecto");
        }

        this.telefono = telefono;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        // Formatea la fecha proporcionada en una cadena de texto
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = sdf.format(fechaNacimiento);

        // Comprueba si la fecha formateada coincide con la expresión regular
        Pattern p = Pattern.compile(FORMATO_FECHA);
        Matcher m = p.matcher(fechaFormateada);

        if (m.matches()) {
            // Si el formato es correcto, establece la fecha de nacimiento
            this.fechaNacimiento = fechaNacimiento;
        } else {
            // Si el formato es incorrecto, lanza una excepción
            throw new IllegalArgumentException("El formato de la fecha de nacimiento es incorrecto. Debe seguir el patrón dd/MM/yyyy");
        }
    }

    public Cliente(String nombre, String DNI, String correo, String telefono, Date fechaNacimiento) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente() {
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
        return "Nombre= "+"("+getIniciales()+")" + nombre + ", DNI=" + DNI + ", correo=" + correo + ", telefono=" + telefono
                + ", fechaNacimiento=" + fechaNacimiento + "]";
    }

    
    

    
    
    
}

