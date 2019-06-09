package murach.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import murach.business.User;

public class SurveyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Cookie c = new Cookie("SuseridCookie", "userid");
        c.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
        
        c.setPath("/"); // allo w access by entire app
        response.addCookie(c);
        String path=c.getPath();

        Cookie[] cookies = request.getCookies();
        String cookieName = "SuseridCookie";
        String cookieValue = "";

        try {
            
            for (Cookie cookie : cookies) {
                if (1==1/*cookieName.equals(cookie.getName())*/) {
                    cookieValue = cookie.getValue();
                    request.setAttribute("SuseridCookie", cookieValue);
                }
                System.out.println(cookieValue);
                //s
                String my="C:\\murach\\"+cookieValue+".txt";
                FileWriter fw = new FileWriter(my);
                fw.write(path);
                fw.close();
            }
          
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Success...q");

        Enumeration names = request.getAttributeNames();
        while (names.hasMoreElements()) {
            System.out.println((String) names.nextElement());
        }

        // get parameters from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String heardFrom = request.getParameter("heardFrom");
        String wantsUpdates = request.getParameter("wantsUpdates");
        String contactVia = request.getParameter("contactVia");

        // process paramters
        if (heardFrom == null) {
            heardFrom = "NA";
        }
        if (wantsUpdates == null) {
            wantsUpdates = "No";
        } else {
            wantsUpdates = "Yes";
        }

        // store data in User object
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setHeardFrom(heardFrom);
        user.setWantsUpdates(wantsUpdates);
        user.setContactVia(contactVia);

        // store User object in request
        request.setAttribute("user", user);

        // forward request to JSP
        String url = "/survey.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
