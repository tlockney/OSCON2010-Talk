import sbt._

class TwilioAppProject(info: ProjectInfo) extends DefaultWebProject(info) {
  val jetty7Webapp = "org.eclipse.jetty" % "jetty-webapp" % "7.0.2.RC0" % "test"
  val jetty7Server = "org.eclipse.jetty" % "jetty-server" % "7.0.2.RC0" % "test"
  val servlet = "javax.servlet" % "servlet-api" % "2.5" % "provided"
}

// vim: set ts=2 sw=2 et:
