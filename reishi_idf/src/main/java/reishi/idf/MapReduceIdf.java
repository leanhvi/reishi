/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.idf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author manhc
 */
public class MapReduceIdf {
    public static int threshold = 0;
    public static class IdfMapper extends Mapper<Object, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        
        private Text word = new Text();
        
        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            
            List<String> strs = new ArrayList<String>();
            while (itr.hasMoreTokens()) {  
                String temp = itr.nextToken();
                if(!strs.contains(temp)) {
                    strs.add(temp);
                    word.set(temp);
                    context.write(word, one);
                }
            }
        }
    }
    
    public static class IdfReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();
        
        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for(IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            if(!(sum < threshold)) {
                context.write(key, result);
            }
        }
    }
    
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        threshold = Integer.parseInt(args[2]);
        Configuration conf = new Configuration();
        
        Job job = new Job(conf, "Calculator Idf");
        
        job.setJarByClass(MapReduceIdf.class);
        job.setMapperClass(IdfMapper.class);
        job.setCombinerClass(IdfReducer.class);
        job.setReducerClass(IdfReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
