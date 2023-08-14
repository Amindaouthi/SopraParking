package com.example.parkingsopahr.services;

import com.example.parkingsopahr.repository.ParkingRepository;
import com.example.parkingsopahr.entities.parking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ParkingImp implements Iparking {

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    public ParkingImp(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public List<parking> getAllParkings() {
        return parkingRepository.findAll();
    }

    @Override
    public Optional<parking>getParkingById(Long idpa) {
        return parkingRepository.findById(idpa);
    }

    @Override
    public parking addParking(parking parking) {
        return parkingRepository.save(parking);
    }

    @Override
    public parking updateParking(Long idpa, parking p) {
        parking existinParking = parkingRepository.findById(idpa).orElseThrow(() ->new RuntimeException("Parking not found" + idpa));
        BeanUtils.copyProperties(p, existinParking , "idpa");
        return  parkingRepository.save(existinParking);
    }


    @Override
    public void deleteParking(Long idpa) {
        parkingRepository.deleteById(idpa);
    }
}
