package com.omvp.app.helper;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.omvp.app.ui.samples.notice_dialog.dialog.view.NoticeDialogFragment;

import timber.log.Timber;

public class DialogHelper {

    private final Resources mResources;
    private final FragmentManager mFragmentManager;
    private final Bundle mExtras;

    public DialogHelper(Activity activity, FragmentManager fragmentManager) {
        mResources = activity.getResources();
        mFragmentManager = fragmentManager;
        mExtras = activity.getIntent().getExtras();
    }

    public DialogHelper(Fragment fragment, FragmentManager fragmentManager) {
        mResources = fragment.getResources();
        mFragmentManager = fragmentManager;
        mExtras = fragment.getArguments();
    }

    public NoticeDialogFragment showError(String title, String message) {
        return showError(title, message, null);
    }

    public NoticeDialogFragment showError(String title, String message, final View.OnClickListener onAcceptClickListener) {
        final NoticeDialogFragment dialog = NoticeDialogFragment.newInstance(mExtras);
        dialog.setTitle(title);
        dialog.setDescription(message);
        dialog.setAcceptButton(mResources.getString(android.R.string.ok), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAcceptClickListener != null) {
                    onAcceptClickListener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        showDialog(dialog, "showError");
        return dialog;
    }

    private void showDialog(final DialogFragment dialog, final String tag) {
        try {
            dialog.show(mFragmentManager, tag);
        } catch (IllegalStateException e) {
            Timber.e(e.getMessage(), e);
        }
    }

}
