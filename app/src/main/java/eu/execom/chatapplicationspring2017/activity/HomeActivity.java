package eu.execom.chatapplicationspring2017.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import eu.execom.chatapplicationspring2017.R;
import eu.execom.chatapplicationspring2017.adapter.ConversationAdapter;
import eu.execom.chatapplicationspring2017.model.Conversation;

/**
 * OptionsMenu annotation adds specified layout to toolbar.
 */
@OptionsMenu(R.menu.signout)
@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {

    @ViewById
    ListView listView;

    @Bean
    ConversationAdapter conversationAdapter;

    @AfterViews
    void init() {
        // if no user is logged in
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            LoginActivity_.intent(this).start();
        }

        listView.setAdapter(conversationAdapter);
    }

    @ItemClick
    void listViewItemClicked(Conversation conversation) {
        ConversationActivity_.intent(this)
                .conversation(conversation)
                .start();
    }

    /**
     * When option item with id signOut is clicked.
     */
    @OptionsItem
    void signOut() {
        FirebaseAuth.getInstance().signOut();

        // restart this activity after user is logged out because if there is no user we will start
        // login activity
        final Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    /**
     * Called when button with id=fab is clicked.
     */
    @Click
    void fab() {
        CreateConversationActivity_.intent(this).start();
    }

}
