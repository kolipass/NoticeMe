package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gdgspb.androidacademy2018.noticeme.R;
import edu.gdgspb.androidacademy2018.noticeme.Utils;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {
    private List<OrderListData> orders = new ArrayList<>();
    private AdapterItemClickListener listener;

    OrderListAdapter(AdapterItemClickListener listener){
        this.listener = listener;
    }

    public void update(List<OrderListData> orderList) {
        orders.clear();
        orders.addAll(orderList);
        notifyDataSetChanged();
    }
    public OrderListData getOrderItem(int position){
        return orders.get(position);
    }

    @NonNull
    @Override
    public OrderListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.ViewHolder holder, final int position) {
        final Context context = holder.orderName.getContext();
        final OrderListData item = orders.get(position);
        String description;
        if (item.isWeather()) {
            holder.orderIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_umbrella_24dp));
            if (item.getMoonPhase() != null) {
                description = context.getString(R.string.moon_desc) + " " + item.getMoonPhase();
            } else {
                description = context.getString(R.string.wind_desc) + " " + item.getWindSpeed() + " m/s";
            }
        } else {
            description = context.getString(R.string.location_desc);
            holder.orderIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_globe_24dp));
        }
        holder.descriptionTv.setText(description);
        holder.orderName.setText(item.getOrderName());
        holder.orderDate.setText(Utils.getStringByDate(item.getOrderDate()));
        if (item.isActual()) {
            holder.activeTv.setVisibility(View.VISIBLE);
            holder.orderCard.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
        } else {
            holder.activeTv.setVisibility(View.INVISIBLE);
            holder.orderCard.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView orderName;
        private TextView orderDate;
        private ConstraintLayout orderCard;
        private AppCompatImageView orderIcon;
        private TextView descriptionTv;
        private TextView activeTv;
        final private AdapterItemClickListener listener;

        ViewHolder(View itemView, final AdapterItemClickListener listener) {
            super(itemView);
            orderName = itemView.findViewById(R.id.order_name);
            orderDate = itemView.findViewById(R.id.order_date);
            orderCard = itemView.findViewById(R.id.card);
            orderIcon = itemView.findViewById(R.id.order_icon);
            descriptionTv = itemView.findViewById(R.id.description);
            activeTv = itemView.findViewById(R.id.active);
            this.listener = listener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onViewHolderClick(orders.get(getAdapterPosition()).getNoteId());
                }
            });
        }
   }

    public void removeAt(Integer position) {
        orders.remove(position);
        notifyItemRemoved(position);
    }
    interface AdapterItemClickListener{
        void onViewHolderClick(int noteId);
    }
}
