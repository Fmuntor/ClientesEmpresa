package org.iesalandalus.programacion.clientesempresa.modelo.dominio;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class ClienteTest {
	
	private static final String ERROR_NOMBRE_NULO = "ERROR: El nombre de un cliente no puede ser nulo.";
	private static final String ERROR_NOMBRE_NO_VALIDO = "ERROR: El nombre de un cliente no puede estar vacío.";
	private static final String ERROR_DNI_NULO = "ERROR: El dni de un cliente no puede ser nulo.";
	private static final String ERROR_DNI_NO_VALIDO = "ERROR: El dni del cliente no tiene un formato válido.";
	private static final String ERROR_LETRA_DNI_NO_VALIDA = "ERROR: La letra del dni del cliente no es correcta.";
	private static final String ERROR_CORREO_NULO = "ERROR: El correo de un cliente no puede ser nulo.";
	private static final String ERROR_CORREO_NO_VALIDO = "ERROR: El correo del cliente no tiene un formato válido.";
	private static final String ERROR_TELEFONO_NULO = "ERROR: El teléfono de un cliente no puede ser nulo.";
	private static final String ERROR_TELEFONO_NO_VALIDO = "ERROR: El teléfono del cliente no tiene un formato válido.";
	private static final String ERROR_COPIAR_CLIENTE_NULO = "ERROR: No es posible copiar un cliente nulo.";
	private static final String NOMBRE_INCORRECTO = "Debería haber saltado una excepción indicando que el nombre es incorrecto";
	private static final String DNI_INCORRECTO = "Debería haber saltado una excepción indicando que el DNI es incorrecto";
	private static final String CORREO_INCORRECTO = "Debería haber saltado una excepción indicando que el correo es incorrecto";
	private static final String TELEFONO_INCORRECTO = "Debería haber saltado una excepción indicando que el teléfono es incorrecto";
	private static final String FECHA_NACIMIENTO_INCORRECTA = "Debería haber saltado una excepción indicando que la fecha de nacimiento es incorrecta";
	private static final String CLIENTE_NULO = "Debería haber saltado una excepción indicando que no se puede copiar un cliente nulo.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String CLIENTE_NO_ESPERADO = "El cliente copiado debería ser el mismo que el pasado como parámetro.";
	private static final String NOMBRE_NO_ESPERADO = "El nombre devuelto no es el mismo que el pasado al constructor.";
	private static final String DNI_NO_ESPERADO = "El DNI devuelto no es el mismo que el pasado al constructor.";
	private static final String TELEFONO_NO_ESPERADO = "El teléfono devuelto no es el mismo que el pasado al constructor.";
	private static final String CORREO_NO_ESPERADO = "El correo devuelto no es el mismo que el pasado al constructor.";
	private static final String FECHA_NACIMIENTO_NO_ESPERADA = "La fecha de nacimiento devuelta no es la misma que la pasada al constructor.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto cliente.";
	private static final String NOMBRE_JRJR = "José Ramón Jiménez Reyes";
	private static final String DNI_JRJR = "11223344B";
	private static final String TELEFONO_JRJR = "950112233";
	private static final String CORREO_JRJR = "joseramon.jimenez@iesalandalus.org";
	private static final String NOMBRE_MAL_ARDR = "  ANDRÉS   RuBiO   dEl             río";
	private static final String NOMBRE_ARDR = "Andrés Rubio Del Río";
	private static final String DNI_ARDR = "22334455Y";
	private static final String TELEFONO_ARDR = "666223344";
	private static final String CORREO_ARDR = "andres.rubio@iesalandalus.org";
	private static final String ERROR_FECHA_NULA = "ERROR: La fecha de nacimiento de un cliente no puede ser nula.";

	private static final LocalDate FECHA_NACIMIENTO_JRJR=LocalDate.of(2002, 9, 15);
	private static final LocalDate FECHA_NACIMIENTO_ARDR=LocalDate.of(1992, 7, 3);
	private static final String FORMATO_FECHA="dd/MM/yyyy";
 
	
	@Test
	public void constructorNombreValidoDniValidoCorreoValidoTelefonoValidoFechaNacimientoValidaCreaClienteCorrectamente() {
		Cliente cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
		assertEquals(NOMBRE_JRJR, cliente.getNombre(), NOMBRE_NO_ESPERADO);
		assertEquals(DNI_JRJR, cliente.getDni(), DNI_NO_ESPERADO);
		assertEquals(CORREO_JRJR, cliente.getCorreo(), CORREO_NO_ESPERADO);
		assertEquals(TELEFONO_JRJR, cliente.getTelefono(), TELEFONO_NO_ESPERADO);
		assertEquals(FECHA_NACIMIENTO_JRJR, cliente.getFechaNacimiento(), FECHA_NACIMIENTO_NO_ESPERADA);
		
		cliente = new Cliente(NOMBRE_MAL_ARDR, DNI_ARDR, CORREO_ARDR, TELEFONO_ARDR, FECHA_NACIMIENTO_ARDR);
		assertEquals(NOMBRE_ARDR, cliente.getNombre(), NOMBRE_NO_ESPERADO);
		assertEquals(DNI_ARDR, cliente.getDni(), DNI_NO_ESPERADO);
		assertEquals(CORREO_ARDR, cliente.getCorreo(), CORREO_NO_ESPERADO);
		assertEquals(TELEFONO_ARDR, cliente.getTelefono(), TELEFONO_NO_ESPERADO);
		assertEquals(FECHA_NACIMIENTO_ARDR, cliente.getFechaNacimiento(), FECHA_NACIMIENTO_NO_ESPERADA);
	}

	@Test
	public void constructorNombreNoValidoDniValidoCorreoValidoTelefonoValidoFechaNacimientoValidaLanzaExcepcion() {
		Cliente cliente = null;
		try {
			cliente = new Cliente(null, DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (NullPointerException e) {
			assertEquals(ERROR_NOMBRE_NULO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			cliente = new Cliente("", DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_NOMBRE_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			cliente = new Cliente("  ", DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_NOMBRE_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoDniNoValidoCorreoValidoTelefonoValidoFechaNacimientoValidaLanzaExcepcion() {
		Cliente cliente = null;
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, null, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (NullPointerException e) {
			assertEquals(ERROR_DNI_NULO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, "", CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_DNI_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, "   ", CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_DNI_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, "11223344", CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_DNI_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, "112233445A", CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_DNI_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, "11223344AA", CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_DNI_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, "11223344A", CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_LETRA_DNI_NO_VALIDA, e.getMessage(), MENSAJE_NO_CORRECTO);		
			assertNull(cliente,OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoDniValidoCorreoNoValidoTelefonoValidoFechaNacimientoValidaLanzaExcepcion() {
		Cliente cliente = null;
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, null, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (NullPointerException e) {
			assertEquals(ERROR_CORREO_NULO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, "", TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_CORREO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, "   ", TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_CORREO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, "11223344", TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_CORREO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, "arr@.com", TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_CORREO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, "arr.es.com", TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_CORREO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, "arr@gmail", TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_CORREO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);		
			assertNull(cliente,OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoDniValidoCorreoValidoTelefonoNoValidoFechaNacimientoValidaLanzaExcepcion() {
		Cliente cliente = null;
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, null, FECHA_NACIMIENTO_JRJR);
			fail(TELEFONO_INCORRECTO);
		} catch (NullPointerException e) {
			assertEquals(ERROR_TELEFONO_NULO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, "", FECHA_NACIMIENTO_JRJR);
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_TELEFONO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, "   ", FECHA_NACIMIENTO_JRJR);
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_TELEFONO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, "1", FECHA_NACIMIENTO_JRJR);
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_TELEFONO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, "arr@.com", FECHA_NACIMIENTO_JRJR);
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_TELEFONO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, "9991122334", FECHA_NACIMIENTO_JRJR);
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_TELEFONO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, "99911223", FECHA_NACIMIENTO_JRJR);
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_TELEFONO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);		
			assertNull(cliente,OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoDniValidoCorreoValidoTelefonoValidoFechaNacimientoNoValidaLanzaExcepcion() {
		Cliente cliente = null;
		
		try {
			cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, null);
			fail(FECHA_NACIMIENTO_INCORRECTA);
		} catch (NullPointerException e) {
			assertEquals(ERROR_FECHA_NULA, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorClienteValidoCopiaClienteCorrectamente() {
		Cliente cliente1 = new Cliente(NOMBRE_ARDR, DNI_ARDR, CORREO_ARDR, TELEFONO_ARDR, FECHA_NACIMIENTO_ARDR);
		Cliente cliente2 = new Cliente(cliente1);
		assertEquals(cliente1, cliente2, CLIENTE_NO_ESPERADO);
		assertEquals(NOMBRE_ARDR, cliente2.getNombre(), NOMBRE_NO_ESPERADO);
		assertEquals(DNI_ARDR, cliente2.getDni(), DNI_NO_ESPERADO);
		assertEquals(CORREO_ARDR, cliente2.getCorreo(), CORREO_NO_ESPERADO);
		assertEquals(TELEFONO_ARDR, cliente2.getTelefono(), TELEFONO_NO_ESPERADO);
		assertEquals(FECHA_NACIMIENTO_ARDR, cliente2.getFechaNacimiento(), FECHA_NACIMIENTO_NO_ESPERADA);
	}
	
	@Test
	public void constructorClienteNuloLanzaExcepcion() {
		Cliente cliente = null;
		
		try {
			cliente = new Cliente(null);
			fail(CLIENTE_NULO);
		} catch (NullPointerException e) {
			assertEquals(ERROR_COPIAR_CLIENTE_NULO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, cliente, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		Cliente cliente = new Cliente(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);

		
		assertEquals(String.format("nombre=%s (%s), DNI=%s, correo=%s, teléfono=%s, fecha nacimiento=%s", NOMBRE_JRJR, "JRJR", DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR.format(DateTimeFormatter.ofPattern(FORMATO_FECHA))), cliente.toString(), CADENA_NO_ESPERADA);
		
		cliente = new Cliente(NOMBRE_MAL_ARDR, DNI_ARDR, CORREO_ARDR, TELEFONO_ARDR, FECHA_NACIMIENTO_ARDR);
		assertEquals(String.format("nombre=%s (%s), DNI=%s, correo=%s, teléfono=%s, fecha nacimiento=%s", NOMBRE_ARDR, "ARDR", DNI_ARDR, CORREO_ARDR, TELEFONO_ARDR, FECHA_NACIMIENTO_ARDR.format(DateTimeFormatter.ofPattern(FORMATO_FECHA))), cliente.toString(), CADENA_NO_ESPERADA);
	}

}
