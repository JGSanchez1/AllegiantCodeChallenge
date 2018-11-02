package com.allegiant;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
// use gson library
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.InputStreamReader;

public class WidgetReader {

    /**
     *
     * Reads data from the given input stream and attempts to parse it as a List of com.allegiant.Widget Objects, including their underlying sprockets.
     * @throws IOException
     */
    public static List<Widget> readFile(InputStream fileIn) throws IOException {
    //create Gson jsonReader to read inputsream type
       JsonReader readFile = new JsonReader(new InputStreamReader(fileIn,"UTF-8"));
        Gson gson = new Gson();
        List<Widget> widgetList = new ArrayList<>();
       Reader readData = new InputStreamReader(fileIn);

       readFile.beginArray();
       //create objects from file
        while(readFile.hasNext()){
            Widget newWidget = gson.fromJson(readFile, Widget.class); // create widget object
            widgetList.add(newWidget);
        }
        readFile.endArray();
        readFile.close();

        return widgetList;


    }

    /**
     * Writes the given list of com.allegiant.Widget objects to the given outputStream
     * @throws IOException
     */
    public void writeFile(OutputStream fileOut, List<Widget> widgets) throws IOException {
        //gson object to convert widget objects to json format

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // write opening bracket
        fileOut.write('[');
        fileOut.write('\n');
        Iterator<Widget> widgetIterator = widgets.iterator();
        while(widgetIterator.hasNext())
        {   Widget widgetOut = widgetIterator.next();
            String json = gson.toJson(widgetOut);//convert to json string
            fileOut.write(json.getBytes()); // write to json string to file
            if(widgetIterator.hasNext())
            {
                fileOut.write(','); //write comma seperator for next object
            }
        }
        //write closing bracket
        fileOut.write('\n');
        fileOut.write(']');
    }

    public static void main (String[] args) throws IOException{
        InputStream in = new FileInputStream("....");
        List<Widget> results = readFile(in);
        System.out.println(results.size());
    }


}
