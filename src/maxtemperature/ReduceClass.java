/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtemperature;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author s530668
 */
public class ReduceClass extends Reducer<Text, IntWritable, Text, IntWritable>{

    private int max_temp = Integer.MIN_VALUE;
    private int temp = 0;

    /**
     *
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, 
            Context context)
            throws IOException, InterruptedException {

        Iterator<IntWritable> itr = values.iterator();

        while (itr.hasNext()) {

            temp = itr.next().get();
            if( temp > max_temp)
            {
                max_temp = temp;
            }
        }

        context.write(key, new IntWritable(max_temp));
    }
}
