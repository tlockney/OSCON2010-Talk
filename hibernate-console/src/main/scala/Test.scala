package main.scala

import net.lockney._
import javax.persistence._

object Test {
  def main(args: Array[String]) {
    val emf = Persistence.createEntityManagerFactory("hibernateExample")
    val em = emf.createEntityManager
    em.createNativeQuery("select * from user")
  }
}

// vim: set ts=2 sw=2 et:
