package io.github.thieunguyenhung.twitsplit.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import io.github.thieunguyenhung.twitsplit.R;
import io.github.thieunguyenhung.twitsplit.models.Message;

public class SentMessageHolder extends RecyclerView.ViewHolder {
    SimpleDateFormat dateFormatter;
    private TextView messageTxt;
    private TextView itemMessageSentTime;

    public SentMessageHolder(View itemView) {
        super(itemView);
        messageTxt = itemView.findViewById(R.id.itemMessageSentTxt);
        itemMessageSentTime = itemView.findViewById(R.id.itemMessageSentTime);
        dateFormatter = new SimpleDateFormat("h:mm a");
    }

    public void bind(Message message) {
        messageTxt.setText(message.getMessageText());
        itemMessageSentTime.setText(dateFormatter.format(message.getSentTime().getTime()).toLowerCase());
    }
}
