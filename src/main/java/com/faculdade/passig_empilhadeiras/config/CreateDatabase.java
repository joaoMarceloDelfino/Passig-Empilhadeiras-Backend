package com.faculdade.passig_empilhadeiras.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class CreateDatabase {
    static Dotenv dotenv = Dotenv.load();
    static String url = dotenv.get("DATABASE_POSTGRES_URL");
    static String user = dotenv.get("DATABASE_USERNAME");
    static String password = dotenv.get("DATABASE_PASSWORD");
    static String databaseName = dotenv.get("DATABASE_NAME");
    static Logger logger = Logger.getLogger(CreateDatabase.class.getName());


    public static void createDatabase() {
        try(Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement()) {

            String checkDbExists = "SELECT 1 FROM pg_database WHERE datname = '" + databaseName + "'";
            ResultSet rs = stmt.executeQuery(checkDbExists);

            if(!rs.next()){
                String sql = "CREATE DATABASE "+ databaseName;
                int affectedRows = stmt.executeUpdate(sql);
                if (affectedRows > 0) {
                    logger.info("Query de criação do banco executada com sucesso!");
                }
            }

        } catch(Exception e){
            e.printStackTrace();
            logger.severe("Não foi possível executar a criação do banco");
        }
    }
}
