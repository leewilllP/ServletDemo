import DAO.HeroDAO;
import bean.Hero;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HeroListServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html,charset=UTF-8");
        List<Hero> heros = new HeroDAO().list();
        StringBuffer sb = new StringBuffer();
        sb.append("<table align='center' border='1' cellspacing='0'>\r\n");
        sb.append("<tr><td>%d</td><td>%s</td><td>%f</td><td>%d</td></tr>\r\n");

        String trFormat = "<tr><td>%d</td><td>%s</td><td>%f</td><td>%d</td></tr>\r\n";

        for (Hero hero:heros
             ) {
            String tr = String.format(trFormat,hero.getId(),hero.getName(),hero.getHp(),hero.getDamage());
            sb.append(tr);
        }
        resp.getWriter().println(sb.toString());
    }
}
