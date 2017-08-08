package com.example.rohanmalik.tictactoerohan;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout Main;
    LinearLayout RowLayout[];
    public static int n=3;
    public static final int NO_PLAYER=0;
    public static final int PLAYER_ONE=1;
    public static final int PLAYER_TWO=2;
    public static final int DRAW=0;
    public static final int PLAYER1_WIN=1;
    public static final int PLAYER2_WIN=2;
    public static final int INCOMPLETE=3;
    ButtonTic btntic[][];
    Boolean player_1turn=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Main=(LinearLayout)findViewById(R.id.MainLayout);
        createBoard();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
                getMenuInflater().inflate(R.menu.menu,menu);
                return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
                int id=item.getItemId();
                if(id==R.id.newGame)
        {
                        resetBoard();
        }
        else if(id==R.id.threebd){
            n=3;
            createBoard();
                    resetBoard();
        }
        else if(id==R.id.fivebd){
            n=5;
            createBoard();
                    Log.d("MainTag","sasa");
                    resetBoard();
        }
        return true;
    }
    public void resetBoard(){
                    for(int i=0;i<n;i++){
                        for(int j=0;j<n;j++){
                            btntic[i][j].turn=NO_PLAYER;
                            btntic[i][j].setText("");
                        }
                    }
    }
    public void createBoard(){
        Main.removeAllViews();
        RowLayout=new LinearLayout[n];
        btntic = new ButtonTic[n][n];
            for(int i=0;i<n;i++){
                RowLayout[i]=new LinearLayout(this);
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1f);
                params.setMargins(5,5,5,5);
                RowLayout[i].setLayoutParams(params);
                RowLayout[i].setOrientation(LinearLayout.HORIZONTAL);
                Main.addView(RowLayout[i]);
            }
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    btntic[i][j]=new ButtonTic(this);
                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1f);
                    params.setMargins(5,5,5,5);
                    btntic[i][j].setLayoutParams(params);
                    btntic[i][j].setTextSize(30);
                    btntic[i][j].setOnClickListener(this);
                    RowLayout[i].addView(btntic[i][j]);
                }
            }
    }

    @Override
    public void onClick(View view) {
        ButtonTic btnCheck=(ButtonTic)view;
        if(btnCheck.turn!=NO_PLAYER){
            Toast.makeText(this,"INVALID MOVE",Toast.LENGTH_SHORT).show();
            return;
        }
        if(player_1turn){
            btnCheck.turn=PLAYER_ONE;
            btnCheck.setText("Alisha");
        }
        else{
            btnCheck.turn=PLAYER_TWO;
            btnCheck.setText("Rohan");
        }
        int status=checkStatus();
        if(status==PLAYER1_WIN){
            Main.removeAllViews();
            Toast.makeText(this,"ALISHA WINS HOLAA",Toast.LENGTH_LONG).show();
        }
        else if(status==PLAYER2_WIN){
            Main.removeAllViews();
            Toast.makeText(this,"ROHAN WINS HOLAAA",Toast.LENGTH_LONG).show();
        }
        else if(status==DRAW){
                Main.removeAllViews();
                Toast.makeText(this,"DRAW NOOO",Toast.LENGTH_LONG).show();
        }
        player_1turn=!player_1turn;
    }
    public int checkStatus(){
        //Check RoW Wise
        for(int i=0;i<n;i++){
            boolean flag=true;
            for(int j=0;j<n;j++){
                if(btntic[i][j].getTurn()==NO_PLAYER || btntic[i][0].getTurn()!=btntic[i][j].getTurn() ){
                        flag=false;
                        break;
                }
            }
            if(flag){
                if(btntic[i][0].getTurn()==PLAYER_ONE){
                    return PLAYER1_WIN;
                }
                else if(btntic[i][0].getTurn()==PLAYER_TWO){
                    return PLAYER2_WIN;
                }
            }
        }
        //Check Winning conditions in Coloumns
        for(int j=0;j<n;j++){
            boolean flag=true;
            for(int i=0;i<n;i++){
                if(btntic[i][j].getTurn()==NO_PLAYER || btntic[0][j].getTurn()!=btntic[i][j].getTurn()){
                    flag=false;
                    break;
                }
            }
            if(flag==true){
                if(btntic[0][j].getTurn()==PLAYER_ONE){
                    return PLAYER1_WIN;
                }
                else if(btntic[0][j].getTurn()==PLAYER2_WIN){
                    return PLAYER2_WIN;
                }
            }
        }
        //Checking in diagonal 1
        boolean flag=true;
        for(int i=0;i<n;i++){
            if(btntic[i][i].getTurn()==NO_PLAYER || btntic[0][0].getTurn()!=btntic[i][i].getTurn()){
                flag=false;
                break;
            }
        }
        if(flag==true){
            if(btntic[0][0].getTurn()==PLAYER_ONE){
                return PLAYER1_WIN;
            }
            else if(btntic[0][0].getTurn()==PLAYER_TWO){
                return PLAYER2_WIN;
            }
        }
        //Checking DIAGONAL 2
        flag=true;
        for(int i=n-1;i>=0;i--){
            int col=n-1-i;
            if(btntic[i][col].getTurn()==NO_PLAYER||btntic[n-1][0].getTurn()!=btntic[i][col].getTurn()){
                flag=false;
                break;
            }

        }
        if(flag==true){
            if (btntic[n - 1][0].getTurn() ==PLAYER_ONE) {
                return PLAYER1_WIN;
            }
            else if(btntic[n-1][0].getTurn()==PLAYER_TWO){
                return PLAYER2_WIN;
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(btntic[i][j].getTurn()==NO_PLAYER){
                    return INCOMPLETE;
                }
            }
        }
            return DRAW;
    }
}
