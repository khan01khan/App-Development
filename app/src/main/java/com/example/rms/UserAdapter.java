package com.example.rms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<User> users;
    private OnUserToggleListener listener;

    public interface OnUserToggleListener {
        void onToggle(int position);
    }

    public UserAdapter(ArrayList<User> users, OnUserToggleListener listener) {
        this.users = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.tvUsername.setText(user.getUsername());
        holder.tvStatus.setText("Status: " + user.getStatus());

        if (user.isAdmin()) {
            holder.btnToggleStatus.setVisibility(View.GONE);
        } else {
            holder.btnToggleStatus.setVisibility(View.VISIBLE);
            holder.btnToggleStatus.setText(user.getStatus().equals("ACTIVE") ? "Block" : "Unblock");
        }

        holder.btnToggleStatus.setOnClickListener(v -> listener.onToggle(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvStatus;
        Button btnToggleStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            btnToggleStatus = itemView.findViewById(R.id.btnToggleStatus);
        }
    }
}
