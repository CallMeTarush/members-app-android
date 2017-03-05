package com.csivit.tarush.csi_membersapp.Message;

import com.csivit.tarush.csi_membersapp.R;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mayank on 10-02-2017.
 */

public class MessageAdapter extends ArrayAdapter<Message> {
    public MessageAdapter(Activity context, ArrayList<Message> messages) {
        super(context, 0, messages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.message_view, parent, false);
        }
        Message currentMessage = getItem(position);

        TextView message = (TextView) v.findViewById(R.id.text_view);
        message.setText(currentMessage.getMessage());
        boolean pos = currentMessage.getPosition();
        FrameLayout frameLayout = (FrameLayout) v.findViewById(R.id.frame_layout);
        LinearLayout.LayoutParams lllp = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        if (pos == false) {
            lllp.gravity = Gravity.RIGHT;
            frameLayout.setBackgroundResource(R.drawable.right);
        } else {
            lllp.gravity = Gravity.LEFT;
            frameLayout.setBackgroundResource(R.drawable.left);
        }
        frameLayout.setLayoutParams(lllp);

        return v;
    }
}
