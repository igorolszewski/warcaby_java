
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;


public class Warcaby extends JFrame implements MouseListener
{
    public int[][]plansza={{0,2,0,2,0,2,0,2},
                           {2,0,2,0,2,0,2,0},
                           {0,2,0,2,0,2,0,2},
                           {1,0,1,0,1,0,1,0},
                           {0,1,0,1,0,1,0,1},
                           {3,0,3,0,3,0,3,0},
                           {0,3,0,3,0,3,0,3},
                           {3,0,3,0,3,0,3,0}};
    
    public int[][]planszaT={{0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0}};
    
    private int kolumna;
    private int wiersz;
    private boolean podswietLewo2=false;
    private boolean podswietlPrawo1=false;
    private boolean podswietLewo1=false;                       
    private boolean podswietPrawo2=false;
    private int bicieW;
    private int bicieK;
    private int zmianaGracza=2;
    
    
    public  Warcaby()
    {
        setSize(500, 500);
       
       
        addMouseListener(this);
       
    }
    
    public static void main(String[] args) 
    {
       Warcaby warcaby= new Warcaby();
       warcaby.setVisible(true);
       warcaby.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    public void paint(Graphics g)
    {
        rysujPlansze(g);
    }
    
    public void rysujLinie(Graphics2D g2)
    {
        for(int j=0; j<9; j++)
            {
                g2.setColor(Color.red);
                g2.fillRect(50, 50, 407, 407);
            }
        
    }
    
    public void rysujPlansze(Graphics g)
    {
        Image img= createImage(getSize().width, getSize().height);
        Graphics2D g2= (Graphics2D)img.getGraphics();
        rysujLinie(g2);
        
        for(int i=0;i<8;i++)
        {
            for(int j=0; j<8;j++)
            {
                
                switch(plansza[i][j])
                {
                    case 0:
                        g2.setColor(Color.white);
                        g2.fillRect(j+50+j*50, i+50+i*50, 50, 50);
                        break;
                    case 1:
                        g2.setColor(Color.black); 
                         
                        if(planszaT[i][j]==2)
                            g2.setColor(Color.pink);
                       if(planszaT[i][j]==3)
                            g2.setColor(Color.yellow);
                        
                            
                        g2.fillRect(j+50+j*50, i+50+i*50, 50, 50);
                        break;  
                    case 2:
                        g2.setColor(Color.black);
                        g2.fillRect(j+50+j*50, i+50+i*50, 50, 50);
                        g2.setColor(Color.red);
                        
                         if(planszaT[i][j]==1)
                            g2.setColor(Color.pink);
                       
                        
                        g2.fillOval(j+53+j*50, i+53+i*50, 44, 44);
                        
                        break;
                    case 3:
                        g2.setColor(Color.black);
                        g2.fillRect(j+50+j*50, i+50+i*50, 50, 50);
                        g2.setColor(Color.LIGHT_GRAY);
                        
                        if(planszaT[i][j]==4)
                            g2.setColor(Color.yellow);
                        
                        g2.fillOval(j+53+j*50, i+53+i*50, 44, 44);
                        
                        break;    
                }
            }
        }
       
       
        
        
        
        g.drawImage(img, 0, 0, this);
    }
    
    public void ktorePole(int x, int y)
    {
        if(x>50&&x<450&&y>50&&y<450)
        {
            
             wiersz= y/50-1;
            kolumna=x/50-1;
            
        }
    }
    
    public void podswietlG2(int w, int k)
    {
        if(plansza[w][k]==2)
        {
            for(int ww=0;ww<8;ww++)
            {
                for(int kk=0; kk<8;kk++)
                {
                   planszaT[ww][kk]=0; 
                }
            }
         planszaT[w][k]=1;
          //lewo  
         if(w+1<=7&&k-1>=0)
         {
             podswietLewo1=true;
             
             if(plansza[w+1][k-1]==1)
             {
                 
                 planszaT[w+1][k-1]=2;
             }
             else
             {
                 
                 planszaT[w+1][k-1]=0;
                 podswietLewo1=false;
             }
                 
                 
             
            
             if(w+2<=7&&k-2>=0)
             {
                 podswietLewo2=true;
                 
                 if(plansza[w+2][k-2]==1&&plansza[w+1][k-1]==3)
                 {
                     bicieW=w+1;
                     bicieK=k-1;
                 planszaT[w+2][k-2]=2;
                 }
                 else
                 {
                     podswietLewo2=false;
                  planszaT[w+2][k-2]=0;
                 }
             }
             else
             {
                 podswietLewo2=false;
             }
             
         }
         else
         {
             podswietLewo1=false;
         }
         //prawo
         if(w+1<=7&&k+1<=7)
         {
             podswietlPrawo1=true;
             
             if(plansza[w+1][k+1]==1)
             {
                 planszaT[w+1][k+1]=2;
             }
             else
             {
                 planszaT[w+1][k+1]=0;
                 podswietlPrawo1=false;
             }
             
             
              if(w+2<=7&&k+2<=7)
             {
                podswietPrawo2=true;
                 
                 if(plansza[w+2][k+2]==1&&plansza[w+1][k+1]==3)
                 {
                     bicieW=w+1;
                     bicieK=k+1;
                 planszaT[w+2][k+2]=2;
                 }
                 else
                 {
                     podswietPrawo2=false;
                  planszaT[w+2][k+2]=0;
                 }
             }
             else
             {
                 podswietPrawo2=false;
             }
         
         
         
         
         
         }
         else
             podswietlPrawo1=false;
         
        
        
        }
        else if(planszaT[w][k]==2)
        {
           for(int ww=0;ww<8;ww++)
            {
                for(int kk=0; kk<8;kk++)
                {
                
                    if(planszaT[ww][kk]==1)
                    {
                        plansza[ww][kk]=1;
                        plansza[w][k]=2;
                        planszaT[ww][kk]=0;
                        zmianaGracza=3;
                        if(ww+2==w)
                        {
                            if(k+2==kk)
                            {
                                //lewo
                                plansza[ww+1][kk-1]=1;
                            }
                            else if(k-2==kk)
                            {
                                //prawo
                                plansza[ww+1][kk+1]=1;
                            }
                        }
                    }
                    planszaT[ww][kk]=0;
                    
                }
            }
        }
        
    }
    
    
    public void podswietlG3(int w, int k)
    {
        if(plansza[w][k]==3)
        {
            for(int ww=0;ww<8;ww++)
            {
                for(int kk=0; kk<8;kk++)
                {
                   planszaT[ww][kk]=0; 
                }
            }
         planszaT[w][k]=4;
          //lewo  
         if(w-1>=0&&k-1>=0)
         {
             podswietLewo1=true;
             
             if(plansza[w-1][k-1]==1)
             {
                 
                 planszaT[w-1][k-1]=3;
             }
             else
             {
                 
                 planszaT[w-1][k-1]=0;
                 podswietLewo1=false;
             }
                 
                 
             
            
             if(w-2<=7&&k-2>=0)
             {
                 podswietLewo2=true;
                 
                 if(plansza[w-2][k-2]==1&&plansza[w-1][k-1]==2)
                 {
                     bicieW=w-1;
                     bicieK=k-1;
                 planszaT[w-2][k-2]=3;
                 }
                 else
                 {
                     podswietLewo2=false;
                  planszaT[w-2][k-2]=0;
                 }
             }
             else
             {
                 podswietLewo2=false;
             }
             
         }
         else
         {
             podswietLewo1=false;
         }
         //prawo
         if(w-1>=0&&k+1<=7)
         {
             podswietlPrawo1=true;
             
             if(plansza[w-1][k+1]==1)
             {
                 planszaT[w-1][k+1]=3;
             }
             else
             {
                 planszaT[w-1][k+1]=0;
                 podswietlPrawo1=false;
             }
             
             
              if(w-2>=0&&k+2<=7)
             {
                podswietPrawo2=true;
                 
                 if(plansza[w-2][k+2]==1&&plansza[w-1][k+1]==2)
                 {
                     bicieW=w-1;
                     bicieK=k-1;
                 planszaT[w-2][k+2]=3;
                 }
                 else
                 {
                     podswietPrawo2=false;
                  planszaT[w-2][k+2]=0;
                 }
             }
             else
             {
                 podswietPrawo2=false;
             }
         
         
         
         
         
         }
         else
             podswietlPrawo1=false;
         
        
        
        }
        else if(planszaT[w][k]==3)
        {
           for(int ww=0;ww<8;ww++)
            {
                for(int kk=0; kk<8;kk++)
                {
                
                    if(planszaT[ww][kk]==4)
                    {
                        plansza[ww][kk]=1;
                        plansza[w][k]=3;
                        planszaT[ww][kk]=0;
                        zmianaGracza=2;
                        if(ww-2==w)
                        {
                            if(k+2==kk)
                            {
                                //lewo
                                plansza[ww-1][kk-1]=1;
                            }
                            else if(k-2==kk)
                            {
                                //prawo
                                plansza[ww-1][kk+1]=1;
                            }
                        }
                    }
                    planszaT[ww][kk]=0;
                    
                }
            }
        }
        
    }

    
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        ktorePole(e.getX(), e.getY());
        if(zmianaGracza==2)
        podswietlG2(wiersz, kolumna);
        else if(zmianaGracza==3)
        podswietlG3(wiersz, kolumna);
        
        
        
        repaint();
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
