/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.cluster;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manhc
 */
public class Vector {
    public List<Node> nodes = new ArrayList<>();
    
    public double getModule() {
        double sum = 0;
        for(int i = 0; i < nodes.size(); ++i) {
            double temp = nodes.get(i).value;
            sum += temp * temp;            
        }
        sum = Math.sqrt(sum);
        return sum;
    }
    
    public void printVector() {
        for(int i = 0; i < nodes.size(); ++i) {
            System.out.print(" " + nodes.get(i).index);
        }
        System.out.println();
        for(int i = 0; i < nodes.size(); ++i) {
            System.out.print(" " + nodes.get(i).value);
        }
    }
    
    public static double cosine(Vector v1, Vector v2) {
        double result = 0;
        try {
            result = Vector.scalar(v1, v2) / (v1.getModule() * v2.getModule());
        }
        catch(IllegalArgumentException ex) {
            throw ex;
        }
        return result;
    } 
    
    public static Vector calculatorCentroid(List<Vector> sample) {
        Vector result = new Vector();
        for(int i = 0; i < sample.size(); ++i) {
            for(int j = 0; j < sample.get(i).nodes.size(); ++j) {
                int k = containVector(result, sample.get(i).nodes.get(j).index);
                if(k > -1) {
                    result.nodes.get(k).value = result.nodes.get(k).value + sample.get(i).nodes.get(j).value;
                }
                else {
                    Node node = new Node();
                    node.index = sample.get(i).nodes.get(j).index;
                    node.value = sample.get(i).nodes.get(j).value;
                    result.nodes.add(node);
                }
            }
        }
        return null;
    }
    
    public static int containVector(Vector vector, int index) {
        for(int i = 0; i < vector.nodes.size(); ++i) {
            if(vector.nodes.get(i).index == index) {
                return i;
            }
        }
        return -1;
    }
    
    public static double scalar(Vector v1, Vector v2) {
        double result = 0;
        int i1 = 0;
        int i2 = 0;
        while(i1 < v1.nodes.size() && i2 < v2.nodes.size()) {
            if(v1.nodes.get(i1).index == v2.nodes.get(i2).index) {
                result += v1.nodes.get(i1).value * v2.nodes.get(i2).value;
                ++i1;
                ++i2;
                continue;
            }
            
            if(v1.nodes.get(i1).index < v2.nodes.get(i2).index) {
                ++i1;
                continue;
            }
            
            if(v1.nodes.get(i1).index > v2.nodes.get(i2).index) {
                ++i2;
                continue;
            }
            
        }
        return result;
    }
}
