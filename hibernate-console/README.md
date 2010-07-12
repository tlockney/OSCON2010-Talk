# Working with Hibernate at the Scala console
    cd ../hibernate-jpa
    sbt publish-local
    cd ../hibernate-console
    sbt update
    sbt console

    > import net.lockney._
    > import javax.persistence._
    > import scala.collection.JavaConversions._

    > val emf = Persistence.createEntityManagerFactory("hibernateExample")
    > val em = emf.createEntityManager
    > em.getTransaction.begin
    > em.persist(new Customer("Thomas","Lockney"))
    > em.getTransaction.commit

    > // for some reason the type returned by getResultList() is not something
    > // Scala is able to deal with
    > val customers = em.createQuery("SELECT c FROM Customer c").getResultList().asInstanceOf[java.util.List[Customer]]

    > def printCustomer(x: Any) = x match {
    >   case x: Customer => println(x.getFullName)
    >   case _ => println("wasn't a Customer")
    > }

    > def printCustomers(customers: Iterable[Customer]) = { customers.foreach(printCustomer(_)) }

    > printCustomers(customers)

    > val thomas = customers.get(0)
    > val order = new Order(thomas)
    > em.getTransaction.begin 
    > em.persist(order) 
    > em.getTransaction.commit

    > val orderQuery = em.createQuery("SELECT o FROM Order o, Customer c WHERE o.customer = c AND c.lastName = :lastName")
    > orderQuery.setParameter("lastName","Lockney")
    > val thomasOrder = orderQuery.getResultList.asInstanceOf[java.util.List[Order]].get(0)
    
    > // here we're using a named query defined on the Order class
    > val orderQuery2 = em.createNamedQuery("Order.byCustomerLastName")
    > orderQuery2.setParameter("lastName","Lockney")
    > val thomasOrder2 = orderQuery2.getResultList.asInstanceOf[java.util.List[Order]].get(0)


