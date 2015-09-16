/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.idf;

import java.util.Arrays;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

/**
 *
 * @author manhc
 */
public class SparkIdf {
    public void CalculatorIdf(String inputFile, String outputFile) {
        SparkConf conf = new SparkConf().setAppName("IDF");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        JavaRDD<String> input = sc.textFile(inputFile);
        
        JavaRDD<String> words = input.flatMap(
            new FlatMapFunction<String, String>() {
                @Override
                public Iterable<String> call(String x) {
                    return Arrays.asList(x.split(" "));
                }
            }
        );
        
        JavaPairRDD<String, Integer> counts = words.mapToPair(
            new PairFunction<String, String, Integer>() {
                @Override
                public Tuple2<String, Integer> call(String x) {
                    return new Tuple2(x, 1);
                }
            }
        ).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer x, Integer y) {
                return x + y;
            }
        });
        
        counts.saveAsTextFile(outputFile);
    }
}
