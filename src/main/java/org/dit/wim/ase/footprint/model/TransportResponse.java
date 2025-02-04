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
    private Integer T_id;
    private String Transportname;
    private Integer Fuel_factor;
    private Integer Emission_factor;

}
