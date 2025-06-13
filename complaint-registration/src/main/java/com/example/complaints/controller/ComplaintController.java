package com.example.complaints.controller;

import com.example.complaints.model.Complaint;
import com.example.complaints.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ComplaintController {

    @Autowired
    private ComplaintRepository repository;

    @GetMapping("/complaints")
    public String listComplaints(Model model) {
        model.addAttribute("complaints", repository.findAll());
        return "complaints";
    }

    @GetMapping("/complaints/status/open")
    public String listOpenComplaints(Model model) {
        model.addAttribute("complaints", repository.findByStatus("open"));
        return "complaints";
    }

    @GetMapping("/complaint/{id}")
    public String getComplaint(@PathVariable Long id, Model model) {
        model.addAttribute("complaint", repository.findById(id).orElse(new Complaint()));
        return "complaint_form";
    }

    @GetMapping("/complaint")
    public String newComplaintForm(Model model) {
        model.addAttribute("complaint", new Complaint());
        return "complaint_form";
    }

    @PostMapping("/complaint")
    public String saveComplaint(@ModelAttribute Complaint complaint) {
        repository.save(complaint);
        return "redirect:/complaints";
    }

    @GetMapping("/complaint/delete/{id}")
    public String deleteComplaint(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/complaints";
    }
}