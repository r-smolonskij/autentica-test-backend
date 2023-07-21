package com.example.autenticatestbackend.inquiry;

import com.example.autenticatestbackend.device.Device;
import com.example.autenticatestbackend.device.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/inquiry")
@Validated
public class InquiryController {
    private final InquiryService inquiryService;
    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping
    public List<Inquiry> getInquiries() { return inquiryService.getInquiries();}

    @GetMapping(path = "{inquiryId}")
    public Inquiry getSingleInquiry(@PathVariable("inquiryId") Long inquiryId) { return inquiryService.getSingleInquiry(inquiryId);}

    @PostMapping
    public Inquiry addNewInquiry( @RequestBody Inquiry inquiry){ return inquiryService.addInquiry(inquiry);}

    @PutMapping(path = "{inquiryId}")
    public Inquiry editInquiry(@PathVariable("inquiryId") Long inquiryId, @RequestBody  Inquiry inquiry){ return inquiryService.updateInquiry(inquiryId, inquiry);}

    @DeleteMapping(path = "{inquiryId}")
    public boolean deleteInquiry(@PathVariable("inquiryId") Long inquiryId){ return inquiryService.deleteInquiry(inquiryId);}
}
