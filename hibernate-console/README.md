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
    
    val firstNames = Seq("Thomas","Trenton","Martin","Jonas","David")
    val lastNames = Seq("Lockney","Lipscomb","Odersky","Boner","Pollack")

    val customers = firstNames.zip(lastNames).map { namePair =>
      new Customer(namePair._1, namePair._2)
    }
    customers.foreach { em.persist(_) }
    em.getTransaction.commit

    // Java is not able to give us enough to infer the parameterized type returned by
    // getResultList(), so we have to tell Scala a bit more to avoid later casting. 
    // Generally, you would write some wrapper around this to make life simpler
    val customersFromQuery = 
      em.createQuery("SELECT c FROM Customer c").getResultList().asInstanceOf[java.util.List[Customer]]

    customersFromQuery.foreach { cust => println(cust.getFullName) }

    val thomas = customersFromQuery.get(0)
    // alternately
    val thomas = customersFromQuery(0)

    val order = new Order(thomas, 156.99f)
    em.getTransaction.begin 
    em.persist(order) 
    em.getTransaction.commit

    val orders = 1 to 10 map { _ => 
      import scala.math.random
      val cust = customers((random * customers.size).intValue)
      val order = new Order(cust, random.floatValue * 100)
      em.persist(order)
      order // put this here so it gets returned to the orders collection
    }

    val orderQuery = em.createQuery("SELECT o FROM Order o, Customer c WHERE o.customer = c AND c.lastName = :lastName")
    orderQuery.setParameter("lastName","Lockney")
    val thomasOrder = orderQuery.getResultList.asInstanceOf[java.util.List[Order]].get(0)
    
    // here we're using a named query defined on the Order class
    val orderQuery2 = em.createNamedQuery("Order.byCustomerLastName")
    orderQuery2.setParameter("lastName","Lockney")
    val thomasOrder2 = orderQuery2.getResultList.asInstanceOf[java.util.List[Order]].get(0)
