package org.iesalandalus.programacion.clientesempresa.modelo.negocio;
import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {
    private int capacidad;
    private int tamano;
    private Cliente[] coleccionClientes;

    public Clientes (int capacidad){
        this.capacidad = capacidad;
        this.coleccionClientes = new Cliente[capacidad];
        this.tamano = 0;
    }

    public int getCapacidad() {
        return capacidad;
    }
    public int getTamano() {
        return tamano;
    }

    public Cliente[] get() {        //////////////////////
        return coleccionClientes;
    }

    private boolean tamanoSuperado(int indice) {
        // Si el indice introducido es inferior o igual a la capacidad actual, retorna false
        if(capacidad <= indice){
            return false;
        }

        // Crear un array de capacidad igual a la definida en el parametro        
        Cliente cliente[] = new Cliente[indice];

        // Rellenar el nuevo array con los datos del antiguo
        for(int i=0;i<=capacidad-1;i++){
            cliente[i] = coleccionClientes[i];
        }

        // Asignar los clientes que no cabían en el array anterior al nuevo
        coleccionClientes = cliente;    ///////////////////////////////////////////

        // Actualizar la capacidad ya que el nuevo array la ha actualizado
        capacidad = indice;
        return true;
    }

    private boolean capacidadSuperada(int indice) {
        // Si la capacidad es menor o igual que le indice introducido, retorna true
        return capacidad <= indice;
    }

    private void desplazarUnaPosicionHaciaLaIzquierda(int indice){
        // Movemos a la izquierda los clientes de la derecha del eliminado
        int vueltas=0;
        for (int i=indice;i<coleccionClientes.length;i++){
            // Si el cliente de la derecha no es null, movemos un cliente a la izquierda y ponemos el cliente de la derecha null
            if(coleccionClientes[i+1]!=null){
                // Mover a la izquierda el cliente a la derecha del eliminado
                coleccionClientes[i+1]=coleccionClientes[i];
                // Poner a null el cliente de la derecha del eliminado
                coleccionClientes[i+1]=null;
                // Actualizar la cantidad de veces que se ha repetido el bucle for
                vueltas++;
            // Si el cliente de la derecha es null, salimos del bucle for
            }else{
                break;
            }
            // Para actualizar el tamaño, aplicaremos la siguiente formula = [tamano = (eliminado + vueltas) -1
            // Si no se ha movido ningún cliente, vueltas=0 asi que reducimos una posición
            tamano=(indice+vueltas)-1;
        }
    }
    public void insertar(Cliente cliente) {
        // Si la capacidad es insuficiente, se crea un nuevo array con 1 posición mas disponible en la memoria, y se actualiza el array anterior con NULL
        if(capacidadSuperada(tamano)){
            tamanoSuperado(capacidad+1);
        }
        // Añadimos el ultimo cliente al nuevo array y aumentamos el tamaño
        coleccionClientes[tamano] = cliente;
        tamano++;
    }

    public void borrar(Cliente cliente) throws Exception{
        int eliminado=0;
        // Buscar el cliente introducido por parametro
        try{
            eliminado=buscarIndice(cliente);
        }
        // Si no lo encuentra, lanza una excepcion
        catch(Exception e){
            System.out.println("El cliente introducido no se ha encontrado.");
        }
        // Ponemos a null el cliente
        coleccionClientes[eliminado]=null;

        // Desplazar a la izquierda los clientes de la derecha del eliminado y actualizar el tamaño
        desplazarUnaPosicionHaciaLaIzquierda(eliminado);
    }

    
    public void buscar(Cliente cliente) {
        // Buscar en el array el cliente introducido
        for(int i=0;i<coleccionClientes.length;i++){
            if(coleccionClientes[i].equals(cliente)){
                coleccionClientes[i].toString();
                break;
            }
        }
    }

    private int buscarIndice(Cliente cliente){
         // Buscar en el array el cliente introducido
         int indice=0;
         for(int i=0;i<coleccionClientes.length;i++){
            if(coleccionClientes[i].equals(cliente)){
                i=indice;
            }
        }
        return indice;
    }
}
