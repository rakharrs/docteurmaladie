package com.etu1999.finals5.entity.medicament;

import com.etu1999.ambovombe.core.process.DAO;
import com.etu1999.ambovombe.mapping.annotation.data.Column;
import com.etu1999.ambovombe.mapping.annotation.data.ForeignKey;
import com.etu1999.ambovombe.mapping.annotation.data.Id;
import com.etu1999.ambovombe.mapping.annotation.data.UnitSource;
import com.etu1999.ambovombe.mapping.annotation.misc.PrimaryKey;
import com.etu1999.ambovombe.mapping.fk.ForeignType;
import com.etu1999.finals5.entity.symptome.Symptome;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@UnitSource("com/etu1999/finals5")
@Getter
@Setter
@NoArgsConstructor
public class Medicament extends DAO {
    @Id
    @Column
    @PrimaryKey(
            prefix = "MED",
            length = 7,
            sequence = "s_medicament"
    )
    String id;

    @Column
    String designation;

    @ForeignKey(
            mappedBy = "id_medicament",
            foreignType = ForeignType.OneToMany,
            initialize = true
    )
    List<Soin> soins;

    @Column
    double prix;

    public double getSoinValeur(String id_symptome){
        for (Soin soin : soins)
            if(soin.getSymptome().getId().equals(id_symptome))
                return soin.getValeurSoin();
        return 0;
    }

    public double getSoinValeur(Symptome symptome){
        return getSoinValeur(symptome.getId());
    }
}
