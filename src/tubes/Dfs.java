  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows 8.1 Pro
 */
public class Dfs extends Thread {
    static Queue qq = Game.qd;
//    static boolean df = false;
    int lg = 16; 
    static int gerak [] = new int [10000];
    static Kotak ortu [] = new Kotak[20000];
    int [] path = new int[1000];
    static int jmlhKembang = 0;
    static int gi = 1;
    static FileWriter dfsFile;
    public Dfs() throws IOException
    {
        dfsFile = new FileWriter("Log/dfs.txt");
        dfsFile.flush();
    }
    static void getGerak() throws IOException
    {          
          dfsFile.write("Membutuhkan ekspansi sebanyak: "+jmlhKembang);
          dfsFile.write(13);dfsFile.write(10);
          int i =0;
          int indx = qq.head.ot;
          gerak[0] = qq.head.g;
          //System.out.print(gerak[0]);
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
          Game.ket.setText(String.valueOf(jmlhKembang));
          dfsFile.write("Jumlah gerakan : "+gi);
          dfsFile.write(13);dfsFile.write(10);          
          for(int jj =gi;jj>=0;jj--)
            {
                if(gerak[jj]==1){dfsFile.write("atas,");}
                if(gerak[jj]==2){dfsFile.write("bawah,");}
                if(gerak[jj]==3){dfsFile.write("kanan,");}
                if(gerak[jj]==4){dfsFile.write("kiri,");}
            }
            dfsFile.flush();
     }
    @Override
    public void run()
    {
      try{ //java lang stack overflow 
          while(true)
          {
            try {
                    Thread.sleep(Game.spd.getValue());
                     int index = Cek.getIndexNol(qq.head);
                   // System.out.println(qq.head.ot);
                    int [] tkir;int [] tAt;int [] tBa;
                    int [] tmph = new int[lg];
                    int [] tkan; 
                    int gg = 0;
                    int qwe = qq.head.ot;
                    Color y = Color.GREEN;
                    Kotak tvm = qq.head;
                    tmph=qq.head.data;
                   // int f = qq.head.ot;               
                   // System.out.println();                 
                    qq.popHead();
                    if(Cek.cekOrtu(tvm,ortu,jmlhKembang))
                    {    
    //                    System.out.println(qwe);
    //                    System.out.println("di kembangkan: ");
    //                    for(int i=0;i<lg;i++)
    //                    {            
    //                        System.out.print(tvm.data[i]+"::");
    //                    }
                        dfsFile.write("dikembangkaaaaaan  : " + tvm.ot+"\n ");
                        dfsFile.write(13);dfsFile.write(10);                            
                        for(int i=0;i<lg;i++)        
                        {    
                            dfsFile.write(tvm.data[i]+"::");
                        }
                        dfsFile.write(13);dfsFile.write(10);
                        Cek.setDataKotak(tmph);
                        Game.gm[index].setBackground(y);
                        ortu[jmlhKembang] = tvm;    
                        tkir = new int[lg]; 
                        tBa = new int[lg];
                        tkan = new int[lg];
                        tAt = new int[lg];
                        for(int i = 1;i<5;i++)
                        {                                                           
                                if("kiri".equals(Game.prio[i]))
                                {
                                    if(Cek.moveKiri(index))
                                    {
                                        gg=4;
                                        System.arraycopy(tmph, 0, tkir, 0, lg);
                                        tkir[index] =  tmph[index-1];
                                        tkir[index-1] = tmph[index];  
                                        qq.addKotak(tkir,jmlhKembang,gg,0);
                                    }                                     
                                }                                
                                if("kanan".equals(Game.prio[i]))
                                {
                                    if(Cek.moveKanan(index))
                                    {
                                        gg=3;
                                        System.arraycopy(tmph, 0, tkan, 0, lg);
                                        tkan[index] = tmph[index+1];
                                        tkan[index+1] = tmph[index];  
                                        qq.addKotak(tkan,jmlhKembang,gg,0);                                
                                    }                                    
                                }
                                if("atas".equals(Game.prio[i]))
                                {
                                    if(Cek.moveAtas(index))
                                    {  
                                        gg = 1;
                                        System.arraycopy(tmph, 0, tAt, 0, lg);
                                        tAt[index] =  tmph[index-4];
                                        tAt[index-4] = tmph[index];
                                        qq.addKotak(tAt,jmlhKembang,gg,0);    
                                    }  
                                }
                                if("bawah".equals(Game.prio[i]))
                                {
                                    if(Cek.moveBawah(index))
                                    {
                                       gg = 2;
                                       System.arraycopy(tmph, 0, tBa, 0, lg);
                                       tBa[index] =  tmph[index+4];
                                       tBa[index+4] = tmph[index];   
                                       qq.addKotak(tBa,jmlhKembang,gg,0);                                    
                                    }                                    
                                }
                        }
                        qq.print(); 
                        jmlhKembang++;       
                    }
                    else
                    {
                        dfsFile.write("skip karena pernah dikembangkan");
                         dfsFile.write(13);dfsFile.write(10);
                         for(int i=0;i<lg;i++)        
                         {
                            dfsFile.write(tvm.data[i]+"::");
                         }   
                        Game.gm[index].setCaretColor(Color.blue);
                        Game.gm[index].setBackground(Color.white);

                    }
                  if(!Cek.cekDataHead(qq.head))
                  {
                      //System.out.println(qq.size);
                      run();
                  }
                  else
                  {
                     dfsFile.write(13);dfsFile.write(10);
                     dfsFile.write(13);dfsFile.write(10);
                     dfsFile.write("solusi ditemukan dengan panjang antrian : " + qq.size);
                     dfsFile.write(13);dfsFile.write(10);
                      getGerak();
                       Game.gm[Cek.getIndexNol(qq.head)].setBackground(Color.blue);
                       Cek.clearWarna();
                       Cek.setDataKotak(ortu[0].data);
                       Solved solv = new Solved(gerak,gi,ortu[0].data);  
                       qq.popAll();Game.qd.popAll();                      
                       gi = 1;
                       jmlhKembang = 0;
                       dfsFile.flush();
                       break;                     
                  }
                  break;
                }//try 
                catch (IOException ex) {
                    Logger.getLogger(Dfs.class.getName()).log(Level.SEVERE, null, ex);
                }                catch (InterruptedException ex) 
                {
                    Logger.getLogger(Mainf.class.getName()).log(Level.SEVERE, null, ex);
                }//catch
          }//while   
      }
      catch(java.lang.StackOverflowError ex)
      {
            try {
                System.out.println(ex);
                Game.ket.setText("Tidak ditemukan");
                dfsFile.flush();
            } catch (IOException ex1) {
                Logger.getLogger(Dfs.class.getName()).log(Level.SEVERE, null, ex1);
            }
      }
    }//run  
}
