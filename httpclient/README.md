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
    
    implicit def tuple2NameValuePair(t:Tuple2[String,String]): BasicNameValuePair = new BasicNameValuePair(t._1, t._2)
    
    var params = new java.util.ArrayList[NameValuePair]()
    params.add(("q","httpclient"))
    val uri = URIUtils.createURI("http","google.com",80,"/search",URLEncodedUtils.format(params, "UTF-8"),null)
    val get = new HttpGet(uri)
    val h = new DefaultHttpClient
    val resp = h.execute(get)
    resp.getAllHeaders.foreach { println _ }
    resp.getEntity.consumeContent
