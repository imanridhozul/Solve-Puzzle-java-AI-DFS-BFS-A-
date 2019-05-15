/*oooooooooooo
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows 8.1 Pro
 */
public class Star extends Thread{
    static Queue qq = Game.qs;
    static int lg = 16;
     static int gi = 1;
    static Color c = Color.red;   
    static int gerak [] = new int [1000];
    static Kotak ortu [] = new Kotak[2000];
    static int jmlhKembang = 0;
    static FileWriter starFile;
    public Star() throws IOException
    {
        starFile = new FileWriter("Log/A Star.txt");
        starFile.flush();
    }
    static void getGerak() throws IOException
    {
         // System.out.println(" membutuhkan sebanyak: "+jmlhKembang);         
          starFile.write("Membutuhkan Ekspansi sebanyak: "+jmlhKembang);
          starFile.write(13);starFile.write(10);
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
          Game.ket.setText(String.valueOf(jmlhKembang));
          starFile.write("Jumlah gerakan : "+gi);
            starFile.write(13);starFile.write(10);
            Game.ket.setText(String.valueOf(jmlhKembang));
            for(int jj =gi;jj>=0;jj--)
            {
                if(gerak[jj]==1){starFile.write("atas,");}
                if(gerak[jj]==2){starFile.write("bawah,");}
                if(gerak[jj]==3){starFile.write("kanan,");}
                if(gerak[jj]==4){starFile.write("kiri,");}
            }
            starFile.flush();
    }//func gerak
    @Override
    public void run()
    {
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
                    qq.sort();
                    Kotak tvm = qq.head;
                    tmph=qq.head.data;                                    
                    int f = qq.head.ot;
                    //System.out.println("dikembangkaaaaaan  : " + tvm.ot+"   "+jmlhKembang);
                     starFile.write("dikembangkaaaaaan  : " + tvm.ot+"\n ");
                     starFile.write(13);starFile.write(10);
                     for(int i=0;i<lg;i++)        
                     {starFile.write(tvm.data[i]+"::");}  
                     starFile.write(13);starFile.write(10);
                        qq.popHead();    
                        Cek.setDataKotak(tmph);
                        Game.gm[index].setBackground(c);    
                        ortu[jmlhKembang]=tvm; 
                        if(Cek.moveKiri(index))
                        {
                           // System.out.println("ini move kiri");
                            tkir = new int[lg];    
                            gg=4;
                            System.arraycopy(tmph, 0, tkir, 0, lg);
                            tkir[index] =  tmph[index-1];
                            tkir[index-1] = tmph[index];              
                            int slh = Cek.cekSalah(tkir)+jmlhKembang;                          
                            qq.addKotak(tkir,jmlhKembang,gg,slh);
                        }
                        if(Cek.moveAtas(index))
                        {
                            //System.out.println("ini move atas");
                            tAt = new int[lg];
                            gg = 1;
                            System.arraycopy(tmph, 0, tAt, 0, lg);
                            tAt[index] =  tmph[index-4];
                            tAt[index-4] = tmph[index];
                             int slh = Cek.cekSalah(tAt)+jmlhKembang;                          
                             qq.addKotak(tAt,jmlhKembang,gg,slh);      
                           
                        } 
                        if(Cek.moveBawah(index))
                        {
                            //System.out.println("ini move Bawah");
                            tBa = new int[lg];
                            gg=2;
                            System.arraycopy(tmph, 0, tBa, 0, lg);
                            tBa[index] =  tmph[index+4];
                            tBa[index+4] = tmph[index];
                             int slh = Cek.cekSalah(tBa)+jmlhKembang;                        
                             qq.addKotak(tBa,jmlhKembang,gg,slh);        
                         
                        }
                        if(Cek.moveKanan(index))
                        {
                           // System.out.println("ini move kanan");
                            tkan = new int[lg];
                            gg=3;
                            System.arraycopy(tmph, 0, tkan, 0, lg);
                            tkan[index] = tmph[index+1];
                            tkan[index+1] = tmph[index];
                             int slh = Cek.cekSalah(tkan)+jmlhKembang;                            
                             qq.addKotak(tkan,jmlhKembang,gg,slh);                          
                        }    
                        jmlhKembang++;
                        qq.sort();
                        qq.print();
//                    else
//                    {
//                         System.out.println("skip karena pernah dikembangkan");
//                          Cek.printOr(jmlhKembang,ortu);
//                          //Game.gm[index].setBackground(Color.white);
//
//                    }
//                    System.out.println();        
//                    System.out.println();
//                    System.out.println();
//                    System.out.println();
                   starFile.write(13);starFile.write(10);
                   starFile.write(13);starFile.write(10);
                   if(!Cek.cekDataHead(qq.head))
                   {
                          run();
                   }
                   else
                   {
                          starFile.write(13);starFile.write(10);
                          starFile.write(13);starFile.write(10);
                          starFile.write("solusi ditemukan dengan panjang antrian : " + qq.size);
                          starFile.write(13);starFile.write(10);
                          starFile.write(13);starFile.write(10);
                          getGerak();
                          Game.gm[Cek.getIndexNol(qq.head)].setBackground(Color.blue);
                          Cek.clearWarna();
                          Cek.setDataKotak(ortu[0].data);
                          Solved solv = new Solved(gerak,gi,ortu[0].data); 
                          qq.popAll();  
                          Game.qs.popAll();
                          gi = 1;
                          jmlhKembang = 0;
                          starFile.flush();
                          break;
                   }
                   break;
            }//try 
            catch (IOException ex) {
                Logger.getLogger(Star.class.getName()).log(Level.SEVERE, null, ex);
            }            catch (InterruptedException ex) 
            {
                Logger.getLogger(Star.class.getName()).log(Level.SEVERE, null, ex);
            }//catch
         
      }//while
    }//run 
}
