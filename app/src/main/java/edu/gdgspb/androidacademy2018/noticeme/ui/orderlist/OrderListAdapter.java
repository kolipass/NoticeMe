package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
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
        final OrderListData item = orders.get(position);
        final Context context = holder.orderName.getContext();
        holder.orderName.setText(item.getOrderName());
        holder.orderDate.setText(Utils.getStringByDate(item.getOrderDate()));
        if (item.isActual()) {
            holder.orderCard.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
        } else {
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
        private AdapterItemClickListener listener;

        ViewHolder(View itemView, final AdapterItemClickListener listener) {
            super(itemView);
            orderName = itemView.findViewById(R.id.order_name);
            orderDate = itemView.findViewById(R.id.order_date);
            orderCard = itemView.findViewById(R.id.card);
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
