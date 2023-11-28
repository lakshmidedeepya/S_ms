package com.example.repository;



/*
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 */

import java.util.HashMap;

import com.example.entity.Medicine;

@Repository
public class MedicineRepositoryStub implements MedicineRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    

    static  HashMap<Integer,Medicine> medicines;

    static {
        medicines = new HashMap<>();
        medicines.put(1,new Medicine(1, "Dolo 650", 10.25,"Apollo Pharmacy",true));
        medicines.put(2,new Medicine(2, "Aceclofenac", 92.41,"Dr Reddy",true));
        medicines.put(3,new Medicine(3, "Diclofenac", 32.34,"Cipla",false));
        medicines.put(4,new Medicine(4, "Cetrizine", 43.21,"Pfizer",true));
        medicines.put(5,new Medicine(5, "pantop", 61.93,"Novartis",false));
        medicines.put(6,new Medicine(6, "Ascoril D", 155.55,"Sanofi Pharmacy",true));
        medicines.put(7,new Medicine(7, "Aceclofenac", 88.41,"Cipla",true));
        System.out.println(medicines);
    }
    
    public HashMap<Integer, Medicine> getMedicines() {
		return medicines;
	}

    public Medicine getMedicine(int id){


        return mongoTemplate.findById(id, Medicine.class);
    }

    public Boolean isAvailable(int id){
        return mongoTemplate.findOne(id, Medicine.class)!=null?true:false;
    }

    public List<String> manufacturers(String name) throws MedicineNotFoundException {
        // TODO Auto-generated method stub

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        query.fields().include("manufacturer");

        return new ArrayList<>(mongoTemplate.find(query, String.class));
    }


   

}
