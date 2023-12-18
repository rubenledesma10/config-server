package com.example.hotelsservice.service;

import com.example.hotelsservice.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService implements IHotelService {

    List<Hotel> hotelsList = new ArrayList<Hotel>();

    @Override
    public List<Hotel> getHotelsByCityId(Long city_id) {
        List<Hotel> hotelCityList = new ArrayList();

        //carga la bd logica
        this.loadHotels();

        for(Hotel h:hotelsList){
            //si el id del hotel es = al id de ciudad que yo traigo desde mi consulta
            //que lo agregue a hotelCityList
            if(h.getCity_id()==city_id){
                hotelCityList.add(h);
            }
        }

        //devolvemos la lista
        return  hotelCityList;

    }

    private void loadHotels() {
        hotelsList.add(new Hotel(1L, "Paradise", 5, 1L));
        hotelsList.add(new Hotel(2L, "Sunset View", 4, 2L));
        hotelsList.add(new Hotel(3L, "Ocean Breeze", 3, 3L));
        hotelsList.add(new Hotel(4L, "Mountain Retreat", 4, 1L));
        hotelsList.add(new Hotel(5L, "City Lights Inn", 3, 2L));
        hotelsList.add(new Hotel(6L, "Riverside Lodge", 4, 3L));
        hotelsList.add(new Hotel(7L, "Cozy Cabin Resort", 2, 1L));
        hotelsList.add(new Hotel(8L, "Luxury Haven", 5, 2L));
        hotelsList.add(new Hotel(9L, "Historic Gran Hotel", 4, 3L));
        hotelsList.add(new Hotel(10L, "Tranquil Gardens", 3, 1L));

    }
}
