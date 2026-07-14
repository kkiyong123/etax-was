package lk.gov.ird.etax.was.repository;

import lk.gov.ird.etax.was.entity.TaxFiling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaxFilingRepository extends JpaRepository<TaxFiling, Long> {
    List<TaxFiling> findByTin(String tin);
    List<TaxFiling> findByTinAndTaxType(String tin, String taxType);
    List<TaxFiling> findByPaymentStatus(String paymentStatus);
}
