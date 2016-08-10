package com.quantum.lhe.coupon.com.quantum.lhe.coupen.controllers;

import android.content.Context;

import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.CategoryModel;

import java.util.ArrayList;

public class CategoryList {

	public static ArrayList<CategoryModel> categoryModels = new ArrayList<CategoryModel>();
	
	public static void saveOffersNewsData(Context context) {
		categoryModels =  new ArrayList<CategoryModel>();
		CategoryModel shop= new CategoryModel();
		
		shop= new CategoryModel();
		shop.setId(0);
		shop.setCategoryTitle("AUTO");
		categoryModels.add(shop);


		shop= new CategoryModel();
		shop.setId(1);
		shop.setCategoryTitle("HEALTH");
		categoryModels.add(shop);

		shop= new CategoryModel();
		shop.setId(2);
		shop.setCategoryTitle("FOOD");
		categoryModels.add(shop);

		shop= new CategoryModel();
		shop.setId(3);
		shop.setCategoryTitle("CARE");
		categoryModels.add(shop);
		

	}
	

}