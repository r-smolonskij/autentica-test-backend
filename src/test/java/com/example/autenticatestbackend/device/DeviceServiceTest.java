package com.example.autenticatestbackend.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;
    private DeviceService underTest;

    @BeforeEach
    void setUp() {
        underTest = new DeviceService(deviceRepository);
    }


    @Test
    void addDevice_success() {
        //given
        Device device = new Device("Device 1", "Parameters 1");
        //when
        underTest.addDevice(device);
        //then
        ArgumentCaptor<Device> deviceArgumentCaptor = ArgumentCaptor.forClass(Device.class);
        verify(deviceRepository).save(deviceArgumentCaptor.capture());

        Device capturedDevice = deviceArgumentCaptor.getValue();

        assertThat(capturedDevice).isEqualTo(device);
    }

    @Test
    void addDevice_nullName() {
        // given
        Device deviceToAdd = new Device(null, "Device Parameters");
        // when
        Device addedDevice = underTest.addDevice(deviceToAdd);
        // then
        assertNull(addedDevice);
    }

    @Test
    void updateDevice_success() {
        //given
        long deviceId = 1;
        Device device = new Device(deviceId,"Device 1", "Parameters 1");

        given(deviceRepository.findById(deviceId))
                .willReturn(Optional.of(device));
        //when
        underTest.updateDevice(deviceId,device);
        //then
        ArgumentCaptor<Device> deviceArgumentCaptor = ArgumentCaptor.forClass(Device.class);
        verify(deviceRepository).save(deviceArgumentCaptor.capture());

        Device capturedDevice = deviceArgumentCaptor.getValue();

        assertThat(capturedDevice).isEqualTo(device);
    }

    @Test
    void updateDevice_wrongId() {
        //given
        long deviceId = 1;
        Device device = new Device(deviceId,"Device 1", "Parameters 1");

        given(deviceRepository.findById(deviceId))
                .willReturn(null);
        //when
        //then
        assertThatThrownBy(()->underTest.updateDevice(deviceId, device))
                .isInstanceOf(IllegalArgumentException.class);

        verify(deviceRepository, never()).save(any());
    }

    @Test
    void deleteDevice_success() {
        //given
        long deviceId = 1;
        given(deviceRepository.existsById(deviceId)).willReturn(true);
        //when
        underTest.deleteDevice(deviceId);
        //then
        verify(deviceRepository).deleteById(deviceId);
    }
    @Test
    void deleteDevice_wrongId() {
        //given
        long deviceId = 1;
        given(deviceRepository.existsById(deviceId)).willReturn(false);
        //when
        assertThatThrownBy(()->underTest.deleteDevice(deviceId))
                .isInstanceOf(IllegalArgumentException.class);
        verify(deviceRepository, never()).deleteById(any());
    }
}