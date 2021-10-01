package com.example.apitest;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;


public class PersonAdapter extends BaseAdapter {
    private List<Person> personList;
    private MainActivity context;

    public PersonAdapter(List<Person> persons, MainActivity mainActivity) {
        personList = persons;
        context = mainActivity;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.person_list_obj, null);
        Person person = personList.get(position);

        TextView personName = v.findViewById(R.id.lstPersonName);
        personName.setText(person.getName());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.personListOnClick(person);
            }
        });
        return v;
    }

}

