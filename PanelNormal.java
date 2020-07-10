import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelNormal extends JPanel implements ActionListener, KeyListener {
  MainFrame mf;
  Dimension d;
  final int Width= 500;
  final int Height= 500;
  int me_size;
  int area_size;
  int[][] area_x;
  int[][] area_y;
  int current_area_x;
  int current_area_y;
  int current_i;
  int current_j;
  int area_num;
  int wall_flag[][];
  Timer timerPN;
  int HP;
  int man_flag;
  int key_area;
  int key_i, key_j;
  int goal_area;
  int goal_j, goal_i;
  int r;

  int keyGet_flag;
  ImageIcon left, right, up, down, key, door, road, wall;

  public PanelNormal(MainFrame frame){
    mf=frame;
    me_size=70;

    if(MainFrame.PD_flag==1){area_num=15;}
    else if(MainFrame.PD_flag==2){area_num=21;}
    else if(MainFrame.PD_flag==3){area_num=27;}


    man_flag=2;
    left=new ImageIcon("leftman.gif");
    right=new ImageIcon("rightman.gif");
    up=new ImageIcon("upman.gif");
    down=new ImageIcon("downman.gif");
    key=new ImageIcon("key.png");
    door=new ImageIcon("door.png");
    road=new ImageIcon("road.png");
    wall=new ImageIcon("wall.png");

    area_size=80;
    current_area_x= (Width - area_size)/2;
    current_area_y= (Height - area_size)/2;
    HP=400;

    wall_flag= new int[area_num][area_num];
    Maze.MazeGenerate(area_num);
    wall_flag= Maze.wall_flag;

    loop4:for(int i=0; i<area_num; i++){
      for(int j=0; j<area_num; j++){
        if(wall_flag[i][j]==0){
          current_i=i;
          current_j=j;
          break loop4;
        }
      }
    }
    area_x =new int[area_num][area_num];
    area_y =new int[area_num][area_num];
    for(int i=0; i<area_num; i++){
      for(int j=0; j<area_num; j++){
        area_x[i][j]= current_area_x + ((j-current_j) *area_size);
        area_y[i][j]= current_area_y + ((i-current_i) *area_size);
      }
    }
    while(true){
      goal_area=(int)(Math.random()*(area_num-2)+1);
      r=(int)(Math.random()*2);
      if(r==0){
        if(wall_flag[goal_area][area_num-2]==0){
            goal_i=goal_area;
            goal_j=area_num-2;
            break;
        }
        else{continue;}
      }
      else{
        if(wall_flag[area_num-2][goal_area]==0){
          goal_i=area_num-2;
          goal_j=goal_area;
          break;
        }
        else{continue;}
      }
    }
    keyGet_flag=0;
    while(true){
      key_area=(int)(Math.random()*(area_num-2)+1)/2;
      r=(int)(Math.random()*2);
      if(r==0){
        if(wall_flag[key_area][(area_num+1)/2]==0){
            key_i=key_area;
            key_j=(area_num+1)/2;
            if(key_i==goal_i && key_j==goal_j){continue;}
            break;
        }
        else{continue;}
      }
      else{
        if(wall_flag[(area_num+1)/2][key_area]==0){
          key_i=(area_num+1)/2;
          key_j=key_area;
          if(key_i==goal_i && key_j==goal_j){continue;}
          break;
        }
        else{continue;}
      }
    }

    setBackground(Color.black);
    setFocusable(true);
    addKeyListener(this);

    timerPN = new Timer(100, this);
    timerPN.start();
  }
  public void keyReleased(KeyEvent e){

  }
  public void keyPressed(KeyEvent e){
    if(MainFrame.PN_flag==1){
      int key=e.getKeyCode();
      //System.out.println(current_i+" "+current_j+" "+goal_i+" "+goal_j);

      if(key==65){ //left
        man_flag=0;
        if(wall_flag[current_i][current_j-1]==0){
          for(int i=0; i<area_num; i++){
            for(int j=0; j<area_num; j++){
              area_x[i][j] += area_size;
            }
          }
          current_j--;
        }
      }
      if(key==68){ //right
        man_flag=2;
        if(wall_flag[current_i][current_j+1]==0){
          for(int i=0; i<area_num; i++){
            for(int j=0; j<area_num; j++){
              area_x[i][j] -= area_size;
            }
          }
          current_j++;
        }
      }
      if(key==87){ //up
        man_flag=1;
        if(wall_flag[current_i-1][current_j]==0){
          for(int i=0; i<area_num; i++){
            for(int j=0; j<area_num; j++){
              area_y[i][j] += area_size;
            }
          }
          current_i--;
        }
      }
      if(key==83){ //down
        man_flag=3;
        if(wall_flag[current_i+1][current_j]==0){
          for(int i=0; i<area_num; i++){
            for(int j=0; j<area_num; j++){
              area_y[i][j] -= area_size;
            }
          }
          current_i++;
        }
      }
      repaint();
      if(current_i==key_i && current_j==key_j){
        keyGet_flag=1;
      }
      if(keyGet_flag==1 && current_i==goal_i && current_j==goal_j){
        timerPN.stop();
        MainFrame.PN_flag=0;
        mf.panelChange("PanelClear");
      }
    }
  }
  public void keyTyped(KeyEvent e){

  }
  public void actionPerformed(ActionEvent e) {
    if(MainFrame.PN_flag==1){
      if (e.getSource()==timerPN) {

        if(MainFrame.PD_flag==1 || MainFrame.PD_flag==2 || MainFrame.PD_flag==3){
          HP= HP - 1;
          repaint();
          if(HP==0){
            timerPN.stop();
            MainFrame.PN_flag=0;
            mf.panelChange("PanelGOver");
          }
        }
      }
    }
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(new Color(98,62,38));
    for(int i=0; i<area_num; i++){
      for(int j=0; j<area_num; j++){
        if(wall_flag[i][j]==0){
          //g.drawRect(area_x[i][j],area_y[i][j] , area_size, area_size);
          road.paintIcon(this, g, area_x[i][j], area_y[i][j]);
        }
        else{
          //g.fillRect(area_x[i][j],area_y[i][j] , area_size, area_size);
          wall.paintIcon(this, g, area_x[i][j], area_y[i][j]);
        }
      }
    }

    door.paintIcon(this, g, area_x[goal_i][goal_j], area_y[goal_i][goal_j]);
    if(keyGet_flag==0){
      key.paintIcon(this, g, area_x[key_i][key_j], area_y[key_i][key_j]);
    }

    if(man_flag==0){left.paintIcon(this, g, current_area_x, current_area_y);}
    else if(man_flag==1){up.paintIcon(this, g, current_area_x, current_area_y);}
    else if(man_flag==2){right.paintIcon(this, g, current_area_x, current_area_y);}
    else if(man_flag==3){down.paintIcon(this, g, current_area_x, current_area_y);}

    g.setColor(Color.gray);
    g.fillRect(50,50,400,30);
    if(HP>200){g.setColor(Color.green);}
    else if(HP>100){g.setColor(Color.yellow);}
    else if(HP<=100){g.setColor(Color.red);}
    g.fillRect(50,50,HP,30);

    g.setColor(Color.red);
    g.drawString("Normal Mode "+MainFrame.PD_flag,10,10);
  }

}
