package com.example.autenticatestbackend.inquiry;

import com.example.autenticatestbackend.device.Device;
import com.example.autenticatestbackend.device.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public InquiryService(InquiryRepository inquiryRepository, DeviceRepository deviceRepository){this.inquiryRepository = inquiryRepository;
        this.deviceRepository = deviceRepository;
    }

    public List<Inquiry> getInquiries(){ return inquiryRepository.findAll();}

    public Inquiry getSingleInquiry(Long inquiryId){
        return inquiryRepository.findById(inquiryId).orElseThrow(() -> new IllegalArgumentException("Inquiry not found with ID: " + inquiryId));
    }

    public Inquiry addInquiry(Inquiry inquiry){
        boolean deviceExists = deviceRepository.existsById(inquiry.getDeviceId());
        if(!deviceExists){
           throw new IllegalArgumentException("Device not found with ID: " + inquiry.getDeviceId());
        }
        inquiry.setDate(LocalDateTime.now());
        inquiry.setStatus("requested");
        return inquiryRepository.save(inquiry);
    }

    public Inquiry updateInquiry(Long inquiryId, Inquiry inquiry){
        Optional<Inquiry> optionalInquiry = inquiryRepository.findById(inquiryId);
        if(!optionalInquiry.isPresent()){
            throw new IllegalArgumentException("Inquiry not found with ID: " + inquiryId);
        }
        Inquiry foundInquiry = optionalInquiry.get();
        if( inquiry.getDeviceId() != null){
            boolean deviceExists = deviceRepository.existsById(inquiry.getDeviceId());
            if(!deviceExists){
                throw new IllegalArgumentException("Device not found with ID: " + inquiry.getDeviceId());
            }
            foundInquiry.setDeviceId(inquiry.getDeviceId());
        }
        if(inquiry.getJustification()!=null){
            foundInquiry.setJustification(inquiry.getJustification());
        }
        if(inquiry.getStatus()!=null){
            foundInquiry.setStatus(inquiry.getStatus());
        }
        return inquiryRepository.save(foundInquiry);
    }

    public boolean deleteInquiry(Long inquiryId){
        boolean inquiryExists = inquiryRepository.existsById(inquiryId);
        if(!inquiryExists){
            throw new IllegalArgumentException("Inquiry not found with ID: " + inquiryId);
        }
        inquiryRepository.deleteById(inquiryId);
        return true;
    }


}
