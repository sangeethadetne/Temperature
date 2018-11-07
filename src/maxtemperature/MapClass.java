/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtemperature;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
 
/**
 *
 * @author s530668
 */
public class MapClass extends Mapper<LongWritable, Text, Text, IntWritable> {
 
    Text k= new Text();
 
    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
 
         String line = value.toString();
 
                //StringTokenizer is breaking the record (line) according to the delimiter whitespace
                StringTokenizer tokenizer = new StringTokenizer(line," ");
                //Iterating through all the tokens and forming the key value pair
                while (tokenizer.hasMoreTokens()) {
 
                //The first token is going in year variable of type string
             String year= tokenizer.nextToken();
             k.set(year);
 
             //Takes next token and removes all the whitespaces around it and then stores it in the string variable called temp
             String temp= tokenizer.nextToken().trim();
 
             //Converts string temp into integer v
             int v = Integer.parseInt(temp);
 
             //Sending to output collector which inturn passes the same to reducer
                context.write(k,new IntWritable(v));
        }
    }
}