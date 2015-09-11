/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package akori;

import static cern.jet.stat.Probability.normal;
import java.util.Random;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 *
 * @author Claudio
 */
public class Normal {
    static public void main(String[] args){
        NormalDistribution n =new NormalDistribution(0,0.4); 
        
        for (double i=0; i< 1;i=i+(1.0/50.0)){
            System.out.println("i: "+i);
            System.out.println("densidad: "+n.density(i));
        }
    } 
}
