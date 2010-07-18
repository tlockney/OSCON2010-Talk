Nothing up our sleeves...

  rm -rf project lib lib_managed target

See that it builds as a maven project

  less src/main/resources/app.xml
  less src/main/java/SpringHarness.java
  mvn clean package

Convert to an sbt project

  head pom.xml
  sbt

Run it

  sbt>
  run

More interactive examples

  sbt>
  console

  scala>
  import org.springframework.context.support._

  val ctx = new ClassPathXmlApplicationContext("app.xml")
  ctx.getBean("single")
  ctx.close
  ctx.getBean("single")
  ctx.refresh
  ctx.getBean("single")

If you wanted to interoperate with existing maven projects locally

  mkdir project/build
  vim project/build/UseMaven.scala

Add to UseMaven.scala:

  import sbt._

  class UseMavenProject(info: ProjectInfo) extends DefaultProject(info) {
    // copied the magics from
    // http://macstrac.blogspot.com/2010/01/using-sbt-on-your-scala-maven-project.html
    val mavenLocal = "Local Maven Repository" at "file://" + Path.userHome + "/.m2/repository"
  }
