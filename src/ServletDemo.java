import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

@WebServlet(name = "ServletDemo")
public class ServletDemo extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init(ServletConfig)"+"fff?????");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            String value = request.getHeader(header);
            System.out.printf("%s\t%s%n",header,value);
        }


        //设置网页响应类型

        try {
            response.setDateHeader("Expires",0);
            response.setHeader("Cache-Control","no-cache");
            response.setHeader("pragma","no-cache");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h1>Hello absalom!</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
