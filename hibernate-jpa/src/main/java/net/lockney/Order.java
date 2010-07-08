package net.lockney;

import javax.persistence.*;

@NamedQuery(name="Order.byUserLastName",
    query="SELECT o FROM Order o, User u WHERE o.user = u AND u.lastName = :lastName")

@Entity
@Table(name="USER_ORDER")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne
  @JoinColumn(name="USER_ID")
  private User user;

  private float totalPrice;

  public Order() { }

  public Order(User user) { this.user = user; }

  public User getUser() { return this.user; }
  public void setUser(User user) { this.user = user; }

  public float getTotalPrice() { return this.totalPrice; }
  public void setTotalPrice(float totalPrice) { this.totalPrice = totalPrice; }
}
