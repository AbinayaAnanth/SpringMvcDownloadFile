package com.jcg.spring.mvc.file.download;

public interface IConstants {

		public static final String DBNAME = "filedownload";		
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "Abinaya@26";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/filedownload";

	
	public static final String GET_TABLES_LIST_QUERY = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + DBNAME + "';";

	
	public static final int BUFFER_SIZE = 4096;
	public static final String NEW_LINE = "\n";
	public static final String PIPE_SYMBOL = "|";	
}