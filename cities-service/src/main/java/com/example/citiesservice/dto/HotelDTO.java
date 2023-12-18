package com.example.citiesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {

    //esta clase la creamos porque vamos a recibir una lista de hoteles
    private Long id;
    private String name;
    private int stars;
    private Long city_id;

}
