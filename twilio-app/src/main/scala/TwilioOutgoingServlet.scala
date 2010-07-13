import javax.servlet.http.{HttpServlet,
  HttpServletRequest => Request, HttpServletResponse => Response}

class TwilioOutgoingServlet extends HttpServlet {

  override def doPost(req: Request, res: Response) = {
    val message = 
      <Response>
        <Say>Hello. How are you?</Say>
      </Response>

    res.setContentType("text/xml")
    res.getWriter().print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n")
    res.getWriter().print(message)
  }
}

// vim: set ts=2 sw=2 et:
