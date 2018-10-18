package com.example.ytpillai.cmsc_355_proj.messaging;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ytpillai.cmsc_355_proj.R;

private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
    private TextView messageText, timeText, nameText;
    ImageView profileImage;

    ReceivedMessageHolder(View itemView) {
        super(itemView);
        messageText = itemView.findViewById(R.id.ChatBody);
      //  timeText = itemView.findViewById(R.id.text_message_time);
      //  nameText = itemView.findViewById(R.id.text_message_name);
        //profileImage = itemView.findViewById(R.id.image_message_profile);
    }

    void bind(Message message) {
        messageText.setText(message.getMessage());

        // Format the stored timestamp into a readable String using method.
    //    timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
    //    nameText.setText(message.getSender().getNickname());

        // Insert the profile image from the URL into the ImageView.
     //   Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
    }
}