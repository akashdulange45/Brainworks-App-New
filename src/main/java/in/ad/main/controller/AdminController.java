package in.ad.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.ad.main.repository.PaymentRepository;
import in.ad.main.repository.StudentRepository;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	 @Autowired
	    private StudentRepository studentRepository;

	    @Autowired
	    private PaymentRepository paymentRepository;

	    @GetMapping("/dashboard")
	    public String getDashboard(Model model) {

	        long totalStudents = studentRepository.count();

	        Double totalRevenue = paymentRepository.getTotalRevenue();
	        if (totalRevenue == null) {
	            totalRevenue = 0.0;
	        }

	        

	        model.addAttribute("totalStudents", totalStudents);
	        model.addAttribute("totalRevenue", totalRevenue);
	       

	        return "admindashboard";
	    }
	
	
	
}
