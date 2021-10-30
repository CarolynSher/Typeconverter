package android.carolynbicycleshop.typeconverter.ui;
import android.carolynbicycleshop.typeconverter.Entity.ThingEntity;
import android.carolynbicycleshop.typeconverter.R;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ThingAdapter extends RecyclerView.Adapter<ThingAdapter.ThingViewHolder>{

class ThingViewHolder extends RecyclerView.ViewHolder {
    private final TextView thingItemView;

    private ThingViewHolder(View itemView) {
        super(itemView);
        thingItemView = itemView.findViewById(R.id.thingTextView);
        itemView.setOnClickListener((v)-> {
                int position = getAdapterPosition();
                final ThingEntity current = mThings.get(position);
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            String dateString=sdf.format(current.getThingDate().getTime());
                Intent intent = new Intent(context, MainDetailActivity.class);
                intent.putExtra("thingName", current.getThingName());
                intent.putExtra("thingDate", dateString);
                intent.putExtra("thingID", current.getThingID());
                intent.putExtra("position", position);
                context.startActivity(intent);

            });
        }

    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<ThingEntity> mThings; // Cached copy of words

    public ThingAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ThingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.thing_list_item, parent, false);
        return new ThingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ThingViewHolder holder, int position) {

        if (mThings != null) {
            final ThingEntity current = mThings.get(position);
            holder.thingItemView.setText(current.getThingName());

        } else {
            // Covers the case of data not being ready yet.
            holder.thingItemView.setText("No Word");
        }
    }

    public void setWords(List<ThingEntity> words) {
        mThings = words;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (mThings != null)
            return mThings.size();
        else return 0;
    }
}

