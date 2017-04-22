package yaskiv.docsviwer.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import yaskiv.docsviwer.Model.Entity.Document;
import yaskiv.docsviwer.R;

/**
 * Created by dyedfox on 22.04.17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder> {
    private LayoutInflater inflater;
    List<Document> listOfDocuments = Collections.EMPTY_LIST;
    public RecyclerViewAdapter (Context context, List<Document> listOfDocuments)
    {
         inflater = LayoutInflater.from(context);
        this.listOfDocuments = listOfDocuments;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textDate;

        public ViewHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.text_name);
            textDate = (TextView) itemView.findViewById(R.id.text_date);


        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_recycler_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Document current = listOfDocuments.get(position);
        holder.textName.setText(current.getName());
        holder.textDate.setText(current.getDateOfDownloads().toString());
    }

    @Override
    public int getItemCount() {
        return listOfDocuments.size();
    }


}
