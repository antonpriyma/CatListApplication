package com.example.anton.catlist.presenters;

import com.example.anton.catlist.models.CatsList;
import com.example.anton.catlist.views.CatListViewFragmentI;

public class CatListPresenter extends BasePresenter<CatsList, CatListViewFragmentI> {

    @Override
    protected void updateView() {
        if (view != null) {
            view.get().showCats();
        }
    }

    protected void onSwipe() {

    }

    protected void onCatClicked() {

    }

    public CatsList getListCats() {
        if (model == null) {
            model = new CatsList();
            model.getWebCats(new CatsList.LoadCatsCallback() {
                @Override
                public void onLoad() {
                    updateView();
                }
            });
        }
        return model;
    }

    public CatsList getNewListCats() {
            model = new CatsList();
            model.getWebCats(new CatsList.LoadCatsCallback() {
                @Override
                public void onLoad() {
                    updateView();
                }
            });
            return model;
    }
}
