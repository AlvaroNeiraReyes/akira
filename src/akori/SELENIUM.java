/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package akori;

/**
 *
 * @author Claudio
 */

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class SELENIUM {
    
    static String URL;
    static String PATH;
    
    public static void main(String[] args) throws Exception {
        // The Firefox driver supports javascript 
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        System.out.println(driver.manage().window().getSize().toString());
        System.out.println(driver.manage().window().getPosition().toString());
        URL = "http://www.mbauchile.cl";
        PATH = "E:\\NetBeansProjects\\AKORI\\1.png";
        
        // Go to the Google Suggest home page
        driver.get(URL);
        
        // Enter the query string "Cheese"
        WebElement query = driver.findElement(By.id("container"));
        Point p = query.getLocation();
        Dimension d = query.getSize();
        
        System.out.println("x: " + p.x + " y: " + p.y);
        System.out.println("width: "+d.width + " height: " + d.height);

        driver.quit();
    }
}