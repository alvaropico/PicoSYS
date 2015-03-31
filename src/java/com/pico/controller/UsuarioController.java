/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pico.controller;

import com.pico.dao.UsuarioDaoImpl;
import com.pico.model.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author apico
 */
public class UsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String SAVE = "/user.jsp";
    private static String LIST_USER = "/listUser.jsp";
    private UsuarioDaoImpl dao;

    public UsuarioController() {
        super();
        dao = new UsuarioDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("id"));
            dao.delete(userId);
            forward = LIST_USER;
            request.setAttribute("usuarios", dao.getUsuarios());    
        } else if (action.equalsIgnoreCase("save")){
            forward = SAVE;
            int userId = Integer.parseInt(request.getParameter("id"));
            Usuario usuario = dao.getUsuario(userId);
            request.setAttribute("usuario", usuario);
        } else if (action.equalsIgnoreCase("list")){
            forward = LIST_USER;
            request.setAttribute("usuarios", dao.getUsuarios());
        } else {
            forward = SAVE;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        usuario.setId(Integer.parseInt(request.getParameter("id")));
        usuario.setUsuario(request.getParameter("usuario"));
        usuario.setPassword(request.getParameter("password"));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setEmail(request.getParameter("email"));
        dao.save(usuario);
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("usuarios", dao.getUsuarios());
        view.forward(request, response);
    }
}
