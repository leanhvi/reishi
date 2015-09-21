/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.svm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_problem;
import org.apache.log4j.Logger;

/**
 *
 * @author manhc
 */
public class Svm {    
    private static Logger logger = Logger.getLogger(Svm.class);
    
    private double[] y;
    private svm_node[][] x;
    private int l;
    private List<String> sLines = new ArrayList<>();    
    
    private double[] yTest;
    private svm_node[][] xTest;
    private int lTest;
    private List<String> sLinesTest = new ArrayList<>();    
    
    
    
    public void readDataTraning(String fileName) {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));
            
            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                sLines.add(sCurrentLine);
            }
            l = sLines.size();
            y = new double[l];
            x = new svm_node[l][];
            
            for(int i = 0; i < sLines.size(); ++i) {
                String[] sLineSplit = sLines.get(i).split(" ");
                y[i] = Double.parseDouble(sLineSplit[0]);
                x[i] = new svm_node[sLineSplit.length - 1];
                for(int j = 1; j < sLineSplit.length; ++j) {
                    String[] valuesString = sLineSplit[j].split(":");
                    svm_node node = new svm_node();
                    node.index = Integer.parseInt(valuesString[0]);
                    node.value = Double.parseDouble(valuesString[1]);
                    x[i][j - 1] = node;
                }
            }
        } 
        catch (IOException e) {
            logger.error(e);
        } 
        finally {
            try {
                if (br != null)br.close();
            } 
            catch (IOException ex) {
                logger.error(ex);
            }
        }
    }
    
    public void readDataTest(String fileName) {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));
            
            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                sLinesTest.add(sCurrentLine);
            }
            lTest = sLinesTest.size();
            yTest = new double[l];
            xTest = new svm_node[l][];
            
            for(int i = 0; i < sLinesTest.size(); ++i) {
                String[] sLineSplit = sLinesTest.get(i).split(" ");
                yTest[i] = Double.parseDouble(sLineSplit[0]);
                xTest[i] = new svm_node[sLineSplit.length - 1];
                for(int j = 1; j < sLineSplit.length; ++j) {
                    String[] valuesString = sLineSplit[j].split(":");
                    svm_node node = new svm_node();
                    node.index = Integer.parseInt(valuesString[0]);
                    node.value = Double.parseDouble(valuesString[1]);
                    xTest[i][j - 1] = node;
                }
            }
        } 
        catch (IOException e) {
            logger.error(e);
        } 
        finally {
            try {
                if (br != null)br.close();
            } 
            catch (IOException ex) {
                logger.error(ex);
            }
        }
    }
    
    private svm_node[] string2Node(String str) {
        
        String[] sLineSplit = str.split(" ");
        svm_node[] result = new svm_node[sLineSplit.length - 1];
        for(int i = 1; i < sLineSplit.length; ++i) {
            String[] valuesString = sLineSplit[i].split(":");
            svm_node node = new svm_node();
            node.index = Integer.parseInt(valuesString[0]);
            node.value = Double.parseDouble(valuesString[1]);       
            result[i - 1] = node;
        }
        return result;               
    }
    
    public double test(svm_node[] nodes, svm_model model, int[] labels) {             
        int totalClasses = labels.length;               
        svm.svm_get_labels(model,labels);

        double[] prob_estimates = new double[totalClasses];
        double v = svm.svm_predict_probability(model, nodes, prob_estimates);

        for (int i = 0; i < totalClasses; i++){
            System.out.print("(" + labels[i] + ":" + prob_estimates[i] + ")");
        }
        System.out.println("(Prediction:" + v + ")");            
                    
        return v;
    }
    
    public svm_model svmTraining() {
        svm_problem prob = new svm_problem();
        prob.y = y;
        prob.x = x;
        prob.l = l;
        
        svm_parameter param = new svm_parameter();
        param.probability = 1;
        param.gamma = 0.5;
        param.nu = 0.5;
        param.C = 1;
        param.svm_type = svm_parameter.C_SVC;
        param.kernel_type = svm_parameter.LINEAR;       
        param.cache_size = 20000;
        param.eps = 0.001;
        
        svm_model model = svm.svm_train(prob, param);
        return model;
    }
}
