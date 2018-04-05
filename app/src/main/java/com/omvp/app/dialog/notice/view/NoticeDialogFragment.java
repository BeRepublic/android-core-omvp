package com.omvp.app.dialog.notice.view;

import android.os.Bundle;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.dialog.notice.presenter.NoticeDialogPresenter;
import com.omvp.components.NoticeDialogComponentView;
import com.raxdenstudios.commons.util.Utils;

import butterknife.BindView;

public class NoticeDialogFragment extends BaseViewFragment<NoticeDialogPresenter, NoticeDialogFragment.FragmentCallback>
        implements NoticeDialogView {

    public static final int LAYOUT_ID = R.layout.notice_dialog_fragment;

    @BindView(R.id.notice_dialog_view)
    NoticeDialogComponentView mNoticeDialogComponentView;

    private String titleText;
    private String descriptionText;
    private String acceptText;
    private String denyText;
    private View.OnClickListener onAcceptClickListener;
    private View.OnClickListener onDenyClickListener;

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static NoticeDialogFragment newInstance(Bundle bundle) {
        NoticeDialogFragment fragment = new NoticeDialogFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        if (Utils.hasValue(titleText)) {
            mNoticeDialogComponentView.setTitleText(titleText);
        }
        if (Utils.hasValue(descriptionText)) {
            mNoticeDialogComponentView.setDescriptionText(descriptionText);
        }
        if (Utils.hasValue(acceptText)) {
            mNoticeDialogComponentView.setAcceptTextButton(acceptText, onAcceptClickListener);
        }
        if (Utils.hasValue(denyText)) {
            mNoticeDialogComponentView.setDenyTextButton(denyText, onDenyClickListener);
        }
    }

    public void setTitle(String titleText) {
        this.titleText = titleText;
    }

    public void setDescription(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public void setAcceptButton(String acceptText, View.OnClickListener onClickListener) {
        this.acceptText = acceptText;
        this.onAcceptClickListener = onClickListener;
    }

    public void setDenyButton(String denyText, View.OnClickListener onClickListener) {
        this.denyText = denyText;
        this.onDenyClickListener = onClickListener;
    }
}

