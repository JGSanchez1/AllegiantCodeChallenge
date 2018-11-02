package com.allegiant;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class managerAndReaderUsageTest {

    @Test
    public void testAll() throws IOException
    {
        WidgetReader readTest = new WidgetReader();
        WidgetManager testWidgetManager = new WidgetManager();
        System.out.println();
        System.out.println("Testing readFile method:");
        System.out.println();
        // open file and read
        InputStream is = new FileInputStream("C:\\Users\\Joshua\\CS Classes\\allegiant\\AllegiantCodeChallenge-master\\widgets\\resources\\widgets.JSON"); //localfile
        List<Widget> widgetList = readTest.readFile(is);
        is.close();

        // insert list into manager

        Iterator<Widget> widgetIterator1= widgetList.iterator();
        while(widgetIterator1.hasNext())
        {
            Widget currWidget = widgetIterator1.next();
            testWidgetManager.add(currWidget);
        }

        //check widgets
        widgetIterator1=widgetList.iterator();
        while(widgetIterator1.hasNext())
        {
            Widget currWidget = testWidgetManager.get(widgetIterator1.next().getId());
            System.out.println("Printing widget: id,title,description, sprocket size");
            System.out.println("Widget ID: " + currWidget.getId()+ ", Title: "+ currWidget.getTitle()+", Description: "
                    + currWidget.getDescription()+", Sprocket list size: " +currWidget.getSprockets().size());
            System.out.println();
        }


        //add new widgets,update, sprockets,etc for sort/writefile testing
        Widget widgetBetaUpdate = new Widget(2);
        widgetBetaUpdate.setDescription("Two Updated!");
        widgetBetaUpdate.setTitle("Beta Updated!");
        testWidgetManager.update(widgetBetaUpdate);

        Widget widgetAlphaUpdate = new Widget(1);
        widgetAlphaUpdate.setDescription("One Updated!");
        widgetAlphaUpdate.setTitle("Alpha Updated!");
        testWidgetManager.update(widgetAlphaUpdate);

        //create a sprocket
        Sprocket sprocketTest = new Sprocket();
        Sprocket sprocketTestBeta = new Sprocket();
        Sprocket sprocketTestCharlie = new Sprocket();

        //create a color
        Color colorTest = new Color(53,189,212);
        Color colorTest2 = new Color(52,199,155);
        Color colorTest3 = new Color(15,152,200);
        Color colorTestAlpha = new Color(34,210,211);

        //set sprocket values
        sprocketTest.setId(1);
        sprocketTest.setColor(colorTest);
        sprocketTest.setPrice(25.25);

        sprocketTestBeta.setId(2);
        sprocketTestBeta.setColor(colorTest2);
        sprocketTestBeta.setPrice(22.53);

        sprocketTestCharlie.setId(3);
        sprocketTestCharlie.setColor(colorTest3);
        sprocketTestCharlie.setPrice(50.15);


        Sprocket sprocketTestAlpha = new Sprocket();
        //set sprocket values
        sprocketTestAlpha.setId(1);
        sprocketTestAlpha.setColor(colorTestAlpha);
        sprocketTestAlpha.setPrice(25.25);

        //add sprockets to manager
        testWidgetManager.addSprocket(1,sprocketTestAlpha);
        testWidgetManager.addSprocket(1,sprocketTest);
        testWidgetManager.addSprocket(1,sprocketTestBeta);
        testWidgetManager.addSprocket(2,sprocketTestCharlie);

        //read in more widgets
        is = new FileInputStream("C:\\Users\\Joshua\\CS Classes\\allegiant\\AllegiantCodeChallenge-master\\widgets\\resources\\widgets2.JSON"); //local file
        List<Widget> widgetList2 = readTest.readFile(is);
        is.close();

        //print both lists,but find them within the manager
        System.out.println();
        System.out.println("Testing manager sort lists; writing sorted list to json files:");
        System.out.println();
        List<Widget> combinedList = new ArrayList<>();
        Iterator<Widget> widgetIterator2= widgetList2.iterator();
        while(widgetIterator2.hasNext())
        {
            Widget currWidget = widgetIterator2.next();
            testWidgetManager.add(currWidget);
        }
        combinedList.addAll(widgetList);
        combinedList.addAll(widgetList2);
        System.out.println("Printing combined lists from manager:");
        System.out.println();


        Iterator<Widget> combinedIterator = combinedList.iterator();
        while(combinedIterator.hasNext())
        {
            Widget currWidget = testWidgetManager.get(combinedIterator.next().getId());
            System.out.println("Printing widget: id,title,description, sprocket size");
            System.out.println("Widget ID: " + currWidget.getId()+ ", Title: "+ currWidget.getTitle()+", Description: "
                    + currWidget.getDescription()+", Sprocket list size: " +currWidget.getSprockets().size());
            System.out.println();
        }

        System.out.print("Writing to files: Before Sort & Title Sort & Sat Sort");
        //write to file combined list

        OutputStream outTest3 = new FileOutputStream("C:\\Users\\Joshua\\CS Classes\\allegiant\\AllegiantCodeChallenge-master\\widgets\\resources\\widgetsBeforeSort.JSON");
        readTest.writeFile(outTest3,combinedList);
        outTest3.close();
        //write to file title sorted
        List<Widget> titleSortedList = testWidgetManager.sortByTitle(combinedList);
        OutputStream outTest = new FileOutputStream("C:\\Users\\Joshua\\CS Classes\\allegiant\\AllegiantCodeChallenge-master\\widgets\\resources\\widgetsSortTitle.JSON");
        readTest.writeFile(outTest,titleSortedList);
        outTest.close();
        //write to file sat sorted
        List<Widget> satSortedList = testWidgetManager.sortBySaturation(combinedList);
        OutputStream outTest2 = new FileOutputStream("C:\\Users\\Joshua\\CS Classes\\allegiant\\AllegiantCodeChallenge-master\\widgets\\resources\\widgetsSortSat.JSON");
        readTest.writeFile(outTest2,satSortedList);
        outTest2.close();
    }
}
