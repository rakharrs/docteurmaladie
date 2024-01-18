package com.etu1999.finals5.entity.maladie;

import com.etu1999.ambovombe.core.process.DAO;
import com.etu1999.ambovombe.mapping.annotation.data.Column;
import com.etu1999.ambovombe.mapping.annotation.data.ForeignKey;
import com.etu1999.ambovombe.mapping.annotation.data.Id;
import com.etu1999.ambovombe.mapping.annotation.data.UnitSource;
import com.etu1999.ambovombe.mapping.annotation.misc.PrimaryKey;
import com.etu1999.ambovombe.mapping.fk.ForeignType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@UnitSource("com/etu1999/finals5")
@Getter
@Setter
@NoArgsConstructor
public class Maladie extends DAO {
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

    @ForeignKey(mappedBy = "id_maladie", initialize = true, foreignType = ForeignType.OneToMany)
    List<MaladieSymptome> maladieSymptomes;
}
