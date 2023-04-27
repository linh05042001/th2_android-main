package com.example.thuchanh2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thuchanh2.dal.SQLiteHelper;
import com.example.thuchanh2.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    public RatingBar rt;
    public Spinner sp;
    public EditText ten,noidung,ngaydenhan;
    public RadioButton ck_chuathuchien,ck_dangthuchien,ck_hoanthanh,ck_1minh,ck_lamchung;
    public CheckBox cb1,cb2;

    public Button btCancel,btUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        ngaydenhan.setOnClickListener(this);
    }
    private void initView(){
        ten = findViewById(R.id.edTenCongViec);
        noidung = findViewById(R.id.edNoiDung);
        ngaydenhan = findViewById(R.id.edNgayDenHan);

        ck_chuathuchien = findViewById(R.id.ck1);
        ck_dangthuchien = findViewById(R.id.ck2);
        ck_hoanthanh = findViewById(R.id.ck3);

        ck_1minh = findViewById(R.id.ck4);
        ck_lamchung = findViewById(R.id.ck5);

        btCancel = findViewById(R.id.btCancel);
        btUpdate = findViewById(R.id.btUpdate);

        cb1=findViewById(R.id.cb1);
        cb2=findViewById(R.id.cb2);

        sp=findViewById(R.id.spCategory);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.iteam_spinner,getResources().getStringArray(R.array.category)));

        rt=findViewById(R.id.rt);
    }

    @Override
    public void onClick(View v) {
        if(v == ngaydenhan){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            String date = "";
                            String s = day+"";
                            if(day<10) s = "0" + s;
                            if(month>8){
                                date = year+"-" +(month+1)+"-"+s;
                            }
                            else{
                                date = year+"-0" +(month+1)+"-"+s;
                            }
                            ngaydenhan.setText(date);
                        }
                    },year,month,day);
            dialog.show();
        }
//        if (v == eDate) {
////            Calendar c = Calendar.getInstance();
////            int y = c.get(Calendar.YEAR);
////            int m = c.get(Calendar.MONTH);
////            int d = c.get(Calendar.DAY_OF_MONTH);
////            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
////                @Override
////                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
////                    eDate.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
////                }
////                }, y, m, d);
////                    dialog.show();
//            Calendar c = Calendar.getInstance();
//            int hh=c.get(Calendar.HOUR_OF_DAY);
//            int mm=c.get(Calendar.MINUTE);
//            TimePickerDialog timedialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//                @Override
//                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                    eDate.setText(hourOfDay+":"+minute);
//                }
//            },hh,mm,false);
//            timedialog.show();
//        }
        if(v == btCancel){
            finish();
        }
        if(v ==btUpdate){
            String t = ten.getText().toString();
            String nd = noidung.getText().toString();

            String tt ="";
            if(ck_chuathuchien.isChecked()){
                tt=ck_chuathuchien.getText().toString();
            }
            if(ck_dangthuchien.isChecked()) {
                tt=ck_dangthuchien.getText().toString();
            }
            if(ck_hoanthanh.isChecked()){
                tt=ck_hoanthanh.getText().toString();
            }

            String ndh = String.format(ngaydenhan.getText().toString() );


            String ht="";
//           if(ck_1minh.isChecked()) {
//                ht = ck_1minh.getText().toString();
//           }
//           if(ck_lamchung.isChecked()){
//                ht = ck_1minh.getText().toString();
//           }
            if(cb1.isChecked()){
                ht = cb1.getText().toString();
            }
            if (cb2.isChecked()&&cb1.isChecked()) {
                ht = cb2.getText().toString()+cb1.getText().toString();
            }

            String c=sp.getSelectedItem().toString();

            String rtt=""+rt.getRating();

            if(!t.isEmpty() && !nd.isEmpty() && ! ndh.isEmpty() && ! tt.isEmpty() ){
                Item i = new Item(t,nd,tt,ndh,rtt);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
        }

    }
}