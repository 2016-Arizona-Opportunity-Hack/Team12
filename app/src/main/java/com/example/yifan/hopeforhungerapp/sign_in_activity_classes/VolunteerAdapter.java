package com.example.yifan.hopeforhungerapp.sign_in_activity_classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yifan.hopeforhungerapp.R;
import com.example.yifan.hopeforhungerapp.volunteer_classes.Volunteer;

import java.util.List;

/**
 * Created by Yifan on 10/1/2016.
 */

public class VolunteerAdapter extends ArrayAdapter<Volunteer> {
    public VolunteerAdapter(Context context, int resource, List<Volunteer> objects) {
        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VolunteerHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.single_volunteer_layout, parent, false);
            holder = new VolunteerHolder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder = (VolunteerHolder) convertView.getTag();
        }

        Volunteer volunteer = getItem(position);
        holder.setPersonNameText(volunteer.getFirstName());
        holder.setImage(volunteer.isSignedIn());
        return convertView;
    }

    static class VolunteerHolder {
        private TextView personName;
        private ImageView signedInCheckMark;

        public VolunteerHolder(View view){
            personName = (TextView) view.findViewById(R.id.person_name);
            signedInCheckMark = (ImageView) view.findViewById(R.id.check_mark);
        }

        public void setPersonNameText(String name){
            personName.setText(name);
        }

        public void setImage(boolean signedIn){
            if(signedIn){
                signedInCheckMark.setImageResource(R.mipmap.check_mark);
            }
            else{
                signedInCheckMark.setImageResource(R.mipmap.x_mark);
            }
        }

    }
}
