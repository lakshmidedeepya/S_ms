package com.example.service;

import java.util.List;

import com.example.entity.Medicine;
import com.example.exception.MedicineNotFoundException;
import com.example.repository.MedicineRepositoryStub;

public class MedicineServiceImpl implements MedicineService{

	MedicineRepositoryStub medicineRepository=new MedicineRepositoryStub();
    
    @Override
    public Medicine getMedicine(int id) throws MedicineNotFoundException {
    	// TODO Auto-generated method stub
		return medicineRepository.getMedicine(id)

    }

    @Override
	public Boolean isAvailable(int id) throws MedicineNotFoundException {
		// TODO Auto-generated method stub
		return medicineRepository.isAvailable(id);
	}

	@Override
	public List<String> manufacturers(String name) throws MedicineNotFoundException {
		// TODO Auto-generated method stub
		return medicineRepository.manufacturers(name);
	}

	@Override
	public String getLowestPriceByManufacturer(String medicineName) throws MedicineNotFoundException {
		// TODO Auto-generated method stub
		List<String> manufacturers=medicineRepository.manufacturers(name);
		Optional<String> name=manufacturers.stream().sorted(Comparator.comparing(Medicine::price)).map(Medicine::name).findFirst();
		name.ifPresent()?return name:return "No manufacturers found";
	}

}
