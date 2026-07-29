package in.ad.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.ad.main.entity.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
List<Payment> findByStatus(String status);

@Query("SELECT SUM(p.amount) FROM Payment p")
Double getTotalRevenue();
}
