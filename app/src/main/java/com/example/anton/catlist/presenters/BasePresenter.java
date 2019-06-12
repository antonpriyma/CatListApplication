package com.example.anton.catlist.presenters;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M, V> {
    protected M model;
    protected WeakReference<V> view;

    public void setModel(M model) {
        resetState();
        this.model = model;
        if (setupDone()) {
            updateView();
        }
    }

    protected void resetState() {
    }

    public void unSetView() {
        this.view = null;
    }

    protected V getView() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }

    public void setView(@NonNull V view) {
        this.view = new WeakReference<>(view);
        if (setupDone()) {
            updateView();
        }
    }

    protected abstract void updateView();

    protected boolean setupDone() {
        return getView() != null && model != null;
    }

}
