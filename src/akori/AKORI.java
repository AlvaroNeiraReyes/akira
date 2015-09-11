/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package akori;
import static akori.Mapa.normalMatrix;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 *
 * @author Claudio
 */
public class AKORI {

    static String URL;
    static String PATH;
    static String NAME;
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
        URL = "http://www.mbauchile.cl";
        PATH = "E:\\NetBeansProjects\\AKORI\\";
        NAME = "mbauchile.png";
        // Extrar DOM tree
        
        Document doc = Jsoup.connect(URL).timeout(0).get();
        
        // The Firefox driver supports javascript 
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        System.out.println(driver.manage().window().getSize().toString());
        System.out.println(driver.manage().window().getPosition().toString());
        int xmax = driver.manage().window().getSize().width;
        int ymax = driver.manage().window().getSize().height;
        
        // Go to the URL page
        driver.get(URL);
        
        File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen,new File(PATH+NAME));
        
        BufferedImage img = ImageIO.read(new File(PATH+NAME));
        //Graphics2D graph = img.createGraphics();
        
        
        BufferedImage img1 = new BufferedImage(xmax, ymax,BufferedImage.TYPE_INT_ARGB);
        Graphics2D graph1 = img.createGraphics();
        double[][] matrix = new double[ymax][xmax];
        BufferedReader in = new BufferedReader(new FileReader("et.txt"));
        String linea;
        double max =0;
        graph1.drawImage(img, 0, 0, null);
        HashMap<String,Integer> lista = new HashMap<String,Integer>();
        int count=0;
        for(int i =0; (linea = in.readLine()) != null && i<10000; ++i){
            String[] datos = linea.split(",");
            int x = (int) Double.parseDouble(datos[0]);
            int y = (int) Double.parseDouble(datos[2]);
            long time =Double.valueOf(datos[4]).longValue();
            if(x>=xmax || y>=ymax) continue;
            if(time < 691215)continue;
            if(time > 705648) break;
            if(lista.containsKey(x+","+y))
                lista.put(x+","+y,lista.get(x+","+y)+1);
            else
                lista.put(x+","+y,1);
            ++count;
        }
        System.out.println(count);
        in.close();
        Iterator iter = lista.entrySet().iterator();
        Map.Entry e;
        for(String key: lista.keySet()){
            Integer i = lista.get(key);
            if(max<i)
                max=i;
        }
        System.out.println(max);
        max = 0;
        while (iter.hasNext()) {
            e = (Map.Entry)iter.next();
            String xy = (String) e.getKey();
            String[] datos = xy.split(",");
            int x = Integer.parseInt(datos[0]);
            int y = Integer.parseInt(datos[1]);
            matrix[y][x]+=(int)e.getValue();
            double aux;
            if( (aux = normalMatrix(matrix,y,x,((int)e.getValue())*4)) >max){
                max =aux;
            }
            //normalMatrix(matrix,x,y,20);
            if(matrix[y][x]>max)
                max = matrix[y][x];
        }
        int A,R,G,B,n;
        for (int i =0; i<xmax;++i){
            for(int j = 0;j<ymax;++j ){
                if(matrix[j][i]!=0){
                    n = (int) Math.round(matrix[j][i]*100/max);
                    R=Math.round((255*n)/100);
                    G=Math.round((255*(100-n))/100);
                    B=0;
                    A =Math.round((255*n)/100);;
                    if(R>255) R=255;
                    if(R<0) R=0;
                    if(G>255) G=255;
                    if(G<0) G=0;
                    if(R<50) A=0; 
                    graph1.setColor(new Color(R,G,B,A));
                    graph1.fillOval(i, j, 1, 1);
                }
            }
        }
        //graph1.dispose();
        
        ImageIO.write(img, "png", new File("example.png"));
        System.out.println(max);
        
        graph1.setColor(Color.RED);
        // Extraer elementos
        Elements e1 = doc.body().getAllElements();
        int i = 1;
        ArrayList<String> tags = new ArrayList<String>();
        for (Element temp : e1) {
                
            if(tags.indexOf(temp.tagName())==-1){
                tags.add(temp.tagName());
            
                List<WebElement> query = driver.findElements(By.tagName(temp.tagName()));
                for (WebElement temp1 : query) {
                    Point po = temp1.getLocation();
                    Dimension d = temp1.getSize();
                    if (d.width <=0 || d.height <=0 || po.x < 0 || po.y < 0) continue;
                    System.out.println(i+" "+temp.nodeName());
                    System.out.println("  x: " + po.x + " y: " + po.y);
                    System.out.println("  width: "+d.width + " height: " + d.height);
                    graph1.draw(new Rectangle(po.x, po.y, d.width, d.height));
                    ++i;
                }
            }
	}
        
        graph1.dispose();
        ImageIO.write(img, "png", new File(PATH+NAME));

        driver.quit();

    }
    
}
