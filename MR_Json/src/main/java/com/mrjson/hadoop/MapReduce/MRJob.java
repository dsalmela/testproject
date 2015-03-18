package com.mrjson.hadoop.MapReduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MRJob {

    public static void main(String[] args) throws IOException,
            InterruptedException, ClassNotFoundException {
 
        Path inputPath = new Path(args[0]);
        Path outputDir = new Path(args[1]);
        System.out.println("Starting.... NOWWW!");
        // Create configuration
        Configuration conf = new Configuration(true);
        
        // Create job
        Job job = new Job(conf, "WordCount");
        job.setJarByClass(WordCountMapper.class);
 
        // Setup MapReduce
        job.setMapperClass(WordCountMapper.class);
      //  job.setReducerClass(WordCountReducer.class);
      //  job.setNumReduceTasks(1);
 
        // Specify key / value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
 
        // Input
        FileInputFormat.addInputPath(job, inputPath);
        // job.setInputFormatClass(TextInputFormat.class);
        
        
        // These Three lines change the Input Format
        job.setNumReduceTasks(0);
      //   job.setInputFormatClass(MultiLineJsonInputFormat.class);
        job.setInputFormatClass(SDFInputFormat.class);
       //  MultiLineJsonInputFormat.setInputJsonMember(job, "type");
        
        // Output
        FileOutputFormat.setOutputPath(job, outputDir);
       //x  job.setOutputFormatClass(TextOutputFormat.class);
        System.out.println("FINISHING.... NOWWW!");
        // Delete output if exists. Smart
        FileSystem hdfs = FileSystem.get(conf);
        if (hdfs.exists(outputDir))
            hdfs.delete(outputDir, true);
 
        // Execute job
        int code = job.waitForCompletion(true) ? 0 : 1;
        System.exit(code);
 
    }
}
