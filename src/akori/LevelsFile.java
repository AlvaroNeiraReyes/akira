/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akori;

import static akori.Cluster2txt.listFilesForFolder;
import static akori.DrawLevel.namefile;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Claudio
 */
public class LevelsFile {

    public static void main(String[] args) throws Exception {
        String[] URLlist = {"http://www.mbauchile.cl/",
            "http://www.mbauchile.cl/plan-de-estudios/",
            "http://www.mbauchile.cl/financiamiento-y-becas/",
            "http://www.mbauchile.cl/descargar-folleto/",
            "http://www.mbauchile.cl/tipo/jornada-completa-y-media-jornada",
            "http://www.mbauchile.cl/2013/06/13/mba-uchile-lider-en-ranking-latinoamericano/",
            "http://www.mbauchile.cl/plataforma-laboral/",
            "http://www.mbauchile.cl/servicio-medico/",
            "http://www.mbauchile.cl/contenido-cursos/",
            "http://www.mbauchile.cl/tipo/jornada-parcial/",
            "http://www.mbauchile.cl/todos/noticias/page/2/",
            "http://www.mbauchile.cl/invitados/",
            "http://www.mbauchile.cl/mapa-ubicacion/",
            "http://www.mbauchile.cl/academicos/andres-weintraub/",
            "http://www.mbauchile.cl/testimonios/felipe-sepulveda/",
            "http://www.mbauchile.cl/ipad/",
            "http://www.mbauchile.cl/postulacion-online/",
            "http://www.mbauchile.cl/admision/",
            "http://www.mbauchile.cl/doble-grado/",
            "http://www.mbauchile.cl/caracteristicas/",
            "http://www.mbauchile.cl/contacto/",
            "http://www.mbauchile.cl/generaciones/",
            "http://www.mbauchile.cl/perfil-alumnos/",
            "http://www.mbauchile.cl/porque-elegirnos/",
            "http://www.mbauchile.cl/formularios/",
            "http://www.mbauchile.cl/reglamento/",
            "http://www.mbauchile.cl/propuesta-academica/",
            "http://www.mbauchile.cl/u-cursos/",
            "http://www.mbauchile.cl/egresados/",
            "http://www.mbauchile.cl/corporacion-egresados/",
            "http://www.mbauchile.cl/quienes-participan/",
            "http://www.mbauchile.cl/biblioteca/",
            "http://www.mbauchile.cl/metodologia/"
        };

//        String PATH = "E:\\NetBeansProjects\\AKORI\\Proccess_1\\cluster2gustolo\\";
        String PATH1 = "E:\\NetBeansProjects\\AKORI\\Proccess_1\\ObjectPage_gustolo\\";
        String PATH = "E:\\NetBeansProjects\\AKORI\\Proccess_1\\ImagesPages\\";
//        String PATH = "E:\\NetBeansProjects\\AKORI\\Proccess_1\\txtclusterselected\\";
//        String PATH = "E:\\NetBeansProjects\\AKORI\\Proccess_1\\txtcluster\\";
//        String PATH1 = "E:\\NetBeansProjects\\AKORI\\Proccess_1\\tests\\";
//        ArrayList<String> list = listFilesForFolder(new File(PATH));
//        URLlist = list.toArray(URLlist);
//        String PATH2 = "E:\\NetBeansProjects\\AKORI\\Proccess_1\\";
//        for (int k = 0; k < URLlist.length; ++k) {
            
//            String num = URLlist[k].substring(URLlist[k].indexOf(".") - 1, URLlist[k].indexOf("."));
//            int cluster = Integer.parseInt(num);
//            String name = URLlist[k].substring(0, URLlist[k].indexOf(".") - 1);
//            name = name.replace("cluster", "");
        for (int k = 0; k < 32; ++k) {
            String URL = URLlist[k];
            String NAME = namefile(URL);
//            String NAME = URL;
            int maxc = 0;
            int maxj = 0;
            ArrayList<String> elements = new ArrayList<String>();
//            Scanner s = new Scanner(new File(PATH+"\\"+URLlist[k]));
//            Scanner s = new Scanner(new File(PATH + "cluster" +NAME + ".txt"));
//            Scanner s = new Scanner(new File(PATH + NAME ));
//            Scanner s = new Scanner(new File(PATH+"ObjectsPage" + (k+1) +".txt"));
            Scanner s = new Scanner(new File(PATH+ (k+1) +".txt"));
            while (s.hasNext()) {
                String temp = s.next();
                elements.add(temp);
                String[] aux = temp.split(",");
//                int c = Integer.parseInt(aux[7]);
//                int j = Integer.parseInt(aux[5]);
//                if (c > maxc) {
//                    maxc = c;
//                }
//                if (j > maxj) {
//                    maxj = j;
//                }
            }
            s.close();

//            int[] conteo = new int[maxj];
//            for (String temp : elements) {
//                String[] aux = temp.split(",");
//                conteo[Integer.parseInt(aux[4]) - 1]++;
//            }
//            BufferedImage img = ImageIO.read(new File(PATH + NAME + ".png"));
//            Graphics2D graph = img.createGraphics();
//
//            for (String temp : elements) {
//                String[] aux = temp.split(",");
//                int x = Integer.parseInt(aux[1]);
//                int y = Integer.parseInt(aux[2]);
//                int w = Integer.parseInt(aux[3]);
//                int h = Integer.parseInt(aux[4]);
//                int j = Integer.parseInt(aux[5]);
//                graph.setColor(Color.RED);
//                graph.setStroke(new BasicStroke(10));
//                graph.draw(new Rectangle(x, y, w, h));
                
//                if (x < 1000 && y < 400 && w < 700 && h < 400) {
//                    graph.setColor(Color.RED);
//                    graph.draw(new Rectangle(x, y, w, h));
//                }
//                else if (j < maxj && conteo[j - 1] > conteo[j]) {
//                    if(w<600 && h<600){
//                        graph.setColor(Color.RED);
//                        graph.draw(new Rectangle(x, y, w, h));
//                    }
//                }
//            }
//            graph.dispose();
//            ImageIO.write(img, "png", new File(PATH + NAME + ".png"));
//            
//            for (int i = 1; i <= maxc; ++i) {
//                //if (i==1){
//                BufferedImage img = ImageIO.read(new File(PATH1 + NAME +".png"));
//                Graphics2D graph = img.createGraphics();
//
//                for (String temp : elements) {
//                    String[] aux = temp.split(",");
//                    int x = Integer.parseInt(aux[1]);
//                    int y = Integer.parseInt(aux[2]);
//                    int w = Integer.parseInt(aux[3]);
//                    int h = Integer.parseInt(aux[4]);
//                    int c1 = Integer.parseInt(aux[7]);
////                    double xp = 1608.0/1349.0;
////                    double yp = 1820.0/1328.0;
////                    
////                    int xprima = (int) (x*xp);
////                    int yprima = (int) (y*yp);
////                    int wprima = (int) (w*xp);
////                    int hprima = (int) (h*yp);
//
//                    if (x < 700 && y < 300 && w < 700 && h < 400) {
//                        graph.setColor(Color.RED);
//                        //graph.setStroke(new BasicStroke(5));
//                        graph.draw(new Rectangle(x, y, w, h));
////                        graph.draw(new Rectangle((int)(x*xp),(int) (y*yp), (int)(w*xp), (int)(h*yp)));
//                    }
//                    
//                    if (x < 700 && y > 200 && y < 400 && w > 700 && w < 1000 && h < 100) {
//                        graph.setColor(Color.RED);
//                        graph.draw(new Rectangle(x, y, w, h));
//                    }
//                    
//                    if (c1 == i) {
//                        graph.setColor(Color.RED);
//                        //graph.setStroke(new BasicStroke(5));
//                        graph.draw(new Rectangle(x, y, w, h));
////                        graph.draw(new Rectangle((int)(x*xp),(int) (y*yp), (int)(w*xp), (int)(h*yp)));
//                    }
//                }
//                graph.dispose();
//                ImageIO.write(img, "png", new File(PATH +"cluster" +NAME + i + ".png"));
//            }

//            BufferedImage img = ImageIO.read(new File(PATH + NAME + "1.png"));
            BufferedImage img = ImageIO.read(new File(PATH1 + (k+1)+ ".png"));
////            System.out.println(PATH1+name+ ".png");
//            BufferedImage img = ImageIO.read(new File(PATH1+NAME+ ".png"));
            Graphics2D graph = img.createGraphics();
            graph.setFont(graph.getFont().deriveFont(20f));
//            
            for (String temp : elements) {
                String[] aux = temp.split(",");
                int x = Integer.parseInt(aux[0]);
                int y = Integer.parseInt(aux[1]);
                int w = Integer.parseInt(aux[2]);
                int h = Integer.parseInt(aux[3]);
                //double t = Double.parseDouble(aux[5]);
                
                //if(t>0){
//                    graph.setColor(Color.BLACK);
//                    graph.drawString(aux[4], x+15, y+15);
                    graph.setColor(Color.RED);
                    graph.draw(new Rectangle(x, y, w, h));
                //}
            }
            graph.dispose();
            ImageIO.write(img, "png", new File(PATH + "ObjectsCluster" + (k+1)+".png"));
//            ImageIO.write(img, "png", new File(PATH + NAME + ".png"));
//            ImageIO.write(img, "png", new File(PATH+"cluster"+name+num+ ".png"));

        }
    }
}
