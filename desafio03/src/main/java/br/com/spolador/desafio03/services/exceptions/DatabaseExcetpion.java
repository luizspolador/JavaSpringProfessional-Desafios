package br.com.spolador.desafio03.services.exceptions;

public class DatabaseExcetpion  extends RuntimeException{
    public DatabaseExcetpion(String msg){
        super(msg);
    }
}
