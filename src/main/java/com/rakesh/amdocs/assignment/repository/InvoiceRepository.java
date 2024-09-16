package com.rakesh.amdocs.assignment.repository;

import com.rakesh.amdocs.assignment.dto.InvoiceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceDTO, Long> {

}