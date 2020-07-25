package com.mypractice.dailytask.exception;

/**
 * 
 * @author manasa.enukonda
 *
 */
public class DuplicateEntityException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1914910947553635600L;
	
	public DuplicateEntityException(final String message) {
        super(message);
    }

}
