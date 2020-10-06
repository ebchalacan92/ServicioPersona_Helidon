package com.distribuida.rest;

import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.distribuida.servicio.UsuarioServicio;
import com.distribuida.usuario.Usuario;

@Path("/personas")
@ApplicationScoped
public class PersonaRest {
	
	@Inject
	private UsuarioServicio servicio;
	
	@GET
	@Produces(value= MediaType.APPLICATION_JSON)
	public List<Usuario> listar() throws SQLException{
		return servicio.selectAllUsers();
	}
	
	@GET @Path("buscar/{id}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("id") Integer id) {
		Usuario per = servicio.buscarUsuario(id);
		if(per!=null) {
			return Response.status(Response.Status.OK).entity(per).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).entity("persona no encontrada").build();
			
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(value=MediaType.APPLICATION_JSON)
	@Path("/add")
	 public Response addPersona(Usuario userRequest) {
	 
		servicio.insertarUsuario(userRequest);
	 
		return Response.ok(userRequest).build();
	 }
	
	@DELETE
	@Path("eliminar/{id}")
	public String deleteOrderById(@PathParam("id") Integer id) {
		servicio.eliminarUsuario(id);
		return "<result>success</result>";
	}
	
	@PUT
	@Path("/actualizar")
	@Produces(value=MediaType.APPLICATION_JSON)
	 public Response reemplazar(Usuario userRequest) {
	 
		servicio.actualizarUsuario(userRequest);
		return Response.ok(userRequest).build();
	
	 }
}
