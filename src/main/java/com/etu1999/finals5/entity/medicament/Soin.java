package com.etu1999.finals5.entity.medicament;

import com.etu1999.ambovombe.core.process.DAO;
import com.etu1999.ambovombe.mapping.annotation.data.Column;
import com.etu1999.ambovombe.mapping.annotation.data.ForeignKey;
import com.etu1999.ambovombe.mapping.annotation.data.Id;
import com.etu1999.ambovombe.mapping.annotation.data.UnitSource;
import com.etu1999.ambovombe.mapping.annotation.misc.PrimaryKey;
import com.etu1999.finals5.entity.symptome.Symptome;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@UnitSource("com/etu1999/finals5")
@Getter @Setter @NoArgsConstructor
public class Soin extends DAO {
    @Id @Column
    @PrimaryKey(
        prefix = "SOI",
        length = 7,
        sequence = "s_soin"
    )
    String id;

    //@ForeignKey(mappedBy = "id_medicament", initialize = false)
    @Column("id_medicament")
    String idMedicament;
    //Medicament medicament;

    @ForeignKey(mappedBy = "id_symptome", initialize = true)
    Symptome symptome;

    @Column("valeur")   // Valeur de soin prodigué
    double valeurSoin;

}
