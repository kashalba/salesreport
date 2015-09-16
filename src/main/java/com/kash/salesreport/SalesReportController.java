package com.kash.salesreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/report")
public class SalesReportController {

    @Autowired
    private SalesEntryRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<SalesEntry> getEntries() {
        return repository.findAll();
    }

}
