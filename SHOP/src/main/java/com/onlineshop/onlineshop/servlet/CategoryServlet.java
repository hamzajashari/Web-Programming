package com.onlineshop.onlineshop.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "category-servlet",urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {
    class Category{

        private String name;
        private String description;

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Category(String name,String description) {
            this.name = name;
            this.description=description;
        }
    }
    //inner memory
    private List<Category> categoryList = null;

    @Override
    public void init() throws ServletException {
        super.init();
        this.categoryList=new ArrayList<>();
        this.categoryList.add(new Category("Software","Software is...."));
        this.categoryList.add(new Category("Books","Books are important.."));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ipAddress=req.getRemoteAddr();
        String clientAgent=req.getHeader("User-Agent");
        PrintWriter out=resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>User Info</h2>");
        out.format("IP address: %s<br/>",ipAddress);
        out.format("Client Agent: %s",clientAgent);

        out.println("<h2>Category List</h2>");
        out.println("<ul>");
        categoryList.forEach(r->
                out.format("<li>%s - %s</li>",r.getName(),r.getDescription())
        );
        out.println("</ul>");

        out.println("<h2>Add New Category</h2>");

        //new form for Adding Category
        out.println("<form method='POST' action='/servlet/category'/>");
        out.println("<label for='name'>Name:<label>");
        out.println("<input id='name' type='text' name='name'/>");
        out.println("<input id='desc' type='text' name='desc'/>");
        out.println("<input type='submit' value='Submit'/>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        String categoryDesc = req.getParameter("desc");
        addCategory(categoryName,categoryDesc);
        resp.sendRedirect("/servlet/category");
    }

    public void addCategory(String name,String desc){
        if(name!=null && !name.isEmpty()){
            this.categoryList.add(new Category(name,desc));
        }
    }
}
