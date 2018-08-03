package pl.com.pers.perscalculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String ERROR = "pole wymagane";

    private TextInputEditText pojemnosc;
    private TextInputEditText cena;
    private TextInputEditText stezenie;
    private EditText ilocsLitrow;
    private EditText cenaZaLitr;
    private Button oblicz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindLayout();
        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    policz(Double.parseDouble(pojemnosc.getText().toString()), Double.parseDouble(cena.getText().toString()), Double.parseDouble(stezenie.getText().toString()));
                    hideKeyboard(getApplicationContext(),v);
                }
            }
        });
    }

    private void bindLayout() {
        pojemnosc = findViewById(R.id.pojemnosc);
        cena = findViewById(R.id.cena);
        stezenie = findViewById(R.id.stezenie_robocze);
        ilocsLitrow = findViewById(R.id.gotowy_produkt);
        cenaZaLitr = findViewById(R.id.cena_1l);
        oblicz = findViewById(R.id.oblicz);

    }

    private boolean validate() {
        int flag = -1;
        System.err.println(flag);
        if (pojemnosc.getText().toString().isEmpty()) {
            pojemnosc.setError(ERROR);
            flag++;
        }
        if (cena.getText().toString().isEmpty()) {
            cena.setError(ERROR);
            flag++;

        }
        if (stezenie.getText().toString().isEmpty()) {
            stezenie.setError(ERROR);
            flag++;

        }

        System.err.println(flag);
        System.err.println(String.valueOf(flag < 0));
        return (flag < 0);
    }

    private void policz(double pojemnosc, double cena, double stezenie) {
        double ilosc = pojemnosc / (stezenie * 0.01);
        String val1=String.format("%.0f",ilosc);
        String val2=String.format("%.2f",cena / ilosc);
        ilocsLitrow.setText(val1);
        cenaZaLitr.setText(val2);
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
