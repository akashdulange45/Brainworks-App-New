package in.ad.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ad.main.entity.Payment;
import in.ad.main.repository.PaymentRepository;

@Service
public class PaymentService {
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
                        new RuntimeException("Payment Not Found"));
    }

    public void approvePayment(Long id) {

        Payment payment = getPaymentById(id);

        payment.setStatus("APPROVED");

        repository.save(payment);
    }

    public void rejectPayment(Long id) {

        Payment payment = getPaymentById(id);

        payment.setStatus("REJECTED");

        repository.save(payment);
    }
	
}
