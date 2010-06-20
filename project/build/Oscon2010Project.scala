import sbt._

class Oscon2010Project(info: ProjectInfo) extends ParentProject(info) {
  lazy val httpclient = project("httpclient")
  lazy val hibernate = project("hibernate-jpa")
  lazy val hibernateConsole = project("hibernate-console", hibernate)
}

// vim: set ts=2 sw=2 et:
