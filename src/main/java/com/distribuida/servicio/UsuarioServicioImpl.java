package com.distribuida.servicio;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.distribuida.usuario.Usuario;

import io.helidon.common.reactive.Multi;
import io.helidon.common.reactive.Single;
import io.helidon.dbclient.DbClient;

@ApplicationScoped
public class UsuarioServicioImpl implements UsuarioServicio {
	
	@Inject private DbClient dbClient; 
	    

	@Override
	public void insertarUsuario(Usuario user){
		dbClient.execute(exec -> exec
				.createNamedInsert("insert-persona")
				.addParam(user.getNombre()).addParam(user.getDireccion()).addParam(user.getEmail())
				.execute())
				.thenAccept(count -> System.out.printf("Datos insertados", count));
				
	}


	@Override
	public Usuario buscarUsuario(int id) {
		
	        Multi<Usuario> rows = dbClient.execute(exec -> exec.namedQuery("select-by-id", id))
	                .map(it -> it.as(Usuario.class));
	        Single<List<Usuario>> list = rows.collectList();
	        try {
	            List<Usuario> listasPersonas = list.get();
	            listasPersonas.forEach(System.out::println);
	            return listasPersonas.get(0);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	            return null;
	        } catch (ExecutionException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }


	@Override
	public List<Usuario> selectAllUsers() {
		
		Multi<Usuario> rows = dbClient.execute(exec -> exec.namedQuery("select-all-persona"))
                .map(it -> it.as(Usuario.class));
        Single<List<Usuario>> list = rows.collectList();
        try {
            List<Usuario> listasPersonas = list.get();
            listasPersonas.forEach(System.out::println);
            return listasPersonas;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }

    }


	@Override
	public void eliminarUsuario(int id) {
		  dbClient.execute(exec -> exec
	                .delete("DELETE FROM usuario where id = ?", id))
	                .thenAccept(count ->
	                    System.out.printf("Columna borrada", count));
	}


	@Override
	public void actualizarUsuario(Usuario user) {
		dbClient.execute(exec -> exec
                .createNamedUpdate("update")
                .addParam(user.getNombre())
                .addParam(user.getDireccion())
                .addParam(user.getEmail())
                .addParam(user.getId())
                .execute())
                .thenAccept(count -> System.out.printf("Dato Actualizado", count));
	}
	


}
