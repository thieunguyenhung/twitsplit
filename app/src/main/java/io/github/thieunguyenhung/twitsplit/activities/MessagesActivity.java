package io.github.thieunguyenhung.twitsplit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.thieunguyenhung.twitsplit.R;
import io.github.thieunguyenhung.twitsplit.adapters.MessageListAdapter;
import io.github.thieunguyenhung.twitsplit.models.Message;

public class MessagesActivity extends AppCompatActivity {
    @BindView(R.id.toolbarMessages)
    Toolbar toolbarMessages;
    @BindView(R.id.messagesRecyclerView)
    RecyclerView messagesRecyclerView;
    @BindView(R.id.messagesSendButton)
    ImageButton messagesSendButton;
    @BindView(R.id.messageChatBoxTxt)
    EditText messageChatBoxTxt;
    private MessageListAdapter messageListAdapter;
    private List<Message> dataChatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        //Butter knife binds view
        ButterKnife.bind(this);

        editControls();
        initializeRecyclerView();
        addEvents();
    }

    private void editControls() {
        /*-Action bar*/
        toolbarMessages.setNavigationIcon(R.drawable.ic_navigation_back_blue);
        setSupportActionBar(toolbarMessages);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    private void initializeRecyclerView() {
        dataChatMessages = new ArrayList<>();
        messageListAdapter = new MessageListAdapter(dataChatMessages);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setStackFromEnd(true);
        messagesRecyclerView.setLayoutManager(mLayoutManager);
        messagesRecyclerView.setAdapter(messageListAdapter);
    }

    private void addEvents() {
        messagesSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chatMessage = messageChatBoxTxt.getText().toString();
                if (!chatMessage.isEmpty()) {
                    dataChatMessages.add(new Message(chatMessage, Calendar.getInstance()));
                    messagesRecyclerView.scrollToPosition(dataChatMessages.size() - 1);
                    messageListAdapter.notifyDataSetChanged();
                    messageChatBoxTxt.getText().clear();
                }
            }
        });
    }
}
