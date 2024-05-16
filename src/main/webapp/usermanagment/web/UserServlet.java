package src.main.webapp.usermanagment.web;


import src.main.webapp.usermanagment.dao.UserDAO;
import src.main.webapp.usermanagment.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class UserServlet extends HttpServlet {
    private static final long serialVersiionUID = 1;
    private UserDAO userDAO;


    public UserServlet(){
        this.userDAO = new UserDAO();


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GELDIM");
        String action = request.getServletPath();
        System.out.println(action);

        switch (action){
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                try {
                    insertUser(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/delete":
                try {
                    deleteUser(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/edit":
                try {
                    showEditForm(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/update":

                try {
                    updateUser(request,response);
                } catch (SQLException e) {

                    throw new RuntimeException(e);
                }
                break;
            default:
                 // handle list
                try {
                    listUser(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

        }

    }


    private void listUser(HttpServletRequest request , HttpServletResponse response) throws SQLException , IOException, ServletException{
        List<User> listUser = userDAO.selectAllUser();
        request.setAttribute("listUser" , listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request,response);
    }


    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request,response);
    }


    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, email, country);

        // UserDAO sınıfından bir örnek oluşturun
        UserDAO userDAO = new UserDAO();

        // Oluşturduğunuz örneği kullanarak insertUser metodunu çağırın
        userDAO.insertUser(newUser);

        response.sendRedirect("list");
    }



    private void deleteUser(HttpServletRequest request , HttpServletResponse response)throws SQLException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");
    }


    private void showEditForm(HttpServletRequest request , HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        User exsistingUser = userDAO.selectUserById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user",exsistingUser);
        dispatcher.forward(request,response);
    }

    private String extractNumericValue(String value) {
        if (value != null && value.contains("value='")) {
            int startIndex = value.indexOf("value='") + 7; // "value='" kısmının sonundan başlar
            int endIndex = value.indexOf("'", startIndex); // Başlangıç indeksinden sonraki ilk tek tırnak
            if (endIndex > startIndex) {
                return value.substring(startIndex, endIndex);
            }
        }
        return null;
    }

    private void updateUser(HttpServletRequest request , HttpServletResponse response )throws SQLException,IOException {

//        String idString = request.getParameter("id");
//        if (idString == null || idString.isEmpty()) {
//            // Handle the error: ID parametresi eksik
//            System.out.println("ID parametresi eksik veya geçersiz.");
//        }else {
//            System.out.println(idString);
//
//            if(idString == null){
//                System.out.println("gelen request null");
//            }
//
//
//        }
        int id = Integer.parseInt(request.getParameter("id"));



        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User user = new User(id,name,email,country);
        userDAO.updateUser(user);
        response.sendRedirect("list");

    }

}