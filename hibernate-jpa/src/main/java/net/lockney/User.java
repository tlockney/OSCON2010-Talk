package net.lockney;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String firstName;

  private String lastName;

  public User() { }

  public User(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() { return this.firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }

  public String getLastName() { return this.lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
}
