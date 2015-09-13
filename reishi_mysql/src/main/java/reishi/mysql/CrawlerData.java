/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import reishi.dataobjects.CrawlerDataObj;

/**
 *
 * @author manhc
 */
public class CrawlerData {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    
    public int CrawlerDataInser(CrawlerDataObj obj) throws ClassNotFoundException, SQLException {
        connect = MySqlConnectionSingleton.getInstance().getConnection();       
        String sproc = "{call CrawlerData_Insert(?,?,?,?,?)}";
        CallableStatement cs = connect.prepareCall(sproc);
        cs.setString("sources", obj.source);
        cs.setString("contents", obj.contents);        
        cs.setString("url", obj.url);
        cs.setString("wordSegmented", obj.wordSegmented);
        cs.setString("raw", obj.raw);
        try {
            cs.execute();
        }
        finally {
            //close();
        }            
        return 0;
    }
    
    public void close() {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(connect != null) {
                connect.close();
            }
        }
        catch(Exception ex) {
            
        }
    }
}
