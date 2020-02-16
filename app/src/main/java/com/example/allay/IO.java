package com.example.allay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class IO {

    public void saveData(String fileName, File path, String key, String text) throws Exception {
        try{

            // TODO Have to remove # on key and text

            File file = new File(path, fileName);
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(key);
            bw.write('#');
            bw.write(text);
            bw.write('\n');
            bw.close();
            fw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void saveData(String fileName, File path, String key, String text, boolean append) throws Exception {
        try{

            File file = new File(path, fileName);
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), append);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(key);
            bw.write('#');
            bw.write(text);
            bw.write('\n');
            bw.close();
            fw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void saveData(String fileName, File path, String text) throws Exception {
        try{

            File file = new File(path, fileName);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(text);
            bw.close();
            fw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void readData(File path, String text) throws Exception {
        BufferedReader objReader = null;
        try{

            // TODO Have to remove # on key and text
            objReader = new BufferedReader(new FileReader(path));

            String line;
            while((line = objReader.readLine()) != null){
                text = line;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                if(objReader != null){
                    objReader.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }

    }

    public String readDataReturnString(File path, String text) throws Exception {
        BufferedReader objReader = null;
        String line = "";
        try{

            // TODO Have to remove # on key and text
            objReader = new BufferedReader(new FileReader(path));


            while((line = objReader.readLine()) != null){
                return line;
            }


        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                if(objReader != null){
                    objReader.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }

            return line;
        }

    }


    public void readDataStringKey(String path, HashMap<String, String> map) throws Exception {
        BufferedReader objReader = null;
        try{

            // TODO Have to remove # on key and text
            objReader = new BufferedReader(new FileReader(path));

            String line;
            while((line = objReader.readLine()) != null){
                String[] tokens = line.split("#");
                map.put(tokens[0], tokens[1]);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                if(objReader != null){
                    objReader.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }

    }

    public void readDataIntegerKey(String path, HashMap<Integer, String> map) throws Exception {
        BufferedReader objReader = null;
        try{

            // TODO Have to remove # on key and text
            objReader = new BufferedReader(new FileReader(path));

            String line;
            while((line = objReader.readLine()) != null){
                String[] tokens = line.split("#");
                map.put(Integer.parseInt(tokens[0]), tokens[1]);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                if(objReader != null){
                    objReader.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }

    }

    public boolean containsIllegalChars(String word){
        for(int i = 0; i < word.length(); ++i){
            if(word.charAt(i) == '#'){
               return true;
            }
        }

        return false;
    }
}
