package com.cekhpp.alfahmi;

import android.os.*;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.*;
import java.text.*;
import android.text.*;

public class MainActivity extends ActionBarActivity 
{
    private Spinner sp1, sp2, sp3, sp4;
	private Button buttonCek, buttonReset;
	private TextView tvHasil, tvHasilKali;
	private EditText edText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getSupportActionBar().setLogo(R.drawable.ic_arrange_bring_to_front);
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setTitle(" Cek HPP");
		
		tvHasil = (TextView)findViewById(R.id.hasil);
		tvHasilKali = (TextView)findViewById(R.id.hasil_kali);
		sp1 = (Spinner) findViewById(R.id.alfahmi_produk);
		sp2 = (Spinner) findViewById(R.id.alfahmi_mkios1);
		sp3 = (Spinner) findViewById(R.id.alfahmi_mkios2);
		sp4 = (Spinner) findViewById(R.id.alfahmi_mkios3);
		edText = (EditText) findViewById(R.id.alfahmi_qty);
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
					
					int sum = produk + mkios1 + mkios2 + mkios3;
					
					DecimalFormat formatter = new DecimalFormat("#,###,###");
					String test = formatter.format(sum);
	
					if (edText.getText().toString().trim().length() == 0) {
		
						tvHasilKali.setText("");
						
					} else {
						int jumlah2 = Integer.parseInt(edText.getText().toString());
						Double jumlahkali2 = ((double) sum)*jumlah2;
						String test1 = formatter.format(jumlahkali2);
						tvHasilKali.setText("x" + edText.getText().toString() + " = Rp. " +test1);
						
					} 
					tvHasil.setText("Rp. " + test);
					
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
					edText.setText(null);
					
				}
		});
	}
}
