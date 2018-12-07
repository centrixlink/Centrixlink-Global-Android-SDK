package com.centrixlink.sdk.global.demo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MainAdTypesListFragmentAdapter extends ArrayAdapter<DemoAdType> {
    static class ViewHolder {
        TextView title;
    }

    LayoutInflater inflater;
    public MainAdTypesListFragmentAdapter(@NonNull Context context, @LayoutRes int resource,
                        @NonNull List<DemoAdType> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View view;
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.main_adtypes_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.title);
        } else  {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        DemoAdType demoAdInfo = getItem(position);
        view.setTag(viewHolder);
        viewHolder.title.setText(demoAdInfo.getTitle());
        return view;
    }
}
