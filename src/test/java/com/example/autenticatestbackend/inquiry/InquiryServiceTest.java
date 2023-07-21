package com.example.autenticatestbackend.inquiry;

import com.example.autenticatestbackend.device.Device;
import com.example.autenticatestbackend.device.DeviceRepository;
import com.example.autenticatestbackend.device.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InquiryServiceTest {

    @Mock
    private InquiryRepository inquiryRepository;

    @Mock
    private DeviceRepository deviceRepository;

    private InquiryService underTest;

    @BeforeEach
    void setUp() {
        underTest = new InquiryService(inquiryRepository, deviceRepository);
    }

    @Test
    void addInquiry_success() {
        //given
        long deviceId = 1;
        given(deviceRepository.existsById(deviceId)).willReturn(true);
        Inquiry inquiry = new Inquiry(deviceId, "Justification 1");
        //when
        underTest.addInquiry(inquiry);
        //then
        ArgumentCaptor<Inquiry> inquiryArgumentCaptor = ArgumentCaptor.forClass(Inquiry.class);
        verify(inquiryRepository).save(inquiryArgumentCaptor.capture());

        Inquiry capturedInquiry = inquiryArgumentCaptor.getValue();

        assertThat(capturedInquiry).isEqualTo(inquiry);
    }

    @Test
    void addInquiry_invalidDeviceId() {
        //given
        long deviceId = 1;
        given(deviceRepository.existsById(deviceId)).willReturn(false);
        Inquiry inquiry = new Inquiry(deviceId, "Justification 1");
        //when
        //then
        assertThatThrownBy(()->underTest.addInquiry(inquiry))
                .isInstanceOf(IllegalArgumentException.class);

        verify(inquiryRepository, never()).save(any());
    }

    @Test
    void updateInquiry_success() {
        //given
        long inquiryId = 1;
        long deviceId = 1;
        Inquiry inquiry = new Inquiry(inquiryId, "Justification 1 updated");
        given(deviceRepository.existsById(deviceId)).willReturn(true);
        given(inquiryRepository.findById(inquiryId)).willReturn(Optional.of(inquiry));

        //when
        underTest.updateInquiry(inquiryId,inquiry);
        //then
        ArgumentCaptor<Inquiry> inquiryArgumentCaptor = ArgumentCaptor.forClass(Inquiry.class);
        verify(inquiryRepository).save(inquiryArgumentCaptor.capture());

        Inquiry capturedInquiry = inquiryArgumentCaptor.getValue();

        assertThat(capturedInquiry).isEqualTo(inquiry);
    }

    @Test
    void updateInquiry_invalidDeviceId() {
        //given
        long inquiryId = 1;
        long deviceId = 1;
        Inquiry inquiry = new Inquiry(inquiryId, "Justification 1 updated");
        given(deviceRepository.existsById(deviceId)).willReturn(false);
        given(inquiryRepository.findById(inquiryId)).willReturn(Optional.of(inquiry));
        //when
        //then
        assertThatThrownBy(()->underTest.updateInquiry(inquiryId,inquiry))
                .isInstanceOf(IllegalArgumentException.class);

        verify(inquiryRepository, never()).save(any());
    }

    @Test
    void updateInquiry_invalidInquiryId() {
        //given
        long inquiryId = 1;
        Inquiry inquiry = new Inquiry(inquiryId, "Justification 1 updated");
        given(inquiryRepository.findById(inquiryId)).willReturn(null);
        //when
        //then
        assertThatThrownBy(()->underTest.updateInquiry(inquiryId,inquiry))
                .isInstanceOf(NullPointerException.class);

        verify(inquiryRepository, never()).save(any());
    }

    @Test
    void deleteInquiry_success() {
        //given
        long inquiryId = 1;
        given(inquiryRepository.existsById(inquiryId)).willReturn(true);
        //when
        underTest.deleteInquiry(inquiryId);
        //then
        verify(inquiryRepository).deleteById(inquiryId);
    }
    @Test
    void deleteInquiry_wrongId() {
        //given
        long inquiryId = 1;
        given(inquiryRepository.existsById(inquiryId)).willReturn(false);
        //when
        assertThatThrownBy(()->underTest.deleteInquiry(inquiryId))
                .isInstanceOf(IllegalArgumentException.class);
        verify(inquiryRepository, never()).deleteById(any());
    }


    @Test
    void updateInquiry() {
    }

    @Test
    void deleteDevice() {
    }
}