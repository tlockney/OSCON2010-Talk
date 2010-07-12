package net.lockney;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;

@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String firstName;

  private String lastName;

  @OneToMany(mappedBy="customer")
  private Set<Order> orders;

  public Customer() { }

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.orders = new HashSet<Order>();
  }

  public String getFirstName() { return this.firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }

  public String getLastName() { return this.lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }

  public String getFullName() { return this.firstName + " " + this.lastName; }

  public void addOrder(Order order) { 
    this.orders.add(order); 
    order.setCustomer(this);
  }
  public Set<Order> getOrders() { return this.orders; }
}
