package com.unity.invoicereport.service;

import com.unity.invoicereport.dao.CustomerRepository;
import com.unity.invoicereport.dao.InvoiceItemsRepository;
import com.unity.invoicereport.dao.InvoiceRepository;
import com.unity.invoicereport.dtos.InvoicePrintDto;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
	

	private final InvoiceRepository invoiceRepository;
	private final CustomerRepository customerRepository;
	private final InvoiceItemsRepository invoiceItemsRepository;

	public InvoiceService(InvoiceRepository invoiceRepository, CustomerRepository customerRepository, InvoiceItemsRepository invoiceItemsRepository) {
		this.invoiceRepository = invoiceRepository;
		this.customerRepository = customerRepository;
		this.invoiceItemsRepository = invoiceItemsRepository;
	}


	public InvoicePrintDto printInvoice(Long id) {
		
		var invDto = new InvoicePrintDto();	
		
		var invoice = invoiceRepository.findById(id).get();


		//var invoiceItems = invoiceItemsRepository.findItemsByInvoiceNo(id);
		
		invDto.setInvoiceID(invoice.getId());
		invDto.setInvoiceDate(invoice.getDate());
		invDto.setCustomerName(invoice.getCustomer().getCustomerNameLatin());
		invDto.setAddress(invoice.getCustomer().getAddress());
		invDto.setContatcsList(invoice.getCustomer().getContactList());
		invDto.setItemsList(invoice.getItemsList());
		invDto.setTotalGrossAmount(invoice.getInvoiceAmount());
		invDto.setTotalVatAmount(invoice.getVatValue());
		invDto.setDiscountAmount(invoice.getDiscountValue());
		invDto.setTotalNetAmount(invoice.getTotalAmount());
		
		return invDto;
	}
	
	
	

}
