package com.kash.salesreport.test;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.StreamSupport;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kash.salesreport.App;
import com.kash.salesreport.Product;
import com.kash.salesreport.SalesEntry;
import com.kash.salesreport.SalesEntryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class SalesReportTest {

    @Autowired
    private SalesEntryRepository repository;

    private static final Logger LOGGER = Logger.getLogger(SalesReportTest.class
            .getName());
    
    @Test
    public void shouldGenerateReport() {
        generate();
    }

    private void generate() {

        Iterable<SalesEntry> entries = repository.findAll();
        Assert.assertNotNull(entries);
        StringBuilder report = new StringBuilder("");
        Map<Product, List<BigDecimal>> sales = StreamSupport.stream(entries.spliterator(), false).collect(
                    groupingBy(SalesEntry::getProduct, TreeMap::new,
                            mapping(SalesEntry::getAmount, toList())));
        
        Assert.assertNotNull(sales);

        report.append("\n***********************************************\n");
        report.append("Product Name   Total # sold   Total amount");
        sales.entrySet().forEach(m -> appendEntries(m, report));
        report.append("\n***********************************************\n");
        LOGGER.info(report.toString());
        
        try {
            Files.write(Paths.get("./target/report.txt"), report.toString().getBytes());
        } catch (IOException e) {
            LOGGER.error("Couldn't create a report file", e);
        }

    }

    private void appendEntries(Entry<Product, List<BigDecimal>> entry,
            StringBuilder report) {
        report.append("\n")
                .append(entry.getKey().getName())
                .append("             ")
                .append(entry.getValue().size())
                .append("             ")
                .append(entry.getValue().stream()
                        .mapToDouble(e -> e.doubleValue()).sum());
    }

}