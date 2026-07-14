package lk.gov.ird.etax.was.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TaxFilingDTO {
    private String tin;
    private String taxType;
    private String taxPeriod;
    private BigDecimal assessedValue;
    private BigDecimal taxAmount;
    private String paymentStatus;
    private String referenceNo;
}
