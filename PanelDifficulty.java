import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelDifficulty extends JPanel implements MouseListener, MouseMotionListener, ActionListener, KeyListener {
  MainFrame mf;
  int sel_flg;

  public PanelDifficulty(MainFrame frame){
    mf=frame;
    sel_flg=1;
    repaint();

    setBackground(Color.black);
    addMouseListener(this);
    addMouseMotionListener(this);
    setFocusable(true);
    addKeyListener(this);
  }

  public void mouseClicked(MouseEvent e){

  }
  public void mousePressed(MouseEvent e){

  }
  public void mouseReleased(MouseEvent e){

  }
  public void mouseExited(MouseEvent e){

  }
  public void mouseEntered(MouseEvent e) {

  }
  public void mouseMoved(MouseEvent e){

  }
  public void mouseDragged(MouseEvent e) {

  }
  public void keyReleased(KeyEvent e){

  }
  public void keyPressed(KeyEvent e){
    if(MainFrame.PD_flag==0){
      int press =e.getKeyCode();
      if(press==KeyEvent.VK_W) {
    	    sel_flg--;
    	    //カーソル上へ
    	    if(sel_flg==0) {
    		sel_flg++;
    	    }
    	    repaint();
    	}
    	if(press==KeyEvent.VK_S) {
    	    sel_flg++;
    	    //カーソル下へ
    	      if(sel_flg==4) {
    		sel_flg--;
    	    }
    	      repaint();
    	}

      if(press == KeyEvent.VK_ENTER && MainFrame.PS_flag==1 && sel_flg==1){
        MainFrame.PD_flag=1;
        mf.validate();
        mf.panelChange("PanelNormal");
      }
      else if(press == KeyEvent.VK_ENTER && MainFrame.PS_flag==1 && sel_flg==2){
        MainFrame.PD_flag=2;
        mf.panelChange("PanelNormal");
      }
      else if(press == KeyEvent.VK_ENTER && MainFrame.PS_flag==1 && sel_flg==3){
        MainFrame.PD_flag=3;
        mf.panelChange("PanelNormal");
      }
      else if(press == KeyEvent.VK_ENTER && MainFrame.PS_flag==2 && sel_flg==1){
        MainFrame.PD_flag=1;
        mf.panelChange("PanelTAttack");
      }
      else if(press == KeyEvent.VK_ENTER && MainFrame.PS_flag==2 && sel_flg==2){
        MainFrame.PD_flag=2;
        mf.panelChange("PanelTAttack");
      }
      else if(press == KeyEvent.VK_ENTER && MainFrame.PS_flag==2 && sel_flg==3){
        MainFrame.PD_flag=3;
        mf.panelChange("PanelTAttack");
      }
    }
  }
  public void keyTyped(KeyEvent e){

  }
  public void actionPerformed(ActionEvent e) {

  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.blue);
  	g.setFont(new Font("Century",Font.ITALIC,40));
  	g.drawString("Easy",125,125);
  	g.drawString("Normal",125,250);
  	g.drawString("Hard",125,375);
  	g.setFont(new Font("Century",Font.ITALIC,40));
  	g.setColor(Color.red);
  	if(sel_flg==1) {
  	    g.fillOval(85,95,30,30);
  	    g.drawString("Easy",125,125);
  	}
  	if(sel_flg==2) {
  	    g.fillOval(85,220,30,30);
  	    g.drawString("Normal",125,250);
  	}
  	if(sel_flg==3) {
  	    g.fillOval(85,345,30,30);
  	    g.drawString("Hard",125,375);
  	}
  }
}
