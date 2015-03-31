/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pico.dao;

import com.pico.model.Usuario;
import java.util.List;

/**
 *
 * @author apico
 */
public interface UsuarioDao {
    public void UsuarioDao();
    public boolean save(Usuario usuario);
    public Usuario getUsuario(int id);
    public boolean delete(int id);
    public List<Usuario> getUsuarios();
}
