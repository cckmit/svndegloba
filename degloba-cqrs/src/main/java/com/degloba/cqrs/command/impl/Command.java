package com.degloba.cqrs.command.impl;

/**
 * @author degloba
 * 
 * @category
 *
 */
public interface Command {
	/**
	 * @category Definim si la comanda es pot executar de manera asíncrona.
	 * <br>
	 * Si true llavors {@link CommandHandler} ha de retornar void - en qualsevol altra cas el sistema llençarà una excepció 
	 * 
	 * @return
	 */
    boolean asynchronous();

    /**
     * @category Definim que s'hauria de testejar si la mateixa comanda s'ha enviat una altra vegada<br>
     * Si true llavors command class must implement equals and hashCode
     * 
     * @return
     */
    boolean unique();

    /**
     * Si l'únic és True llavors aquesta propietat pot especificar el temps màxim en mil·lisegons abans que pugui executar-se el mateix
     * @return
     */
    long uniqueStorageTimeout();
}
