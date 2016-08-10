package com.quantum.lhe.coupon.com.quantum.lhe.coupen.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.adapters.CategoryOverviewAdapter;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.controllers.CategoryList;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.CategoryModel;

import java.util.ArrayList;

/**
 * Created by Sharjeel on 7/12/2016.
 */

public class CategoryOveviewActivity extends Activity {

    GridView gridView, searchGridView;
    EditText searchField;
    private Button cancel_search;
    CategoryOverviewAdapter adapter;
    CategoryOverviewAdapter searchAdapter;

    ImageView btnMenu;
    ArrayList<CategoryModel> loyaltyCardModels;
    ArrayList<CategoryModel> loyaltyCardSearchModels;
    ArrayList<CategoryModel> loyaltyCardSearchResultsModels;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_overview_screen);
        CategoryList.saveOffersNewsData(this);
        initViews();
    }

    void initViews() {
        gridView = (GridView) findViewById(R.id.grid);
        searchGridView = (GridView) findViewById(R.id.search_grid);
        searchField = (EditText) findViewById(R.id.search_feild);
        cancel_search = (Button) findViewById(R.id.cancel_search);
        btnMenu = (ImageView) findViewById(R.id.btnMenu);

        searchAdapter = new CategoryOverviewAdapter(this, this, R.layout.row_cat_list, CategoryList.categoryModels);
        searchGridView.setAdapter(searchAdapter);
        searchGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(CategoryOveviewActivity.this, AllCouponActivity.class);
                startActivity(intent);
            }
        });

        adapter = new CategoryOverviewAdapter(this, this, R.layout.row_cat_list, CategoryList.categoryModels);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(CategoryOveviewActivity.this, AllCouponActivity.class);
                startActivity(intent);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CategoryOveviewActivity.this, MenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {

                /*String searchString = cs.toString().trim();
                int textLength = searchString.length();

                if (textLength > 0) {

                    cancel_search.setTextColor(getResources().getColor(R.color.colorPrimary));
                    gridView.setVisibility(View.GONE);

                    if (loyaltyCardSearchModels == null || loyaltyCardSearchModels.size() == 0) {
                        loyaltyCardSearchResultsModels = new ArrayList<CategoryModel>();

                    }

                    for (int i = 0; i < loyaltyCardSearchModels.size(); i++) {
                        //Log.e(TAG, "shop_name = .....get");
                        String cardTitle = loyaltyCardSearchModels.get(i).getCategoryTitle().toString();
                        //Log.e(TAG, "shop_name = ...."+ shop_name);
                        if (textLength <= cardTitle.length()) {

                            if (searchString.equalsIgnoreCase(cardTitle.substring(0, textLength)))
                                loyaltyCardSearchResultsModels.add(loyaltyCardSearchModels.get(i));
                        }
                    }

                    if (loyaltyCardSearchResultsModels != null && loyaltyCardSearchResultsModels.size() > 0) {
                        searchGridView.setVisibility(View.VISIBLE);
                        searchAdapter.notifyDataSetChanged();

                    } else {
                        gridView.setVisibility(View.VISIBLE);
                    }
                } else {
                    cancel_search.setTextColor(getResources().getColor(R.color.grey));
                    gridView.setVisibility(View.VISIBLE);
                    searchGridView.setVisibility(View.GONE);

                }*/
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}