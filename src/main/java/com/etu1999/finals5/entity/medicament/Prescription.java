package com.etu1999.finals5.entity.medicament;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Prescription {
    List<Traitement> traitements;
}
