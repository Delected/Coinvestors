package me.delected.coinvestors.exceptions;

public class IncorrectFileTypeException extends IllegalArgumentException {
    public IncorrectFileTypeException() {
        super("The file type provided is not valid!");
    }
}
