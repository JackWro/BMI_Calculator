package com.example.bmicalculatorspring;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BmiController {

    @GetMapping("/")
    public String showForm() {
        return "bmiForm";
    }

    @PostMapping("/calculate")
    public String calculateBmi(@RequestParam("weight") double weight,
                               @RequestParam("height") double height,
                               Model model) {
        double bmi = weight / (height * height);
        double roundedBmi = Math.round(bmi * 100.0) / 100.0; // Zaokrąglenie do dwóch miejsc po przecinku
        model.addAttribute("bmi", roundedBmi);

        if (bmi < 18.5) {
            model.addAttribute("category", "Niedowaga");
        } else if (bmi >= 18.5 && bmi < 25) {
            model.addAttribute("category", "Waga prawidłowa");
        } else {
            model.addAttribute("category", "Nadwaga");
        }

        return "bmiResult";
    }
}

