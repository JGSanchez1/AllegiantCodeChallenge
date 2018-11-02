package tests;

import com.allegiant.Widget;
import com.allegiant.WidgetReader;
import org.junit.Test;

import java.io.*;
import java.util.Iterator;
import java.util.List;


public class WidgetReaderTest {

    @Test
    public void readFile() throws IOException {

        WidgetReader readTest = new WidgetReader();
        System.out.println("Testing readFile method:");
        System.out.println();
        // open file
        InputStream is = new FileInputStream("C:\\Users\\Joshua\\CS Classes\\allegiant\\AllegiantCodeChallenge-master\\widgets\\resources\\widgets.JSON");
        // read file
        List<Widget> WidgetList = readTest.readFile(is);

        // close
        is.close();
        Iterator<Widget> widgetIterator= WidgetList.iterator();
        while(widgetIterator.hasNext())
        {
            Widget currWidget = widgetIterator.next();
            System.out.println("Printing widget: id,title,description, sprocket size");
            System.out.println("Widget ID: " + currWidget.getId()+ ", Title: "+ currWidget.getTitle()+", Description: "
                    + currWidget.getDescription()+" Sprocket list size: " +currWidget.getSprockets().size());
            System.out.println();
        }
        System.out.println("Widget list size from readFile: "+ WidgetList.size());
    }

    @Test
    public void writeFile() throws IOException {
        WidgetReader readTest = new WidgetReader();
        InputStream is = new FileInputStream("C:\\Users\\Joshua\\CS Classes\\allegiant\\AllegiantCodeChallenge-master\\widgets\\resources\\widgets2.JSON");
        // read file
        List<Widget> WidgetTest = readTest.readFile(is);
        // close
        is.close();
        System.out.println();
        System.out.println("Testing writeFile method:");
        System.out.println();
        System.out.println("Writefile Widget list size is "+ WidgetTest.size());
        //output test
        OutputStream outTest = new FileOutputStream("C:\\Users\\Joshua\\CS Classes\\allegiant\\AllegiantCodeChallenge-master\\widgets\\resources\\widgetsWriteTest.JSON");
        readTest.writeFile(outTest,WidgetTest);
        outTest.close();
    }



}