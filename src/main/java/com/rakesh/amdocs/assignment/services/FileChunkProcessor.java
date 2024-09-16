package com.rakesh.amdocs.assignment.services;

import com.rakesh.amdocs.assignment.dto.InvoiceDTO;
import com.rakesh.amdocs.assignment.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

// Class to process a chunk of the file
class FileChunkProcessor implements Callable<Void> {

    private final FileChannel fileChannel;
    private final long position;
    private final long size;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public FileChunkProcessor(FileChannel fileChannel, long position, long size) {
        this.fileChannel = fileChannel;
        this.position = position;
        this.size = size;
    }

    @Override
    public Void call() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocateDirect((int) size); // Memory-efficient allocation
        fileChannel.read(buffer, position);
        buffer.flip();

        processBuffer(buffer);

        return null;
    }

    // Process the buffer to extract and store invoices
    private void processBuffer(ByteBuffer buffer) {
        List<InvoiceDTO> invoicesList = new ArrayList<>();

        // Dummy parsing logic (adjust based on the actual format of your invoices)
        StringBuilder sb = new StringBuilder();
        while (buffer.hasRemaining()) {
            char c = (char) buffer.get(); // Read a character
            if (c == '\n') {
                invoicesList.add(parseInvoice(sb.toString())); // Parse and add to list
                sb.setLength(0); // Reset the StringBuilder
            } else {
                sb.append(c);
            }
        }

        // Store invoices in the database in batches
        invoiceRepository.saveAll(invoicesList);
    }

    // Dummy method to parse an invoice (adjust based on your file format)
    private InvoiceDTO parseInvoice(String invoiceLine) {
        String[] parts = invoiceLine.split(",");
        return new InvoiceDTO(parts[0], parts[1], new BigDecimal(parts[2]));
    }

}

