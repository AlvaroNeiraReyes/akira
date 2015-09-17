/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akori;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import com.codeborne.selenide.Selenide;

/**
 *
 * @author Claudio
 */
public class DrawLevel {
    public static final String PATH = "/Users/aneira/lalo/test2/";
    public static final String WEBSITES_PATH = "/Users/aneira/lalo/websites/";
    public static final String PICTURES_PATH = "/Users/aneira/lalo/akori/build/reports/tests/";
    public static final String[] URLlist = {
        "http://www.mbauchile.cl"
        //"http://www.businessinsider.com/best-iphone-only-apps-you-cant-get-on-android-2015-6"
        //WEBSITES_PATH+"bi_apps/www.businessinsider.com/best-iphone-only-apps-you-cant-get-on-android-2015-650f4.html"
    };
    public static final Integer MAX_DEPTH = 100;
    public static final Integer MAX_DEPTH2 = 100;
    public static void main(String[] args) throws Exception {
        System.out.println("esto es DrawLevel");
        for (int k = 0; k < URLlist.length; ++k) {
            String URL = URLlist[k];
            String NAME = namefile(URL);

            SelenideElement s = Selenide.$(By.tagName("body"));
            
            Document doc=getDoc(URL,false);
            Elements e1 = doc.body().getAllElements();

            ArrayList<String> tags = new ArrayList<>();
            Selenide.screenshot(NAME);

            ArrayList<String> elements = new ArrayList<>();
            int maxj = 0;
            
            for (Element temp : e1) {
                System.out.println("Examining element '"+temp.nodeName()+"'");
                if (tags.indexOf(temp.tagName()) == -1) {
                    tags.add(temp.tagName());
                    ElementsCollection query = Selenide.$$(By.tagName(temp.tagName()));
                    int id = 1;
                    for (SelenideElement temp2 : query) {
                        System.out.println("Examining selenide element '" + temp2 + "'");
                        if (id > MAX_DEPTH2) {
                            break;
                        }
                        WebElement temp1 = temp2.toWebElement();
                        Point po = temp1.getLocation();
                        Dimension d = temp1.getSize();
                        if (d.width <= 0 || d.height <= 0 || po.x < 0 || po.y < 0) {
                            continue;
                        }
                        int dep = 0;
                        int j=1;
                        for (; !temp2.equals(s); ++j) {
                            System.out.println("id="+id+"/"+query.size());
                            System.out.println("dep="+(dep++));
                            if(id==31 || id == 20){
                                System.out.println("wawa");
                            }
                            temp2 = temp2.parent();
                            if (j > MAX_DEPTH) {
                                break;
                            }
                        }
                        System.out.println("post for");

                        if(temp.hasText()) 
                            elements.add(temp.nodeName() + "," + po.x + "," + po.y + "," + d.width + "," + d.height + "," + j + "," + 1+ "," + id+ "," + (k+1));
                        else 
                            elements.add(temp.nodeName() + "," + po.x + "," + po.y + "," + d.width + "," + d.height + "," + j + "," + 0 + "," + id+ "," + (k+1));
                        if (j > maxj) {
                            maxj = j;
                        }
                        id++;
                    }
                }
            }

            System.out.println("out of loop");

            PrintWriter writer = new PrintWriter(PATH + NAME+".txt", "UTF-8");
            
            for (String temp : elements) {
                writer.println(temp);
            }
            writer.close();
            
            int[] conteo = new int[maxj];
            for (String temp : elements) {
                String[] aux = temp.split(",");
                conteo[Integer.parseInt(aux[5]) - 1]++;
            }

            int acum = 0;
            double[] acumulado = new double[maxj];
            for (int i =0; i<conteo.length;++i) {
                acum += conteo[i];
                acumulado[i]=acum;
            }
            
            BufferedImage img = null;
            try{
                img = ImageIO.read(new File(PICTURES_PATH+NAME + ".png"));
            }catch (Exception e){
                System.err.println("Trying to read '"+PICTURES_PATH+NAME+".png'");
                e.printStackTrace();
                System.exit(1);
            }
            Graphics2D graph = img.createGraphics();
            graph.setColor(Color.RED);
            ArrayList<String> elementsGraphed = new ArrayList<>();

            for (String temp : elements) {
                String[] aux = temp.split(",");
                if (Integer.parseInt(aux[5]) < maxj && conteo[Integer.parseInt(aux[5])-1] > conteo[Integer.parseInt(aux[5])]) {
                    int x = Integer.parseInt(aux[1]);
                    int y = Integer.parseInt(aux[2]);
                    int w = Integer.parseInt(aux[3]);
                    int h = Integer.parseInt(aux[4]);

                    graph.draw(new Rectangle(x, y, w, h));
                    elementsGraphed.add(temp);
                }
            }

            graph.dispose();
            
            //Here it generates the png file
            ImageIO.write(img, "png", new File(PATH + NAME + ".png"));

            System.out.println("DrawLevel terminado");
        }
    }
    
    public static Document getDoc(String url, Boolean isOffline){
        Document doc = null;
        
        try {
                
            if(isOffline){
                Selenide.open("file://"+url);
                File in = new File(url);
                doc = Jsoup.parse(in, "UTF-8", "http://www.google.com");
            }else{
                Selenide.open(url);
                doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:24.0) Gecko/20100101 Firefox/24.0")
                      .timeout(0)
                      .referrer("http://www.google.com")
                      .get(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            return doc;
        }
    }

    public static String namefile(String url) {
        String new_url = url.replaceAll("/", "");
        new_url = new_url.replaceAll("\\.", "");
        new_url = new_url.replaceAll("www", "");
        new_url = new_url.replaceAll("http", "");
        new_url = new_url.replaceAll(":", "");
        return new_url;
    }
}
