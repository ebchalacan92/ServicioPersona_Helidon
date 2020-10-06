package com.distribuida.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.distribuida.usuario.Usuario;

import io.helidon.dbclient.DbColumn;
import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.DbRow;

public class PersonaMapper implements DbMapper<Usuario> {
	 @Override
	    public Usuario read(DbRow row) {
	        DbColumn id = row.column("id");
	        DbColumn nombre = row.column("nombre");
	        DbColumn direccion = row.column("direccion");
	        DbColumn email = row.column("email");
	        return new Usuario(id.as(Integer.class), nombre.as(String.class)
	                           , direccion.as(String.class), email.as(String.class));
	    }

	    @Override
	    public Map<String, ?> toNamedParameters(Usuario usuario) {
	        Map<String, Object> map = new HashMap<>(4);
	        map.put("id", usuario.getId());
	        map.put("nombre", usuario.getNombre());
	        map.put("direccion", usuario.getDireccion());
	        map.put("email", usuario.getEmail());
	        return map;
	    }

	    @Override
	    public List<?> toIndexedParameters(Usuario usuario) {
	        List<Object> list = new ArrayList<>(4);
	        list.add(usuario.getId());
	        list.add(usuario.getNombre());
	        list.add(usuario.getDireccion());
	        list.add(usuario.getEmail());
	        return list;
	    }



	}