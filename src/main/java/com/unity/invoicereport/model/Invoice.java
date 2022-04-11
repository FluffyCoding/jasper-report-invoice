package com.unity.invoicereport.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice_core")
@Entity
public class Invoice {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "Invoice_no")
	    private Long id;

	    @Column(name = "invoice_code")
	    private String invoiceCode;

	    @DateTimeFormat(pattern = "dd-MM-yyyy")
	    @Column(name = "Invoice_date")
	    private Date date;

	    private Long jobNo;

	    @Column(name = "customer_code")
	    private Long CustomerCode;

	    @Transient
	    private String customerName;


	    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JoinColumn(name = "invoice_id")
	    @OrderBy("sequenceNo ASC")
	    private List<InvoiceItems> itemsList = new ArrayList<>();


	    @Column(name = "gross_amount")
	    private BigDecimal invoiceAmount;

	    @Column(name = "vat_amount")
	    private BigDecimal vatValue;

	    @Column(name = "discount")
	    private BigDecimal discountValue;

	    @Column(name = "invoice_total_amount")
	    private BigDecimal totalAmount;

	    @ManyToOne()
	    @JoinColumn(name = "customer_id")
	    private Customer customer;

	    @Column(nullable = false,unique = true)
	    private String uniqueCode = UUID.randomUUID().toString();

	
}
