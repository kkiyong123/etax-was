package lk.gov.ird.etax.was.service;

import lk.gov.ird.etax.was.dto.TaxFilingDTO;
import lk.gov.ird.etax.was.entity.TaxFiling;
import lk.gov.ird.etax.was.repository.TaxFilingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TaxFilingService {

    @Autowired
    private TaxFilingRepository taxFilingRepository;

    public List<TaxFiling> getFilingsByTin(String tin) {
        return taxFilingRepository.findByTin(tin);
    }

    public TaxFiling createFiling(TaxFilingDTO dto) {
        TaxFiling filing = new TaxFiling();
        filing.setTin(dto.getTin());
        filing.setTaxType(dto.getTaxType());
        filing.setTaxPeriod(dto.getTaxPeriod());
        filing.setAssessedValue(dto.getAssessedValue());
        filing.setTaxAmount(calculateTax(dto));
        filing.setPaymentStatus("PENDING");
        filing.setReferenceNo("TRN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return taxFilingRepository.save(filing);
    }

    public TaxFiling updatePaymentStatus(Long id, String status) {
        TaxFiling filing = taxFilingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Filing not found: " + id));
        filing.setPaymentStatus(status);
        return taxFilingRepository.save(filing);
    }

    private BigDecimal calculateTax(TaxFilingDTO dto) {
        if (dto.getTaxAmount() != null) {
            return dto.getTaxAmount();
        }
        BigDecimal rate;
        switch (dto.getTaxType()) {
            case "PROPERTY_TAX": rate = new BigDecimal("0.01"); break;
            case "TRANSFER_TAX": rate = new BigDecimal("0.005"); break;
            case "LICENSE_TAX":  return new BigDecimal("1000");
            default:             rate = new BigDecimal("0.01");
        }
        return dto.getAssessedValue() != null
            ? dto.getAssessedValue().multiply(rate)
            : BigDecimal.ZERO;
    }
}
