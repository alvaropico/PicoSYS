/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pico.dao;

import com.pico.model.Usuario;
import com.pico.utils.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apico
 */
public class UsuarioDaoImpl implements UsuarioDao {
    
    private Connection conexion;

    @Override
    public void UsuarioDao() {
        conexion = DbUtil.getConnection();
    }
    
    @Override
    public boolean save(Usuario usuario) {
        boolean saved = false;
        try {
            if(usuario.getId()==0) {
                PreparedStatement preparedStatement = conexion.prepareStatement("insert into usuarios values (null, ?, ?, ?, ? )");
                // Parameters start with 1
                preparedStatement.setString(1, usuario.getUsuario());
                preparedStatement.setString(2, usuario.getPassword());
                preparedStatement.setString(3, usuario.getNombre());
                preparedStatement.setString(4, usuario.getEmail());
                preparedStatement.executeUpdate();
                saved = true;
            } else {
                PreparedStatement preparedStatement = conexion.prepareStatement("update usuarios set usuario=?, nombre=?, email=? where id=?");
                // Parameters start with 1
                preparedStatement.setString(1, usuario.getUsuario());
                preparedStatement.setString(2, usuario.getNombre());
                preparedStatement.setString(3, usuario.getEmail());
                preparedStatement.setInt(4, usuario.getId());
                preparedStatement.executeUpdate();
                saved = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saved;
    }
    
    @Override
    public Usuario getUsuario(int id) {
        Usuario usuario = new Usuario();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("select * from usuarios where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
        }
        return usuario;
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("delete from usuarios where userid=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            deleted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }
    
    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> list = new ArrayList<Usuario>();
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("select * from usuarios");
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                list.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}