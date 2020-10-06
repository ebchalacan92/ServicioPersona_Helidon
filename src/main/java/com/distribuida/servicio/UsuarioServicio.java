package com.distribuida.servicio;


import java.sql.SQLException;
import java.util.List;

import com.distribuida.usuario.Usuario;

// Creo los diferentes métodos del CRUD de la BDD persona
public interface UsuarioServicio {
	
	public void insertarUsuario(Usuario user);
	
	public Usuario buscarUsuario(int id);
	
	public List<Usuario> selectAllUsers() throws SQLException;
	
	public void eliminarUsuario(int id);
	
	public void actualizarUsuario(Usuario user);
	
	
	
}
