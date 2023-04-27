package com.example.thuchanh2.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh2.R;
import com.example.thuchanh2.adapter.RecycleViewAdapter;
import com.example.thuchanh2.dal.SQLiteHelper;
import com.example.thuchanh2.model.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class FragmntSearch extends Fragment
        implements View.OnClickListener
{
    private RecyclerView recyclerView;
    private SearchView searchView;

    private Button bt;

    private ListView listView;
    private EditText efrom,eTo;
    private Spinner sp1;
    private RecycleViewAdapter adapter;
    private ArrayAdapter<String> adapter2;
    private SQLiteHelper db;
    private  String filter1 ="Tên công việc";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        List<Item> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext()
                                ,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Item> list = new ArrayList<>();
                if(filter1.equals("Tên")) list = db.searchByTen(newText);
                else list = db.searchByNoiDung(newText);
                adapter.setList(list);
                return true;
            }
        });

//        efrom.setOnClickListener(this);
//        eTo.setOnClickListener(this);
        bt.setOnClickListener(this);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filter1 = sp1.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void initView(View v){
        recyclerView = v.findViewById(R.id.recycleViewSearch);
        searchView = v.findViewById(R.id.search);
        sp1 = v.findViewById(R.id.sp1);

        bt = v.findViewById(R.id.btthongke);
        listView = v.findViewById(R.id.lv);

        String[] arr = getResources().getStringArray(R.array.filter1);
        sp1.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spinner,arr));

//        efrom = view.findViewById(R.id.eFrom);
//        eTo = view.findViewById(R.id.eTo);
    }

    @Override
    public void onClick(View v) {
//        if(v==efrom){
//            final Calendar c=Calendar.getInstance();
//            int year=c.get(Calendar.YEAR);
//            int month=c.get(Calendar.MONTH);
//            int day=c.get(Calendar.DAY_OF_MONTH);
//            DatePickerDialog dialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                    String date="";
//                    if(month>8){
//                        date=dayOfMonth+"/"+(month+1)+"/"+year;
//
//                    }else {
//                        date=dayOfMonth+"/0"+(month+1)+"/"+year;
//                    }
//                    efrom.setText(date);
//                }
//            },year,month,day);
//            dialog.show();
//        }
//        if(v==eTo){
//            final Calendar c=Calendar.getInstance();
//            int year=c.get(Calendar.YEAR);
//            int month=c.get(Calendar.MONTH);
//            int day=c.get(Calendar.DAY_OF_MONTH);
//            DatePickerDialog dialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                    String date="";
//                    if(month>8){
//                        date=dayOfMonth+"/"+(month+1)+"/"+year;
//
//                    }else {
//                        date=dayOfMonth+"/0"+(month+1)+"/"+year;
//                    }
//                    eTo.setText(date);
//                }
//            },year,month,day);
//            dialog.show();
//        }
//        if(v==btSearch){
//            String from=efrom.getText().toString();
//            String to=eTo.getText().toString();
//            if(!from.isEmpty()&&!to.isEmpty()){
//                List<Item> list=db.searchByDate(from,to);
//                tvTong.setText("Tong tien"+tong(list));
//
//            }
//        }
        if(v==bt){
            List<String> Array = db.ThongKe();
            adapter2 = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_list_item_1,Array);
            listView.setAdapter(adapter2);
        }
    }
}
