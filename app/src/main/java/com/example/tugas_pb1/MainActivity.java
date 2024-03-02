package com.example.tugas_pb1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etMie1, etMie2, etMie3, etMie4, etTeh, etKopi;
    private TextView tvTitle;
    private RadioGroup rgGroup;
    private Button btnPesan;
    private RadioButton rbNone, rbBronze, rbSilver, rbGold, rbPlatinum;
    private TextView tvTotal;

    private int[] hargaBarang = {10000, 10000, 12000, 500000, 5000, 5000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMie1 = findViewById(R.id.etMie1);
        etMie2 = findViewById(R.id.etMie2);
        etMie3 = findViewById(R.id.etMie3);
        etMie4 = findViewById(R.id.etMie4);
        etTeh = findViewById(R.id.etTeh);
        etKopi = findViewById(R.id.etKopi);
        tvTitle = findViewById(R.id.tvTitle);
        rgGroup = findViewById(R.id.rgGroup);
        btnPesan = findViewById(R.id.btnPesan);
        rbNone = findViewById(R.id.rbNone);
        rbBronze = findViewById(R.id.rbBronze);
        rbSilver = findViewById(R.id.rbSilver);
        rbGold = findViewById(R.id.rbGold);
        rbPlatinum = findViewById(R.id.rbPlatinum);
        tvTotal = findViewById(R.id.tvTotal);

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitungTotal();
            }
        });
    }

    private void hitungTotal() {
        int diskon = 0;
        int totalHarga = 0;
        int jumlahMie1 = 0, jumlahMie2 = 0, jumlahMie3 = 0, jumlahMie4 = 0, jumlahTeh = 0, jumlahKopi = 0;

        try {
            jumlahMie1 = Integer.parseInt(etMie1.getText().toString());
            jumlahMie2 = Integer.parseInt(etMie2.getText().toString());
            jumlahMie3 = Integer.parseInt(etMie3.getText().toString());
            jumlahMie4 = Integer.parseInt(etMie4.getText().toString());
            jumlahTeh = Integer.parseInt(etTeh.getText().toString());
            jumlahKopi = Integer.parseInt(etKopi.getText().toString());
        } catch (NumberFormatException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Peringatan");
            builder.setMessage("Mohon isi semua kolom terlebih dahulu! " );
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
            return;
        }
        int totalHargaMie1 = jumlahMie1 * hargaBarang[0];
        int totalHargaMie2 = jumlahMie2 * hargaBarang[1];
        int totalHargaMie3 = jumlahMie3 * hargaBarang[2];
        int totalHargaMie4 = jumlahMie4 * hargaBarang[3];
        int totalHargaTeh = jumlahTeh * hargaBarang[4];
        int totalHargaKopi = jumlahKopi * hargaBarang[5];


        totalHarga += totalHargaMie1;
        totalHarga += totalHargaMie2;
        totalHarga += totalHargaMie3;
        totalHarga += totalHargaMie4;
        totalHarga += totalHargaTeh;
        totalHarga += totalHargaKopi;


        int totalHargaSebelumDiskon = totalHarga;
        if (totalHarga >= 1000000) {
            diskon = totalHarga * 10 / 100;
            totalHarga -= diskon;
        }
        int diskonMembership = 0;
        if (rgGroup.getCheckedRadioButtonId() == R.id.rbPlatinum) {
            diskonMembership = 200000;
        }else if (rgGroup.getCheckedRadioButtonId() == R.id.rbGold) {
            diskonMembership = 5000;
        } else if (rgGroup.getCheckedRadioButtonId() == R.id.rbSilver) {
            diskonMembership = 3000;
        } else if (rgGroup.getCheckedRadioButtonId() == R.id.rbBronze) {
            diskonMembership = 1000;
        }

        totalHarga -= diskonMembership;

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Detail Transaksi");
        builder.setMessage("Indomie Goreng : " + jumlahMie1 + " x Rp " + hargaBarang[0] + " = Rp " + totalHargaMie1 + "\n" +
                "Indomie Kuah : " + jumlahMie2 + " x Rp " + hargaBarang[1] + " = Rp " + totalHargaMie2 + "\n" +
                "Indomie Seblak : " + jumlahMie3 + " x Rp " + hargaBarang[2] + " = Rp " + totalHargaMie3 + "\n" +
                "Indomie Wagyu : " + jumlahMie4 + " x Rp " + hargaBarang[3] + " = Rp " + totalHargaMie4 + "\n" +
                "Teh Es : " + jumlahTeh + " x Rp " + hargaBarang[4] + " = Rp " + totalHargaTeh + "\n" +
                "Kopi : " + jumlahKopi + " x Rp " + hargaBarang[5] + " = Rp " + totalHargaKopi + "\n" +
                "Total Harga Sebelum Diskon: Rp " + totalHargaSebelumDiskon + "\n" +
                "-------------------------------------------------------------------\n" +
                "Diskon Belanja Lebih dari Sejuta: Rp " + diskon + "\n" +
                "Diskon Membership: Rp " + diskonMembership + "\n" +
                "-------------------------------------------------------------------\n" +
                "Total Belanja: Rp " + totalHarga);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}