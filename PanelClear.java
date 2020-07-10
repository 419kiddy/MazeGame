import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelClear extends JPanel implements ActionListener, KeyListener {
  MainFrame mf;
  Dimension d;
  Timer timerPC;
  int n = 6;
  int enter_key;
  int moji_y[] = new int[n];
  int flag_c=0,flag_l=0,flag_e,flag_a,flag_r,flag_1 = 0;
  int moji_v;
  int pre_en_color;
  int pre_en_color_flag=0;
  int time_attack_Time;// PanelTAttack.time_attack_Time;
  int TA_sec, TA_c_sec; String sec_str,c_sec_str;
  int time;
  int clear_moji_size, clear_time_size, press_size;
  int enter_flag = 0;
  int GOver_time_flag = 0;

  public PanelClear(MainFrame frame){
    mf=frame;

    setBackground(Color.black);
    timerPC = new Timer(50,this);
    setFocusable(true);
    addKeyListener(this);
    timerPC.start();

    moji_v = 10;
    for(int i=0; i<n; i++) {
      moji_y[i] = 0;
    }
    time_attack_Time = 2064;
    pre_en_color = 255;
    time = 0;
    clear_moji_size = 50;
    clear_time_size = 30;
    press_size = 20;
  }
  public void keyReleased(KeyEvent e){

  }
  public void keyPressed(KeyEvent e){
    if(MainFrame.PC_flag==1){
      enter_key = e.getKeyCode();
      if(enter_key==KeyEvent.VK_ENTER&&enter_flag==1) {
        GOver_time_flag = 1;
        MainFrame.PC_flag = 0;
        mf.panelChange("PanelStart");
      }
    }
  }
  public void keyTyped(KeyEvent e){

  }
  public void actionPerformed(ActionEvent e) {
    if(MainFrame.PC_flag==1){
      if(e.getSource()==timerPC) {

        if(flag_c==0) {
          moji_y[0] -= moji_v;
          if(moji_y[0]==-60) {flag_c=1;}
        }
        if(flag_c==1) {
          moji_y[0]+=moji_v;
          moji_y[1]-=moji_v;
          if(moji_y[0]==0) {flag_c=2;}
          if(moji_y[1]==-60) {flag_l=1;}
        }

        if(flag_l==1) {
          moji_y[1]+=moji_v;
          moji_y[2]-=moji_v;
          if(moji_y[1]==0) {flag_l=2;}
          if(moji_y[2]==-60) {flag_e=1;}
        }
        if(flag_e==1) {
          moji_y[2]+=moji_v;
          moji_y[3]-=moji_v;
          if(moji_y[2]==0) {flag_e=2;}
          if(moji_y[3]==-60) {flag_a=1;}
        }
        if(flag_a==1) {
          moji_y[3]+=moji_v;
          moji_y[4]-=moji_v;
          if(moji_y[3]==0) {flag_a=2;}
          if(moji_y[4]==-60) {flag_r=1;}
        }
        if(flag_r==1) {
          moji_y[4]+=moji_v;
          moji_y[5]-=moji_v;
          if(moji_y[4]==0) {flag_r=2;}
          if(moji_y[5]==-60) {flag_1=1;}
        }
        if(flag_1==1) {
          moji_y[5]+=moji_v;
          flag_c = 0;
          if(moji_y[5]==0) { flag_1=2;}
        }
        if(pre_en_color_flag==0){
          pre_en_color-=10;
          if(pre_en_color<100){pre_en_color_flag=1; pre_en_color+=5; }
        }
        if(pre_en_color_flag==1) {
          pre_en_color+=10;
          if(pre_en_color>245){pre_en_color_flag=0; pre_en_color-=5; }
        }
        time += 1;
      }
      repaint();
    }
  }
  public void paintComponent(Graphics g) {
    d = getSize();
    super.paintComponent(g);

    g.setColor(Color.white);
    g.setFont(new Font("Century", Font.ITALIC, clear_moji_size));
    g.drawString("C",1*d.width/7,d.height/4+moji_y[0]);//-3*clear_moji_size
    g.drawString("L",2*d.width/7,d.height/4+moji_y[1]);//-2*clear_moji_size
    g.drawString("E",3*d.width/7,d.height/4+moji_y[2]);//-1*clear_moji_size
    g.drawString("A",4*d.width/7,d.height/4+moji_y[3]);//-0*clear_moji_size
    g.drawString("R",5*d.width/7,d.height/4+moji_y[4]);//+1*clear_moji_size
    g.drawString("!",6*d.width/7,d.height/4+moji_y[5]);//+2*clear_moji_size

    if(MainFrame.PS_flag==2){
      TA_sec = (int)(MainFrame.PT_time/100);
      sec_str = Integer.toString(TA_sec);
      TA_c_sec = (MainFrame.PT_time%100);
      c_sec_str = Integer.toString(TA_c_sec);

      g.setColor(Color.white);
      if(time>=40){
        g.setFont(new Font("Century",Font.ITALIC,clear_time_size));
        g.drawString(sec_str+"s"+" "+c_sec_str,5*d.width/8,d.height/2);
      }
      g.setFont(new Font("HGP行書体",Font.ITALIC,clear_time_size));
      g.drawString("クリアタイム　",2*d.width/8,d.height/2);
    }
    if(time>=70){
      g.setColor(new Color(pre_en_color,pre_en_color,pre_en_color));
      g.setFont(new Font("Century", Font.BOLD,press_size));
      g.drawString("press enter", d.width/2-3*press_size, 3*d.height/4);
      enter_flag = 1;
    }
  }
}
