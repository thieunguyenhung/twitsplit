package io.github.thieunguyenhung.twitsplit.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.github.thieunguyenhung.twitsplit.R;
import io.github.thieunguyenhung.twitsplit.models.Message;
import io.github.thieunguyenhung.twitsplit.viewholders.SentMessageHolder;

public class MessageListAdapter extends RecyclerView.Adapter {
    // private Context mContext;
    private List<Message> mMessageList;

    public MessageListAdapter(/*Context mContext, */ List<Message> mMessageList) {
        // this.mContext = mContext;
        this.mMessageList = mMessageList;
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
            ((SentMessageHolder) holder).bind(mMessageList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }
}
