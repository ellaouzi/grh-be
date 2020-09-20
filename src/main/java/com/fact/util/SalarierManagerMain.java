package com.fact.util;


        import com.fact.model.Employee;
        import com.fact.model.Manager;
        import com.fact.model.Salarier;
        import org.hibernate.Session;
        import org.hibernate.SessionFactory;

        import java.lang.reflect.UndeclaredThrowableException;
        import java.util.Arrays;

public class SalarierManagerMain {

    public static void main(String[] args) {


        Salarier manager1 = new Salarier("Chuck", "Norris");

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        Employee employee3 = new Employee();
        Manager manager = new Manager();
        employee1.setNom("Mrouane");
        employee2.setNom("Hicham");
        employee3.setNom("Oussama");
        manager.setNom("Mohamed");
        employee1.setInterim(employee2);
        manager.setEmployees(Arrays.asList(employee1, employee2, employee3));



        System.out.println(employee1.getNom() +" Remplaced by: " + employee1.getInterim().getNom());
        System.out.println(manager.getNom() + " Manages : " + manager.getEmployees());

    }
}
