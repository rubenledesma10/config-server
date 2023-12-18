package com.example.citiesservice.service;

import com.example.citiesservice.dto.CityDTO;

public interface ICityService {

    //le pasamos el nombre y el pais de una ciudad, y en base a esto nos va a devolver los hoteles asociados a ella
    //los hoteles espera el id de hotel, y aca nostoros estamos recibiendo el nombre y el pais
    //por lo cual vamos a armar la logica para que busquemos la ciudad por nombre y pais y a partir de eso obtener el id
    public CityDTO getCitiesHotels(String name, String country);

}
