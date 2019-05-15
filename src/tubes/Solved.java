/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows 8.1 Pro
 */
public class Solved extends Thread {
    static int geger [],jumlah,posAwal[];
    int lg = 16;
    public Solved (int g[],int j,int pAwal[])
    {
        geger = g;
        jumlah = j;
        posAwal = pAwal;
    }
    public Solved ()
    {
       
    }
    int getIndexNol(int t[])
    {
        int indexNol = 0;
        for(int i=0;i<lg;i++)
        {
            if(t[i]==0)
            {
                indexNol = i;
            }
        }
        return indexNol;
    }
    @Override
    public void run()
    {
      for(int i = jumlah-1;i>=0;i--)                 
      {
        try {
                 Thread.sleep(Game.spd.getValue());
                 int tmph [] = new int[lg]; 
                 int index = getIndexNol(posAwal);                     
                     if(geger[i]==4)
                        {
                          for(int j=0;j<lg;j++)
                            {
                                tmph[j] = posAwal[j];
                            }
                            tmph[index] =  posAwal[index-1];
                            tmph[index-1] = posAwal[index];
                            posAwal = tmph;  
                            Cek.setDataKotak(tmph);
                            Game.gm[getIndexNol(tmph)].setBackground(Color.red); 
                        }
                     if(geger[i]==1)
                        {
                            for(int j=0;j<lg;j++)
                            {
                                tmph[j] = posAwal[j];
                            }
                            tmph[index] =  posAwal[index-4];
                            tmph[index-4] = posAwal[index];
                            posAwal = tmph;    
                            Cek.setDataKotak(tmph);
                            Game.gm[getIndexNol(tmph)].setBackground(Color.red); 
                        } 
                        if(geger[i]==2)
                        {
                            
                            for(int j=0;j<lg;j++)
                            {
                                tmph[j] = posAwal[j];
                            }
                            tmph[index] =  posAwal[index+4];
                            tmph[index+4] = posAwal[index];
                            posAwal = tmph;   
                            Cek.setDataKotak(tmph);
                            Game.gm[getIndexNol(tmph)].setBackground(Color.red); 
                           
                        }
                        if(geger[i]==3)
                        {
                           for(int j=0;j<lg;j++)
                            {
                                tmph[j] = posAwal[j];
                            }
                            tmph[index] =  posAwal[index+1];
                            tmph[index+1] = posAwal[index];
                            posAwal = tmph;  
                            Cek.setDataKotak(tmph);
                            Game.gm[getIndexNol(tmph)].setBackground(Color.red); 
                        }
            }//try 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(Solved.class.getName()).log(Level.SEVERE, null, ex);
            }//catcy
         
      }//while
      
      
    }//run 
}
