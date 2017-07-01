package eu.execom.chatapplicationspring2017.dao;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.EBean;

import eu.execom.chatapplicationspring2017.model.Conversation;
import eu.execom.chatapplicationspring2017.model.Message;

@EBean
public class MessageDao {

    private static final String MESSAGE_TAG = "messages";

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private Conversation conversation;

    public void initFor(Conversation conversation) {
        this.conversation = conversation;

        database.getReference(ConversationDao.CONVERSATION_TAG + "/" +
                conversation.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // TODO get new messages
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void write(Message message) {
        final DatabaseReference reference =
                database.getReference(ConversationDao.CONVERSATION_TAG)
                        .child(conversation.getId())
                        .child(MESSAGE_TAG).push();

        message.setId(reference.getKey());      // get unique key for new message
        reference.setValue(message);            // push message to database
    }
}
