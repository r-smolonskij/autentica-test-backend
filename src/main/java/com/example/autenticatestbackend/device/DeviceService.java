package com.example.autenticatestbackend.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getDevices(){
        return deviceRepository.findAll();
    }

    public Device getSingleDevice(Long deviceId){
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        if(!optionalDevice.isPresent()){
            throw new IllegalArgumentException("Device not found with ID: " + deviceId);
        }
        return optionalDevice.get();
    }

    public Device addDevice(Device device){
        return deviceRepository.save(device);
    }

    public Device updateDevice(Long deviceId,Device device){
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        if(!optionalDevice.isPresent()){
            throw new IllegalArgumentException("Device not found with ID: " + deviceId);
        }
        Device foundDevice = optionalDevice.get();
        if(device.getName() !=null){
            foundDevice.setName(device.getName());
        }
        if(device.getParameters()!=null){
            foundDevice.setParameters(device.getParameters());
        }
        deviceRepository.save(foundDevice);
        return foundDevice;
    }

    public boolean deleteDevice(Long deviceId){
        boolean deviceExists = deviceRepository.existsById(deviceId);
        if(!deviceExists){
            throw new IllegalArgumentException("Device not found with ID: " + deviceId);
        }
        deviceRepository.deleteById(deviceId);
        return true;
    }

}
