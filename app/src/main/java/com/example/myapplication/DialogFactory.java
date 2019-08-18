package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DialogFactory {
    private Context context;

    public interface DialogFactoryInteraction {

        void onAcceptButtonClicked(String... strings);

        void onDeniedButtonClicked(boolean cancel_dialog);
    }

    public DialogFactory(Context ctx) {

        this.context = ctx;
    }

    public void createNoInternetDialog(DialogFactoryInteraction listener, View root) {

        View customLayout = LayoutInflater.from(context).inflate(R.layout.no_internet_dialog, (ViewGroup) root, false);
//        View customLayout = LayoutInflater.from(context).inflate(R.layout.no_internet_dialog1, (ViewGroup) root, false);

        //define views inside of dialog
//        ImageView image_dialog = customLayout.findViewById(R.id.image_dialog);
        TextView text_body = customLayout.findViewById(R.id.text_body);
        TextView btn_wifi_dialog = customLayout.findViewById(R.id.btn_exit_dialog);
        TextView btn_data_dialog = customLayout.findViewById(R.id.btn_cancel_dialog);

        //set default values of views
        text_body.setText("text_no_internet_connection");
        btn_wifi_dialog.setText("text_internet_setting");
        btn_data_dialog.setText("text_data_setting");
//        image_dialog.setImageResource(R.drawable.no_wifi);


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(customLayout);

        //create dialog and set background transparent
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        if (dialog.getWindow() != null) {

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        //set click listener for views inside of dialog
        btn_wifi_dialog.setOnClickListener(view -> listener.onAcceptButtonClicked(""));
        btn_data_dialog.setOnClickListener(view -> listener.onDeniedButtonClicked(false));


        //if dialog dismissed, this action will be called
        dialog.setOnDismissListener(dialogInterface -> listener.onDeniedButtonClicked(true));

        dialog.show();
    }

    public void createTokenDialog(View root) {

        View customLayout = LayoutInflater.from(context).inflate(R.layout.token_verify_dialog, (ViewGroup) root, false);

        //define views inside of dialog
        TextView text_body = customLayout.findViewById(R.id.text_body);
        TextView btn_exit_dialog = customLayout.findViewById(R.id.btn_exit_dialog);
//        PreferenceStorage preference = PreferenceStorage.getInstance(context);
//        String user_details = preference.retriveUserDetails();

//        UserDetailsPrefrence userDetails = new Gson().fromJson(user_details, UserDetailsPrefrence.class);
//        text_body.setText(new StringBuilder().append("توکن (نشانه) شما این ").append(userDetails.getIdentity() == null || userDetails.getIdentity().equals("") ? "- - - -" : userDetails.getIdentity()).append(" است لطفا آن را در جای خالی وارد کنید."));

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(customLayout);

        //create dialog and set background transparent
        AlertDialog dialog = builder.create();
        if (dialog.getWindow() != null) {

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        btn_exit_dialog.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
    }



    public void create_testdialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage("testi");
        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create().show();
    }


}
