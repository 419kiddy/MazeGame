import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelExplanation extends JPanel implements ActionListener, KeyListener {
  MainFrame mf;
  Dimension d;
  private int rect_x,rect_y,scale_x,scale_y,rule_x,rule_y,moji_x,moji_y;
  private int Width,Height,scale_min,size;


  public PanelExplanation(MainFrame frame){
    mf=frame;
    size=500;
    setBackground(Color.black);
    setFocusable(true);
    addKeyListener(this);
  }
  public void getScale() {
    d=getSize();
    Width=d.width;
    Height=d.height;
    rect_x=Width*50/size;
    rect_y=Height*40/size;
    moji_x=Width*60/size;
    moji_y=Height*110/size;
    rule_x=Width*125/size;
    rule_y=Height*80/size;
    scale_x=Width*400/size;
    scale_y=Height*400/size;
    if(Width<Height) {
      scale_min=Width;
    }
    else {
      scale_min=Height;
    }
  }

  public void keyReleased(KeyEvent e){
  }

  public void keyTyped(KeyEvent e){
  }

  public void keyPressed(KeyEvent e){
    int key=e.getKeyCode();
    if(key==KeyEvent.VK_ENTER) {
      MainFrame.PE_flag=0;
      mf.panelChange("PanelStart");
    }
  }

  public void actionPerformed(ActionEvent e) {
  }

  public void drawShape(Graphics g) {
    getScale();
    g.setColor(Color.black);
    g.fillRect(rect_x,rect_y,scale_x,scale_y);
    g.setColor(new Color(167,87,168));
    g.fillRect(rect_x-Width*5/500,rect_y,Width*10/500,scale_y);
    g.fillRect(rect_x-Width*5/size,rect_y+scale_y-Height*5/size,scale_x+Width*10/size,Height*10/size);
    g.fillRect(rect_x+scale_x-Width*5/size,rect_y,Width*10/size,scale_y);
    g.fillRect(rect_x-Width*5/size,rect_y-Height*5/size,scale_x+Width*10/size,Height*10/size);
  }

  public void drawString(Graphics g) {
    getScale();
    g.setColor(Color.white);
    g.setFont(new Font("Century",Font.ITALIC,scale_min*44/size));
    g.drawString("ルール説明",rule_x,rule_y);
    g.setFont(new Font("Century",Font.ITALIC,scale_min*30/size));
    g.drawString("ノーマルモード",moji_x,moji_y);
    g.drawString("タイムアタックモード",moji_x,moji_y+Height*80/size);
    g.drawString("共通",moji_x,moji_y+Height*160/size);
    g.drawString("Press enter to go back.",moji_x+Width*30/size,moji_y+Height*280/size);
    g.setFont(new Font("Century",Font.ITALIC,scale_min*20/size));
    g.drawString("・画面上のゲージが０になる前にクリアし",moji_x,moji_y+Height*30/size);
    g.drawString("なければならない。",moji_x,moji_y+Height*50/size);
    g.drawString("・時間制限なし。クリア後にタイムが表示",moji_x,moji_y+Height*110/size);
    g.drawString("される。",moji_x,moji_y+Height*130/size);
    g.drawString("・難易度はeasy,normal,hardの３種類。",moji_x,moji_y+Height*190/size);
    g.drawString("・鍵をとってからゴールへ行かなければな",moji_x,moji_y+Height*210/size);
    g.drawString("らない。",moji_x,moji_y+Height*230/size);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawShape(g);
    drawString(g);
    requestFocusInWindow();
  }
}
