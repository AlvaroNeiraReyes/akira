/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akori;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 *
 * @author Claudio
 */
public class Impact {

    static public void main(String[] args) throws IOException {
        String PATH = "E:\\Trabajos\\AKORI\\datosmatrizgino\\";
        String PATHIMG = "E:\\NetBeansProjects\\AKORI\\Proccess_1\\ImagesPages\\";
        for (int i = 1; i <=32; ++i) {
            for(int k = 1; k <=15; ++k){
            System.out.println("Matrix " + i + "-" + k);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(PATHIMG + i + ".png"));
            } catch (IOException ex) {
                ex.getStackTrace();
            }

            int ymax = img.getHeight();
            int xmax = img.getWidth();

            double[][] imagen = new double[ymax][xmax];

            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(PATH + i +"-" +k+".txt"));
            } catch (FileNotFoundException ex) {
                ex.getStackTrace();
            }

            String linea;
            ArrayList<String> lista = new ArrayList<String>();
            HashMap<String, String> lista1 = new HashMap<String, String>();
            try {
                for (int j = 0; (linea = in.readLine()) != null; ++j) {
                    String[] datos = linea.split(",");
                    int x = (int) Double.parseDouble(datos[1]);
                    int y = (int) Double.parseDouble(datos[2]);
                    if (x >= xmax || y >= ymax || x <= 0 || y <= 0) {
                        continue;
                    }
                    lista.add(x + "," + y);
                }
            } catch (Exception ex) {
                ex.getStackTrace();
            }

            try {
                in.close();
            } catch (IOException ex) {
                ex.getStackTrace();
            }

            Iterator iter = lista.iterator();
            int[][] matrix = new int[lista.size()][2];

            for (int j = 0; iter.hasNext(); ++j) {
                String xy = (String) iter.next();
                String[] datos = xy.split(",");
                matrix[j][0] = Integer.parseInt(datos[0]);
                matrix[j][1] = Integer.parseInt(datos[1]);
            }

            for (int j = 0; j < matrix.length; ++j) {

                int std = 50;
                int x = matrix[j][0];
                int y = matrix[j][1];
                imagen[y][x] += 1;
                double aux;
                normalMatrix(imagen, y, x, std);

            }

            FileWriter fw = new FileWriter(PATH+"Matrix"+i+"-"+k+".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int j = 0; j < imagen.length; ++j) {
                for(int t = 0 ; t<imagen[j].length; ++t){
                    if (t+1==imagen[j].length)
                        bw.write(imagen[j][t]+"");
                    else
                        bw.write(imagen[j][t]+",");
                }
                bw.write("\n");
            }
            bw.close();
        }
        }
    }

    static public void normalMatrix(double[][] matrix, int x, int y, int std) {
        double max = 0;
        NormalDistribution n = new NormalDistribution(0, 0.4);
        for (int i = x - std; i < x + std && matrix.length > i && i >= 0; ++i) {
            for (int j = y - std; j < y + std && matrix[0].length > j && j >= 0; ++j) {
                double r = Math.sqrt((i - x) * (i - x) + (j - y) * (j - y));
                if (r > 0 && r <= std) {
                    matrix[i][j] = matrix[i][j] + n.density(r / std);
                }
            }
        }
    }
}
