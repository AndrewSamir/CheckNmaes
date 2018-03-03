package samir.andrew.checknmaes.adapters;

/**
 * Created by ksi on 19-Jul-17.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.List;

import samir.andrew.checknmaes.R;
import samir.andrew.checknmaes.interfaces.InterfaceAddDataToFirebase;
import samir.andrew.checknmaes.models.ModelData;
import samir.andrew.checknmaes.utlities.HandleAddDataToFirebase;


/**
 * Created by andre on 07-May-17.
 */

public class AdapterModel extends RecyclerView.Adapter<AdapterModel.MyViewHolder> implements InterfaceAddDataToFirebase
{

    private List<ModelData> adapterList;
    private Activity activity;

    @Override
    public void onDataAddedSuccess(String flag)
    {
        Log.d("data", "0");
    }

    @Override
    public void onDataAddedSuccess(String flag, int position)
    {
        Log.d("data", "2");

    }

    @Override
    public void onDataAddedFailed(String flag)
    {

    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public CheckBox tvRvItemName;


        public MyViewHolder(View view)
        {
            super(view);
            tvRvItemName = view.findViewById(R.id.tvRvItemName);
            tvRvItemName.setOnClickListener(this);
        }


        @Override
        public void onClick(View v)
        {
            boolean isChecked = ((CheckBox) v).isChecked();
            String status;
            if (!isChecked)
                status = "yes";
            else
                status = "no";

            HandleAddDataToFirebase.getInstance(activity).callAddAbsent("flag", adapterList.get(getAdapterPosition()).getKeyString()
                    , getAdapterPosition(), status);

        }
    }

    public AdapterModel(List<ModelData> adapterList, Activity activity)
    {
        this.adapterList = adapterList;
        this.activity = activity;
        HandleAddDataToFirebase.getInstance(activity).setClickDialogListener(this);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_name, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        ModelData modelData = adapterList.get(position);
        holder.tvRvItemName.setText(modelData.getName());
        if (modelData.getAbsent().equals("yes"))
            holder.tvRvItemName.setChecked(true);
    }

    @Override
    public int getItemCount()
    {
        return adapterList.size();
    }


    //region helper methods

    public void addItem(ModelData item)
    {
        insertItem(item, adapterList.size());
        notifyDataSetChanged();
    }

    public void insertItem(ModelData item, int position)
    {
        adapterList.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position)
    {
        adapterList.remove(position);
        notifyItemRemoved(position);
    }

    public void clearAllListData()
    {
        int size = adapterList.size();
        adapterList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(List<ModelData> items)
    {
        int startIndex = adapterList.size();
        adapterList.addAll(items);
        notifyItemRangeInserted(startIndex, items.size());
    }

    public List<ModelData> getAllData()
    {
        return adapterList;
    }


    //endregion

}

