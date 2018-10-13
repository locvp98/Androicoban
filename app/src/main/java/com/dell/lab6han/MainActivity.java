package com.dell.lab6han;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dell.lab6han.adapter.AdapterNhanVien;
import com.dell.lab6han.database.DBManager;
import com.dell.lab6han.model.NhanVien;
import com.dell.lab6han.sqliteDAO.NhanVienDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtma;
    private EditText edtten;
    private EditText edtluong;
    private Button btnthem;
    private Button btnhuy;
    private ListView lvthemnv;

    private DBManager dbManager;
    private NhanVienDAO nhanVienDAO;
    private List<NhanVien> nhanVienList;
    private AdapterNhanVien adapterNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtma = findViewById(R.id.edtma);
        edtten = findViewById(R.id.edtten);
        edtluong = findViewById(R.id.edtluong);
        btnthem = findViewById(R.id.btnthem);
        btnhuy = findViewById(R.id.btnhuy);
        lvthemnv=findViewById(R.id.lvthemnv);

        Toast.makeText(this,"Chạm Lâu Để sửa",Toast.LENGTH_LONG).show();

        nhanVienDAO=new NhanVienDAO(new DBManager(this));
        nhanVienList=nhanVienDAO.getallnhanvien();
        adapterNhanVien=new AdapterNhanVien(MainActivity.this,R.layout.item_nhanvien,nhanVienList);
        lvthemnv.setAdapter(adapterNhanVien);


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma=edtma.getText().toString().trim();
                String ten=edtten.getText().toString().trim();
                String luong=edtluong.getText().toString();
                NhanVien nhanVien=new NhanVien(ma,ten,luong);
                    nhanVienDAO.themnhanvien(nhanVien);
                    nhanVienList.add(0,nhanVien);
                    adapterNhanVien.notifyDataSetChanged();


            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtma.setText("");
                edtten.setText("");
                edtluong.setText("");
            }
        });

        lvthemnv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final Dialog dialog=new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialogupdate);

                final NhanVien nhanVien=nhanVienList.get(position);

                final EditText edtma = dialog.findViewById(R.id.edtma);
                edtma.setVisibility(View.GONE);
                final EditText edtten = dialog.findViewById(R.id.edtten);
                final EditText  edtluong = dialog.findViewById(R.id.edtluong);
                Button btnthem = dialog.findViewById(R.id.btnthem);
                Button  btnhuy = dialog.findViewById(R.id.btnhuy);

                edtma.setText(nhanVien.getmMa());
                edtten.setText(nhanVien.getmTennv());
                edtluong.setText(nhanVien.getMluong());

                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btnthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NhanVien nhanVien1=new NhanVien();
                        nhanVien1.setmMa(edtma.getText()+"");
                        nhanVien1.setmTennv(edtten.getText()+"");
                        nhanVien1.setMluong(edtluong.getText()+"");

                        nhanVienDAO.updatenhanvien(nhanVien1);
                        nhanVienList.remove(position);
                        nhanVienList.add(nhanVien1);
                        adapterNhanVien.notifyDataSetChanged();

                        dialog.cancel();
                    }
                });

                dialog.show();

                return false;
            }
        });


    }

    public void delete(int position){

        NhanVien nhanVien=nhanVienList.get(position);
       nhanVienDAO.deletenhanvien(nhanVien.getmMa());
            nhanVienList.remove(position);
            adapterNhanVien.notifyDataSetChanged();


    }
}
