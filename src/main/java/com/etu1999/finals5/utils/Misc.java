package com.etu1999.finals5.utils;

import com.etu1999.finals5.entity.patient.SymptomePatient;
import com.etu1999.finals5.entity.symptome.Symptome;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Misc {

    public static List<Symptome> getSymptomeByString(String[] symptomesString, List<Symptome> symptomes){
        List<Symptome> val = new ArrayList<>();
        for( Symptome symptome : symptomes)
            for(String sympt : symptomesString)
                if(sympt.equals(symptome.getId()))
                    val.add(symptome);
        return val;
    }

    public static List<SymptomePatient> getSymptomePatient(String symptomeString, String valeur, List<Symptome> symptomes) throws Exception{
        String[] symptomesString = symptomeString.trim().split(";");
        String[] valeurs = valeur.trim().split(";");
        if(valeurs.length != symptomesString.length)
            throw new Exception("symptomes et valeurs doivent avoir la même taille");
        List<Symptome> symptomesP = getSymptomeByString(symptomesString, symptomes);
        List<SymptomePatient> symptomePatients = new ArrayList<>();
        for (int i = 0; i < symptomesP.size(); i++) {
            Symptome symptome = symptomesP.get(i);
            SymptomePatient symptomePatient = new SymptomePatient();
            symptomePatient.setSymptome(symptome);
            symptomePatient.setValeur(Double.parseDouble(valeurs[i]));
            symptomePatients.add(symptomePatient);
        }
        return symptomePatients;
    }

    public static int getAge(Date dateDeNaissance){
        return 0;
    }
}
