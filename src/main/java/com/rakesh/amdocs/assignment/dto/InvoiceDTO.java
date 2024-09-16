package com.rakesh.amdocs.assignment.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "InvoiceDTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class InvoiceDTO {
    @Id
    private String id;
    private String customer;
    private BigDecimal amount;
}
