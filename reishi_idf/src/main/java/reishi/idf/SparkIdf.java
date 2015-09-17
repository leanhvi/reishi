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
    public void calculatorIdf(String inputFile, String outputFile) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("IDF");
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println(inputFile);
        JavaRDD<String> input = sc.textFile(inputFile);
        System.out.println(inputFile);
        JavaRDD<String> words;
        words = input.flatMap(
                new FlatMapFunction<String, String>() {                                       
                    @Override
                    public Iterable<String> call(String t) throws Exception {
                        return Arrays.asList(t.split(" "));
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
