package com.example.jordan.apppropinas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText EdtCantidad;
    Button btnCalcular;
    TextView txtPorcentaje;
    TextView txtPropina;
    TextView txtTotal;
    Editable cantidad;
    double propina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdtCantidad = (EditText)this.findViewById(R.id.cantidad);
        btnCalcular = (Button)this.findViewById(R.id.calcular);
        txtPorcentaje = (TextView)this.findViewById(R.id.procentaje);
        txtPropina = (TextView)this.findViewById(R.id.propina);
        txtTotal = (TextView)this.findViewById(R.id.total);

        btnCalcular.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                calcularPropina();
            }
        });

        Toolbar menu = (Toolbar) findViewById(R.id.menu);
        setSupportActionBar(menu);
    }
    public void calcularPropina() {
        obtenerPropina();
        cantidad = EdtCantidad.getText();
        SharedPreferences preferencias = this.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        String porcentaje = preferencias.getString("porcentajePropina", "10.0");


        propina = Double.parseDouble(cantidad.toString())*Double.parseDouble(porcentaje)/100;
        txtPropina.setText((Double.toString(propina)));
        double total = propina + Double.parseDouble(cantidad.toString());
        txtTotal.setText(Double.toString(total));
    }
    public void obtenerPropina() {
        SharedPreferences preferencias = this.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        String porcentaje = preferencias.getString("porcentajePropina", "10.0");
        txtPorcentaje.setText(String.format("%s%%", porcentaje));
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.configuracion:
                this.empezarConfiguracion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void empezarConfiguracion(){
        Intent i = new Intent(getBaseContext(), ConfigurationAct.class);
        startActivity(i);
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
