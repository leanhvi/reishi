/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.word2vector;

import java.sql.SQLException;

/**
 *
 * @author manhc
 */
public class App {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(App.class);
    public static void main(String[] args) {
        Word2Vector w2v = new Word2Vector();
        try {
            w2v.createVocabulary();
        } catch (ClassNotFoundException ex) {
            logger.error(ex);
        } catch (SQLException ex) {
            logger.error(ex);
        }
        try {
            w2v.initVectorToDb();
        } catch (ClassNotFoundException ex) {
            logger.error(ex);
        } catch (SQLException ex) {
            logger.error(ex);
        }
        System.out.println("Completed.");
    }
}
