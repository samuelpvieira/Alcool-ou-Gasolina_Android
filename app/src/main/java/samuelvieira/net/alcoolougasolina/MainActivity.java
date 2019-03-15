package samuelvieira.net.alcoolougasolina;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editPrecoAlcool;
    private EditText editPrecoGasolina;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // faz a activity abrir com o teclado minimizado, mesmo se houver foco em algum edit text
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        editPrecoAlcool = findViewById(R.id.editPrecoAlcool);
        editPrecoGasolina = findViewById(R.id.editPrecoGasolina);
        textResultado = findViewById(R.id.tvResultado);

        textResultado.setVisibility(View.GONE);
    }

    public void calcularCombustivel (View view){
        //recuperar valores digitados
        String precoAlcool =  editPrecoAlcool.getText().toString();
        String precoGasolina = editPrecoGasolina.getText().toString();

        //Validar os campos
        Boolean camposValidados = this.validarCampos(precoAlcool, precoGasolina);
        if(camposValidados){
           this.calcularMelhorPreco(precoAlcool, precoGasolina);
        }else{
            textResultado.setText("Preencha os preços primeiro!");
            textResultado.setVisibility(View.VISIBLE);
        }
    }

    public void calcularMelhorPreco(String pAlcool, String pGasolina){
        // converte string para numeros
        Double precoAlcool = Double.parseDouble(pAlcool);
        Double precoGasolina = Double.parseDouble(pGasolina);

        /*
        * Faz o cálculo: Divide o preço do alcool pelo preço da gasolina.
        * Se o resultado for maior ou igual a 0.7, é melhor utilizar gasolina
        */

        Double resultado = precoAlcool/precoGasolina;
        if (resultado>=0.7){
            textResultado.setText("Melhor utilizar Gasolina!");
            textResultado.setVisibility(View.VISIBLE);
        } else{
            textResultado.setText("Melhor utilizar Álcool!");
            textResultado.setVisibility(View.VISIBLE);
        }
    }

    public boolean validarCampos(String pAlcool, String pGasolina){
        Boolean camposValidados = true;

        // Validar
        if (pAlcool==null || pAlcool.equals("")){
            camposValidados = false;
        } else if(pGasolina==null || pGasolina.equals("")){
            camposValidados=false;
        }

        return camposValidados;
    }
}
