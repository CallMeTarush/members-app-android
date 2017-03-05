package com.csivit.tarush.csi_membersapp.Fragments;


import com.csivit.tarush.csi_membersapp.activity.MainActivity;
import com.csivit.tarush.csi_membersapp.Message.Message;
import com.csivit.tarush.csi_membersapp.Message.MessageAdapter;
import com.csivit.tarush.csi_membersapp.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Chat_fragment extends Fragment {


    Button button;
    TextView textView;
    EditText editText;

    public Chat_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.chat_fragment, container, false);

        ((MainActivity) getActivity()).setActionBarTitle("Chat");

        final ArrayList<Message> messages = new ArrayList<Message>();

        messages.add(new Message("This is a custom message from Computer Society of India", true));
        messages.add(new Message("Hey this is a custom message from  my side", false));
        messages.add(new Message("Message3", true));
        messages.add(new Message("Message4", false));
        messages.add(new Message("Message5", true));
        messages.add(new Message("Message6", false));
        messages.add(new Message("Message7", true));
        messages.add(new Message("Message8", false));
        messages.add(new Message("Message9", true));
        messages.add(new Message("Message10", false));
        messages.add(new Message("Message11", true));
        messages.add(new Message("Message12", false));


        final MessageAdapter itemsAdapter = new MessageAdapter(getActivity(), messages);

        final ListView listView = (ListView) v.findViewById(R.id.list);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.setAdapter(itemsAdapter);

        editText = (EditText) v.findViewById(R.id.editText);
        button = (Button) v.findViewById(R.id.button);
        button.setEnabled(false); // set button disable initially

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

                if (s.toString().equals("")) {
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                messages.add(new Message(editText.getText().toString(), true));
                messages.add(new Message(editText.getText().toString(), false));
                editText.setText("");
                itemsAdapter.notifyDataSetChanged();

            }
        });
        return v;
    }

}
