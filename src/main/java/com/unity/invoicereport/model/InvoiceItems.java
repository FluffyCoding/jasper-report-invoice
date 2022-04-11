package com.unity.invoicereport.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice_items_detail")
public class InvoiceItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_no")
    private Long id;

    @Column(name = "item_code",nullable = false)
    private Long itemCode;

    @Column(name = "item_description",nullable = false)
    private String description;


    @Column(name = "item_amount")
    private BigDecimal itemPrice;

    @Column(name = "vat_amount")
    private BigDecimal vatValue;


    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "item_sequence")
    private Integer sequenceNo;

    public InvoiceItems(Long itemCode,
                        String description,
                        BigDecimal itemPrice,
                        BigDecimal vatValue,
                        BigDecimal totalAmount

                        ) {


        this.itemCode = itemCode;
        this.description = description;
        this.itemPrice = itemPrice;
        this.vatValue = vatValue;
        this.totalAmount = totalAmount;

    }
    
}
