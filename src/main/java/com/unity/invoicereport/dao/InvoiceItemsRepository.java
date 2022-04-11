package com.unity.invoicereport.dao;

import com.unity.invoicereport.model.InvoiceItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceItemsRepository  extends JpaRepository<InvoiceItems, Long> {
	
	
	 	@Query(value = "SELECT * FROM invoice_items_detail WHERE invoice_id=:keyword ORDER BY item_sequence",nativeQuery = true)
	    List<InvoiceItems> findItemsByInvoiceNo(Long keyword);

}
