package com.example.android.codelabs.navigation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class DeepLinkFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.deeplink_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.text);
        if (null != getArguments()) {
            textView.setText(getArguments().getString("myarg"));
        }

        Button notificationButton = view.findViewById(R.id.send_notification_button);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                EditText editArgs = view.findViewById(R.id.args_edit_text);
                Bundle args = new Bundle();
                args.putString("myarg", editArgs.getText().toString());

                PendingIntent deeplink = Navigation.findNavController(getView()).createDeepLink()
                        .setDestination(R.id.deeplink_dest)
                        .setArguments(args)
                        .createPendingIntent();

                NotificationManager notificationManager =
                        (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.createNotificationChannel(new NotificationChannel(
                            "deeplink", "Deep Links", NotificationManager.IMPORTANCE_HIGH));
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        getContext(), "deeplink");
                builder.setContentTitle("Navigation")
                        .setContentText("Deep link to Android")
                        .setSmallIcon(R.drawable.ic_android)
                        .setContentIntent(deeplink)
                        .setAutoCancel(true);
                notificationManager.notify(0, builder.build());
            }
        });
        {
        }
    }
}
