package Consultas;

import java.sql.*;

public class Conectar{

    static String bd = "erp_comermex_pruebas";
    static String login = "abenitez";
    static String password = "dtmx.2020";
    static String driver = "org.postgresql.Driver";
    static String url = "jdbc:postgresql://10.99.99.74:5432/"+bd;
    Connection connection = null;

    public Conectar(){
        try{
        Class.forName(driver);
        connection = DriverManager.getConnection(url,login,password);
        if (connection!=null){
            System.out.println("Conexión a base de datos "+bd+" OK\n");
        }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void desconectar(){
        connection = null;
    }


}

