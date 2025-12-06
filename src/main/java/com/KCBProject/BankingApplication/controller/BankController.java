//Authored by Christal Cain and Brandon Lloyd

package com.KCBProject.BankingApplication.controller;

import com.KCBProject.BankingApplication.entity.Bank;
import com.KCBProject.BankingApplication.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BankController {

    @Autowired
    private BankService bankService;

    // Show all banks (admin)
    @GetMapping("/banks")
    public String viewAllBanks(Model model) {
        model.addAttribute("banks", bankService.getAllBanks());
        return "bank-list"; // page we will create next
    }

    // View single bank details
    @GetMapping("/banks/{id}")
    public String viewBank(@PathVariable Long id, Model model) {
        Bank bank = bankService.getBankById(id);
        model.addAttribute("bank", bank);
        return "bank-details"; // page we will create next
    }
}