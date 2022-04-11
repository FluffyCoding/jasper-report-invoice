package com.unity.invoicereport.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

	
	
	
    public Customer() {
		super();
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String customerNameArabic;

  
    private String customerNameLatin;

    //@DateTimeFormat(pattern = "dd,MM,yyyy")
    @Column(name = "record_date")
    private Date recordDate;

    @Column(name = "address")
    private String address;

    @Column(name = "zipcode")
    private String zipCode;

    @Column(name = "po_box")
    private String poBox;

   
    @Column(name = "email")
    private String email;

  
    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_name_arabic")
    private String contactNameArabic;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "registration_no")
    private String RegistrationNo;

   
    @Column(name = "expiry_date")
    private Date ExpiryDate;

    @Column(name = "expiry_date_hijri")
    private String dateHijri;

   

    @Column(name = "unique_record_no")
    private String uniqueNo = UUID.randomUUID().toString();

 
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private List<Contact> contactList = new ArrayList<>();

  
    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Invoice> invoiceList;



    
}

