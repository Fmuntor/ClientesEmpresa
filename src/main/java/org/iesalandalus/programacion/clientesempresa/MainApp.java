package org.iesalandalus.programacion.clientesempresa;

import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;

public class MainApp {

	public static void main(String[] args) {
		// Inicializo el objeto de clientes, que a su vez dentro tengo la lista de clientes
		// El array tendrá un espacio para un cliente (capacidad = 1)
		Clientes clientes = new Clientes(1);

		// Muestro el menu al usuario
		Consola.mostrarMenu();

		// Leo la opcion que el usuario ha elegido
		Opcion opcionElegida = Consola.elegirOpcion();

		switch(opcionElegida){
			case BUSCAR_CLIENTE:
				Cliente clienteEncontrado = Consola.leerClienteDNI(clientes);

				// Muestro la info del cliente por consola
			break;

			case BORRAR_CLIENTE:
				// Le digo al usuario que defina el cliente a borrar (uso la vista)
				Cliente clienteElegido = Consola.leerClienteDNI(clientes);

				// Borro el cliente (uso el modelo)
				clientes.borrar(clienteElegido);
			break;
		}

		

		//Cliente encontrado = Consola.LeerClienteDNI(clientes);
	}

}
/* 
/////////////////////////////////////////////////////////////
/*DUDAS*/

1. coleccionClientes se instancia en Clientes y se recoge informacion de Cliente. En Cliente no se ha declarado el significado de coleccionClientes, se deberia incluir en algun lado? Por que?

2. buscar y buscarindice diferencias.

3. copiaprofundaClientes? 

4.mostrarclientesFecha() y mostrarclientes() usa clientes?



/*









 * 
 * 
 * 
*/

*/