package org.iesalandalus.programacion.clientesempresa;

import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;

import java.text.ParseException;
import java.time.LocalDate;
import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;

public class MainApp {
	
	final static int NUM_MAX_CLIENTES=2;
	static Clientes clientes;

	public static void main(String[] args) throws ParseException {
		// Inicializo el objeto de clientes, que a su vez dentro tengo la lista de clientes
		// El array tendrá un espacio para un cliente (capacidad = 1)
		clientes = new Clientes(NUM_MAX_CLIENTES);
		Opcion opcionElegida = null;
		do{
			Consola.mostrarMenu();
			opcionElegida = Consola.elegirOpcion();
			ejecutarOpcion(opcionElegida);
		}while(opcionElegida != Opcion.SALIR);

		// Si la opcion es "SALIR", muestra un mensaje de despedida y termina el programa
		Consola.mostrarMensaje("¡Hasta pronto!");
	}

	private static void ejecutarOpcion(Opcion opcion) throws ParseException{
		switch(opcion){
			case BUSCAR_CLIENTE:
				// Llamar al metodo leerClienteDNI de la clase Consola
				Cliente clienteBuscado = Consola.leerClienteDNI(clientes);

				// Muestro la info del cliente por consola
				if(clienteBuscado != null){
					Consola.mostrarMensaje(clienteBuscado.toString());
				}
			break;

			case INSERTAR_CLIENTE:
				// Crear un cliente
				Cliente clienteInsertado=Consola.leerCliente();

				try{
					clientes.insertar(clienteInsertado);
				} catch (Exception e) {
					Consola.mostrarMensaje(e);
				}
				break;
			case MOSTRAR_CLIENTE:
				if(clientes.getTamano() == 0){
					Consola.mostrarMensaje("No hay clientes para mostrar");
				} else {
					for(int i=0;i<clientes.getTamano();i++){
						Consola.mostrarMensaje(clientes.get()[i].toString());
					}
				}
			break;
			case BORRAR_CLIENTE:
				// Le digo al usuario que defina el cliente a borrar (uso la vista)
				Cliente clienteBorrado = Consola.leerClienteDNI(clientes);

				// Borro el cliente (uso el modelo)
				if(clienteBorrado != null) {
					try{
						clientes.borrar(clienteBorrado);
					}catch(Exception e){
						Consola.mostrarMensaje(e);
					}
					
				}
			break;
			case MOSTRAR_CLIENTES_FECHA:
				if(clientes.getTamano() == 0){
					Consola.mostrarMensaje("No hay clientes para mostrar");
				} else {
					LocalDate fechaNacimiento = Consola.leerFechaNacimiento();
					int mostrados = 0;
					for(int i=0;i<clientes.getTamano();i++){
						if(clientes.get()[i].getFechaNacimiento().equals(fechaNacimiento)) {
							Consola.mostrarMensaje(clientes.get()[i].toString());
							mostrados++;
						}
					}
					if(mostrados == 0){
						Consola.mostrarMensaje("No hay clientes con esa fecha de nacimiento");
					}
				}
				break;
			case SALIR:
				break;
			default:
				break;
		}
	}
}
