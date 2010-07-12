package net.lockney;

import javax.persistence.*;

import java.util.*;

public class CustomerDAO {

  EntityManagerFactory emf;

  public CustomerDAO() { 
    emf = Persistence.createEntityManagerFactory("hibernateExample");
  }

  public List<Customer> getCustomers() {
    EntityManager em = emf.createEntityManager();
    List<Customer> customers = em.createQuery("SELECT c FROM Customer c").getResultList();
    em.close();
    return customers; 
  }

  public Customer createCustomer(Customer customer) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(customer);
    em.getTransaction().commit();
    em.close();
    return customer;
  }
}
