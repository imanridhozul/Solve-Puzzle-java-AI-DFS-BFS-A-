/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.awt.Color;

/**
 *
 * @author Windows 8.1 Pro
 */
public class Cek {
    static int lg = 16;
    static int ht = 0;
    static int resetColor=0;
    
     static int cekSalah(int tt[])
     {
       //int goal [] = {1,2,3,8,0,4,7,6,5};
       int goal [] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
        boolean torf = false;
        int jumlahSama = 0;
        for(int i=0;i<lg;i++)
        {     
            if(tt[i] != goal[i])
            {
                jumlahSama++;
            }
        }        
        return jumlahSama;
     }
    static void resColor()
    {   
        ht++;
        if(ht==lg)
        {
            if(resetColor==0)
            {
                Mainf.c=Color.YELLOW;
                resetColor = 1;
            }
            else
            {
                Mainf.c=Color.red;
                resetColor = 0;
            }
        }     
    }
    static boolean moveKanan(int index)
    {
        boolean f=true;
        if(index ==3||index ==7||index ==11 || index==15)
        {
            f = false;
        }
        return f;
    }
    static boolean moveKiri(int index)
    {
        boolean f=true;
        if(index ==0||index ==4||index ==8||index ==12)
        {
            f = false;
        }
        return f;
    }
     static boolean moveAtas(int index)
    {
        boolean f=true;
        if(index ==0||index ==1||index ==2||index ==3)
        {
            f = false;
        }
        return f;
    }
     static boolean moveBawah(int index)
    {
        boolean f=true;
         if(index ==12||index ==13||index ==14||index ==15)
        {
            f = false;
        }
        return f;
    } 
     static void printOr(int jmlhKembang,Kotak ortu[])
     {
         for(int i=0;i<jmlhKembang;i++)
            {               
                for(int j = 0;j<lg;j++)
                {
                   System.out.print(ortu[i].data[j]+",,");
                } 
                 System.out.print(" : ortu : "+ortu[i].ot); 
                 System.out.println();
                 
            }      
     }
     static boolean cekOrtu(Kotak cek,Kotak ortu[],int jmlhKembang)
     {
         int smt = 1,jumlahSama = 0;
         boolean torf = true;
        for(int i=0;i<jmlhKembang;i++)
        {
            jumlahSama = 0;
            for(int j = 0;j<lg;j++)
            {
                if(cek.data[j]==ortu[i].data[j])
                {
                    jumlahSama++;
                }
            }
            if(jumlahSama==lg)
            {
                torf = false;
                break;
            }
        }
        return torf;
     }
     static boolean cekDataHead(Kotak head)
     {
       //int goal [] = {1,2,3,8,0,4,7,6,5};
        int goal [] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
        boolean torf = false;
        int jumlahSama = 0;
        for(int i=0;i<lg;i++)
        {     
            if(head.data[i] == goal[i])
            {
                jumlahSama++;
            }
        }
        if(jumlahSama==lg)
        {
            torf = true;
        }
        return torf;
     }
     static int getIndexNol(Kotak head)
    {
        int indexNol = 0;
        for(int i=0;i<lg;i++)
        {
            if(head.data[i]==0)
            {
                indexNol = i;
            }
        }
        return indexNol;
    }
     static void clearWarna()
    {
         for(int k=0;k<lg;k++)
         {
            Game.gm[k].setBackground(Color.white);
         }
    }
    static void setDataKotak(int tmph[])
    {
         for(int k=0;k<lg;k++)
         {
            Game.gm[k].setText(String.valueOf(tmph[k]));
         }
    }     
}
