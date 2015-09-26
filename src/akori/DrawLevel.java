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
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 *
 * @author Claudio
 */
public class DrawLevel {
    public static final String RESULTS_PATH = "../test2/";
    public static final String WEBSITES_PATH = "../websites/";
    public static final String PICTURES_PATH = "./build/reports/tests/";
    public static final String[] URLlist = {
        //"http://www.mbauchile.cl"
        //"http://www.businessinsider.com/best-iphone-only-apps-you-cant-get-on-android-2015-6"
        //WEBSITES_PATH+"bi_apps/www.businessinsider.com/best-iphone-only-apps-you-cant-get-on-android-2015-650f4.html" //missing 10 hours
        //WEBSITES_PATH+"ds_berlin/www.designsponge.com/2015/05/berlin-germany-city-guide.html"
        //WEBSITES_PATH+"ds_argentina/www.designsponge.com/2012/12/palermo-buenos-aires-argentina-city-guide.html"
        //WEBSITES_PATH+"columbia_about/www.columbia.edu/content/about-columbia.html"
        //WEBSITES_PATH+"columbia_adm/www.columbia.edu/node/52.html" //missing
//        WEBSITES_PATH+"columbia_nobel/www.columbia.edu/content/nobel-laureates.html" //105 minutes
        //WEBSITES_PATH+"dc_manhattan/www.divinecaroline.com/lifestyle/food-drink/how-make-manhattan.html"
        toAbsolutePath(WEBSITES_PATH)+"/ds_eggs/www.designsponge.com/2015/04/in-the-kitchen-with-marnie-andrea-and-jens-deviled-eggs.html"
        //WEBSITES_PATH+"ds_instagram/www.designsponge.com/2014/09/10-travel-instagram-feeds-to-follow-and-swoon-over.html"
        //WEBSITES_PATH+"ds_london/www.designsponge.com/2015/03/24-hours-in-london-england-with-james-greig.html"
        //WEBSITES_PATH+"emol_vino/www.emol.com/tendenciasymujer/Noticias/2015/06/09/27386/Expertos-eligen-a-vino-sudafricano-como-el-mejor-del-mundo.html"
//        WEBSITES_PATH+"es_berlin/www.elizabethstreet.com/travel/berlin-international-film-festival/the-luxer.html"
//        WEBSITES_PATH+"gq_autos/www.gq.com.mx/maquinas/autos.html" //missing
//        WEBSITES_PATH+"gq_bitbite/www.gq.com.mx/maquinas/gadgets/articulos/bitbite-el-gadget-ideal-para-bajar-de-peso/5040.html" //24 minutes
//        WEBSITES_PATH+"gq_secuelas/www.gq.com.mx/actualidad/cine-tv/articulos/cuales-son-las-mejores-segundas-partes-en-la-historia-del-cine/4842.html" //error
//        WEBSITES_PATH+"primer_battery/www.primermagazine.com/2009/field-manual/five-ways-to-make-your-cell-phone-battery-last-longer.html" //missing 153 minutes
//        WEBSITES_PATH+"primer_beer/www.primermagazine.com/2012/learn/a-simple-guide-to-pairing-beer-with-food.html" //7 hours and did not finished
//        WEBSITES_PATH+"science_cat/news.sciencemag.org/categories.html" //ugly
//        WEBSITES_PATH+"science_mag/www.sciencemag.org/magazine.html"
//        WEBSITES_PATH+"uncrate_phones/uncrate.com/tech/cell-phones/index.html"
//        WEBSITES_PATH+"werd_rides/www.werd.com/category/rides/index.html"
        //WEBSITES_PATH+"wharton_phd/doctoral.wharton.upenn.edu/class-profile/index.html"
       // WEBSITES_PATH+"wiki_paine/es.wikipedia.org/wiki/Parque_nacional_Torres_del_Paine.html" //paragraphs missing
        //WEBSITES_PATH+"wiki_pisco/es.wikipedia.org/wiki/Pisco_(aguardiente).html" //paragraphs reguleque
//          WEBSITES_PATH+"wired_note4/www.wired.com/2014/10/samsung-galaxy-note-4-2/index.html"
//          WEBSITES_PATH+"enfem/Diez%20alimentos%20que%20puedes%20comer%20antes%20de%20hacer%20ejercicio.htm" //84 minutes and didn't stop
//          WEBSITES_PATH+"enfem/Recetas%20sin%20gluten%20-%20enfemenino.htm" //No results after 4 hours
    };
    public static final Integer MAX_DEPTH = 100;
    //public static final Integer MAX_DEPTH2 = 100;
    static SelenideElement s;
    static ArrayList<String> elements;
    static Integer maxj;
    static Integer numberElements;
    public static void processSelenideElement(SelenideElement elem, Element temp, Integer id) {
//        System.out.println("Examining selenide element '" + elem + "'");
        WebElement temp1 = elem.toWebElement();
        Point po = temp1.getLocation();
        Dimension d = temp1.getSize();
        if (d.width <= 0 || d.height <= 0 || po.x < 0 || po.y < 0) {
            return;
        }
        int dep = 0;
        int j = 1;
        for (; !elem.equals(s); ++j) {
            elem = elem.parent();
            if (j > MAX_DEPTH) {
                break;
            }
        }
        Integer k=0;
        String str="";
        if (temp.hasText()) {
            str = temp.nodeName() + "," + po.x + "," + po.y + "," + d.width + "," + d.height + "," + j + "," + 1 + "," + id + "," + (k + 1);
        } else {
            str = temp.nodeName() + "," + po.x + "," + po.y + "," + d.width + "," + d.height + "," + j + "," + 0 + "," + id + "," + (k + 1);
        }
        elements.add(str);
        if (j > maxj) {
            maxj = j;
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("esto es DrawLevel");
        for (int k = 0; k < URLlist.length; ++k) {
            String URL = URLlist[k];
            String NAME = namefile(URL);

            By by = By.tagName("body");
            s = Selenide.$(by);
            
            Document doc=getDoc(URL,true);
            Elements e1 = doc.body().getAllElements();

            ArrayList<String> tags = new ArrayList<>();
            Selenide.screenshot(NAME);

            elements = new ArrayList<>();
            maxj = 0;
            numberElements = e1.size();
            Integer numberSelenideElements=0;
            System.out.println("number of elements="+numberElements);
            Integer elementCounter = 0;
            for (Element elem : e1) {
                elementCounter++;
                System.out.println("Examining element '"+elem.nodeName()+"'");
                if (tags.indexOf(elem.tagName()) != -1) {
                    System.out.println("Skipping "+elem.tagName());
                    continue;
                }
                tags.add(elem.tagName());
                ElementsCollection selenideElements = Selenide.$$(By.tagName(elem.tagName()));
                numberSelenideElements=selenideElements.size();
                System.out.println("number of selenide elements="+numberSelenideElements);
                int id = 1;
                for (SelenideElement temp2 : selenideElements) {
                    System.out.println(id+"/"+numberSelenideElements+"/"+elementCounter + "/" + numberElements);

                    processSelenideElement(temp2,elem,id);
                    id++;
                    //if(id>MAX_DEPTH2){
                    //    continue;
                    //}
                }
            }

            System.out.println("out of loop");

            PrintWriter writer = new PrintWriter(RESULTS_PATH + NAME+".txt", "UTF-8");
            
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
            ImageIO.write(img, "png", new File(RESULTS_PATH + NAME + ".png"));

            System.out.println("DrawLevel terminado");
        }
    }
    
    public static Document getDoc(String url, Boolean isOffline){
        Document doc = null;
        
        try {
                
            if(isOffline){
                System.out.println("Trying to open 'file://"+url+"'");
                Selenide.open("file://"+url);
                File in = new File(url2file(url));
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
        } 
        return doc;
    }

    public static String namefile(String url) {
        String new_url = url.replaceAll("/", "");
        new_url = new_url.replaceAll("\\.", "");
        new_url = new_url.replaceAll("www", "");
        new_url = new_url.replaceAll("http", "");
        new_url = new_url.replaceAll(":", "");
        return new_url;
    }

    public static String toAbsolutePath(String relativePath){
        Path p = Paths.get(relativePath);
        String retVal="";
        try {
            retVal = p.toAbsolutePath().toRealPath().toString();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return retVal;
    }

    public static String url2file(String url) {
        return url.replaceAll("%20", "\\ ");
    }
}
