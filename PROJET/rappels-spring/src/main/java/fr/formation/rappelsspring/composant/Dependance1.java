package fr.formation.rappelsspring.composant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dependance1 {
    @Autowired
    private Dependance2 dependance2;

    public void demo() {
        System.out.println(dependance2);
    }
}
