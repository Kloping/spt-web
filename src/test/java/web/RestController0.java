package web;

import io.github.kloping.little_web.annotations.GetMethod;
import io.github.kloping.little_web.annotations.RequestParm;
import io.github.kloping.little_web.annotations.WebRestController;
import entity.M0;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author github.kloping
 */
@WebRestController
public class RestController0 {
    @GetMethod("/get")
    public String m0(@RequestParm("name") String name) {
        return "Hello! " + name;
    }

    @GetMethod("/get0")
    public List m1(@RequestParm("name") String name) {
        List list = new LinkedList();
        for (int i = 0; i < 100; i++) {
            M0 m0 = new M0(name, 10);
            list.add(m0);
        }
        return list;
    }

    @GetMethod("favicon.ico")
    public void favicon(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://q1.qlogo.cn/g?b=qq&nk=3474006766&s=640");
    }

    @GetMethod("/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("index.html");
    }
}
