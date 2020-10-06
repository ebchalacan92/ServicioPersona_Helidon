package com.distribuida.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;

@ApplicationScoped
public class ConfigSource {
	
	@Produces @ApplicationScoped
	public DbClient dbClient() {
		Config config =  Config.create();
		Config dbConfig= config.get("db");
		DbClient dbClient = DbClient.builder(dbConfig).build();
		return dbClient;
	}
   
    }
