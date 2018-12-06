package Teste;

import java.lang.annotation.Annotation;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.validation.Schema;

public class teste {   
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoPU");  
    }
}
