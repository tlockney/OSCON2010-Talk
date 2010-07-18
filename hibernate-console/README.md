# Working with Hibernate at the Scala console
    cd ../hibernate-jpa
    sbt publish-local
    cd ../hibernate-console
    sbt update
    sbt console

    scala >
    import net.lockney._
    import javax.persistence._
    import scala.collection.JavaConversions._

    val emf = Persistence.createEntityManagerFactory("hibernateExample")
    val em = emf.createEntityManager
    em.getTransaction.begin
    
    val firstNames = Set("Thomas","Trenton","Martin","Jonas","David")
    val lastNames = Set("Lockney","Lipscomb","Odersky","Boner","Pollack")

    val customers = firstNames.zip(lastNames).map { namePair =>
      new Customer(namePair._1, namePair._2)
    }
    customers.foreach { em.persist(_) }
    em.getTransaction.commit

    // for some reason the type returned by getResultList() is not something
    // Scala is able to deal with
    val customersFromQuery = 
      em.createQuery("SELECT c FROM Customer c").getResultList().asInstanceOf[java.util.List[Customer]]

    customers.foreach( cust => println(cust.getFullName) }

    val thomas = customersFromQuery.get(0)
    // alternately
    val thomas = customersFromQuery(0)

    val order = new Order(thomas, 156.99f)
    em.getTransaction.begin 
    em.persist(order) 
    em.getTransaction.commit

    val orders = 1 to 10 map { _ => 
      new Order(scala.math.random.floatValue * 100) 
    }

    val orderQuery = em.createQuery("SELECT o FROM Order o, Customer c WHERE o.customer = c AND c.lastName = :lastName")
    orderQuery.setParameter("lastName","Lockney")
    val thomasOrder = orderQuery.getResultList.asInstanceOf[java.util.List[Order]].get(0)
    
    // here we're using a named query defined on the Order class
    val orderQuery2 = em.createNamedQuery("Order.byCustomerLastName")
    orderQuery2.setParameter("lastName","Lockney")
    val thomasOrder2 = orderQuery2.getResultList.asInstanceOf[java.util.List[Order]].get(0)
