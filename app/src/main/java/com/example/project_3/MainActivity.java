package com.example.project_3;

import static java.lang.Double.NaN;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView textAnswer;
    String firstNum ="",secondNum="",opperand = "";
    boolean restart = false,firstAnswer=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
       textAnswer = findViewById(R.id.textAnswerID);
        textAnswer.setText("0");
    }

    public void addNumberToText(View view) {

        Button btn = (Button) view;
        String num = btn.getText().toString();
        if(textAnswer.getText().toString().equals("0")||restart)
        {
            restart=false;
            textAnswer.setText(num);
        }
        else
        {
            textAnswer.append(num);
        }

        if(opperand.isEmpty())
        {
            firstNum+=num;
        }
        else
        {
           secondNum+=num;
        }
    }

    public void oppFunc(View view) {
        Button btn = (Button) view;
        String btnOpperand = btn.getText().toString();
        if(firstNum.isEmpty()&& btnOpperand.equals("-"))
        {
            firstNum+="-";
            textAnswer.setText("-");
            return;
        }
        if(secondNum.isEmpty()&& btnOpperand.equals("-")&&!opperand.isEmpty())
        {
            secondNum+="-";
            textAnswer.append("-");
            return;
        }
        if(!opperand.isEmpty())
            return;
        opperand = btnOpperand;
        textAnswer.append(opperand);
    }

    public void equalFunc(View view) {
        if(firstNum.isEmpty()||secondNum.isEmpty()||opperand.isEmpty())
            return;
        Integer num1,num2;
        Integer answer=0;
        num1 = Integer.parseInt(firstNum);
        num2 = Integer.parseInt(secondNum);


        if(opperand.equals("+"))
        {
            answer = num1+num2;
        }
        if(opperand.equals("-"))
        {
            answer = num1-num2;
        }
        if(opperand.equals("x"))
        {
            answer = num1*num2;
        }
        if(opperand.equals("÷"))
        {
            if(num2 != 0)
            {
                answer = num1 / num2;
            }
            else
            {
                textAnswer.setText("NaN");
                firstNum="";
                secondNum="";
                opperand="";
                firstAnswer=true;
                restart=true;
                return;
            }
        }
        textAnswer.setText(String.valueOf(answer));
        firstNum = answer.toString();
        secondNum="";
        opperand="";
        firstAnswer=false;

    }

    public void delOpp(View view) {
        textAnswer.setText("0");
        firstNum="";
        secondNum="";
        opperand="";
        firstAnswer=true;
        restart=true;
    }
}