import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class RegisterServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("获取单值参数name"+req.getParameter("name"));
        String[] hobits = req.getParameterValues("hobits");
        System.out.println("通过 getParameterMap 遍历所有参数: ");
        Map<String,String[]> parameters = req.getParameterMap();

        Set<String> paramNames = parameters.keySet();
        for (String param: paramNames
             ) {
            String[] value = parameters.get(param);
            System.out.println(param+":"+ Arrays.asList(value));
        }
    }
}
