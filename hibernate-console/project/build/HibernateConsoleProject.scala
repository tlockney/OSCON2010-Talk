import sbt._

class HibernateConsoleProject(info: ProjectInfo) extends DefaultProject(info) {
  val hibernateJpa = "net.lockney" %% "hibernate-jpa" % "0.1"
}

// vim: set ts=2 sw=2 et:
