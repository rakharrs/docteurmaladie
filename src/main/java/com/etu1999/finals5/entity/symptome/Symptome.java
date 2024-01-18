package com.etu1999.finals5.entity.symptome;

import com.etu1999.ambovombe.core.process.DAO;
import com.etu1999.ambovombe.mapping.annotation.data.Column;
import com.etu1999.ambovombe.mapping.annotation.data.Id;
import com.etu1999.ambovombe.mapping.annotation.data.UnitSource;
import com.etu1999.ambovombe.mapping.annotation.misc.PrimaryKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@UnitSource("com/etu1999/finals5")
@Getter
@Setter
@NoArgsConstructor
public class Symptome extends DAO {
    @Id
    @Column
    @PrimaryKey(
            prefix = "SYM",
            length = 7,
            sequence = "s_symptome"
    )
    String id;

    @Column
    String designation;
}
