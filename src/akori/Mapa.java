/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package akori;

import static cern.jet.stat.Probability.normal;
import static cern.jet.stat.Probability.normal;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.imageio.*;

/**
 *
 * @author Claudio
 */
public class Mapa {
    
    static public void main(String[] args) throws IOException{
        BufferedImage img = new BufferedImage(1600, 900,BufferedImage.TYPE_INT_ARGB);
        Graphics2D graph = img.createGraphics();
        double[][] matrix = new double[900][1600];
        BufferedReader in = new BufferedReader(new FileReader("et.txt"));
        String linea;
        double max =0;
        BufferedImage overlay = ImageIO.read(new File("1_1.png"));
        graph.drawImage(overlay, 0, 0, null);
        HashMap<String,Integer> lista = new HashMap<String,Integer>();
        int count=0;
        for(int i =0; (linea = in.readLine()) != null && i<10000; ++i){
            String[] datos = linea.split(",");
            int x = (int) Double.parseDouble(datos[0]);
            int y = (int) Double.parseDouble(datos[2]);
            long time =Double.valueOf(datos[4]).longValue();
            if(x>=1600 || y>=900) continue;
            if(time <691215)continue;
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
        float F,n;
        int R,G,B,A;
        for (int i =0; i<1600;++i){
            for(int j = 0;j<900;++j ){
                if(matrix[j][i]!=0){
//                    n = (int) Math.round(matrix[j][i]*100/max);
//                    R=Math.round((255*n)/100);
//                    G=Math.round((255*(100-n))/100);
//                    B=0;
//                    A =Math.round((255*n)/100);
//                    if(R>255) R=255;
//                    if(R<0) R=0;
//                    if(G>255) G=255;
//                    if(G<0) G=0;
//                    if(R<50) A=0; 
//                    graph.setColor(new Color(R,G,B,A));
                    n = (float) ((float) matrix[j][i]*100/max);
                    F = (float) ((float) 0.7 - 0.007*n);
                    //System.out.println("0.7 - 00.7*"+n+" = "+R);
                    Color c = Color.getHSBColor(F, (float) 0.9, (float) 0.9);
                    B = c.getBlue();
                    R = c.getRed();
                    G = c.getGreen();
                    graph.setColor(new Color(R,G,B,R));
                    graph.fillOval(i, j, 2, 2);
                }
            }
        }
        graph.dispose();
        
        ImageIO.write(img, "png", new File("example.png"));
        System.out.println(max);
    }
    
    static public double normalMatrix(double[][] matrix, int x, int y, int std){
        double max = 0;
        for(int i = x-std; i<x+std && matrix.length >i && i>=0 ; ++i){
            for(int j = y-std; j<y+std && matrix[0].length >j && j>=0;++j){
                double r = Math.sqrt((i-x)*(i-x)+(j-y)*(j-y)); 
                if( r> 0 && r<=std){
                    matrix[i][j] = matrix[i][j] + std*100/(normal(0,std,r));
                    if(max<matrix[i][j])
                        max = matrix[i][j];
                }
            }
        }
        return max;
    }
}
