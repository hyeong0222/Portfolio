import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class One extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws IOException, ServletException {
    String name = request.getParameter("who");
    String age = request.getParameter("age");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("Well, " + name + " you will be " + (age + 1) + " next year.");
  }
}