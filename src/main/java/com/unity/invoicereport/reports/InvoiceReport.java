package com.unity.invoicereport.reports;

import com.unity.invoicereport.service.InvoiceService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;


@Service
public class InvoiceReport {
	
	private final InvoiceService invoiceService;

    public InvoiceReport(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public ResponseEntity<byte[]> exportReport(Long code ) throws FileNotFoundException, JRException {

		
		var invoiceServie = invoiceService.printInvoice(code);

		File file = ResourceUtils.getFile("classpath:reports/invoice-report.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new 
        		JRBeanCollectionDataSource(invoiceServie.getItemsList());
        
        JRBeanCollectionDataSource contacts = new 
        		JRBeanCollectionDataSource(invoiceServie.getContatcsList());
        
        
        Map<String, Object> parameters = new HashMap<>();
        
        parameters.put("InvoiceNo",invoiceServie.getInvoiceID());
        parameters.put("InvoiceDate",invoiceServie.getInvoiceDate());
        parameters.put("CustomerName", invoiceServie.getCustomerName());
        parameters.put("CustomerAddress", invoiceServie.getAddress());
        parameters.put("GrossAmount", invoiceServie.getTotalGrossAmount());
        parameters.put("VatAmount", invoiceServie.getTotalVatAmount());
        parameters.put("discountAmount", invoiceServie.getDiscountAmount());
        parameters.put("NetAmount", invoiceServie.getTotalNetAmount());
        
        
        
        parameters.put("CustomerContacts", contacts);
        parameters.put("InvoiceItems", dataSource);
        parameters.put("Currency", "SAR");
        
        JasperPrint jasperPrint = 
        		JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        
        byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);

        
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "filename=" + System.currentTimeMillis() + "_adr.pdf");

        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(data);
        
	}
}
