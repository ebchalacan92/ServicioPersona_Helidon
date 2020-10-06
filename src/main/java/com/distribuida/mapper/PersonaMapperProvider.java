package com.distribuida.mapper;
import java.util.Optional;

import javax.annotation.Priority;

import com.distribuida.usuario.Usuario;

import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.spi.DbMapperProvider;

@Priority(1000)
public class PersonaMapperProvider implements DbMapperProvider  {
	 private static final PersonaMapper MAPPER = new PersonaMapper();

	    @SuppressWarnings("unchecked")
	    @Override
	    public <T> Optional<DbMapper<T>> mapper(Class<T> type) {
	        if (type.equals(Usuario.class)) {
	            return Optional.of((DbMapper<T>) MAPPER);
	        }
	        return Optional.empty();
	    }

	}