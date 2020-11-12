package com.example.ex020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Tahel Hazan <th8887@bs.amalnet.k12.il>
 * @version	1.1
 * @since 12.11.2020
 * the user choses the type of the series and enters the first number,
 * the "jumps" and goes to the second screen.
 */
public class numberList extends AppCompatActivity implements AdapterView.OnItemClickListener,
        View.OnCreateContextMenuListener{
    ListView series;
    TextView c;
    boolean op;
    double n0, nd;
    int cp;
    /**
     * @param cp - current position.
     * @param n0 - first number in the series.
     * @param nd - "jumps" between the numbers.
     * @param op - type of the series.
     */
    String [] arrs= new String[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_list);
        series=(ListView) findViewById(R.id.series);
        c=(TextView) findViewById(R.id.c);
        series.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        shows();
        series.setOnItemClickListener(this);
        ArrayAdapter<String> connect= new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,arrs);

        series.setAdapter(connect);
    }

    /**
     * Shows-calculates the numbers in the series and puts them in the array.
     */
    public void shows(){
        Intent here= getIntent();
        op=here.getBooleanExtra("type",false);
        n0=here.getDoubleExtra("start",-0.003);
        nd=here.getDoubleExtra("jump",0);
        arrs[0]=Double.toString(n0);
        if(op){
            if(nd==1)
                for (int i = 1; i < 20; i++) {
                    arrs[i] =Double.toString(n0);
                }
            else{
                for (int i = 1; i < 20; i++) {
                    arrs[i] =Double.toString((n0 * Math.pow(nd, i)));
                }
            }
        }
        else{
            if (nd == 0)
                for (int i = 1; i < 20; i++) {
                    arrs[i] = Double.toString(n0);
                }
            else {
                for (int i = 1; i < 20; i++) {
                    arrs[i] =Double.toString( (n0 + (nd * i)));
                }
            }
        }
    }

    /**
     * Back.
     *
     * @param view the view
     */
    public void back(View view) {
        finish();
    }

    /**
     *
     * @param parent
     * @param view
     * @param pos=cp ->takes the position from the list.
     * @param id
     * Connects the items in the ListView to the ContextMenu Listener.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        view.setOnCreateContextMenuListener(this);
        cp=pos;
    }

    /**
     * @param menu
     * @param v
     * @param menuInfo
     * builds the ContextMenu.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.setHeaderTitle("Series Options:");
        menu.add("Show n");
        menu.add("Show Sum until n");
    }

    /**
     * @param item
     * @return true-puts the positon in the TextView or calculates the sum until the chosen
     * item and puts it in the TextView.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item){
    String oper= item.getTitle().toString();
    if(oper.equals("Show n")){
        c.setText("n="+(cp+1));
    }
    if(oper.equals("Show Sum until n")){
        if (op) {
            if (nd == 1)
                c.setText("Sn=" + n0 * (cp + 1));
            else
                c.setText("Sn=" + (n0 * (Math.pow(nd, cp + 1) - 1)) / (nd - 1));
        }
        else {
            c.setText("Sn=" + (((2 * n0 + (cp) * nd) / 2) * (cp + 1)));
        }
    }
    return true;
    }
}