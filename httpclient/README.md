# HTTPClient Scala/SBT Example

To get started, first download the 4.0.1 (GA) version of HTTPClient with dependencies. The resulting download should be
named httpcomponents-client-4.0.1-bin-with-dependencies.tar.gz. You'll want to extract this to a temporary directory and
then copy the JAR files into the lib directory of this project.

    cd /tmp
    wget http://apache.imghat.com/httpcomponents/httpclient/binary/httpcomponents-client-4.0.1-bin-with-dependencies.tar.gz
    tar xzf httpcomponents-client-4.0.1-bin-with-dependencies.tar.gz
    cp httpcomponents-client-4.0.1/lib/*.jar ~/projects/OSCON2010-Talk/httpclient/lib/

### A walk through with SBT

    sbt console
    scala >

    import org.apache.http.NameValuePair
    import org.apache.http.message.BasicNameValuePair
    import org.apache.http.client.utils.{URIUtils,URLEncodedUtils}
    import org.apache.http.client.methods.HttpGet
    import org.apache.http.impl.client.DefaultHttpClient

    var params = new java.util.ArrayList[NameValuePair]()

    params.add(new BasicNameValuePair("num","50"))
    //                ^^ this sucks!

    // so let's add a way to simplify this
    implicit def tuple2NVP(t:Tuple2[String,String]): BasicNameValuePair = new BasicNameValuePair(t._1, t._2)
    params.add(("q","httpclient"))

    val uri = URIUtils.createURI("http","google.com",80,"/search",URLEncodedUtils.format(params, "UTF-8"),null)
    val get = new HttpGet(uri)
    val h = new DefaultHttpClient
    val resp = h.execute(get)
    resp.getAllHeaders.foreach { println _ }

    // need to consume the request content to reuse the response object
    resp.getEntity.consumeContent

    // now let's try getting the twitter public timeline

    val uri = URIUtils.createURI("http","twitter.com",80,"/statuses/public_timeline.xml",
      URLEncodedUtils.format(params, "UTF-8"),null)
    val get = new HttpGet(uri)

    // this gives warnings; twitter has bad headers
    val resp = h.execute(get)

    import scala.io.Source
    import java.io.InputStream
    import scala.xml._

    // this will let use take the InputStream and get a String from it
    implicit def stream2String(is:InputStream): String =
      Source.fromInputStream(is).getLines().reduceLeft(_+_)   

    val bodyXml = XML.loadString(convertStreamToString(resp.getEntity.getContent))
    (bodyXml \\ "text") . take(10) . foreach {e => println(e.text.take(60))}
