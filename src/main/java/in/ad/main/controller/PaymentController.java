package in.ad.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import in.ad.main.services.PaymentService;

@Controller
@RequestMapping("/admin")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/payments")
    public String payments(Model model) {

        model.addAttribute(
                "payments",
                service.getAllPayments());

        return "payments";
    }

    @GetMapping("/payment/approve/{id}")
    public String approve(
            @PathVariable Long id) {

        service.approvePayment(id);

        return "redirect:/admin/payments";
    }

    @GetMapping("/payment/reject/{id}")
    public String reject(
            @PathVariable Long id) {

        service.rejectPayment(id);

        return "redirect:/admin/payments";
    }
}
