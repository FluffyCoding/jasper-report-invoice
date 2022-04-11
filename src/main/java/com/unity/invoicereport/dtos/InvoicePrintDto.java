package com.unity.invoicereport.dtos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import com.unity.invoicereport.model.Contact;
import com.unity.invoicereport.model.InvoiceItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoicePrintDto {

	
	private Long invoiceID;
	
	private Date invoiceDate;
	
	private String customerName;
	
	private String address;
	
	private List<Contact> contatcsList;

	private List<InvoiceItems> itemsList;
	
	private BigDecimal totalGrossAmount;
	
	private BigDecimal totalVatAmount;
	
	private BigDecimal discountAmount;
	
	private BigDecimal totalNetAmount;

	
	
	

}
