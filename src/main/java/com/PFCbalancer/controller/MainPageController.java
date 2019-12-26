package com.PFCbalancer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.PFCbalancer.model.FormBodyData;
import com.PFCbalancer.model.FormFoodsData;
import com.PFCbalancer.model.PFC;

@Controller
public class MainPageController {
	
    String label[] = {"Carbohydrate(炭水化物)","Fat(脂質)","Protein(タンパク質)"};

    int kal;
    int protein;
    int fat;
    int carb;
    int pfcData[] = {protein, fat, carb};
    
	String foodsName;
	int foodsProtein;
	int foodsFat;
	int foodsCarb;
    
    

	@GetMapping("/")
	public String getMain(@ModelAttribute FormBodyData formBodyData, FormFoodsData formFoodsData, Model model) {
		
		model.addAttribute("label",label);
        model.addAttribute("protein",protein);
        model.addAttribute("fat",fat);
        model.addAttribute("carb",carb);
        model.addAttribute("kal",kal); 
		
//		model.addAttribute("foodsName",foodsName);
//        model.addAttribute("foodsProtein",foodsProtein);
//        model.addAttribute("foodsFat",foodsFat);
//        model.addAttribute("foodsCarb",foodsCarb);
		
//        model.addAttribute("formFoodsData",formFoodsData); 
		
		return "mainPage";
	}
	
	@PostMapping("/main/calcIdealPFC")
	public String postMainIdealPFC(@ModelAttribute FormBodyData formBodyData, FormFoodsData formFoodsData, BindingResult bindingResult, Model model) {

		PFC pfc = new PFC();
		
		pfc.setWeight(formBodyData.getWeight());
		pfc.setHeight(formBodyData.getHeight());
		pfc.setAge(formBodyData.getAge());
		pfc.setPFC();
		
		protein = pfc.getProtein();
		fat = pfc.getFat();
		carb = pfc.getCarb();
		kal = protein + fat + carb;
		
		model.addAttribute("label",label);
        model.addAttribute("protein",protein);
        model.addAttribute("fat",fat);
        model.addAttribute("carb",carb);
        model.addAttribute("kal",kal); 
        
//        model.addAttribute("foodsName",foodsName);
//        model.addAttribute("foodsProtein",foodsProtein);
//        model.addAttribute("foodsFat",foodsFat);
//        model.addAttribute("foodsCarb",foodsCarb);
        		
		return "mainPage";
	}
	
	@PostMapping("/main/calcNowPFC")
	public String postMainNowPFC(@ModelAttribute FormBodyData formBodyData, FormFoodsData formFoodsData, BindingResult bindingResult, Model model) {
		
//		model.addAttribute("formFoodsData",formFoodsData);
		
		List<FormFoodsData> foodsList = new ArrayList<>();
		foodsList.add(formFoodsData);
		model.addAttribute("foodsList",foodsList);
		
		model.addAttribute("label",label);
        model.addAttribute("protein",protein);
        model.addAttribute("fat",fat);
        model.addAttribute("carb",carb);
        model.addAttribute("kal",kal);
        
//        model.addAttribute("foodsName",foodsName);
//        model.addAttribute("foodsProtein",foodsProtein);
//        model.addAttribute("foodsFat",foodsFat);
//        model.addAttribute("foodsCarb",foodsCarb);
		
		return "mainPage";
	}
}

