package com.PFCbalancer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.PFCbalancer.model.FormBodyData;
import com.PFCbalancer.model.PFC;

@Controller
public class MainPageController {
	
    String label[] = {"Carbohydrate(炭水化物)","Fat(脂質)","Protein(タンパク質)"};

    int kal;
    int protein;
    int fat;
    int carb;
    int pfcData[] = {protein, fat, carb};

	@GetMapping("/")
	public String getMain(@ModelAttribute FormBodyData form, Model model) {

		model.addAttribute("label",label);
        model.addAttribute("protein",protein);
        model.addAttribute("fat",fat);
        model.addAttribute("carb",carb);
        model.addAttribute("kal",kal);
        
		return "mainPage";
	}
	
	@PostMapping("/main/calc")
	public String postMain(@ModelAttribute FormBodyData form, BindingResult bindingResult, Model model) {

		PFC pfc = new PFC();
		
		pfc.setWeight(form.getWeight());
		pfc.setHeight(form.getHeight());
		pfc.setAge(form.getAge());
		pfc.setPFC();
		
		protein = pfc.getProtein();
		fat = pfc.getFat();
		carb = pfc.getCarb();
		kal = protein + fat + carb;
		
		System.out.println("Protein=" + protein + "kal," + (protein / 4) + "g");
		System.out.println("Fat=" + fat + "kal," + (fat / 9) + "g");
		System.out.println("Carbohydrate=" + carb + "kal," + (carb / 4) + "g");
		System.out.println("kal=" + kal);
		
		model.addAttribute("label",label);
        model.addAttribute("protein",protein);
        model.addAttribute("fat",fat);
        model.addAttribute("carb",carb);
        model.addAttribute("kal",kal);
        
        System.out.println(pfcData[0]);
        
        
		return "mainPage";
	}
}

