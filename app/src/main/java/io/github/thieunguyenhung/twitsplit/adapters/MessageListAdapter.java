package io.github.thieunguyenhung.twitsplit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.github.thieunguyenhung.twitsplit.R;
import io.github.thieunguyenhung.twitsplit.models.Message;
import io.github.thieunguyenhung.twitsplit.viewholders.SentMessageHolder;

public class MessageListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Message> messageList;
    private CoordinatorLayout rootLayout;

    public MessageListAdapter(Context context, List<Message> messageList, CoordinatorLayout rootLayout) {
        this.context = context;
        this.messageList = messageList;
        this.rootLayout = rootLayout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_sent, parent, false);
        return new SentMessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SentMessageHolder) {
            ((SentMessageHolder) holder).bind(context, rootLayout, messageList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
