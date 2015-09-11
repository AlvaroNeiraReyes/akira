/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package akori;

import static akori.Features.URL;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Claudio
 */
public class FeaturesSelenide {
    
    public static void main(String[] args) throws Exception {
        System.out.println("esto es FeaturesSelenide");
        PrintWriter writer = new PrintWriter("features.txt", "UTF-8");
        try{
            
        String URL = "http://www.mbauchile.cl";
        
        open(URL);
        
        SelenideElement s = $(By.tagName("body"));
        
        Document doc = Jsoup.connect(URL).timeout(0).get();
        Elements e1 = doc.body().getAllElements();
        
        ArrayList<String> tags = new ArrayList<String>();
        
        int i = 1;
        for (Element temp : e1) {
            if (tags.indexOf(temp.tagName()) == -1) {
                tags.add(temp.tagName());
                ElementsCollection query = $$(By.tagName(temp.tagName()));
                for (SelenideElement temp2 : query) {
                    WebElement temp1 = temp2.toWebElement();
                    Point po = temp1.getLocation();
                    Dimension d = temp1.getSize();             
                    if (d.width <= 0 || d.height <= 0 || po.x < 0 || po.y < 0) {
                        continue;
                    }
                    if(temp1.getTagName().equals("img")){
                        writer.print(i + "," + temp1.getTagName() +  "," + po.x + "," + po.y + "," + d.width + "," + d.height +"," +temp1.getAttribute("class")+"," +temp1.getAttribute("src"));
                        int j = 1;
                        for(j = 1; !temp2.equals(s);++j){
                            temp2 = temp2.parent();
                            if (j>100) break;
                        }
                        writer.println(","+j);
                    }else if(temp1.getTagName().equals("a")){    
                        writer.println(i + "," + temp1.getTagName() +  "," + po.x + "," + po.y + "," + d.width + "," + d.height +"," +temp1.getAttribute("class")+"," +temp1.getAttribute("href"));
                        int j = 1;
                        for(j = 1; !temp2.equals(s);++j){
                            temp2 = temp2.parent();
                            if (j>100) break;
                        }
                        writer.println(","+j);
                    }else{    
                        writer.println(i + "," + temp1.getTagName() +  "," + po.x + "," + po.y + "," + d.width + "," + d.height +"," +temp1.getAttribute("class")+"," +temp1.getText());
                        int j = 1;
                        for(j = 1; !temp2.equals(s);++j){
                            temp2 = temp2.parent();
                            if (j>100) break;
                        }
                        writer.println(","+j);
                    }
                    ++i;
                    
                }
            }
        }
        
        }catch(Exception e){
            e.printStackTrace();
        }
        writer.close();   
    }
    
}
