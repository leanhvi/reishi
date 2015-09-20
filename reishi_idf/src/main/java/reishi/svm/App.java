/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.svm;

/**
 *
 * @author manhc
 */
public class App {
    public static void main(String[] args) {
        Svm svm = new Svm();
        svm.readDataTraning("D:\\svmdata.txt");
        svm.svmTraining();
    }
}
