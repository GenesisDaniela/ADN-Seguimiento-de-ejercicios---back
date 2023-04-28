package com.ceiba.core;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BasePrueba {

	private static final String PERO_FUE_LANZADA = " Pero fue lanzada ";
	private static final String SE_ESPERABA_LA_EXCEPCION = "Se esperaba la excepcion ";

	public static <T> void assertThrows(Supplier<T> supplier, Class<? extends Exception> exception, String message) {
		try {
			supplier.get();
			fail();
		} catch (Exception e) {
			assertTrue(exception.isInstance(e), SE_ESPERABA_LA_EXCEPCION + exception.getCanonicalName() + PERO_FUE_LANZADA
					+ e.getClass().getCanonicalName());
			assertTrue(e.getMessage().contains(message));
		}
	}

	public static void assertThrows(Thunk thunk, Class<? extends Exception> exception, String message) {
		try {
			thunk.execute();
			fail();
		} catch (Exception e) {
			assertTrue(exception.isInstance(e), SE_ESPERABA_LA_EXCEPCION + exception.getCanonicalName() + PERO_FUE_LANZADA
					+ e.getClass().getCanonicalName());
			assertTrue(e.getMessage().contains(message));
		}
	}

	@FunctionalInterface
	public interface Thunk {
		void execute();
	}

}
