package in.alfahmi.cekhppalf;

import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;

import java.text.*;

public class MainActivity extends AppCompatActivity
{
    private Spinner sp1, sp2, sp3, sp4;
    private Button buttonCek, buttonReset;
    private TextView tvHasil, tvHasilKali;
    private EditText edtQuantity, edtCashback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alfdev__main);
        getSupportActionBar().setLogo(R.drawable.alfdev__app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(" Cek HPP");

        tvHasil = (TextView)findViewById(R.id.hasil);
        tvHasilKali = (TextView)findViewById(R.id.hasil_kali);
        sp1 = (Spinner) findViewById(R.id.alfahmi_produk);
        sp2 = (Spinner) findViewById(R.id.alfahmi_mkios1);
        sp3 = (Spinner) findViewById(R.id.alfahmi_mkios2);
        sp4 = (Spinner) findViewById(R.id.alfahmi_mkios3);

        edtQuantity = (EditText) findViewById(R.id.alfahmi_qty);
        edtCashback = (EditText) findViewById(R.id.alfahmi_cashback);
        buttonCek = (Button) findViewById(R.id.alfahmi_cek_btn);
        buttonReset = (Button) findViewById(R.id.alfahmi_reset_btn);

        tombolCek();
        tombolReset();

    }
    public void tombolCek() {

        buttonCek.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int spinner_produk = sp1.getSelectedItemPosition();
                int spinner_mkios1 = sp2.getSelectedItemPosition();
                int spinner_mkios2 = sp3.getSelectedItemPosition();
                int spinner_mkios3 = sp4.getSelectedItemPosition();

                String[] harga_produk = getResources().getStringArray(R.array.alf_produk_values);
                String[] harga_mkios1 = getResources().getStringArray(R.array.alf_mkios_values);
                String[] harga_mkios2 = getResources().getStringArray(R.array.alf_mkios_values);
                String[] harga_mkios3 = getResources().getStringArray(R.array.alf_mkios_values);

                int produk = Integer.valueOf(harga_produk[spinner_produk]);
                int mkios1 = Integer.valueOf(harga_mkios1[spinner_mkios1]);
                int mkios2 = Integer.valueOf(harga_mkios2[spinner_mkios2]);
                int mkios3 = Integer.valueOf(harga_mkios3[spinner_mkios3]);

                String str_quantity = edtQuantity.getText().toString().trim();
                String str_cashback = edtCashback.getText().toString().trim();

                int sum = produk + mkios1 + mkios2 + mkios3;

                if (str_cashback.length() == 0 && str_quantity.length() == 0) {
                    DecimalFormat formatter = new DecimalFormat("#,###,###");
                    String format_sum = formatter.format(sum);

                    tvHasilKali.setText("");
                   tvHasil.setText("Rp. " + format_sum);
                } else if (str_cashback.length() == 0 && str_quantity.length() >= 0) {

                    int quantity = Integer.parseInt(edtQuantity.getText().toString());
                    Double jumlah_kali_sum = ((double) sum)*quantity;

                    DecimalFormat formatter = new DecimalFormat("#,###,###");
                    String format_sum = formatter.format(sum);
                    String format_kali_sum = formatter.format(jumlah_kali_sum);

                    tvHasilKali.setText("x" + str_quantity + " = Rp. " +format_kali_sum);
                    tvHasil.setText("Rp. " + format_sum);
                } else if (str_cashback.length() >= 0 && str_quantity.length() == 0) {
                    int substrack = Integer.parseInt(edtCashback.getText().toString());
                    int cashback = sum - substrack;

                    DecimalFormat formatter = new DecimalFormat("#,###,###");
                    String format_cashback = formatter.format(cashback);

                    tvHasil.setText("Rp. " + format_cashback);
                    tvHasilKali.setText("");
                } else if (str_cashback.length() >= 0 && str_quantity.length() >= 0) {
                    int substrack = Integer.parseInt(edtCashback.getText().toString());
                    int quantity = Integer.parseInt(edtQuantity.getText().toString());
                    int cashback = (produk + mkios1 + mkios2 + mkios3) - substrack;

                    Double jumlah_kali_cashback = ((double)cashback)*quantity;
                    DecimalFormat formatter = new DecimalFormat("#,###,###");

                    String format_cashback = formatter.format(cashback);
                    String format_kali_cashback = formatter.format(jumlah_kali_cashback);

                    tvHasilKali.setText("x" + str_quantity + " = Rp. " +format_kali_cashback);
                    tvHasil.setText("Rp. " + format_cashback);
                }


            }
        });
    }
    public void tombolReset() {

        buttonReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sp1.setSelection(0);
                sp2.setSelection(0);
                sp3.setSelection(0);
                sp4.setSelection(0);
                tvHasil.setText("Rp. 0");
                tvHasilKali.setText("");
                edtQuantity.setText(null);
                edtCashback.setText(null);

            }
        });
    }
}