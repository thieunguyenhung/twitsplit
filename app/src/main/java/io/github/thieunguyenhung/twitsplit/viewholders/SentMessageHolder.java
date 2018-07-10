package io.github.thieunguyenhung.twitsplit.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import io.github.thieunguyenhung.twitsplit.R;
import io.github.thieunguyenhung.twitsplit.models.Message;

public class SentMessageHolder extends RecyclerView.ViewHolder {
    private SimpleDateFormat dateFormatter;
    private TextView messageTxt;
    private TextView itemMessageSentTime;
    private ImageView itemMessageSeenImage;

    public SentMessageHolder(View itemView) {
        super(itemView);
        messageTxt = itemView.findViewById(R.id.itemMessageSentTxt);
        itemMessageSentTime = itemView.findViewById(R.id.itemMessageSentTime);
        itemMessageSeenImage = itemView.findViewById(R.id.itemMessageSeenImage);
        dateFormatter = new SimpleDateFormat("h:mm a", Locale.getDefault());
    }

    public void bind(Message message) {
        messageTxt.setText(message.getMessageText());
        if (message.getItemBackgroundResource() != Integer.MIN_VALUE) {
            messageTxt.setBackgroundResource(message.getItemBackgroundResource());
        }
        if (null != message.getSentTime()) {
            itemMessageSentTime.setVisibility(View.VISIBLE);
            itemMessageSeenImage.setVisibility(View.VISIBLE);
            itemMessageSentTime.setText(dateFormatter.format(message.getSentTime().getTime()).toLowerCase());
        } else {
            itemMessageSentTime.setVisibility(View.GONE);
            itemMessageSeenImage.setVisibility(View.GONE);
        }
    }
}
