package fr.servermanager.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private Connection connection;
    private String urlBase, host, dbName, user, pass;


    public DbConnection(String urlBase, String host, String dbName, String user, String pass) {
        this.urlBase = urlBase;
        this.host = host;
        this.dbName = dbName;
        this.user = user;
        this.pass = pass;
    }

    public void Connected(){
        if(!isConnected()){
            try {
                this.connection = DriverManager.getConnection(urlBase + host + "/" + dbName, user, pass);
                System.out.println("Connection etablie");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection(){
        if(connection != null) {
            return connection;
        }
        Connected();
        return connection;

    }

    public void Close(){
        if(isConnected()){
            try {
                this.connection.close();
                this.connection = null;
                System.out.println("Connection ferme");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected(){
        return this.connection != null;
    }
}
