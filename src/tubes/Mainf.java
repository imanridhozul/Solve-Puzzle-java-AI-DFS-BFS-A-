/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Windows 8.1 Pro
 */
public class Mainf extends Thread{
    static Queue qq = Game.qb;
    static int lg = 16;
    static Color c = Color.red; 
    static int gi = 1;
    static int gerak [] = new int [10000];
    static Kotak ortu [] = new Kotak[20000];    
    static int jmlhKembang = 0;
    static FileWriter bfsFile;
    public Mainf() throws IOException
    {
        bfsFile = new FileWriter("Log/bfs.txt");
    }
    static void getGerak()
    {
        try {
            //System.out.println("membutuhkan sebanyak: "+jmlhKembang);   
             bfsFile.write("membutuhkan Ekspansi sebanyak: "+jmlhKembang);
             bfsFile.write(13);bfsFile.write(10);
            int i =0;
            int indx = qq.head.ot;
            gerak[0] = qq.head.g;
            while(i<jmlhKembang)
            {
                if(indx == i)
                {
                    indx = ortu[i].ot;                  
                    gerak[gi] = ortu[i].g;                
                    gi++;i=0;
                }
                i++;
            }
            //System.out.println("Jumlah gerakan"+gi);
            bfsFile.write("Jumlah gerakan : "+gi);
            bfsFile.write(13);bfsFile.write(10);
            Game.ket.setText(String.valueOf(jmlhKembang));
            for(int jj =gi;jj>=0;jj--)
            {
                if(gerak[jj]==1){bfsFile.write("atas,");}
                if(gerak[jj]==2){bfsFile.write("bawah,");}
                if(gerak[jj]==3){bfsFile.write("kanan,");}
                if(gerak[jj]==4){bfsFile.write("kiri,");}
            }
            bfsFile.flush();
        } //func gerak
        catch (IOException ex) {
            Logger.getLogger(Mainf.class.getName()).log(Level.SEVERE, null, ex);
        }
     }//func gerak
    @Override
    public void run()
    {
      try{
          while(true)
          {
            try {
                        Thread.sleep(Game.spd.getValue());
                        int index = Cek.getIndexNol(qq.head);
                        //System.out.println(index);
                        int [] tkir;int [] tAt;int [] tBa;
                        int [] tmph = new int[lg];
                        int [] tkan; 
                        int gg = 0;                  
                        Kotak tvm = qq.head;
                        tmph=qq.head.data;
                        qq.popHead();
                        if(Cek.cekOrtu(tvm,ortu,jmlhKembang))      
                        {
//                            System.out.println("dikembangkaaaaaan  : " + tvm.ot);
//                            for(int i=0;i<lg;i++)        
//                            {
//    
//                                System.out.print(tvm.data[i]+"::");
//                            }
//                            System.out.println();  
                            bfsFile.write("dikembangkaaaaaan  : " + tvm.ot+"\n ");
                            bfsFile.write(13);bfsFile.write(10);
                            
                            for(int i=0;i<lg;i++)        
                            {    
                                bfsFile.write(tvm.data[i]+"::");
                            }
                            bfsFile.write(13);bfsFile.write(10);
                            Game.gm[index].setBackground(Color.blue);                     
                            Cek.setDataKotak(tmph);                    
                            ortu[jmlhKembang]=tvm;                           
                            if(Cek.moveKiri(index))
                            {
                               // System.out.println("ini move kiri");
                                tkir = new int[lg];    
                                gg=4;
                                System.arraycopy(tmph, 0, tkir, 0, lg);
                                tkir[index] =  tmph[index-1];
                                tkir[index-1] = tmph[index];                
                                qq.addTail(tkir,jmlhKembang,gg,0);
                            }

                            if(Cek.moveAtas(index))
                            {
                                //System.out.println("ini move atas");
                                tAt = new int[lg];
                                gg = 1;
                                System.arraycopy(tmph, 0, tAt, 0, lg);
                                tAt[index] =  tmph[index-4];
                                tAt[index-4] = tmph[index];          
                                qq.addTail(tAt,jmlhKembang,gg,0);        
                            } 
                            if(Cek.moveBawah(index))
                            {
                                //System.out.println("ini move Bawah");
                                tBa = new int[lg];
                                gg=2;
                                System.arraycopy(tmph, 0, tBa, 0, lg);
                                tBa[index] =  tmph[index+4];
                                tBa[index+4] = tmph[index];
                                qq.addTail(tBa,jmlhKembang,gg,0);  
                            }
                            if(Cek.moveKanan(index))
                            {
                               // System.out.println("ini move kanan");
                                tkan = new int[lg];
                                gg=3;
                                System.arraycopy(tmph, 0, tkan, 0, lg);
                                tkan[index] = tmph[index+1];
                                tkan[index+1] = tmph[index];
                                qq.addTail(tkan,jmlhKembang,gg,0);                
                            }
                            jmlhKembang++;
                            Game.gm[index].setBackground(Color.red);       
                            qq.print();                        
                        }
                        else
                        {
//                               System.out.println("skip karena pernah dikembangkan");
//                               for(int i=0;i<lg;i++)        
//                                {
//                                    System.out.print(tvm.data[i]+"::");
//                                }
//                                Cek.printOr(jmlhKembang,ortu);
                               bfsFile.write("skip karena pernah dikembangkan");
                               bfsFile.write(13);bfsFile.write(10);
                               for(int i=0;i<lg;i++)        
                                {
                                    bfsFile.write(tvm.data[i]+"::");
                                }
                                //Cek.printOr(jmlhKembang,ortu);
                             // Game.gm[index].setBackground(Color.white);

                        }
                        bfsFile.write(13);bfsFile.write(10);
                        bfsFile.write(13);bfsFile.write(10);
//                        System.out.println();        
//                        System.out.println();

                       if(!Cek.cekDataHead(qq.head))
                       {
                              //System.out.println(qq.size);
                              run();
                       }                    
                       else
                       {
                             //System.out.println("solusi ditemukan" + qq.size);
                              bfsFile.write(13);bfsFile.write(10);
                              bfsFile.write(13);bfsFile.write(10);
                              bfsFile.write("solusi ditemukan dengan panjang antrian : " + qq.size);
                              bfsFile.write(13);bfsFile.write(10);
                              getGerak();
                              Game.gm[Cek.getIndexNol(qq.head)].setBackground(Color.blue);
                              bfsFile.write(13);bfsFile.write(10);
                              Cek.setDataKotak(ortu[0].data);
                              Cek.clearWarna();
                              Solved solv = new Solved(gerak,gi,ortu[0].data);
                              qq.popAll();
                              Game.qb.popAll();                             
                              gi = 1;
                              jmlhKembang = 0;
                              bfsFile.flush();
                              
                              break;                         
                       }
                       break;

                }//try 
                catch (IOException ex) {
                    Logger.getLogger(Mainf.class.getName()).log(Level.SEVERE, null, ex);
                }                catch (InterruptedException ex) 
                {
                    Logger.getLogger(Mainf.class.getName()).log(Level.SEVERE, null, ex);
                }//catcy

          }//while
      }
      catch(java.lang.StackOverflowError ex)
      {
            try {
                System.out.println(ex);
                Game.ket.setText("Tidak ditemukan");
                bfsFile.flush();
            } catch (IOException ex1) {
                Logger.getLogger(Mainf.class.getName()).log(Level.SEVERE, null, ex1);
            }
      }
    }//run 
}
