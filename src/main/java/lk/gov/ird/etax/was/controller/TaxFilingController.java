package lk.gov.ird.etax.was.controller;

import lk.gov.ird.etax.was.dto.TaxFilingDTO;
import lk.gov.ird.etax.was.entity.TaxFiling;
import lk.gov.ird.etax.was.service.TaxFilingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tax")
@CrossOrigin(origins = "*")
public class TaxFilingController {

    @Autowired
    private TaxFilingService taxFilingService;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "etax-was",
            "version", "1.0.0"
        ));
    }

    @GetMapping("/filings/{tin}")
    public ResponseEntity<List<TaxFiling>> getFilings(@PathVariable String tin) {
        return ResponseEntity.ok(taxFilingService.getFilingsByTin(tin));
    }

    @PostMapping("/filings")
    public ResponseEntity<TaxFiling> createFiling(@RequestBody TaxFilingDTO dto) {
        return ResponseEntity.ok(taxFilingService.createFiling(dto));
    }

    @PutMapping("/filings/{id}/payment")
    public ResponseEntity<TaxFiling> updatePayment(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(taxFilingService.updatePaymentStatus(id, status));
    }

    @GetMapping("/types")
    public ResponseEntity<List<Map<String, String>>> getTaxTypes() {
        return ResponseEntity.ok(List.of(
            Map.of("code", "PROPERTY_TAX",   "name", "Property Tax",           "rate", "1%"),
            Map.of("code", "TRANSFER_TAX",   "name", "Transfer Tax",            "rate", "0.5%"),
            Map.of("code", "LICENSE_TAX",    "name", "License Tax",             "rate", "Fixed"),
            Map.of("code", "STAMP_DUTY",     "name", "Documentary Stamp Tax",   "rate", "1.5%"),
            Map.of("code", "REGISTRATION",   "name", "Registration Tax",        "rate", "0.5%")
        ));
    }
}
