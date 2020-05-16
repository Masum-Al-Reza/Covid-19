package com.example.covid_savar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<Countrymodel> {

    private Context context;
    private List<Countrymodel> countryModelsList;

    private List<Countrymodel> countryModelsListFiltered;

    public MyCustomAdapter(Context context, List<Countrymodel> countryModelsList) {
        super(context, R.layout.list_custom_item,countryModelsList);

        this.context = context;
        this.countryModelsList = countryModelsList;
       this.countryModelsListFiltered = countryModelsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);
        TextView affected=view.findViewById(R.id.affected);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        TextView tvrecovered = view.findViewById(R.id.total_recoveredTV);
        TextView tvactive = view.findViewById(R.id.total_activeTV);
        TextView tvdeaths = view.findViewById(R.id.total_deathsTV);
        TextView tvasia = view.findViewById(R.id.tvasi);
        ImageView imageView = view.findViewById(R.id.imageFlag);


        tvCountryName.setText(countryModelsListFiltered.get(position).getCountry());
        tvrecovered.setText(countryModelsListFiltered.get(position).getRecovered());
        tvactive.setText(countryModelsListFiltered.get(position).getActive());
        tvdeaths.setText(countryModelsListFiltered.get(position).getDeaths());
        Glide.with(context).load(countryModelsListFiltered.get(position).getFlag()).into(imageView);
        affected.setText(countryModelsListFiltered.get(position).getCases());
        tvasia.setText(countryModelsListFiltered.get(position).getAsia());

        return view;
    }

    @Override
    public int getCount() {
        return countryModelsListFiltered.size();
    }

    @Nullable
    @Override
    public Countrymodel getItem(int position) {
        return countryModelsListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
            Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryModelsList.size();
                    filterResults.values = countryModelsList;

                }else{
                    List<Countrymodel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(Countrymodel itemsModel:countryModelsList){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryModelsListFiltered = (List<Countrymodel>) results.values;
                AffectedCountries.countryModelsList = (List<Countrymodel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
