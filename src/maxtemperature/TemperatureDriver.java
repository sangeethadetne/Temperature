/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtemperature;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
 
/**
 *
 * @author s530668
 */
public class TemperatureDriver {
    public static void main(String[] args) throws Exception {
 
        // Create a new job
        Configuration conf = new Configuration();
         Job job = Job.getInstance(conf);
        // Set job name to locate it in the distributed environment
        job.setJarByClass(TemperatureDriver.class);
// job.setJobName("Max Temperature");
 
        // Set input and output Path, note that we use the default input format
        // which is TextInputFormat (each record is a line of input)
       FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
 
        // Set Mapper and Reducer class
        job.setMapperClass(MapClass.class);
// job.setCombinerClass(ReduceClass.class);
        job.setReducerClass(ReduceClass.class);
 
        // Set Output key and value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
 
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}