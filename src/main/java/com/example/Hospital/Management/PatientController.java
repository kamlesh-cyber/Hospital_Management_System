package com.example.Hospital.Management;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class PatientController {

    HashMap<Integer, Patient> patientDB = new HashMap<>();

    @PostMapping("/addPatientViaParam")
    public String addPatient(@RequestParam ("patientID") Integer patientID, @RequestParam ("name") String name,
                             @RequestParam ("disease")String disease, @RequestParam("age") Integer age){

        Patient patient = new Patient(patientID, name, disease,age);
        patientDB.put(patientID, patient);

        return "Patient added via Request Parameter";
    }

    @PostMapping("/addPatientViaRequestBody")
    public String addPatient(@RequestBody Patient patient){

        int key = patient.getPatientID();
        patientDB.put(key, patient);

        return "Patient added via Request Body";
    }

    @GetMapping("/getPatientInfo")
    public Patient getPatient(@RequestParam ("patientID") Integer patientID){

        Patient patient = patientDB.get(patientID);
        return patient;

    }

    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients(){
        List<Patient> patients = new ArrayList<>();

        for(Patient p : patientDB.values()){
            patients.add(p);
        }
        return patients;
    }

    @GetMapping("/getPatientByName")
    public Patient getPatientByName(@RequestParam("name") String name){

        for(Patient p : patientDB.values()){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }


    @GetMapping("/getPatientsListGreaterByAge")
    public List<Patient> getPatientsListGreaterByAge(@RequestParam("age") Integer age){

        List<Patient> patients = new ArrayList<>();

        for(Patient p : patientDB.values()){
            if(p.getAge() > age){
                patients.add(p);
            }
        }
        return patients;
    }
}
