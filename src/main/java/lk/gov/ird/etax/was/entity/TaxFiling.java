package lk.gov.ird.etax.was.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tax_filings")
@Data
public class TaxFiling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tin;

    @Column(name = "tax_type", nullable = false)
    private String taxType;

    @Column(name = "tax_period")
    private String taxPeriod;

    @Column(name = "assessed_value")
    private BigDecimal assessedValue;

    @Column(name = "tax_amount")
    private BigDecimal taxAmount;

    @Column(name = "filing_date")
    private LocalDateTime filingDate;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "reference_no")
    private String referenceNo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        filingDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
