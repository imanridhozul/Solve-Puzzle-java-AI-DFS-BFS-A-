/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.io.IOException;

/**
 *
 * @author Windows 8.1 Pro
 */
public class Queue {
     Kotak head=null,tail=null;
     int size;
    void addKotak(int dbaru[],int ot,int gg, int b)
    {
        Kotak tmp = new Kotak();
        tmp.data =dbaru;
        tmp.g = gg;tmp.ot = ot;
        tmp.salah = b;
        if(head==null)
        {
           head=tmp;
           tail=tmp;
           size++;
        }
        else
        {
            tmp.next = head;
            head.back = tmp;
            tmp.back=null;
            head = tmp;
            size++;
        }
    }
     void addTail(int dbaru[],int ot,int gg,int b)
    {
        Kotak tmp = new Kotak();
        tmp.data =dbaru;
        tmp.g = gg;tmp.ot = ot;
        tmp.salah = b;
        if(head==null)
        {
           head=tmp;
           tail=tmp;
           size++;
        }
        else
        {
            tail.next = tmp;
            tmp.back = tail;            
            tail = tmp;
            size++;
        }
    }
    void popHead()
    {
        Kotak tmp = head;
        head = head.next;
        tmp.next=null;
        size--;
    }
    void popAll()
    {
        while (size!=0)
        {
            popHead();
        }
    }
   void print() throws IOException
   {
         if(Game.pil<2)
         {
             Mainf.bfsFile.write("isi Queue : ");
             Mainf.bfsFile.write(13);Mainf.bfsFile.write(10);
             //System.out.println("isi Queue : ");
             for(Kotak j = head;j!=tail.next;j=j.next)
             {
                 for(int i=0;i<16;i++)
                 {
                    // System.out.print(j.data[i]+",");
                     Mainf.bfsFile.write(j.data[i]+",");
                     if(!Cek.moveKanan(i))
                     {
                         Mainf.bfsFile.write(13);Mainf.bfsFile.write(10);
                     }
                 }
                 //System.out.println("salah : "+j.salah + " OT : " + j.ot +" gerak : " +j.g); 
                  Mainf.bfsFile.write(" OT : " + j.ot +" gerak : " +j.g);
                  Mainf.bfsFile.write(13);Mainf.bfsFile.write(10);
             }
             //System.out.println("END Isi");
             Mainf.bfsFile.write("END ISI");
             Mainf.bfsFile.write(13);Mainf.bfsFile.write(10);
             Mainf.bfsFile.write(13);Mainf.bfsFile.write(10);
            // System.out.println();
         }
         else if(Game.pil<3)
         {
             Star.starFile.write("isi Queue : ");
             Star.starFile.write(13);Star.starFile.write(10);
             //System.out.println("isi Queue : ");
             for(Kotak j = head;j!=tail.next;j=j.next)
             {
                 for(int i=0;i<16;i++)
                 {
                    // System.out.print(j.data[i]+",");
                     Star.starFile.write(j.data[i]+",");
                     if(!Cek.moveKanan(i))
                     {
                         Star.starFile.write(13);Star.starFile.write(10);
                     }
                 }
                  Star.starFile.write("salah : "+j.salah + " OT : " + j.ot +" gerak : " +j.g);                  
                  Star.starFile.write(13);Star.starFile.write(10);
             }
             //System.out.println("END Isi");
             Star.starFile.write("END ISI");
             Star.starFile.write(13);Star.starFile.write(10);
             Star.starFile.write(13);Star.starFile.write(10);
            // System.out.println();
         }
         else if(Game.pil<4)
         {
             Dfs.dfsFile.write("isi Queue : ");
              Dfs.dfsFile.write(13); Dfs.dfsFile.write(10);
             //System.out.println("isi Queue : ");
             for(Kotak j = head;j!=tail.next;j=j.next)
             {
                 for(int i=0;i<16;i++)
                 {                   
                      Dfs.dfsFile.write(j.data[i]+",");
                     if(!Cek.moveKanan(i))
                     {
                          Dfs.dfsFile.write(13); Dfs.dfsFile.write(10);
                     }
                 }
                   Dfs.dfsFile.write(" OT : " + j.ot +" gerak : " +j.g);                  
                   Dfs.dfsFile.write(13); Dfs.dfsFile.write(10);
             }
             //System.out.println("END Isi");
             Dfs.dfsFile.write("END ISI");
             Dfs.dfsFile.write(13); Dfs.dfsFile.write(10);
            Dfs.dfsFile.write(13); Dfs.dfsFile.write(10);
            // System.out.println();
         }
         
         
         
     }
     void sort()
     {
         Kotak tmp = null; 
         int tor = 0;
         for(Kotak i = head;i!=tail.next;i=i.next)
         {
                 
             for(Kotak j = i.next;j!=tail.next;j=j.next)
             {
                 if(i.salah>j.salah)
                 {
                    int fs = i.salah;
                    i.salah = j.salah;
                    j.salah = fs;
                    
                    int fot = i.ot;
                    i.ot=j.ot;
                    j.ot = fot;
                    
                    int gg = i.g;
                    i.g = j.g;
                    j.g =gg;
                    
                    int d[] = i.data;
                    i.data = j.data;
                    j.data=d;
                    
                 }
             }//for
         }//for outer
     }//func sort
}
