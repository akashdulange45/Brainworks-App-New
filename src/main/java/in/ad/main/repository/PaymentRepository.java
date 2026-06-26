package in.ad.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ad.main.entity.Payment;
import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
List<Payment> findByStatus(String status);
}
