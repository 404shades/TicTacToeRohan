package com.example.rohanmalik.tictactoerohan;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Rohan Malik on 16-06-2017.
 */

public class ButtonTic extends Button {
    int turn=MainActivity.NO_PLAYER;
    public ButtonTic(Context context) {
        super(context);
    }
    public int getTurn(){
        return turn;
    }
}
