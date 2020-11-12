package com.example.ex020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 *@author Tahel Hazan <th8887@bs.amalnet.k12.il>
 *@version	1.1
 *@since 12.11.2020
 *the user choses the type of the series and enters the first number,
 * the "jumps" and goes to the second screen.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * @param ty- the type of the series->
     *          false->Invoice series
     *          true->geometric series
     */
    EditText jumps,first;
    String f,j;
    boolean ty=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jumps=(EditText) findViewById(R.id.jumps);
        first=(EditText) findViewById(R.id.first);

    }

    /**
     * Next page.
     * @param view the view
     *takes the chosen parameters and goes to the second activity.
     */
    public void nextPage(View view) {
        f=first.getText().toString();
        j=jumps.getText().toString();
        if((f.equals(""))||(j.equals("")))
            Toast.makeText(this, "you have to enter a number", Toast.LENGTH_SHORT).show();
        else{
            Intent next= new Intent(this, numberList.class);
            next.putExtra("type",ty);
            next.putExtra("start",Double.parseDouble(f));
            next.putExtra("jump",Double.parseDouble(j));
            startActivity(next);
        }
    }

    /**
     * Type.
     *
     * @param view the view
     * shows the type after the user chooses in the switch the type he wants.
     */
    public void type(View view) {
        if(ty==false)
            ty=true;
        else
            ty=false;
    }
}