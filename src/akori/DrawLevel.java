/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akori;

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.*;
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

/**
 *
 * @author Claudio
 */
public class DrawLevel {

    public static void main(String[] args) throws Exception {
        System.out.println("esto es DrawLevel");

        String[] URLlist = {
            "http://www.mbauchile.cl"
        };

        String PATH = "C:\\Users\\Lalo\\Desktop\\Lalo\\U\\Trabajo de Título\\Experimento y Análisis\\Códigos\\objetos\\test2\\";

        for (int k = 0; k < URLlist.length; ++k) {

            String URL = URLlist[k];
            String NAME = namefile(URL);
            open(URL);

            SelenideElement s = $(By.tagName("body"));
            
            Document doc = null;
            try{
                doc = Jsoup.connect(URL).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:24.0) Gecko/20100101 Firefox/24.0")
                    .timeout(0)
                    .referrer("http://www.google.com")
                    .get();
            }
            catch(Exception e){
                e.printStackTrace();
                continue;
            }
            Elements e1 = doc.body().getAllElements();

            ArrayList<String> tags = new ArrayList<String>();
            screenshot(NAME);

            ArrayList<String> elements = new ArrayList<String>();
            int maxj = 0;
            int id = 1;
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
                        int j = 1;
                        for (j = 1; !temp2.equals(s); ++j) {
                            temp2 = temp2.parent();
                            if (j > 100) {
                                break;
                            }
                        }
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
            
            BufferedImage img = ImageIO.read(new File(NAME + ".png"));
            Graphics2D graph = img.createGraphics();
            graph.setColor(Color.RED);
            ArrayList<String> elementsGraphed = new ArrayList<String>();

            for (String temp : elements) {
                String[] aux = temp.split(",");
                //if (Integer.parseInt(aux[5]) < maxj && acumulado[Integer.parseInt(aux[5]) - 1]*1.1 > acumulado[Integer.parseInt(aux[5])]) {
                //if (Integer.parseInt(aux[5]) < maxj && (conteo[Integer.parseInt(aux[5])-1] > conteo[Integer.parseInt(aux[5])] || conteo[Integer.parseInt(aux[5])] > conteo[Integer.parseInt(aux[5])+1])) {
                if (Integer.parseInt(aux[5]) < maxj && conteo[Integer.parseInt(aux[5])-1] > conteo[Integer.parseInt(aux[5])]) {
                    int x = Integer.parseInt(aux[1]);
                    int y = Integer.parseInt(aux[2]);
                    int w = Integer.parseInt(aux[3]);
                    int h = Integer.parseInt(aux[4]);

                    boolean add = true;
//                    for (String temp1 : elementsGraphed) {
//                        String[] aux1 = temp1.split(",");
//                        int x1 = Integer.parseInt(aux1[1]);
//                        int y1 = Integer.parseInt(aux1[2]);
//                        int w1 = Integer.parseInt(aux1[3]);
//                        int h1 = Integer.parseInt(aux1[4]);
//                        Rectangle rec = new Rectangle(x, y, w, h);
//                        Rectangle rec1 = new Rectangle(x1, y1, w1, h1);
//                        if (rec1.contains(rec) || rec.contains(rec1)) {
//                            add = false;
//                        }
//                    }
                    if (add) {
                        graph.draw(new Rectangle(x, y, w, h));
                        elementsGraphed.add(temp);
                    }
                }
            }

            graph.dispose();
            ImageIO.write(img, "png", new File(PATH + NAME + ".png"));

//            BufferedImage img = ImageIO.read(new File(PATH + NAME + ".png"));
//            Graphics2D graph = img.createGraphics();
//            graph.setColor(Color.RED);
//            ArrayList<String> elementsGraphed = new ArrayList<String>();
//
//            for (String temp : elements) {
//                String[] aux = temp.split(",");
//                if (Integer.parseInt(aux[5]) > 1 && Integer.parseInt(aux[5]) < maxj) {
//                    int x = Integer.parseInt(aux[1]);
//                    int y = Integer.parseInt(aux[2]);
//                    int w = Integer.parseInt(aux[3]);
//                    int h = Integer.parseInt(aux[4]);
//                    double r = (Math.max(w, h) * 1.0) / (Math.min(w, h) * 1.0);
//
//                    if (aux[0].equals("img") && Math.sqrt(w * h) > 200) {
//                        graph.draw(new Rectangle(x, y, w, h));
//                        elementsGraphed.add(temp);
//
//                    } else if (r >= 1 && r <= 5 && Math.sqrt(w * h) < 400 && Math.sqrt(w * h) > 50) {
//                        boolean add = true;
//                        for (String temp1 : elementsGraphed) {
//                            String[] aux1 = temp1.split(",");
//                            if (Integer.parseInt(aux1[5]) > Integer.parseInt(aux[5])) {
//                                int x1 = Integer.parseInt(aux1[1]);
//                                int y1 = Integer.parseInt(aux1[2]);
//                                int w1 = Integer.parseInt(aux1[3]);
//                                int h1 = Integer.parseInt(aux1[4]);
//                                Rectangle rec = new Rectangle(x, y, w, h);
//                                Rectangle rec1 = new Rectangle(x1, y1, w1, h1);
//                                if (rec1.contains(rec)) {
//                                    add = false;
//                                }
//                            }
//
//                        }
//                        if (add) {
//                            graph.draw(new Rectangle(x, y, w, h));
//                            elementsGraphed.add(temp);
//                        }
//                    }
//
//                }
//            }
//
//            graph.dispose();
//            ImageIO.write(img, "png", new File(PATH + NAME + ".png"));
//            
//            int[] conteo = new int[maxj];
//            for (int j = 1; j <= maxj; ++j) {
//                BufferedImage img = ImageIO.read(new File(PATH + NAME + ".png"));
//                Graphics2D graph = img.createGraphics();
//                graph.setColor(Color.RED);
//
//                for (String temp : elements) {
//                    String[] aux = temp.split(",");
//                    if (Integer.parseInt(aux[5]) == j) {
//                        int x = Integer.parseInt(aux[1]);
//                        int y = Integer.parseInt(aux[2]);
//                        int w = Integer.parseInt(aux[3]);
//                        int h = Integer.parseInt(aux[4]);
//                        graph.draw(new Rectangle(x, y, w, h));
//                        conteo[j - 1]++;
//                    }
//                }
//                graph.dispose();
//                ImageIO.write(img, "png", new File(PATH + NAME + (j + 1) + ".png"));
//            }
//
//            for (int h = 0; h < conteo.length; ++h) {
//                System.out.println("depth " + (h + 1) + ":" + conteo[h]);
//            }
//            close();
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
