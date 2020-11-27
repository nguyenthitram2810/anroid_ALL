package com.example.ptbac2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv_result;
    private EditText variable_c, variable_b, variable_a;
    private ArrayList<String> arr;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_result = findViewById(R.id.lv_result);
        variable_c = findViewById(R.id.variable_c);
        variable_b = findViewById(R.id.variable_b);
        variable_a = findViewById(R.id.variable_a);

        arr = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        lv_result.setAdapter(adapter);

        lv_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ResultDetail.class);
                String data = arr.get(i);
                intent.putExtra("result", data);
                startActivity(intent);
            }
        });
        lv_result.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                arr.remove(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
    public void solveMath(View v) {
        try {
            if (!checkValidVariable(variable_a.getText().toString())) {
                throw new Exception("Biến a không hợp lệ");
            }
            if (!checkValidVariable(variable_b.getText().toString())) {

                throw new Exception("Biến b không hợp lệ");
            }
            if (!checkValidVariable(variable_c.getText().toString())) {
                throw new Exception("Biến c không hợp lệ");
            }
            String result = "";
            double a = Double.parseDouble(variable_a.getText().toString());
            if(a == 0) {
                throw new Exception("Biến a phải lớn hơn 0");
            }
            result += variable_a.getText() + "x^2 + " + variable_b.getText() + "x + " + variable_c.getText() + " = 0";
            double b = Double.parseDouble(variable_b.getText().toString());
            double c = Double.parseDouble(variable_c.getText().toString());
            double delta = Math.pow(b, 2) - 4*a*c;
            if(delta < 0) {
                result += "\n" + "Phương trình vô nghiệm";
            }
            else if(delta == 0) {

                result += "\n" + "Phương trình có nghiệm kép: x1 = x2 = "+ (-b)/(2*a);
            }
            else {
                result += "\n" + "Phương trình có hai nghiệm: x1 = " + (-b - Math.sqrt(delta))/(2*a) + ", x2 = " + (-b + Math.sqrt(delta))/(2*a);
            }
            arr.add(result);
            adapter.notifyDataSetChanged();
            variable_c.setText("");
            variable_b.setText("");
            variable_a.setText("");
        }
        catch(Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    public boolean checkValidVariable(String variable) {
        if(variable.trim().matches("[-0-9.]+") == false || variable.trim().length() <= 0) {
            return false;
        }
        return true;
    }
}