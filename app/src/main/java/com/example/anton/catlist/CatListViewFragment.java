package com.example.anton.catlist;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.anton.catlist.models.CatsList;
import com.example.anton.catlist.presenters.CatListPresenter;
import com.example.anton.catlist.views.CatListViewFragmentI;


public class CatListViewFragment extends Fragment implements CatListViewFragmentI {
    public final static String LIST_STATE_KEY = "recycler_list_state";
    public final static String LIST_STATE_DATA = "recycler_list_data";
    Parcelable listState;
    private SwipeRefreshLayout swipeContainer;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CatRecyclerAdapter adapter;
    private CatsList list;
    private CatListPresenter presenter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) restorePreviousState(savedInstanceState);
        presenter = new CatListPresenter();

        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.cat_list_fragment, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        View view = getView();

        recyclerView = view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        if (list == null) {
            list = presenter.getListCats();
            swipeContainer = (SwipeRefreshLayout)getView().findViewById(R.id.swipeContainer);

            adapter = new CatRecyclerAdapter(list);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        } else {

            adapter = new CatRecyclerAdapter(list);
            swipeContainer = (SwipeRefreshLayout)getView().findViewById(R.id.swipeContainer);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
            adapter.notifyDataSetChanged();
        }
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list = presenter.getNewListCats();
            }
        });

    }


    @Override
    public void showCats() {
        adapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        presenter.unSetView();
    }


    private void restorePreviousState(Bundle state) {
        list = state.getParcelable(LIST_STATE_DATA);
        listState = state.getParcelable(LIST_STATE_KEY);
    }
}
