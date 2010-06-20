package net.lockney;

import javax.persistence.*;

import java.util.*;

public class UserController {

  EntityManagerFactory emf;

  public UserController() { 
    emf = Persistence.createEntityManagerFactory("hibernateExample");
  }

  public List<User> getUsers() {
    EntityManager em = emf.createEntityManager();
    List<User> users = em.createQuery("SELECT u FROM User u").getResultList();
    em.close();
    return users; 
  }

  public User createUser(User user) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(user);
    em.getTransaction().commit();
    em.close();
    return user;
  }
}
