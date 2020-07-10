public class Maze{
  static int[][] wall_flag;
  static int c=15;
  static int start_flag=0;

  //public static void main (String[] args){
    //wall_flag= new int[c][c];
    //MazeGenerate(c);
    //MazeDraw();
  //}

  public static boolean StartCheck(int x, int y){
    if(y==0 || x==0 || y==c-1 || x==c-1){return(false);}
    if(wall_flag[y][x]==0){return(false);}
    int counter=0;
    if(start_flag==0){start_flag=1;return(true);}
    else{
      if(wall_flag[y-1][x]==0){counter++;}
      if(wall_flag[y][x+1]==0){counter++;}
      if(wall_flag[y+1][x]==0){counter++;}
      if(wall_flag[y][x-1]==0){counter++;}
      if(counter==1){return(true);}
      else{return(false);}
    }
  }

  public static boolean EndCheck(int x, int y){
    if(y==0 || x==0 || y==c-1 || x==c-1){return(false);}
    if(wall_flag[y][x]==0){return(false);}
    int counter=0;
    if(wall_flag[y-1][x]==0){counter++;}
    if(wall_flag[y][x+1]==0){counter++;}
    if(wall_flag[y+1][x]==0){counter++;}
    if(wall_flag[y][x-1]==0){counter++;}
    if(counter==1){return(true);}
    else{return(false);}
  }

  public static boolean DigCheck(int d, int x, int y){
    switch(d){
      case 0:
      if(wall_flag[y-1][x]==0){return(false);}
      if(y-1==0){return(false);}
      if(wall_flag[y-2][x]==0 || wall_flag[y-1][x+1]==0 || wall_flag[y-1][x-1]==0){
        return(false);
      }else{return(true);}

      case 1:
      if(wall_flag[y][x+1]==0){return(false);}
      if(x+1==c-1){return(false);}
      if(wall_flag[y-1][x+1]==0 || wall_flag[y][x+2]==0 || wall_flag[y+1][x+1]==0){
        return(false);
      }else{return(true);}

      case 2:
      if(wall_flag[y+1][x]==0){return(false);}
      if(y+1==c-1){return(false);}
      if(wall_flag[y+1][x+1]==0 || wall_flag[y+2][x]==0 || wall_flag[y+1][x-1]==0){
        return(false);
      }else{return(true);}

      case 3:
      if(wall_flag[y][x-1]==0){return(false);}
      if(x-1==0){return(false);}
      if(wall_flag[y-1][x-1]==0 || wall_flag[y+1][x-1]==0 || wall_flag[y][x-2]==0){
        return(false);
      }else{return(true);}

      default:
      return(false);
    }
  }

  public static void MazeDraw(){
    for(int i=0; i<c; i++){
      for(int j=0; j<c; j++){
        System.out.print(wall_flag[i][j]+" ");
        if(j==c-1){System.out.println();}
      }
    }
    System.out.println();
  }

  public static void MazeGenerate(int n){
    start_flag=0;
    c=n;
    wall_flag= new int[n][n];
    if(n%2==0 || n<5){System.out.println("Error!");System.exit(0);}
    else{
      for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
          wall_flag[i][j]=1;
        }
      }
      int random_i;
      int random_j;
      int end_flag;
      loop1:while(true){
        random_i= (int)(Math.random()*n);
        random_j= (int)(Math.random()*n);
        if(start_flag==1){
          end_flag=0;
          for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
              if(EndCheck(j,i)){end_flag++;}
            }
          }
          if(end_flag==0){break loop1;}
        }

        if(!StartCheck(random_j, random_i)){continue loop1;}
        wall_flag[random_i][random_j]=0;
        //MazeDraw();
        loop2:while(true){
          int random_dir= (int)(Math.random()*4);
          if(!DigCheck(random_dir, random_j, random_i)){break;}
          else{
            switch(random_dir){
              case 0:
              wall_flag[random_i-1][random_j]=0;
              random_i-=1;
              break;

              case 1:
              wall_flag[random_i][random_j+1]=0;
              random_j+=1;
              break;

              case 2:
              wall_flag[random_i+1][random_j]=0;
              random_i+=1;
              break;

              case 3:
              wall_flag[random_i][random_j-1]=0;
              random_j-=1;
              break;

            }
            //MazeDraw();
          }
        }
      }
    }
    //return(wall_flag);
  }
}
