package Teste;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//testando

public class teste {   
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoPU");  
    }
}
