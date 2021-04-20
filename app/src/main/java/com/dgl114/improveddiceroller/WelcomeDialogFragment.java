package com.dgl114.improveddiceroller;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
//**************************************************************************************************
// WelcomeDialogFragment.java          Author: zakacat
//
// This fragment class is needed to display the welcome dialog on app startup.
// This feature is not necessary for the assignment, but it was something that I personally
// wanted to learn.
//**************************************************************************************************
public class WelcomeDialogFragment extends DialogFragment {

   //**********************************************************************************************
   // onCreateDialog() essentially retrieves the MyDialog layout, inflates it, and adds an OK button
   // at the bottom.
   //**********************************************************************************************
    @NonNull
   @Override
   public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
      AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity(),R.style.MyDialog);

      LayoutInflater inflater = requireActivity().getLayoutInflater();

      builder.setView(inflater.inflate(R.layout.welcome_layout, null))

      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
             // 'which' is the index position chosen
         }
      });
      return builder.create();
   }

}


