package com.mrjson.hadoop.MapReduce;

import java.io.IOException;
//import java.util.Formatter;
//import java.util.Locale;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//import org.json;

import com.cloudera.com.amazonaws.util.json.JSONException;
//import com.cloudera.com.amazonaws.util.json.JSONException;
import com.cloudera.com.amazonaws.util.json.JSONObject;
public class WordCountMapper extends
        Mapper<Object, Text, Text, IntWritable> {
 
 //   private final IntWritable ONE = new IntWritable(1);
 //   private Text word = new Text();
	 public void map(Object key, Text value, Context context)
	            throws IOException, InterruptedException {
	    		//StringBuilder sb = new StringBuilder();
	    		//Formatter formatter = new Formatter(sb, Locale.US);
	    		
		// 		String[] fields = {"date", "type", "id", "user"};
	            String formatted = value.toString().replaceAll("\n", " ");
	            String out = "";
	            try {
	            	
					JSONObject jObject = new JSONObject(formatted);
					//d = jObject.getString("date");
					out = String.format("%1$2s,%2$2s,%3$2s,%4$2s", jObject.getString("id"), jObject.getString("type"), jObject.getString("user"), jObject.getString("date")) ;
				   
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					out = "error in json input" + e.toString();
					e.printStackTrace();
				}
	            //String out = "";
	            
	            // emit the tuple and the original contents of the line
	            
	           // context.write(new Text(out), null);
	            context.write(new Text(out), null);
	       // }
	    }

}
