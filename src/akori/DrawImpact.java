/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akori;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Claudio
 */
public class DrawImpact {

    public static void main(String[] vars) throws Exception {

//        String PATH = "E:\\NetBeansProjects\\AKORI\\Proccess_1\\matlab_code\\IDImpacts1.csv";
//        String PATHIMG1 = "E:\\NetBeansProjects\\AKORI\\Proccess_1\\ImagesPages\\";
//        String PATHIMG2 = "E:\\NetBeansProjects\\AKORI\\Proccess_1\\ImagesImpact\\";
        
        String PATH = "E:\\Dropbox\\paper-BIH-AMT 2015\\data\\impacts\\";

        ArrayList<String> elements = new ArrayList<String>();
        Scanner s = new Scanner(new File(PATH+"Imp7.csv"));
        while (s.hasNext()) {
            String temp = s.next();
            elements.add(temp);
            String[] aux = temp.split(",");
        }
        s.close();

        for (int i = 7; i <= 7; ++i) {
            BufferedImage img = ImageIO.read(new File(PATH + (i) + ".png"));
            Graphics2D graph = img.createGraphics();
            for (String temp : elements) {
                String[] aux = temp.split(",");
                //int id = Integer.parseInt(aux[0]);
                //if(id==i){
                    int x = Integer.parseInt(aux[1]);
                    int y = Integer.parseInt(aux[2]);
                    int w = Integer.parseInt(aux[3]);
                    int h = Integer.parseInt(aux[4]);
                    
                    double imp = Double.parseDouble(aux[5]);
                    double rms = Double.parseDouble(aux[6]);
                    double var = Double.parseDouble(aux[7]);
//                    Color myColour = new Color(255, 0,0, (int) (200* imp) );
                    Color myColour = new Color(255, 0,0, 180);
                    graph.setColor(myColour);
                    //graph.fillRect(x, y, w, h);
                    int d = (int)(200* var);
                    graph.fillOval(x+w/2 -d/2,y+h/2 -d/2,d ,d);
                    graph.setColor(Color.BLACK);
                    graph.draw(new Rectangle(x, y, w, h));
                //}
            }
            graph.dispose();
            ImageIO.write(img, "png", new File(PATH + "var" + (i) + ".png"));
        }

    }

}
