package org.iesalandalus.programacion.clientesempresa.modelo.negocio;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ClientesTest {

	private static final String ERROR_CAPACIDAD_NO_CORRECTA = "ERROR: La capacidad debe ser mayor que cero.";
	private static final String ERROR_INSERTAR_CLIENTE_NULO = "ERROR: No se puede insertar un cliente nulo.";
	private static final String ERROR_BORRAR_CLIENTE_NULO = "ERROR: No se puede borrar un cliente nulo.";
	private static final String ERROR_NO_MAS_CLIENTES = "ERROR: No se aceptan más clientes.";
	private static final String ERROR_CLIENTE_EXISTE = "ERROR: Ya existe un cliente con ese dni.";
	private static final String ERROR_CLIENTE_BORRAR_NO_EXISTE = "ERROR: No existe ningún cliente como el indicado.";
	//private static final String ERROR_DIA_NULO = "ERROR: No se pueden devolver las citas para un día nulo.";
	private static final String OPERACION_NO_PERMITIDA = "Debería haber saltado una excepción indicando que dicha operación no está permitida.";
	private static final String CLIENTE_NULO = "Debería haber saltado una excepción indicando que no se puede operar con una cliente nulo.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String EXCEPCION_NO_PROCEDE = "No debería haber saltado la excepción.";
	private static final String OPERACION_NO_REALIZADA = "La operación no la ha realizado correctamente.";
	private static final String CLIENTES_NO_CREADOS = "Debería haber creado los clientes correctamente.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";
	private static final String TAMANO_NO_ESPERADO = "El tamaño devuelto no es el esperado.";
	private static final String CLIENTE_NO_ESPERADO = "El cliente devuelto no es el que debería ser.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto.";
	
	private static final String NOMBRE_JRJR = "José Ramón Jiménez Reyes";
	private static final String DNI_JRJR = "11223344B";
	private static final String TELEFONO_JRJR = "950112233";
	private static final String CORREO_JRJR = "joseramon.jimenez@iesalandalus.org";
	private static final LocalDate FECHA_NACIMIENTO_JRJR=LocalDate.of(2002, 9, 15);
	private static final String NOMBRE_ARDR = "Andrés Rubio Del Río";
	private static final String DNI_ARDR = "22334455Y";
	private static final String TELEFONO_ARDR = "666223344";
	private static final String CORREO_ARDR = "andres.rubio@iesalandalus.org";
	private static final LocalDate FECHA_NACIMIENTO_ARDR=LocalDate.of(1992, 7, 3);
	private static final String NOMBRE_BE = "Bob Esponja";
	private static final String DNI_BE = "33445566R";
	private static final String TELEFONO_BE = "600334455";
	private static final String CORREO_BE = "bog.esponja@iesalandalus.org";
	private static final LocalDate FECHA_NACIMIENTO_BE=LocalDate.of(1996, 10, 30);
	
	
	private static Cliente cliente1;
	private static Cliente cliente2;
	private static Cliente cliente3;
	private static Cliente clienteRepetido1;
	
	
	@BeforeAll
	public static void asignarValoresAtributos() {
		cliente1 = new Cliente(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
		cliente2 = new Cliente(NOMBRE_ARDR, DNI_ARDR, CORREO_ARDR, TELEFONO_ARDR, FECHA_NACIMIENTO_ARDR);
		cliente3 = new Cliente(NOMBRE_BE, DNI_BE, CORREO_BE, TELEFONO_BE, FECHA_NACIMIENTO_BE);
		clienteRepetido1=new Cliente(NOMBRE_ARDR, DNI_ARDR, CORREO_ARDR, TELEFONO_ARDR, FECHA_NACIMIENTO_ARDR);
		
	}
	
	@Test
	public void constructorCapacidadValidaCreaClientesCorrectamente() {
		Clientes clientes = new Clientes(5);
		assertNotEquals(null, clientes, CLIENTES_NO_CREADOS);
		assertEquals(5, clientes.getCapacidad(), CLIENTES_NO_CREADOS);
		assertEquals(0, clientes.getTamano(), TAMANO_NO_ESPERADO);
	}
	
	@Test
	public void constructorCapacidadNoValidaLanzaExcepcion() {
		Clientes clientes = null;
		
		try {
			clientes = new Clientes(0);
			fail(OPERACION_NO_PERMITIDA);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_CAPACIDAD_NO_CORRECTA, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, clientes, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			clientes = new Clientes(-1);
			fail(OPERACION_NO_PERMITIDA);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_CAPACIDAD_NO_CORRECTA, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, clientes, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarClienteValidoInsertaClienteCorrectamente() {
		Clientes clientes = new Clientes(5);
		
		try {
			clientes.insertar(cliente1);
			
			Cliente[] copiaClientes = clientes.get();
			assertEquals(1, clientes.getTamano(), TAMANO_NO_ESPERADO);
			assertEquals(cliente1, clientes.buscar(cliente1), CLIENTE_NO_ESPERADO);
			assertNotSame(cliente1, copiaClientes[0], REFERENCIA_NO_ESPERADA);
			assertEquals(cliente1, copiaClientes[0], OPERACION_NO_REALIZADA);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarDosClientesValidosInsertaClientesCorrectamente() {
		Clientes clientes = new Clientes(5);
		
		try {
			clientes.insertar(cliente1);
			clientes.insertar(cliente2);
			
			Cliente[] copiaClientes = clientes.get();
			assertEquals(2, clientes.getTamano(), TAMANO_NO_ESPERADO);
			assertEquals(cliente1, clientes.buscar(cliente1), CLIENTE_NO_ESPERADO);
			assertNotSame(cliente1, copiaClientes[0], REFERENCIA_NO_ESPERADA);
			assertEquals(cliente1, copiaClientes[0], OPERACION_NO_REALIZADA);
			assertEquals(cliente2, clientes.buscar(cliente2), CLIENTE_NO_ESPERADO);
			assertNotSame(cliente2, copiaClientes[1], REFERENCIA_NO_ESPERADA);
			assertEquals(cliente2, copiaClientes[1], OPERACION_NO_REALIZADA);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarTresClientesValidosInsertaClientesCorrectamente() {
		Clientes clientes = new Clientes(5);
		
		try {
			clientes.insertar(cliente1);
			clientes.insertar(cliente2);
			clientes.insertar(cliente3);
			
			Cliente[] copiaClientes = clientes.get();
			
			assertEquals(3, clientes.getTamano(), TAMANO_NO_ESPERADO);
			assertEquals(cliente1, clientes.buscar(cliente1), CLIENTE_NO_ESPERADO);
			assertNotSame(cliente1, copiaClientes[0], REFERENCIA_NO_ESPERADA);
			assertEquals(cliente1, copiaClientes[0], OPERACION_NO_REALIZADA);
			assertEquals(cliente2, clientes.buscar(cliente2), CLIENTE_NO_ESPERADO);
			assertNotSame(cliente2, copiaClientes[1], REFERENCIA_NO_ESPERADA);
			assertEquals(cliente2, copiaClientes[1], OPERACION_NO_REALIZADA);
			assertEquals(cliente3, clientes.buscar(cliente3), CLIENTE_NO_ESPERADO);
			assertNotSame(cliente3, copiaClientes[2], REFERENCIA_NO_ESPERADA);
			assertEquals(cliente3, copiaClientes[2], OPERACION_NO_REALIZADA);
			
			
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarClienteNuloLanzaExcepcion() {
		Clientes clientes = new Clientes(5);
		
		try {
			clientes.insertar(null);
			fail(CLIENTE_NULO);
		} catch (NullPointerException e) {
			assertEquals(ERROR_INSERTAR_CLIENTE_NULO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(0, clientes.getTamano(), TAMANO_NO_ESPERADO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarClienteRepetidoLanzaExcepcion() {
		Clientes clientes = new Clientes(5);
		
		try {
			clientes.insertar(cliente1);
			clientes.insertar(cliente2);
			clientes.insertar(cliente3);
			clientes.insertar(clienteRepetido1);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertEquals(ERROR_CLIENTE_EXISTE, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(3, clientes.getTamano(), TAMANO_NO_ESPERADO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		clientes = new Clientes(5);
		try {
			clientes.insertar(cliente2);
			clientes.insertar(cliente1);
			clientes.insertar(cliente3);
			clientes.insertar(clienteRepetido1);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertEquals(ERROR_CLIENTE_EXISTE, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(3, clientes.getTamano(), TAMANO_NO_ESPERADO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		clientes = new Clientes(5);
		try {
			clientes.insertar(cliente2);
			clientes.insertar(cliente3);
			clientes.insertar(cliente1);
			clientes.insertar(clienteRepetido1);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertEquals(ERROR_CLIENTE_EXISTE, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(3, clientes.getTamano(), TAMANO_NO_ESPERADO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarClienteValidoConClientesLlenoLanzaExcepcion() {
		Clientes clientes = new Clientes(2);

		try {
			clientes.insertar(cliente1);
			clientes.insertar(cliente2);
			clientes.insertar(cliente3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertEquals(ERROR_NO_MAS_CLIENTES, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(2, clientes.getTamano(), TAMANO_NO_ESPERADO);
			assertEquals(cliente1, clientes.buscar(cliente1), CLIENTE_NO_ESPERADO);
			assertNotSame(cliente1, clientes.buscar(cliente1), REFERENCIA_NO_ESPERADA);
			assertEquals(cliente1, clientes.get()[0], OPERACION_NO_REALIZADA);
			assertEquals(cliente2, clientes.buscar(cliente2), CLIENTE_NO_ESPERADO);
			assertNotSame(cliente2, clientes.buscar(cliente2), REFERENCIA_NO_ESPERADA);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarClienteExistenteBorraClienteCorrectamente() throws OperationNotSupportedException {
		Clientes clientes = new Clientes(5);
		
		try {
			clientes.insertar(cliente1);
			clientes.borrar(cliente1);
			assertEquals(0, clientes.getTamano(), TAMANO_NO_ESPERADO);
			assertEquals(null, clientes.buscar(cliente1), CLIENTE_NO_ESPERADO);
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		
		clientes = new Clientes(5);
		try {
			clientes.insertar(cliente1);
			clientes.insertar(cliente2);
			clientes.borrar(cliente1);
			assertEquals(1, clientes.getTamano(), TAMANO_NO_ESPERADO);
			assertEquals(cliente2, clientes.buscar(cliente2), CLIENTE_NO_ESPERADO);
			assertEquals(null, clientes.buscar(cliente1), CLIENTE_NO_ESPERADO);
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		
		clientes = new Clientes(5);
		try {
			clientes.insertar(cliente1);
			clientes.insertar(cliente2);
			clientes.borrar(cliente2);
			assertEquals(1, clientes.getTamano(), TAMANO_NO_ESPERADO);
			assertEquals(cliente1, clientes.buscar(cliente1), CLIENTE_NO_ESPERADO);
			assertEquals(null, clientes.buscar(cliente2), CLIENTE_NO_ESPERADO);
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		
		clientes = new Clientes(5);
		try {
			clientes.insertar(cliente1);
			clientes.insertar(cliente2);
			clientes.insertar(cliente3);
			clientes.borrar(cliente1);
			assertEquals(2, clientes.getTamano(), TAMANO_NO_ESPERADO);
			assertEquals(null, clientes.buscar(cliente1), CLIENTE_NO_ESPERADO);
			assertEquals(cliente2, clientes.buscar(cliente2), CLIENTE_NO_ESPERADO);
			assertEquals(cliente3, clientes.buscar(cliente3), CLIENTE_NO_ESPERADO);
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		
		clientes = new Clientes(5);
		try {
			clientes.insertar(cliente1);
			clientes.insertar(cliente2);
			clientes.insertar(cliente3);
			clientes.borrar(cliente2);
			assertEquals(2, clientes.getTamano(), TAMANO_NO_ESPERADO);
			assertEquals(null, clientes.buscar(cliente2), CLIENTE_NO_ESPERADO);
			assertEquals(cliente1, clientes.buscar(cliente1), CLIENTE_NO_ESPERADO);
			assertEquals(cliente3, clientes.buscar(cliente3), CLIENTE_NO_ESPERADO);
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		
		clientes = new Clientes(5);
		try {
			clientes.insertar(cliente1);
			clientes.insertar(cliente2);
			clientes.insertar(cliente3);
			clientes.borrar(cliente3);
			assertEquals(2, clientes.getTamano(), TAMANO_NO_ESPERADO);
			assertEquals(null, clientes.buscar(cliente3), CLIENTE_NO_ESPERADO);
			assertEquals(cliente1, clientes.buscar(cliente1), CLIENTE_NO_ESPERADO);
			assertEquals(cliente2, clientes.buscar(cliente2), CLIENTE_NO_ESPERADO);
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void borrarClienteNoExistenteLanzaExcepcion() {
		Clientes clientes = new Clientes(5);
		
		try {
			clientes.insertar(cliente1);
			clientes.borrar(cliente2);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertEquals(ERROR_CLIENTE_BORRAR_NO_EXISTE, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(1, clientes.getTamano(),TAMANO_NO_ESPERADO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		
		clientes = new Clientes(5);
		try {
			clientes.insertar(cliente1);
			clientes.insertar(cliente2);
			clientes.borrar(cliente3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertEquals(ERROR_CLIENTE_BORRAR_NO_EXISTE, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(2, clientes.getTamano(),TAMANO_NO_ESPERADO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarClienteNuloLanzaExcepcion() {
		Clientes clientes = new Clientes(5);
		try {
			clientes.insertar(cliente1);
			clientes.borrar(null);
			fail(CLIENTE_NULO);
		} catch (NullPointerException e) {
			assertEquals(ERROR_BORRAR_CLIENTE_NULO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(1, clientes.getTamano(), TAMANO_NO_ESPERADO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	

}
