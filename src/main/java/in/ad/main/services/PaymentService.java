package in.ad.main.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ad.main.entity.Payment;
import in.ad.main.exception.ResourceNotFoundException;
import in.ad.main.repository.PaymentRepository;

@Service
public class PaymentService {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    @Autowired
    private PaymentRepository repository;

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    public List<Payment> getPendingPayments() {
        return repository.findByStatus("PENDING");
    }

    public Payment savePayment(Payment payment) {
        return repository.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment Not Found"));
    }

    public void approvePayment(Long id) {

//        Payment payment = getPaymentById(id);
//
//        payment.setStatus("APPROVED");
//
//        repository.save(payment);
    	
    	Payment payment = getPaymentById(id);
    	
    	logger.info("Payment Approved : Id={}, Student={}, Course={}",
    	        payment.getId(),
    	        payment.getStudentName(),
    	        payment.getCourseName());
    	
    	
    	
    	payment.setStatus("APPROVED");
    	
    	repository.save(payment);
    
    }

    public void rejectPayment(Long id) {

        Payment payment = getPaymentById(id);

        logger.warn("Payment Rejected : Id={}, Student={}",
                payment.getId(),
                payment.getStudentName());
        
        payment.setStatus("REJECTED");

        repository.save(payment);
    }
	
}
