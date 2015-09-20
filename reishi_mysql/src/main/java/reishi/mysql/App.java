/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.log4j.Logger;
import reishi.dataobjects.CrawlerDataObj;
import reishi.objects.vocabulary.Word;

/**
 *
 * @author manhc
 */
public class App {
    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static Logger logger = Logger.getLogger(App.class);
        
    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
        /*
        try {
            
            connect = MySqlConnectionSingleton.getInstance().getConnection();
            
            statement = connect.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM crawler.DataCrawlers where Id = 10");
            while(resultSet.next()) {
                System.out.println(resultSet.getString("Id"));
            }            
        }
        catch (ClassNotFoundException ex) {
            logger.error(ex);
        } 
        catch (SQLException ex) {
            logger.error(ex);
        }
        finally{
            close();
        }                
        */
        CrawlerData cr = new CrawlerData();
        CrawlerDataObj obj = cr.crawlerDataGetById(1);
        boolean rs = cr.crawlerDataUpdateVector(1, "Linh Chi");
        System.out.println(obj.wordSegmented);
        System.out.println(rs + " Chi");
        List<Word> vc = cr.wordsGetAll();
        System.out.println(vc.size() + " Chi");
        System.out.println(vc.get(1).word + " - " + vc.get(1).idf);
    }
    
    public static void close() {
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
        catch(SQLException e) {
            logger.error(e);
        }
    }
}
