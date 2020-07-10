import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelGOver extends JPanel implements ActionListener, KeyListener {
  MainFrame mf;
  Dimension d;
  Timer timerPG;

  String con, exit, IRO;
  int Game_size, Over_size;
  int Game_y, Over_y;
  int Game_size_flag;
  int Over_size_flag;
  int GM_timing_flag;

  int color_changer;
  int color_changer_flag = 0;

  int press,press_flag;
  int change_panel_flag;
  int panel_serect_flag;

  int panel_choice_flag; //簡単か難しいかを判断する員数

  public PanelGOver(MainFrame frame){
    mf=frame;

    Game_size = 0;
    Over_size = 0;
    Game_y = 0;
    Over_y = 0;
    Game_size_flag = 0;
    press_flag = 0;
    change_panel_flag = 0;
    GM_timing_flag = 0;
    panel_serect_flag = 0;
    color_changer = 200;

    timerPG = new Timer(50,this);
    setBackground(Color.black);
    setFocusable(true);
    addKeyListener(this);
    timerPG.start();
  }
  public void keyReleased(KeyEvent e){

  }
  public void keyPressed(KeyEvent e){
    if(MainFrame.PG_flag==1){
      press = e.getKeyCode();

      if(press==KeyEvent.VK_W){press_flag = 1;}

      else if(press==KeyEvent.VK_S){press_flag = 2;}

      else if(press==KeyEvent.VK_ENTER&&change_panel_flag==1){
        if(MainFrame.PS_flag==1){
          timerPG.stop();
          MainFrame.PG_flag=0;
          mf.panelChange("PanelNormal");
        }
        else if(MainFrame.PS_flag==2){
          timerPG.stop();
          MainFrame.PG_flag=0;
          mf.panelChange("PanelTAttack");
        }
      }
      else if(press==KeyEvent.VK_ENTER&&change_panel_flag==2){
        MainFrame.PG_flag=0;
        mf.panelChange("PanelStart");
      }
      repaint();
    }
  }
  public void keyTyped(KeyEvent e){

  }
  public void actionPerformed(ActionEvent e) {
    if(MainFrame.PG_flag==1){
      if(e.getSource()==timerPG){

        if(Game_size_flag==0){
          Game_size += 5;
          if(Game_size >= 80){
            Game_size_flag = 1;
            Game_size += 0;
            GM_timing_flag = 1;
          }
        }
        if(Game_size_flag==1){
          Over_size += 5;
          if(Over_size >= 80){
            Game_size_flag = 2;
            Over_size += 0;
          }
        }
        if(Game_size_flag==2){
          Game_y -= 5;
          Over_y -= 5;
          if(Game_y<=-120){Game_size_flag = 3;}
        }
        if(Game_size_flag==3){
          Game_y -= 0;
          Over_y -= 0;
          Game_size_flag = 4;
          panel_serect_flag = 1;
          press_flag=1;
        }
        if(color_changer_flag==0){
          color_changer -= 10;
          if(color_changer<=150){
            color_changer_flag = 1;
          }
        }
        if(color_changer_flag==1){
          color_changer += 10;
          if(color_changer>=250){
            color_changer_flag = 0;
          }
        }
        repaint();
      }
    }
  }
  public void paintComponent(Graphics g) {
    d = getSize();
    super.paintComponent(g);

    if( GM_timing_flag==0){
      g.setColor(Color.white);
      g.setFont(new Font("Century",Font.ITALIC,Game_size));
      g.drawString("GAME",d.width/2-Game_size*3,d.height/2);
    }
    if( GM_timing_flag==1){
      g.setColor(Color.white);
      g.setFont(new Font("Century",Font.ITALIC,Game_size));
      g.drawString("GAME",d.width/2-Game_size*3,d.height/2 + Game_y);
      g.setFont(new Font("Century",Font.ITALIC,Over_size));
      g.drawString("OVER",d.width/2 ,d.height/2 + Over_y);
    }
    if(panel_serect_flag==1){
      if(press_flag==1){
        g.setColor(new Color(color_changer,color_changer,color_changer));
        g.fillRect(d.width/2-105,d.height/2-25,200,60);
        g.setColor(Color.red);
        g.fillRect(d.width/2-100,d.height/2+70,190,50);
        g.setColor(Color.red);
        g.fillRect(d.width/2-100,d.height/2-20,190,50);

        g.setColor(Color.white);
        g.setFont(new Font("Century",Font.ITALIC,30));
        g.drawString("  Retry",d.width/2-65 ,d.height/2+15);
        g.drawString(" Back to title",d.width/2-100 ,d.height/2+105);

        change_panel_flag = 1;
      }
      if(press_flag==2){
        g.setColor(new Color(color_changer,color_changer,color_changer));
        g.fillRect(d.width/2-105,d.height/2+65,200,60);
        g.setColor(Color.red);
        g.fillRect(d.width/2-100,d.height/2-20,190,50);
        g.setColor(Color.red);
        g.fillRect(d.width/2-100,d.height/2+70,190,50);

        g.setColor(Color.white);
        g.setFont(new Font("Century",Font.ITALIC,30));
        g.drawString("  Retry",d.width/2-65 ,d.height/2+15);
        g.drawString(" Back to title",d.width/2-100 ,d.height/2+105);

        change_panel_flag = 2;
      }
      if(press_flag==0){
        g.setColor(new Color(color_changer,color_changer,color_changer));
        g.fillRect(d.width/2-105,d.height/2-25,200,60);
        g.setColor(Color.red);
        g.fillRect(d.width/2-100,d.height/2-20,190,50);
        g.fillRect(d.width/2-100,d.height/2+70,190,50);

        g.setColor(Color.white);
        g.setFont(new Font("Century",Font.ITALIC,30));
        g.drawString("  Retry",d.width/2-65 ,d.height/2+15);
        g.drawString(" Back to title",d.width/2-100 ,d.height/2+105);
      }
    }
  }
}
