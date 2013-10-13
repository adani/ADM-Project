package org.adm.project;

import org.neo4j.graphdb.GraphDatabaseService;

import com.mongodb.DB;

public class SessionData {
	public static String CURRENT_USER;
	
	public static DB MONGO_DB;
	
	public static GraphDatabaseService GRAPH_DB;
}
