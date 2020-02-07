package com.mahmoudsaad.market.data.entites;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse implements Serializable {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("category_img")
	private String categoryImg;

	@SerializedName("products")
	private List<ProductsItem> products;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCategoryImg(String categoryImg){
		this.categoryImg = categoryImg;
	}

	public String getCategoryImg(){
		return categoryImg;
	}

	public void setProducts(List<ProductsItem> products){
		this.products = products;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}

	@Override
 	public String toString(){
		return 
			"CategoryResponse{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",category_img = '" + categoryImg + '\'' + 
			",products = '" + products + '\'' + 
			"}";
		}
}