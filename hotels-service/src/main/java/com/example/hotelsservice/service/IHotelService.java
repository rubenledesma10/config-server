package com.example.hotelsservice.service;

import com.example.hotelsservice.model.Hotel;

import java.util.List;

public interface IHotelService {

    public List<Hotel> getHotelsByCityId(Long city_id);

}
