package com.example.autenticatestbackend.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/device")
@Validated
public class DeviceController {

    private final DeviceService deviceService;
    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<Device> getDevices() { return deviceService.getDevices();}

    @GetMapping(path = "{deviceId}")
    public Device getSingleDevice(@PathVariable("deviceId") Long deviceId) { return deviceService.getSingleDevice(deviceId);}

    @PostMapping
    public Device addNewDevice( @RequestBody Device device){ return deviceService.addDevice(device);}

    @PutMapping(path = "{deviceId}")
    public Device editDevice(@PathVariable("deviceId") Long deviceId, @RequestBody Device device){
        return deviceService.updateDevice(deviceId, device);}

    @DeleteMapping(path = "{deviceId}")
    public Boolean deleteDevice(@PathVariable("deviceId") Long deviceId){ return deviceService.deleteDevice(deviceId);}
}
