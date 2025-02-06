package org.dit.wim.ase.footprint.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportResponse {
    private Integer t_id;
    private String transportname;
    private Integer fuel_factor;
    private Integer emission_factor;

}
