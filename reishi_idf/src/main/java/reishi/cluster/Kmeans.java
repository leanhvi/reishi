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
public class Kmeans {
    public List<Cluster> kmeansCluster(List<Vector> sample, int k) {
        List<Vector> centroids = initCentroids(sample, k);
        
        return null;
    }
    
    public List<Vector> initCentroids(List<Vector> sample, int k) {
        if(k > sample.size()) {
            return null;
        }
        List<Vector> result = new ArrayList<>();
        for(int i = 0; i < k; ++i) {
            result.add(sample.get(i));
        }
        return result;
    }
}
