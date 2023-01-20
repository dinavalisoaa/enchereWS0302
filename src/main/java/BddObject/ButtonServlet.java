///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package BddObject;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.sql.Connection;
//import java.sql.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
////import model.TableSync;
//
///**
// *
// * @author dina
// */
//@WebServlet(name = "ButtonServlet", urlPatterns = {"/ButtonServlet"})
//public class ButtonServlet extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException, Exception {
//        String e = request.getParameter("objet");
//        Object obj = Class.forName(e).newInstance();
//        Connection con = null;
//        Method m = obj.getClass().getMethod("insert", Connection.class);
////        Method = obj.getClass().getMethod("insert", Connection.class);
//        Field[] att = obj.getClass().getDeclaredFields();
//        Method[] setters = new Method[att.length];
//        for (int i = 0; i < att.length; i++) {
//            String str = att[i].getName().substring(0, 1).toUpperCase() + att[i].getName().substring(1);
//            setters[i] = obj.getClass().getMethod("set" + str, att[i].getType());
//            System.out.println("genetic.ButtonServlet.processRequest()" + att[i].getName());
//            if (request.getParameter(att[i].getName()) != null) {
//                if (att[i].getType().getSimpleName().equals("int")) {
//                    setters[i].invoke(obj, Integer.valueOf((request.getParameter(att[i].getName()).trim())));
//                }
//                if (att[i].getType().getSimpleName().equals("String")) {
//                    setters[i].invoke(obj, request.getParameter(att[i].getName()));
//                }
//                if (att[i].getType().getSimpleName().equals("Date")) {
//                    setters[i].invoke(obj, Date.valueOf(request.getParameter(att[i].getName())));
//                }
//                if (att[i].getType().getSimpleName().equals("double")) {
//                    setters[i].invoke(obj, Double.valueOf(request.getParameter(att[i].getName())));
//                }
//            }
//        }
//        m.invoke(obj, con);
//        String sql=((ObjectBDD)obj).insertion();
//        sql = sql.replaceAll("'", "`");
//       String page = request.getParameter("page");
//        String params = "";
//        if (request.getParameter("params") != null) {
//            params = request.getParameter("params");
//        }
//        String url = page + "?" + params;
//        response.sendRedirect(url);
////        request.getRequestDispatcher("./"+url).forward(request, response); 
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (Exception ex) {
//            Logger.getLogger(ButtonServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (Exception ex) {
//            Logger.getLogger(ButtonServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
