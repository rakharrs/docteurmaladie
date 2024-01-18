package com.etu1999.finals5.entity.maladie;

import com.etu1999.ambovombe.core.process.DAO;
import com.etu1999.ambovombe.mapping.annotation.data.*;
import com.etu1999.ambovombe.mapping.annotation.misc.PrimaryKey;
import com.etu1999.finals5.entity.symptome.Symptome;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@UnitSource("com/etu1999/finals5")
@Getter
@Setter
@NoArgsConstructor
@Table("maladie_symptome")
public class MaladieSymptome extends DAO {
    @Id
    @Column
    @PrimaryKey(
            prefix = "MED",
            length = 7,
            sequence = "s_medicament"
    )
    String id;
    @ForeignKey(mappedBy = "id_symptome", initialize = true)
    Symptome symptome;
    @Column
    String id_maladie;
    @Column
    double min;
    @Column
    double max;
}
