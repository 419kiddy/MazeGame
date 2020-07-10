import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame{
  public static int PS_flag= 0;
  public static int PE_flag= 0;
  public static int PD_flag= 0;
  public static int PN_flag= 0;
  public static int PT_flag= 0;
  public static int PG_flag= 0;
  public static int PC_flag= 0;
  public static int PT_time= 0;

  public MainFrame(){
    setSize(500,500);
    setTitle("Java Programing");
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setBackground(Color.black);

    this.setVisible(true);
  }

  public void panelChange(String str) {
    this.getContentPane().removeAll();
    switch(str){
      case "PanelStart":
      PanelStart ps;
      ps=new PanelStart(this);
      this.add(ps);
      PS_flag=0;
      validate();
      ps.requestFocusInWindow();
      break;

      case "PanelExplanation":
      PanelExplanation pe;
      pe= new PanelExplanation(this);
      this.add(pe);
      PE_flag=1;
      validate();
      pe.requestFocusInWindow();
      break;

      case "PanelDifficulty":
      PanelDifficulty pd;
      pd=new PanelDifficulty(this);
      this.add(pd);
      PD_flag=0;
      validate();
      pd.requestFocusInWindow();
      break;

      case "PanelNormal":
      PanelNormal pn;
      pn=new PanelNormal(this);
      this.add(pn);
      PN_flag=1;
      validate();
      pn.requestFocusInWindow();
      break;

      case "PanelTAttack":
      PanelTAttack pt;
      pt=new PanelTAttack(this);
      this.add(pt);
      PT_flag=1;
      PT_time=0;
      validate();
      pt.requestFocusInWindow();
      break;

      case "PanelGOver":
      PanelGOver pg;
      pg=new PanelGOver(this);
      this.add(pg);
      PG_flag=1;
      validate();
      pg.requestFocusInWindow();
      break;

      case "PanelClear":
      PanelClear pc;
      pc=new PanelClear(this);
      this.add(pc);
      PC_flag=1;
      validate();
      pc.requestFocusInWindow();
      break;
    }
  }

  public static void main(String[] args){
    //new MainFrame();
    MainFrame mf;
    mf= new MainFrame();
    //mf.panelChange("PanelGOver");
    mf.panelChange("PanelStart");
  }
}
