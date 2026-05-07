package com.example.calculater;

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

    TextView texView;
    String lhs="";String savedOpreator="";
    boolean isNewOprator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        texView = findViewById(R.id.tv_display);

    }
    public void onDigitClick(View v){

        if(v instanceof Button){
        Button clickedButton=(Button) v;
            String buttonText = clickedButton.getText().toString();
            String currentText = texView.getText().toString();
        if(isNewOprator){
            texView.setText("");
            isNewOprator=false;
        }
            if (buttonText.equals(".") && currentText.contains(".")) {
                return;
            }
        texView.append(buttonText);}
    };
//    public void onOpratorClick(View view){
//      Button newOperator=(Button) view;
//      if(savedOpreator.isEmpty()){
//      lhs=texView.getText().toString();
//      savedOpreator=newOperator.getText().toString();
//      texView.setText("");}
//      else{
//        lhs= calculate(lhs,savedOpreator,texView.getText().toString());
//        texView.setText(lhs);
//          savedOpreator=newOperator.getText().toString();
//          isNewOprator=true;
//      }
//    }
//    String calculate(String lhs, String operator, String rhs) {
//        double n1, n2, result;
//        n1 = Double.parseDouble(lhs);
//        n2 = Double.parseDouble(rhs);
//
//        if (operator.equals("+")) {
//            result = n1 + n2;
//        } else if (operator.equals("-")) {
//            result = n1 - n2;
//        } else if (operator.equals("*")) {
//            result = n1 * n2;
//        } else if (operator.equals("/")) {
//            if (n2 == 0) return "Error"; // تجنب القسمة على الصفر
//            result = n1 / n2;
//        } else {
//            return "Error";
//        }
//
//        return String.valueOf(result);
//    }
public void onOpratorClick(View view) {
    Button newOperator = (Button) view;
    String currentText = texView.getText().toString();

    if (lhs.isEmpty()) {
        // أول مرة نضغط فيها على Operator
        lhs = currentText;
        savedOpreator = newOperator.getText().toString();
        isNewOprator = true;
        return;
    }

    if (currentText.isEmpty() || isNewOprator) {
        // المستخدم ضغط على أكثر من Operator على التوالي
        savedOpreator = newOperator.getText().toString(); // نحدث الـ operator فقط
        return;
    }

    // تنفيذ العملية الحسابية العادية
    lhs = calculate(lhs, savedOpreator, currentText);
    texView.setText(lhs);
    savedOpreator = newOperator.getText().toString();
    isNewOprator = true;
}

    String calculate(String lhs, String operator, String rhs) {
    try {
        double n1 = Double.parseDouble(lhs);
        double n2 = Double.parseDouble(rhs);
        double result;

        switch (operator) {
            case "+":
                result = n1 + n2;
                break;
            case "-":
                result = n1 - n2;
                break;
            case "*":
                result = n1 * n2;
                break;
            case "/":
                if (n2 == 0) return "Error"; // تجنب القسمة على صفر
                result = n1 / n2;
                break;
            default:
                return "Error";
        }

        return String.valueOf(result);
    } catch (Exception e) {
        return "Error";
    }
}
    public void onEquleClicked(View view) {
        String rhs = texView.getText().toString();

        if (lhs.isEmpty() || savedOpreator.isEmpty() || rhs.isEmpty()) {
            // لا تقم بأي عملية إذا ما فيش معطيات كافية
            return;
        }

        String result = calculate(lhs, savedOpreator, rhs);
        texView.setText(result);
        lhs = "";             // reset بعد الحساب
        savedOpreator = "";
        isNewOprator = true;  // علشان الكتابة تبدأ من جديد بعد الضغط على =
    }


//    public void onEquleClicked(View view){
//       texView.setText(calculate(lhs,savedOpreator,texView.getText().toString()));
//       lhs="";savedOpreator="";
//
//    }
    public void onClear(View view){
        lhs="";
        savedOpreator="";
        texView.setText("");
    }



}






