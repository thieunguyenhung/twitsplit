package io.github.thieunguyenhung.twitsplit.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.thieunguyenhung.twitsplit.R;
import io.github.thieunguyenhung.twitsplit.adapters.MessageListAdapter;
import io.github.thieunguyenhung.twitsplit.business.MessageSplitterImpl;
import io.github.thieunguyenhung.twitsplit.models.Message;

public class MessagesActivity extends AppCompatActivity {
    /*Bind view*/
    @BindView(R.id.toolbarMessages)
    Toolbar toolbarMessages;
    @BindView(R.id.messagesRecyclerView)
    RecyclerView messagesRecyclerView;
    @BindView(R.id.messagesSendButton)
    ImageButton messagesSendButton;
    @BindView(R.id.messageChatBoxTxt)
    EditText messageChatBoxTxt;
    @BindView(R.id.messageRootLayout)
    CoordinatorLayout messageRootLayout;
    /*Bind resources*/
    @BindString(R.string.error_message_only_white_space)
    String ERROR_MESS_WHITE_SPACE;
    @BindString(R.string.btn_dismiss)
    String BTN_DISMISS;
    MessageListAdapter messageListAdapter;
    List<Message> dataChatMessages;

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
        messageListAdapter = new MessageListAdapter(MessagesActivity.this, dataChatMessages, messageRootLayout);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setStackFromEnd(true);
        messagesRecyclerView.setLayoutManager(mLayoutManager);
        messagesRecyclerView.setAdapter(messageListAdapter);
    }

    private void addEvents() {
        messagesSendButton.setOnClickListener(buttonView -> {
            String chatMessage = messageChatBoxTxt.getText().toString();
            messageChatBoxTxt.getText().clear();
            if (!chatMessage.isEmpty()) {
                if (chatMessage.trim().isEmpty()) {
                    final Snackbar snackbar = Snackbar.make(messageRootLayout, ERROR_MESS_WHITE_SPACE, Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction(BTN_DISMISS, snackbarView -> snackbar.dismiss());
                    snackbar.show();
                    return;
                }
                List<String> result = new MessageSplitterImpl().splitMessage(chatMessage);
                if (CollectionUtils.isNotEmpty(result)) {
                    for (int i = 0; i < result.size(); i++) {
                        Message message = new Message(result.get(i), Calendar.getInstance(), R.drawable.message_sent_middle_background);
                        if (i == 0) {
                            if (i != result.size() - 1) {
                                message.setSentTime(null);
                            }
                            message.setItemBackgroundResource(R.drawable.message_sent_top_background);
                        } else if (i == result.size() - 1) {
                            message.setItemBackgroundResource(R.drawable.message_sent_bottom_background);
                        } else {
                            message.setSentTime(null);
                        }
                        dataChatMessages.add(message);
                    }
                    messagesRecyclerView.scrollToPosition(dataChatMessages.size() - 1);
                    messageListAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
