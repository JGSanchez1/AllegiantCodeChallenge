package com.allegiant;

import java.util.Comparator;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList; // need to instantiate widgets list
/**
 * Implement these methods;
 */
public class WidgetManager {


    private List<Widget> widgets;

    //constructor to initalize widgets when created
    public WidgetManager() {
        widgets = new ArrayList<>();
    }

    /**
     * Returns the widget with the given id. If none is found, this method will return null;
     */
    public Widget get(int id) {
        // iterate through list to see if id exists, return object Widget if exists
        Iterator<Widget> widgetIterator = widgets.iterator();
        while(widgetIterator.hasNext())
        {
            Widget widgetFind = widgetIterator.next();
            if(widgetFind.getId()==id) { //get id of current widget, if widgetId=paramId return
                return widgetFind; //return current widget
            }
        }
        // if list is iterated through and no action performed, default with return null
        return null; // returns null if no widget has parameter id
    }


    /**
     * Adds the given widget to the manager.
     *
     */
    public void add(Widget addWidget) {
           if(addWidget != null) { // ensure widget is not null
               widgets.add(addWidget); // adding widget
           }
        }


    /**
     * Deletes the given widget from the manaager
     */
    public void delete(Widget deleteWidget) {
        widgets.remove(deleteWidget); //removing widget, List class remove method checks if it exists
    }

    /**
     * Updates the widget with the given widget's id.
     * @Throws IllegalArgumentException if no widget with that ID is found
     */
    public void update(Widget updateWidget) {
        Widget currWidget = this.get(updateWidget.getId()); //get widget from id

        if(currWidget != null) { // perform update setActions if widget exists in list
            currWidget.setId(updateWidget.getId());
            currWidget.setTitle(updateWidget.getTitle());
            currWidget.setDescription(updateWidget.getDescription());
            currWidget.setSprockets(updateWidget.getSprockets());
        }
        else { // else throw exception if illegal id
            throw new IllegalArgumentException("Update Failed: Widget with that ID is not found.");
        }
    }

    /**
     * Adds the given sprocket to the widget with the given ID.
     * @Throws IllegalArgumentException if no such widget is found.
     */
    public void addSprocket(int widgetId, Sprocket sprocket) {
        Widget currWidget = this.get(widgetId); //get widget using widgetmanager?? test
        if (currWidget!=null) { // perform action if widget exists in list
            currWidget.addSprocket(sprocket);
        }
        else { // else throw exception if illegal id
            throw new IllegalArgumentException("Add Sprocket Failed: Widget with that ID is not found.");
        }
    }
    /**
     * Returns the sum of the prices of the sprockets in the given widget
     * @param widgetId
     * @throws IllegalArgumentException if no widget with that ID is found
     */
    public Double getTotalPrice (int widgetId) {
        Widget currWidget = this.get(widgetId);
        double totalPrice = 0.0;
        if(currWidget!= null)
        {
            // iterate through sprocket list
            List<Sprocket> priceList = currWidget.getSprockets();
            Iterator<Sprocket> sprocketIt = priceList.iterator();
                while (sprocketIt.hasNext()) {
                    Sprocket currSprocket = sprocketIt.next();
                    totalPrice = totalPrice + currSprocket.getPrice();
                }
            return totalPrice; // return new added price, or default 0.0 if list was empty
        }
        else
        {
            throw new IllegalArgumentException("Get Total Price Failed: Widget with that ID is not found.");
        }
    }

    /**
     *
     * returns all widgets whose title contains the given search string. (Use a case insensitive comparison)
     */
    public List<Widget> searchByTitle(String title) {
        List<Widget> resultList = new ArrayList<>(); //holder list

        Iterator<Widget> widgetIterator = widgets.iterator();
        while(widgetIterator.hasNext()) {
            Widget widgetFind = widgetIterator.next();

                //low-case to make case-insensitive; trim title in case of whitespace; search if same name
            if (widgetFind.getTitle().toLowerCase().contains(title.toLowerCase().trim())) {

                    resultList.add(widgetFind); //return current widget
            }
        }

        return resultList;
    }

    /**
     * Returns all widgets whose description contains the given search string. (Use a case insensitive comparison)
     */
    public List<Widget> searchByDescription(String description){

        List<Widget> resultList = new ArrayList<>(); //holder list
        Iterator<Widget> widgetIterator = widgets.iterator();

        while(widgetIterator.hasNext())
        {
            Widget widgetFind = widgetIterator.next();
            //low-case to make case-insensitive; trim title incase of whitespace; search if contains string
            if(widgetFind.getDescription().toLowerCase().trim().contains(description.toLowerCase().trim())) {
                resultList.add(widgetFind); //return current widget
            }
        }
        return resultList;
    }


    /**
     * Returns the given input list sorted by title in ascending lexicographic order. ("Alpha","Beta","Charlie"...)
     *
     */
    public List<Widget> sortByTitle(List<Widget> inputs) {
        Collections.sort(inputs,new Comparator<Widget>(){ // use collections and comparator to sort based on field value
            @Override
            public int compare(Widget widgetOne,Widget widgetTwo)
            {
                return widgetOne.getTitle().compareTo(widgetTwo.getTitle());
            }

        });

        return inputs;
    }



    /**
     * Returns the given input list sorted in order of the average saturation value of the color attributes of each widgets sprockets.
     */
    public List<Widget> sortBySaturation(List<Widget> inputs) {

        Collections.sort(inputs,new Comparator<Widget>(){ // use collections and comparator to sort based on field value
            @Override
            public int compare(Widget widgetOne,Widget widgetTwo) {
                List<Sprocket> sprocketListOne = widgetOne.getSprockets();
                List<Sprocket> sprocketListTwo = widgetTwo.getSprockets();

                double sprocketSatValueOne = 0.0;
                double sprocketSatCounterOne = 0.0;
                double sprocketSatAvgOne = 0.0;
                double sprocketSatValueTwo = 0.0;
                double sprocketSatCounterTwo = 0.0;
                double sprocketSatAvgTwo = 0.0;

                //find avg saturation level for sprocket list one (REFACTOR INTO A METHOD FOR CLEANER CODE)
                if (!(sprocketListOne.equals(null))) {
                    Iterator<Sprocket> sprocketIteratorOne = sprocketListOne.iterator();
                    while (sprocketIteratorOne.hasNext()) {
                        //grab sprocket and color
                        Sprocket currSprocketOne = sprocketIteratorOne.next();

                        Color currColorOne = currSprocketOne.getColor();

                        //grab color values and calculate min/max(RBG)
                        double redCurrOne = ((double) currColorOne.getRed()) / 255.0;
                        double blueCurrOne = ((double) currColorOne.getBlue()) / 255.0;
                        double greenCurrOne = ((double) currColorOne.getGreen()) / 255.0;

                        //compare
                        double maxRGBOne = Math.max(redCurrOne, Math.max(blueCurrOne, greenCurrOne));
                        double minRGBOne = Math.min(redCurrOne, Math.max(blueCurrOne, greenCurrOne));

                        //calculate luminosity
                        double luminosityOne = (1.0 / 2.0) * (maxRGBOne + minRGBOne);

                        double saturationOne;
                        //calculate saturation
                        if (luminosityOne < 1.0) {
                            saturationOne = (maxRGBOne - minRGBOne) / (1.0 - Math.abs((2.0 * luminosityOne) - 1.0));
                        } else {
                            saturationOne = 0.0;
                        }
                        sprocketSatValueOne += saturationOne;
                        sprocketSatCounterOne++;
                    }
                    sprocketSatAvgOne = sprocketSatValueOne / sprocketSatCounterOne;
                } else {

                    sprocketSatAvgOne = 2; //no sprockets so put at end of list
                }

                //find average saturation value for second sprocket list
                Iterator<Sprocket> sprocketIteratorTwo = sprocketListTwo.iterator();
                if (!(sprocketListTwo.equals(null))) {
                    while (sprocketIteratorTwo.hasNext()) {
                        //grab sprocket and color
                        Sprocket currSprocketOne = sprocketIteratorTwo.next();
                        Color currColorOne = currSprocketOne.getColor();

                        //grab color values and calculate min/max(RBG)
                        double redCurrOne = ((double) currColorOne.getRed()) / 255.0;
                        double blueCurrOne = ((double) currColorOne.getBlue()) / 255.0;
                        double greenCurrOne = ((double) currColorOne.getGreen()) / 255.0;

                        //compare
                        double maxRGBOne = Math.max(redCurrOne, Math.max(blueCurrOne, greenCurrOne));
                        double minRGBOne = Math.min(redCurrOne, Math.max(blueCurrOne, greenCurrOne));

                        //calculate luminosity
                        double luminosityOne = (1.0 / 2.0) * (maxRGBOne + minRGBOne);

                        double saturationOne;
                        //calculate saturation
                        if (luminosityOne < 1.0) {
                            saturationOne = (maxRGBOne - minRGBOne) / (1.0 - Math.abs((2.0 * luminosityOne) - 1.0));
                        } else {
                            saturationOne = 0.0;
                        }
                        sprocketSatValueTwo += saturationOne;
                        sprocketSatCounterTwo++;
                    }
                    sprocketSatAvgTwo = sprocketSatValueTwo / sprocketSatCounterTwo;
                } else {

                    sprocketSatAvgTwo = 2; //no sprockkets so put at end of list
                }

                if(Double.isNaN(sprocketSatAvgOne))
                {
                    sprocketSatAvgOne = 2; // nan results due to empty sprocket list
                }
                if(Double.isNaN((sprocketSatAvgTwo)))
                {
                    sprocketSatAvgTwo = 2; //nan results due to empty sprocket list
                }
                //compareTo can only take in integers, must manually compare and return proper value
                if (sprocketSatAvgOne < sprocketSatAvgTwo) {
                    return -1; //place before
                } else if (sprocketSatAvgOne > sprocketSatAvgTwo) {
                    return 1; //place after
                }
                return 0;

            }
        });

        return inputs;
    }



}
