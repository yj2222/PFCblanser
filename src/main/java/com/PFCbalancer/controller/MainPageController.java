package com.PFCbalancer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.PFCbalancer.model.FormBodyData;
import com.PFCbalancer.model.FormFoodsData;
import com.PFCbalancer.model.FormFoodsList;
import com.PFCbalancer.model.PFC;

@Controller
public class MainPageController {
	
	@Autowired
	HttpSession session;

	String label[] = {"Carbohydrate(炭水化物)","Fat(脂質)","Protein(タンパク質)"};
	
	String loginUser;

	int kal;
	int protein;
	int fat;
	int carb;
	int pfcData[] = {protein, fat, carb};

	int foodsProtein;
	int foodsFat;
	int foodsCarb;

	List<FormFoodsData> foodsList = new ArrayList<>();


	@GetMapping("/")
	public String getMain(@ModelAttribute FormBodyData formBodyData,
		FormFoodsData formFoodsData,
		FormFoodsList formFoodsList,
		Model model) {

		foodsProtein = 0;
		foodsFat = 0;
		foodsCarb = 0;

		for(FormFoodsData ffd : foodsList) {
			foodsProtein += ffd.getFoodsProtein();
			foodsFat += ffd.getFoodsFat();
			foodsCarb += ffd.getFoodsCarb();
		}

		model.addAttribute("foodsList",foodsList);

		model.addAttribute("label",label);
	    model.addAttribute("protein",protein);
	    model.addAttribute("fat",fat);
	    model.addAttribute("carb",carb);
	    model.addAttribute("kal",kal);
	
	    model.addAttribute("foodsProtein",foodsProtein);
	    model.addAttribute("foodsFat",foodsFat);
	    model.addAttribute("foodsCarb",foodsCarb);
	    
	    System.out.println(session.getAttribute("loginUser"));
//	    if() {
//	    	
//	    }
//	    loginUser = (String) session.getAttribute("loginUser");
//	    System.out.println(loginUser);
	    
//	    session.setAttribute("loginUser", loginUser);
	    
	    
	    

		return "mainPage";
	}

	@PostMapping("/main/calcIdealPFC")
	public String postMainIdealPFC(@ModelAttribute FormBodyData formBodyData,
		FormFoodsData formFoodsData,
		FormFoodsList formFoodsList,
		BindingResult bindingResult,
		Model model) {

		PFC pfc = new PFC();

		pfc.setWeight(formBodyData.getWeight());
		pfc.setHeight(formBodyData.getHeight());
		pfc.setAge(formBodyData.getAge());
		pfc.setPFC();

		protein = pfc.getProtein();
		fat = pfc.getFat();
		carb = pfc.getCarb();
		kal = protein + fat + carb;

		return getMain(formBodyData, formFoodsData, formFoodsList, model);
	}

	@PostMapping("/main/calcNowPFC")
	public String postMainNowPFC(@ModelAttribute FormBodyData formBodyData,
		FormFoodsData formFoodsData,
		FormFoodsList formFoodsList,
		BindingResult bindingResult,
		Model model) {

		foodsList.add(formFoodsData);

		return getMain(formBodyData, formFoodsData, formFoodsList, model);
	}

	@PostMapping("/deleteListData")
    public String postUserDetailDelete(@ModelAttribute FormBodyData formBodyData,
  		FormFoodsData formFoodsData,
  		FormFoodsList formFoodsList,
		BindingResult bindingResult,
		Model model) {

	  	int num = formFoodsList.getDelete();
	  	foodsList.remove(num);

	  	return getMain(formBodyData, formFoodsData, formFoodsList, model);
    }
}


