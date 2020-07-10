import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelStart extends JPanel implements ActionListener, KeyListener {
    MainFrame mf;
    Dimension d;
    Image ima_title,ima_back,ima_back_rot;
    static int start_flg;
    int sel_flg;
    int moji_x,moji_y,maru_x,maru_y,ima_title_x,ima_title_y,ima_back_x,ima_back_rot_x,ima_back_v,ima_back_rot_v,key;
    int Width,Height,scale_min,size,count;
    Timer timer;

    public PanelStart(MainFrame frame){
	mf=frame;
	sel_flg=1;
	count=0;
	timer=new Timer(100,this);
	ImageIcon icon_title=new ImageIcon("title.png");
	ima_title=icon_title.getImage();
	ImageIcon icon_back=new ImageIcon("back_floor.jpg");
	ima_back=icon_back.getImage();
	ImageIcon icon_back_rot=new ImageIcon("back_floor_rot.jpg");
	ima_back_rot=icon_back_rot.getImage();


	size=500;
	ima_title_x=50;
	ima_title_y=50;
	ima_back_v=2;
	ima_back_rot_v=1;
	setBackground(Color.black);
	timer.addActionListener(this);
	setFocusable(true);
	addKeyListener(this);
    }

    public static int getStart_flg() {
	return start_flg;
    }
    public void getScale() {
	d=getSize();
	Width=d.width;
	Height=d.height;
	moji_x=Width*50/size;
	moji_y=Height*270/size;
	maru_x=Width*25/size;
	maru_y=Height*258/size;
	if(Width<Height) {
	    scale_min=Width;
	}
	else {
	    scale_min=Height;
	}
    }

    public void getPlace() {
	ima_back_x=-Width/2;
	ima_back_rot_x=-Width/2;
    }

    public void keyReleased(KeyEvent e){
    }

    public void keyTyped(KeyEvent e){
    }

    public void keyPressed(KeyEvent e){
	key=e.getKeyCode();
	if(key==KeyEvent.VK_W) {
	    sel_flg--;
	    //カーソル上へ
	    if(sel_flg==0) {
		sel_flg++;
	    }
	    repaint();
	}
	if(key==KeyEvent.VK_S) {
	    sel_flg++;
	    //カーソル下へ
	      if(sel_flg==5) {
		sel_flg--;
	    }
	      repaint();
	}
	if(key== KeyEvent.VK_ENTER && sel_flg==1) {
	    MainFrame.PS_flag=1;
	    mf.panelChange("PanelDifficulty");
	}
	if(key== KeyEvent.VK_ENTER && sel_flg==2) {
	    MainFrame.PS_flag=2;
	    mf.panelChange("PanelDifficulty");
	}
	if(key== KeyEvent.VK_ENTER && sel_flg==3) {
	    mf.panelChange("PanelExplanation");
	}
	if(key== KeyEvent.VK_ENTER && sel_flg==4) {
	    System.exit(0);
	}
    }

    public void actionPerformed(ActionEvent e) {
	count++;
	if(e.getSource()==timer) {
	    ima_back_x+=ima_back_v;
	    ima_back_rot_x += ima_back_rot_v;
	    if(ima_back_x>0 || ima_back_x<-Width/2) {
		ima_back_v=-ima_back_v;
	    }
	    if(ima_back_rot_x>0 || ima_back_rot_x<-Width/2) {
		ima_back_rot_v=-ima_back_rot_v;
	    }
	}
	repaint();
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	if(count==0) {
	    getPlace();
	    timer.start();
	}
	drawImage(g);
	drawString(g);
	requestFocusInWindow();
    }

    public void drawImage(Graphics g) {
	getScale();
	g.drawImage(ima_back,ima_back_x,0,Width*2,Height,this);
	g.drawImage(ima_back_rot,ima_back_rot_x,0,Width*2,Height*370/size,this);
	g.drawImage(ima_title,ima_title_x,ima_title_y,this);
    }

    public void drawString(Graphics g){
	getScale();
	g.setColor(Color.blue);
	g.setFont(new Font("Century",Font.ITALIC,scale_min*25/size));
	g.drawString("Normal Mode",moji_x,moji_y);
	g.drawString("TimeAttatck Mode",moji_x+Width*20/size,moji_y+Height*30/size);
	g.drawString("Explanation",moji_x+Width*40/size,moji_y+Height*60/size);
	g.drawString("Exit",moji_x+Width*60/size,moji_y+Height*90/size);
	g.setFont(new Font("Century",Font.ITALIC,scale_min*25/size));
	g.setColor(Color.red);
	if(sel_flg==1) {
	    g.fillOval(maru_x,maru_y,scale_min*10/size,scale_min*10/size);
	    g.drawString("Normal Mode",moji_x,moji_y);
	}
	if(sel_flg==2) {
	    g.fillOval(maru_x+Width*20/size,maru_y+Height*30/size,scale_min*10/size,scale_min*10/size);
	    g.drawString("TimeAttatck Mode",moji_x+Width*20/size,moji_y+Height*30/size);
	}
	if(sel_flg==3) {
	    g.fillOval(maru_x+Width*40/size,maru_y+Height*60/size,scale_min*10/size,scale_min*10/size);
	    g.drawString("Explanation",moji_x+Width*40/size,moji_y+Height*60/size);
	}
	if(sel_flg==4) {
	    g.fillOval(maru_x+Width*60/size,maru_y+Height*90/size,scale_min*10/size,scale_min*10/size);
	    g.drawString("Exit",moji_x+Width*60/size,moji_y+Height*90/size);
	}
    }
}
