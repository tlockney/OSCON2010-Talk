package net.lockney;

import javax.persistence.*;

@NamedQuery(name="Order.byCustomerLastName",
    query="SELECT o FROM Order o, Customer c WHERE o.customer = c AND c.lastName = :lastName")

@Entity
@Table(name="CUSTOMER_ORDER")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne
  @JoinColumn(name="CUSTOMER_ID")
  private Customer customer;

  private float totalPrice;

  public Order() { }

  public Order(Customer customer) { this.customer = customer; }

  public Order(Customer customer, float totalPrice) {
    this(customer);
    this.totalPrice = totalPrice;
  }

  public Order(float totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Customer getCustomer() { return this.customer; }
  public void setCustomer(Customer customer) { this.customer = customer; }

  public float getTotalPrice() { return this.totalPrice; }
  public void setTotalPrice(float totalPrice) { this.totalPrice = totalPrice; }
}
