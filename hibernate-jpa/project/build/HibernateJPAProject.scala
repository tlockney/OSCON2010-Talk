import sbt._

class HibernateJPAProject(info: ProjectInfo) extends DefaultProject(info) {
//  val shannonRepo = "Triactive Repo" at "http://shannon:8081/content/groups/public"
  val jbossRepo = "JBoss Repository" at "http://repository.jboss.org/maven2"

  val hibernate = "org.hibernate" % "hibernate-core" % "3.5.1-Final" 
  val hibernateAnnotations = "org.hibernate" % "hibernate-annotations" % "3.5.1-Final"
  val hibernateEntityManager = "org.hibernate" % "hibernate-entitymanager" % "3.5.1-Final"

  //val javaxPersistence = "javax.persistence" % "persistence-api" % "1.0"

  val sfl4j = "org.slf4j" % "slf4j-api" % "1.6.0"
  val sfl4j_jdk14 = "org.slf4j" % "slf4j-jdk14" % "1.6.0"

  val hsqldb = "hsqldb" % "hsqldb" % "1.8.0.7"
}
