package com.etu1999.finals5.entity.patient;

import com.etu1999.finals5.entity.symptome.Symptome;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SymptomePatient {
    Symptome symptome;
    double valeur;
}