package com.mrjson.hadoop.MapReduce;

import java.io.IOException;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import com.cloudera.com.amazonaws.util.json.JSONException;

import com.cloudera.com.amazonaws.util.json.JSONObject;
public class WordCountMapper extends
        Mapper<Object, Text, Text, IntWritable> {
 

	 public void map(Object key, Text value, Context context)
	            throws IOException, InterruptedException {

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
	            
	            
	            // emit the tuple and the original contents of the line
	            context.write(new Text(out), null);
	       // }
	    }

}
