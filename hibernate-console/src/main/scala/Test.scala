package main.scala

import net.lockney._
import javax.persistence._

object Test {
  def main(args: Array[String]) {
    val emf = Persistence.createEntityManagerFactory("hibernateExample")
    val em = emf.createEntityManager
    val userList = em.createNativeQuery("select * from user").getResultList.asInstanceOf[java.util.List[User]]
    println(userList)

    val dao = new UserDAO
    dao.createUser(new User("Thomas","Lockney"))
    println(dao.getUsers)
  }
}

// vim: set ts=2 sw=2 et:
