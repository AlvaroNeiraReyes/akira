/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akori;

import java.io.*;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 *
 * @author Claudio
 */
public class Features {

    static String URL;
    static String PATH;
    static String NAME;

    public static void main(String[] args) throws IOException, InterruptedException {

        URL = "http://www.mbauchile.cl";

        Document doc = Jsoup.connect(URL).timeout(0).get();

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        
        driver.get(URL);

        Elements e1 = doc.body().getAllElements();
        Element e = doc.body();
        PrintWriter writer = new PrintWriter("features.txt", "UTF-8");
        int i = 1;
//        String[][] matrix = new String[e1.size()][10];

//        traverse(e, 1, 1, "", 1, writer, driver);

        ArrayList<String> tags = new ArrayList<String>();
        System.out.println("");
//        for (Element temp : e1) {
//            if (!temp.nodeName().equals("br")) {
//                writer.println(i + "," + temp.hashCode() + "," + temp.nodeName() + "," + temp.id());
//                //System.out.println(i+","+temp.hashCode()+","+temp.nodeName());
//                ++i;
//            }
//        }
        i = 1;
        for (Element temp : e1) {
            if (tags.indexOf(temp.tagName()) == -1) {
                tags.add(temp.tagName());
                List<WebElement> query = driver.findElements(By.tagName(temp.tagName()));
                for (WebElement temp1 : query) {
                    Point po = temp1.getLocation();
                    Dimension d = temp1.getSize();             
                    if (d.width <= 0 || d.height <= 0 || po.x < 0 || po.y < 0) {
                        continue;
                    }
                    if(temp1.getTagName().equals("img"))
                        writer.println(i + "," + temp1.getTagName() +  "," + po.x + "," + po.y + "," + d.width + "," + d.height +"," +temp1.getAttribute("class")+"," +temp1.getAttribute("src"));
                    else if(temp1.getTagName().equals("a"))    
                        writer.println(i + "," + temp1.getTagName() +  "," + po.x + "," + po.y + "," + d.width + "," + d.height +"," +temp1.getAttribute("class")+"," +temp1.getAttribute("href"));
                    else    
                        writer.println(i + "," + temp1.getTagName() +  "," + po.x + "," + po.y + "," + d.width + "," + d.height +"," +temp1.getAttribute("class")+"," +temp1.getText());
                    ++i;
                }
            }
        }
        driver.quit();
        writer.close();
    }

    public static void traverse(Element e, int level, int index, String xpath, int siblings, PrintWriter writer, WebDriver driver) {

        if (siblings > 1) {
            WebElement webe = null;
            try{
                webe = driver.findElement(By.xpath("/" + xpath + "/" + e.nodeName() + "[" + index + "]"));
            } 
            catch(Exception exp){
                System.out.println("exception no se encontro");
            }
            if (webe != null) {
                Point po = webe.getLocation();
                Dimension d = webe.getSize();
                if (d.width <= 0 || d.height <= 0 || po.x < 0 || po.y < 0) {
                    System.out.println("elemento negativo");
                }
                else{
                    writer.println("/" + xpath + "/" + e.nodeName() + "[" + index + "]," + e.nodeName() + "," + po.x + "," + po.y + "," + d.width + "," + d.height + "," + level);
                }
            }
        } else {
            WebElement webe = null;
            try{
                webe = driver.findElement(By.xpath("/" + xpath + "/" + e.nodeName()));
            }
            catch(Exception exp){
                System.out.println("exception no se encontro");
            }
            if (webe != null) {
                Point po = webe.getLocation();
                Dimension d = webe.getSize();
                if (d.width <= 0 || d.height <= 0 || po.x < 0 || po.y < 0) {
                    System.out.println("elemento negativo");
                } 
                else{
                    writer.println("/" + xpath + "/" + e.nodeName() + "," + e.nodeName() + "," + po.x + "," + po.y + "," + d.width + "," + d.height + "," + level);
                }
            }
        }

        int childSize = e.children().size();
        Elements children = e.children();
        int i = 1;
        for (Element temp : children) {
            if (siblings > 1) {
                traverse(temp, level + 1, i, xpath + "/" + e.nodeName() + "[" + index + "]", childSize, writer, driver);
            } else {
                traverse(temp, level + 1, i, xpath + "/" + e.nodeName(), childSize, writer, driver);
            }
            ++i;
        }

    }
}
