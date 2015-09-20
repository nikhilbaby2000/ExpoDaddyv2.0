package com.apps.nikhil.expodaddyv20;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Nikhil on 20-09-2015.
 */
public class FileReaderWriterClass {

    private FileWriter fileWriter;

    public void writeToFile(Context context, String value){
        try{
            File file = new File("/storage/sdcard0/ExpoDaddy/user.json");

            if (file.createNewFile()){
                //Toast.makeText(context, "File is created!", Toast.LENGTH_SHORT).show();
            }else{
                //Toast.makeText(context, "File already Exists.", Toast.LENGTH_SHORT).show();
            }
            fileWriter = new java.io.FileWriter("/storage/sdcard0/ExpoDaddy/user.json");
            fileWriter.write(value);
            fileWriter.flush();
            fileWriter.close();
            //Toast.makeText(context, "We have a successfull write: Data: [ " + value + " ]", Toast.LENGTH_LONG ).show();
        }
        catch (IOException io){
            Toast.makeText(context, "Error: File cannot be created/modified: " + io.toString(), Toast.LENGTH_SHORT ).show();
        }
    }

    public JSONObject readFromFile(String path ){
        return  null;
    }
}
