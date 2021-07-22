/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class AcessoDB {

    Connection con;
    private Statement stm = null;
    String url,user,password;
    
    public AcessoDB() {
        //this.url = "jdbc:mysql://127.0.0.1/sc";
    }

    public AcessoDB(String DB) {
        this.url = DB;
    }

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //con = DriverManager.getConnection("jdbc:mysql://192.168.0.102/sc?user=root&password=");
            //quando roda em profgeraldo
            //url = "jdbc:mysql://127.0.0.1/sc";
            //user = "root";
            user = "newuser";
            //password = "";
            password = "password";
            con = DriverManager.getConnection(url + "?user=" + user + "&password=" + password);
            //con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/sc?user=root&password=");
            // quado roda de mastergg
            //con = DriverManager.getConnection("jdbc:mysql://192.168.1.13/test?user=MasterGG&password=mastergg01");

        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possível encontrar o Driver!");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível conectar ao banco!");
            //JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco!","Atenção",WIDTH);
        }
        return con;
    }

    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void executaSqlUpdate(String sql) {
        conectar();
        try {
            stm = con.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao Executar SQL Update" + e);
        }
    }
}
