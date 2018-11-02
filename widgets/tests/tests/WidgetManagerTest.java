package tests;

import com.allegiant.Color;
import com.allegiant.Sprocket;
import com.allegiant.Widget;
import com.allegiant.WidgetManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WidgetManagerTest {

    //add, get, delete,
    @Test
    public void testAddGetDelete(){
        WidgetManager testWidgetManager = new WidgetManager();
        System.out.println();
        System.out.println("Add and Get and Delete methods  test:");
        System.out.println();
        Widget widgetAlpha = new Widget(1);
        widgetAlpha.setDescription("Widget One");
        widgetAlpha.setTitle("Alpha");
        testWidgetManager.add(widgetAlpha);

        Widget widgetBeta = new Widget(2);
        widgetBeta.setDescription("Widget Two");
        widgetBeta.setTitle("Beta");
        testWidgetManager.add(widgetBeta);

        Widget widgetCharlie = new Widget(3);
        widgetCharlie.setDescription("Widget Three");
        widgetCharlie.setTitle("Charlie");
        testWidgetManager.add(widgetCharlie);


        Widget testWidgetAlpha = testWidgetManager.get(1);
        Widget testWidgetBeta = testWidgetManager.get(2);
        Widget testWidgetCharlie = testWidgetManager.get(3);

        //check if exist
        assertEquals(testWidgetAlpha.getId(),1);


        //delete
        testWidgetManager.delete(testWidgetAlpha);
        //look for deleted and see if return null (null=not in list, thus deleted)
        assertNull(testWidgetManager.get(1));
        //see if others still exist in list
        assertEquals(testWidgetBeta.getId(),2);
        assertEquals(testWidgetCharlie.getId(),3);
    }

    @Test
    public void updateAndAddSprocketTest(){
        Widget widgetAlpha = new Widget(1);
        WidgetManager testWidgetManager = new WidgetManager();
        widgetAlpha.setDescription("Widget One");
        widgetAlpha.setTitle("Alpha");
        testWidgetManager.add(widgetAlpha);

        Widget widgetAlphaUpdate = new Widget(1);
        widgetAlphaUpdate.setDescription("Widget One Update");
        widgetAlphaUpdate.setTitle("Alpha Update");

        Sprocket sprocketTest = new Sprocket();
        testWidgetManager.update(widgetAlphaUpdate);

        //create a color
        Color colorTest = new Color(53,189,212);
        //set sprocket values
        sprocketTest.setId(1);
        sprocketTest.setColor(colorTest);
        sprocketTest.setPrice(25.25);
        testWidgetManager.addSprocket(widgetAlpha.getId(),sprocketTest);
        System.out.println("Widget Alpha Title,Descpriotion, sprocket list size:");
        System.out.println(widgetAlpha.getTitle());
        System.out.println(widgetAlpha.getDescription());
        System.out.println(widgetAlpha.getSprockets().size());


    }
    @Test
    public void getPriceTest(){
        Widget widgetAlpha = new Widget(1);
        WidgetManager testWidgetManager = new WidgetManager();
        widgetAlpha.setDescription("Widget One");
        widgetAlpha.setTitle("Alpha");
        testWidgetManager.add(widgetAlpha);
        Sprocket sprocketTest = new Sprocket();
        Sprocket sprocketTest2 = new Sprocket();

        //create a color
        Color colorTest = new Color(53,189,212);
        //set sprocket values
        sprocketTest.setId(1);
        sprocketTest.setColor(colorTest);
        sprocketTest.setPrice(25.25);
        testWidgetManager.addSprocket(widgetAlpha.getId(),sprocketTest);

        //create a color
        Color colorTest2 = new Color(53,189,212);
        //set sprocket values
        sprocketTest2.setId(2);
        sprocketTest2.setColor(colorTest);
        sprocketTest2.setPrice(12.53);
        testWidgetManager.addSprocket(widgetAlpha.getId(),sprocketTest2);

        double testResult = testWidgetManager.getTotalPrice(widgetAlpha.getId());
        System.out.println("Hard coded math is 37.78 and getTotalPrice method calculated "+testResult);

    }
    @Test
    //tests all methods at the same time, includes search and sort methods that do not have their own individual test to allow more robust testing
    public void testAll() {
        //create widgets manually to avoid WidgetReader complications
        WidgetManager testWidgetManager = new WidgetManager();
        System.out.println();
        System.out.println("Add and Get and Delete methods  test:");
        System.out.println();
        Widget widgetAlpha = new Widget(1);
        widgetAlpha.setDescription("Widget One");
        widgetAlpha.setTitle("Alpha");
        testWidgetManager.add(widgetAlpha);

        Widget widgetBeta = new Widget(2);
        widgetBeta.setDescription("Widget Two");
        widgetBeta.setTitle("Beta");
        testWidgetManager.add(widgetBeta);

        Widget widgetCharlie = new Widget(3);
        widgetCharlie.setDescription("Widget Three");
        widgetCharlie.setTitle("Charlie");
        testWidgetManager.add(widgetCharlie);


        Widget testWidgetAlpha = testWidgetManager.get(1);
        Widget testWidgetBeta = testWidgetManager.get(2);
        Widget testWidgetCharlie = testWidgetManager.get(3);

        //check
        assertEquals(testWidgetAlpha.getId(),1);
        //double check title
        System.out.println(testWidgetAlpha.getTitle());
        System.out.println(testWidgetBeta.getTitle());
        System.out.println(testWidgetCharlie.getTitle());

        // double check description
        System.out.println(testWidgetAlpha.getDescription());
        System.out.println(testWidgetBeta.getDescription());
        System.out.println(testWidgetCharlie.getDescription());

        //delete
        testWidgetManager.delete(testWidgetAlpha);
        //look for deleted and see if return null (null=not in list, thus deleted)
        assertNull(testWidgetManager.get(1));
        //see if others still exist in list
        assertEquals(testWidgetBeta.getId(),2);
        assertEquals(testWidgetCharlie.getId(),3);

        System.out.println();
        System.out.println("Update method test:");
        System.out.println();
        //update
        Widget widgetBetaUpdate = new Widget(2);
        widgetBetaUpdate.setDescription("Two Updated!");
        widgetBetaUpdate.setTitle("Beta Updated!");
        testWidgetManager.update(widgetBetaUpdate);

        Widget widgetCharlieUpdate = new Widget(3);
        widgetCharlieUpdate.setDescription("Three Updated!");
        widgetCharlieUpdate.setTitle("Charlie Updated!");
        testWidgetManager.update(widgetCharlieUpdate);



        //check updated information & total price
        System.out.println(widgetBeta.getTitle());
        System.out.println(widgetBeta.getDescription());
        System.out.println(widgetCharlie.getTitle());
        System.out.println(widgetCharlie.getDescription());

        //sprocket
        System.out.println();
        System.out.println("Add Sprocket method test:");
        System.out.println();
        //create a sprocket
        Sprocket sprocketTest = new Sprocket();
        Sprocket sprocketTestBeta = new Sprocket();
        Sprocket sprocketTestCharlie = new Sprocket();

        //create a color
        Color colorTest = new Color(53,189,212);
        Color colorTest2 = new Color(52,199,155);
        Color colorTest3 = new Color(15,152,200);

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

        //add sprockets to manager
        testWidgetManager.addSprocket(3,sprocketTest);
        testWidgetManager.addSprocket(3,sprocketTestBeta);
        testWidgetManager.addSprocket(3,sprocketTestCharlie);

        //create test lists and print sizes to ensure they've been added
        //print total price for an empty, and populated list
        List<Sprocket> testSprocketListCharlie = widgetCharlie.getSprockets();
        List<Sprocket> testSprocketListBeta = widgetBeta.getSprockets();

        System.out.println(testSprocketListCharlie.size());
        System.out.println(testSprocketListBeta.size());
        System.out.println(testWidgetManager.getTotalPrice(3));
        System.out.println(testWidgetManager.getTotalPrice(2));

        System.out.println();
        System.out.println("Search by Title method test:");
        System.out.println();
        //search title

        List<Widget> searchTitleResult = testWidgetManager.searchByTitle("      UpDaTe    ");
        List<Widget> searchTitleSingleResult = testWidgetManager.searchByTitle("Charlie");
        List<Widget> searchTitleNoResult = testWidgetManager.searchByTitle("ASDAJSDKLJASKL");
        System.out.println(searchTitleResult.size());
        System.out.println(searchTitleSingleResult.size());
        System.out.println(searchTitleNoResult.size());

        System.out.println();
        System.out.println("Search by Description method test:");
        System.out.println();

        List<Widget> searchDescResult = testWidgetManager.searchByDescription("      UpDaTed    ");
        List<Widget> searchDescSingleResult = testWidgetManager.searchByDescription("Two");
        List<Widget> searchDescNoResult = testWidgetManager.searchByDescription("ASDAJSDKLJASKL");
        System.out.println(searchDescResult.size());
        System.out.println(searchDescSingleResult.size());
        System.out.println(searchDescNoResult.size());

        System.out.println();
        System.out.println("Sort by title method test:");
        System.out.println();
        //test sort by title

        Widget sortAlpha = new Widget(1);
        sortAlpha.setDescription("Widget One");
        sortAlpha.setTitle("A aa");


        Widget sortBeta = new Widget(2);
        sortBeta.setDescription("Widget Two");
        sortBeta.setTitle("Abb");


        Widget sortCharlie = new Widget(3);
        sortCharlie.setDescription("Widget Three");
        sortCharlie.setTitle("Abbc");

        widgetBetaUpdate.setTitle("Beta");
        widgetCharlieUpdate.setTitle("Charlie");
        List<Widget> sortTest = new ArrayList<>();
        sortTest.add(sortBeta);
        sortTest.add(sortAlpha);
        sortTest.add(sortCharlie);
        sortTest.add(widgetCharlie);
        sortTest.add(widgetBeta);
        sortTest.add(widgetAlpha);
        sortTest.add(widgetBetaUpdate);
        sortTest.add(widgetCharlieUpdate);


        testWidgetManager.sortByTitle(sortTest);

        Iterator<Widget> widgetIterator = sortTest.iterator();

        while(widgetIterator.hasNext()) {
            Widget widgetFind = widgetIterator.next();
            System.out.println(widgetFind.getTitle());
        }


        //add sprocket to alpha widget to test for 1 sprocket
        Color colorTestAlpha = new Color(34,210,211);

        Sprocket sprocketTestAlpha = new Sprocket();
        //set sprocket values
        sprocketTestAlpha.setId(1);
        sprocketTestAlpha.setColor(colorTestAlpha);
        sprocketTestAlpha.setPrice(25.25);

        testWidgetManager.add(widgetAlpha);
        testWidgetManager.addSprocket(1,sprocketTestAlpha);

        List<Widget> sortedSaturationList = new ArrayList<>();
        sortedSaturationList.add(widgetAlpha);
        sortedSaturationList.add(widgetBeta);
        sortedSaturationList.add(widgetCharlie);

        //insert widgets into list
       testWidgetManager.sortBySaturation(sortedSaturationList);


        System.out.println();
        System.out.println("Sort by Saturation method test:");
        System.out.println();
        Iterator<Widget> satIterator = sortedSaturationList.iterator();
        while(satIterator.hasNext()) {
            Widget widgetFind = satIterator.next();
            System.out.println(widgetFind.getTitle());
        }

    }

    //get method ID failure test
    @Test
    public void getFail() {
        WidgetManager testWidgetManager = new WidgetManager();
        Widget testWidget = testWidgetManager.get(22);
        assertNull(testWidget);

    }


}