package com.etu1999.finals5.entity.patient;

import com.etu1999.finals5.Dokotera;
import com.etu1999.finals5.entity.maladie.Maladie;
import com.etu1999.finals5.entity.symptome.Symptome;
import com.etu1999.finals5.utils.Misc;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Patient {
    String nom;
    String prenom;
    Date dateDeNaissance;
    List<SymptomePatient> symptomePatients;

    public int getAge(){
        return Misc.getAge(getDateDeNaissance());
    }

    public List<Maladie> getMaladies(List<Maladie> maladies){
        return Dokotera.getMaladies(maladies, this);
    }

    public double getSymptomeValeur(Symptome symptome){
        return getSymptomeValeur(symptome.getId());
    }

    public double getSymptomeValeur(String symptome_id){
        for(SymptomePatient symptomePatient : symptomePatients)
            if(symptomePatient.getSymptome().getId().equals(symptome_id))
                return symptomePatient.getValeur();
        return 0;
    }
}
