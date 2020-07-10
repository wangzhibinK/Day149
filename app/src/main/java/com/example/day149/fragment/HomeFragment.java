package com.example.day149.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day149.R;
import com.example.day149.adapter.HomeAdapter;
import com.example.day149.bean.ProjectBean;
import com.example.day149.presenter.ProjectPresenter;
import com.example.day149.view.ProjectView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ProjectView {

    private ArrayList<ProjectBean.DataBean.DatasBean> list;
    private ProjectPresenter projectPresenter;
    private HomeAdapter homeAdapter;
    private RecyclerView rv_home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
       initView(inflate);
        projectPresenter = new ProjectPresenter(this);
        projectPresenter.http();
        return inflate;
    }

    private void initView(View inflate) {
        rv_home = inflate.findViewById(R.id.rv_home);
        rv_home.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();


        homeAdapter = new HomeAdapter(list, getActivity());
        rv_home.setAdapter(homeAdapter);
    }


    @Override
    public void onSuccess(ArrayList<ProjectBean.DataBean.DatasBean> view) {
        list.addAll(view);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
