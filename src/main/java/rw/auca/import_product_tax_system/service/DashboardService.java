package rw.auca.import_product_tax_system.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.import_product_tax_system.domain.DeclarationStatus;
import rw.auca.import_product_tax_system.repository.CustomsOfficerRepository;
import rw.auca.import_product_tax_system.repository.ImportDeclarationRepository;
import rw.auca.import_product_tax_system.repository.ImporterRepository;
import rw.auca.import_product_tax_system.repository.InspectionBayRepository;
import rw.auca.import_product_tax_system.repository.TaxAssessmentRepository;

@Service
public class DashboardService {

    @Autowired
    private ImporterRepository importerRepository;

    @Autowired
    private CustomsOfficerRepository customsOfficerRepository;

    @Autowired
    private InspectionBayRepository inspectionBayRepository;

    @Autowired
    private ImportDeclarationRepository importDeclarationRepository;

    @Autowired
    private TaxAssessmentRepository taxAssessmentRepository;

    public Map<String, Long> getSummary()
    {
        Map<String, Long> summary = new LinkedHashMap<>();

        summary.put("importers", importerRepository.count());
        summary.put("customsOfficers", customsOfficerRepository.count());
        summary.put("freeInspectionBays", (long) inspectionBayRepository.findFreeInspectionBays().size());
        summary.put("declarationsToday", importDeclarationRepository.countByDeclarationDate(LocalDate.now()));
        summary.put("submitted", importDeclarationRepository.countByStatus(DeclarationStatus.SUBMITTED));
        summary.put("taxAssessments", taxAssessmentRepository.count());

        return summary;
    }
}