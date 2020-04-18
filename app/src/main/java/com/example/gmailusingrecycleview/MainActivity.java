package com.example.gmailusingrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<EmailItemModel> items;
    List<EmailItemModel> tempItems;
    EditText editTextSearch;
    Button buttonFavourite;
    Boolean showFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();

        items.add(new EmailItemModel("Quang Hung", "Subject thumon", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Quang Dung", "Subject hauve", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Quang Dang", "Subject tiendao", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Nguyen Hung", "Subject tienve", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Tran Hung", "Subject tiendaocanh", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Nguyen Nam", "Subject hauvetrungtam", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Tran Nam", "Subject hauvecanh", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Ngoc Linh", "Subject thumon", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Thanh Nam", "Subject thumon", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Phi Hung", "Subject tienvephongngu", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Ho Hai", "Subject codongvien", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Kim Quy", "Subject huanluyenvien", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Quang Hai", "Subject bongda", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Que Ngoc Hai", "Subject bongda", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Duy Manh", "Subject bongda", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Van Truong", "Subject bongda", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Cong Phuong", "Subject bongda", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Van Thanh", "Subject bongda", "Content content","11:00 AM"));
        items.add(new EmailItemModel("Duc Huy", "Subject bongda", "Content content","11:00 AM"));

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        EmailItemAdapter adapter = new EmailItemAdapter(items);
        recyclerView.setAdapter(adapter);

        showFavourite = false;

        buttonFavourite = findViewById(R.id.btnFavourite);
        buttonFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFavourite = (!showFavourite);
                if(showFavourite){
                    tempItems = new ArrayList<>();
                    for(int i = 0; i < items.size(); i++){
                        if(items.get(i).isFavourite)
                            tempItems.add(items.get(i));
                    }
                    EmailItemAdapter tempAdapter = new EmailItemAdapter(tempItems);
                    recyclerView.setAdapter(tempAdapter);
                }
                else{
                    EmailItemAdapter adapter = new EmailItemAdapter(items);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
        editTextSearch = findViewById(R.id.search);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = editTextSearch.getText().toString();
                if(searchText.length() <3){
                    EmailItemAdapter adapter = new EmailItemAdapter(items);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    tempItems = new ArrayList<>();
                    for(int i = 0; i < items.size();i++){
                        if(items.get(i).getName().contains(searchText) ||
                        items.get(i).getSubject().contains(searchText) ||
                        items.get(i).getContent().contains(searchText))
                            tempItems.add(items.get(i));
                    }
                    EmailItemAdapter tempAdapter = new EmailItemAdapter(tempItems);
                    recyclerView.setAdapter(tempAdapter);
                }
            }
        });
    }
}
