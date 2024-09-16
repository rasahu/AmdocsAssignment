package com.rakesh.amdocs.assignment.controller;

import com.rakesh.amdocs.assignment.services.InvoiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceServices invoiceServices;

    @PutMapping("/processFile")
    public ResponseEntity<String> processLargeFile(@RequestParam("file") MultipartFile file) {
        try {
            invoiceServices.processLargeFileWithFuture(file.getName());
            return ResponseEntity.ok("File has been processed successfully.");
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
        }
    }
}
