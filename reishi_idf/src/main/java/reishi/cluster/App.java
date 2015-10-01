/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.cluster;

import java.util.ArrayList;

/**
 *
 * @author manhc
 */
public class App {
    public static void main(String[] args) {
        Vector v1 = new Vector();
        Vector v2 = new Vector();
        
        v1.nodes = new ArrayList<>();
        for(int i = 0; i < 10;) {
            Node node = new Node();
            node.index = i;
            node.value = i;
            v1.nodes.add(node);
            i = i + 2;
        }
        
        v2.nodes = new ArrayList<>();
        for(int i = 0; i < 10;) {
            Node node = new Node();
            node.index = i;
            node.value = i;
            v2.nodes.add(node);
            i = i + 3;
        }              
        
        v1.printVector();
        System.out.println("");
        v2.printVector();
        System.out.println("");
        System.out.println(v1.getModule());
        System.out.println(v2.getModule());
        System.out.println("result: " + Vector.cosine(v1, v2));
    }
}
