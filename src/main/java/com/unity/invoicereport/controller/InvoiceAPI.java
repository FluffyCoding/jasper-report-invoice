package com.unity.invoicereport.controller;


import com.unity.invoicereport.dtos.InvoicePrintDto;
import com.unity.invoicereport.reports.InvoiceReport;
import com.unity.invoicereport.service.InvoiceService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping(path = "/api")
public class InvoiceAPI {

	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	InvoiceReport invoiceReport;
	
	@GetMapping(path = "/inv/{id}")
	public InvoicePrintDto printInvoice(@PathVariable(name = "id") Long id){
		
		return invoiceService.printInvoice(id);
	}
	
	@GetMapping(path = "/inv/print/{id}")
	public ResponseEntity<byte[]> printPdfInvoice(@PathVariable(name = "id") Long code) throws JRException, FileNotFoundException {
        return invoiceReport.exportReport(code);
}
	
	
}
