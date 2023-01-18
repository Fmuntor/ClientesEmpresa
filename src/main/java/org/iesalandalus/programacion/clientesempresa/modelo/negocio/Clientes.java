package org.iesalandalus.programacion.clientesempresa.modelo.negocio;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {
    private int capacidad;
    private int tamano;
    public Cliente[] coleccionClientes;

    public Clientes (int capacidad){
        if(capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
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

    public Cliente[] get() {        
        Cliente[] coleccionClientesClon = new Cliente[capacidad];
        for(int i = 0; i < tamano; i++){
            coleccionClientesClon[i] = new Cliente(coleccionClientes[i]);
        }
        return coleccionClientesClon;
    }

    /* AL NO SER USADO EL METODO, QUEDA COMENTADO
    private boolean tamanoSuperado(int indice) {
        // Si el indice introducido es inferior o igual a la capacidad actual, retorna false
        if(capacidad >= indice){
            return false;
        }

        // Crear un array de capacidad igual a la definida en el parametro        
        Cliente cliente[] = new Cliente[indice];

        // Rellenar el nuevo array con los datos del antiguo
        for(int i=0;i<=capacidad-1;i++){
            cliente[i] = coleccionClientes[i];
        }

        // Asignar los clientes que no cabían en el array anterior al nuevo
        coleccionClientes = cliente;

        // Actualizar la capacidad ya que el nuevo array la ha actualizado
        capacidad = indice;
        return true;
    }
    */

    private boolean capacidadSuperada(int indice) {
        // Si la capacidad es menor o igual que le indice introducido, retorna true
        return capacidad <= indice;
    }

    private void desplazarUnaPosicionHaciaLaIzquierda(int indice){
        // Movemos a la izquierda los clientes de la derecha del eliminado
        for (int i=indice;i<tamano;i++){

            // Si he llegado al ultimo elemento, seteo a null y termino
            if(i == capacidad-1){
                coleccionClientes[i]=null;
            }else{
                // Mover a la izquierda el cliente a la derecha del eliminado
                coleccionClientes[i]=coleccionClientes[i + 1];
                // Si el elemento que he movido es nulo, los siguientes elementos seran nulos, por lo que termino el bucle
                if(coleccionClientes[i] == null) {
                    break;
                }
            }
        }
        tamano--;
    }
    public void insertar(Cliente cliente) throws OperationNotSupportedException {

        if(capacidadSuperada(tamano)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más clientes.");
        }

        if(cliente==null){
            throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
        }
        // Si el cliente está repetido por DNI
        Cliente clienteExiste = buscar(cliente);

        if(clienteExiste != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese dni.");
        }

        /* PARA AMPLIAR LA CAPACIDAD Y ACTUALIZAR EL TAMAÑO DINAMICAMENTE
        // Si la capacidad es insuficiente, se crea un nuevo array con 1 posición mas disponible en la memoria, y se actualiza el array anterior con NULL
        if(capacidadSuperada(tamano)){
            tamanoSuperado(capacidad+1);
        }
        */

        // Añadimos el ultimo cliente al nuevo array y aumentamos el tamaño
        coleccionClientes[tamano] = cliente;
        tamano++;
    }

    public void borrar(Cliente cliente) throws Exception{
        if(cliente==null){
            throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
        }
        int eliminado=buscarIndice(cliente);
        if(eliminado == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún cliente como el indicado.");
        
        }else{
            // Ponemos a null el cliente
            coleccionClientes[eliminado]=null;

            // Desplazar a la izquierda los clientes de la derecha del eliminado y actualizar el tamaño
            desplazarUnaPosicionHaciaLaIzquierda(eliminado);
        }
    }

    public Cliente buscar(Cliente cliente) {
        // Buscar en el array el cliente introducido
        for(int i=0;i<tamano;i++){
            if(coleccionClientes[i].getDni().equalsIgnoreCase(cliente.getDni())){
                return new Cliente(coleccionClientes[i]);
            }
        }
        return null;
    }

    private int buscarIndice(Cliente cliente){
         // Buscar en el array el cliente introducido
         int indice=-1;
         for(int i=0;i<tamano;i++){
            if(coleccionClientes[i].getDni().equalsIgnoreCase(cliente.getDni())){
                indice = i;
                break;
            }
        }
        return indice;
    }
}
