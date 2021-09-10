package com.rename.materialdialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.Window;

import androidx.annotation.NonNull;

import com.rename.materialdialogs.internal.MDRootLayout;

/**
 * @author Aidan Follestad (afollestad)
 */
class DialogBase extends Dialog implements DialogInterface.OnShowListener {

    protected MDRootLayout view;
    private OnShowListener showListener;

    DialogBase(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public View findViewById(int id) {
        return view.findViewById(id);
    }

    @Override
    public final void setOnShowListener(OnShowListener listener) {
        showListener = listener;
    }

    final void setOnShowListenerInternal() {
        super.setOnShowListener(this);
    }

    final void setViewInternal(View view) {
        super.setContentView(view);
        Window window = getWindow();
        if (window != null) {
            view.setBackgroundColor(getContext().getResources().getColor(R.color.white));
            view.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(),
                            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics()));
                }
            });
            view.setClipToOutline(true);

            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onShow(DialogInterface dialog) {
        if (showListener != null) {
            showListener.onShow(dialog);
        }
    }

    @Override
    @Deprecated
    public void setContentView(int layoutResID) throws IllegalAccessError {
        throw new IllegalAccessError(
                "setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
    }

    @Override
    @Deprecated
    public void setContentView(@NonNull View view) throws IllegalAccessError {
        throw new IllegalAccessError(
                "setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
    }

    @Override
    @Deprecated
    public void setContentView(@NonNull View view, ViewGroup.LayoutParams params)
            throws IllegalAccessError {
        throw new IllegalAccessError(
                "setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
    }
}
