package com.excel.sheet;

import com.excel.Sheet;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class TablesActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Sheet sheet = (Sheet) findViewById(R.id.mysheet);
        String[] record = new String[]{
        		"rreew",
        		"1231",
        		"1231",
        		"1231",
        		"1231",
        		"1231",
        		"1231",
        		"1231",
        		"1231",
        		"1231",
        		"1231",
        		"1231",
        		"1231",
        		"1231",
        		"1231",
        		"1231"
        };
        
        String[] record2 = new String[]{
        		"adf",
        		"12ff31",
        		"as1231",
        		"ffe",
        		"ww",
        		"223fff",
        		"ffdd",
        		"fewer",
        		"asdfasfd",
        		"ffdds",
        		"asdffew",
        		"dsfxcv",
        		"ewegc",
        		"afwerq",
        		"ddcdqa",
        		"werwc"
        };
        
        sheet.setBackgroundColor(Color.WHITE);
        sheet.AddRecord(record2);
        for (int i = 0; i < 30; ++i){
        	 sheet.AddRecord(record);
        }
        
        sheet.cell(1, 2).setBKColor(Color.BLUE);
        
        sheet.cell(0, 0).setBKColor(Color.BLUE);
        
        sheet.cell(0, 3).setBKColor(Color.BLUE);
        
        sheet.cell(3, 0).setBKColor(Color.BLUE);
    }
}