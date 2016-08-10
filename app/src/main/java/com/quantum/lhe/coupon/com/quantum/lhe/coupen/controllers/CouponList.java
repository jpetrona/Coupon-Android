package com.quantum.lhe.coupon.com.quantum.lhe.coupen.controllers;

import android.content.Context;

import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.CouponModel;

import java.util.ArrayList;

public class CouponList {

	public static ArrayList<CouponModel> shop_objects = new ArrayList<CouponModel>();
	
	public static void saveOffersNewsData(Context context) {
		shop_objects =  new ArrayList<CouponModel>();
		CouponModel shop= new CouponModel();
		
		shop= new CouponModel();
		shop.setId(0);
		shop.setCouponTitle("Burger King");
		shop.setCouponDisc("Our latest Open Store in this center");
		shop.setDiscount("20%");
		shop.setCity("Lahore");
		shop.setDistance("20 Km");
		shop.setLogo_image("burger_king_logo");
		shop.setShop_image("img_05");
		shop_objects.add(shop);

		shop= new CouponModel();
		shop.setId(1);
		shop.setCouponTitle("Burger King");
		shop.setCouponDisc("Our latest Open Store in this center");
		shop.setDiscount("20%");
		shop.setCity("Lahore");
		shop.setDistance("20 Km");
		shop.setLogo_image("burger_king_logo");
		shop.setShop_image("all_coup_bg");
		shop_objects.add(shop);

		shop= new CouponModel();
		shop.setId(2);
		shop.setCouponTitle("Burger King");
		shop.setCouponDisc("Our latest Open Store in this center");
		shop.setDiscount("20%");
		shop.setCity("Lahore");
		shop.setDistance("20 Km");
		shop.setLogo_image("burger_king_logo");
		shop.setShop_image("all_coup_bg");
		shop_objects.add(shop);

		shop= new CouponModel();
		shop.setId(3);
		shop.setCouponTitle("Burger King");
		shop.setCouponDisc("Our latest Open Store in this center");
		shop.setDiscount("20%");
		shop.setCity("Lahore");
		shop.setDistance("20 Km");
		shop.setLogo_image("burger_king_logo");
		shop.setShop_image("all_coup_bg");
		shop_objects.add(shop);
		

	}
	

}