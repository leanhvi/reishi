/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.idf;

/**
 *
 * @author manhc
 */
public class App {
    public static void main(String[] args) {
        SparkIdf sparkIdf = new SparkIdf();
        sparkIdf.calculatorIdf(args[0], args[1]);
        System.out.println("Done");
    }
}