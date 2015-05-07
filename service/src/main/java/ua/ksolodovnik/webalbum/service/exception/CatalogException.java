package ua.ksolodovnik.webalbum.service.exception;

/**
 * Catalog catagory doesn't match Album category
 * @author ksolodovnik
 */
public class CatalogException extends Exception{

    /**
     * Constructor
     * @param message
     */
    public CatalogException(String message){
        super(message);
    }

    /**
     * Getting exception message
     * @return message
     */
    public String getMessage() {
        return super.getMessage();
    }
}
