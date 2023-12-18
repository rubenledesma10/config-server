package com.example.citiesservice.service;

import com.example.citiesservice.dto.CityDTO;
import com.example.citiesservice.model.City;
import com.example.citiesservice.repository.IHotelsAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService implements ICityService {

    @Autowired
    private IHotelsAPI hotelsAPI;

    //para guardar todas als ciudades que van a coincidir
    List<City> cities = new ArrayList<>();

    @Override
    //le pasamos dos parametros, el nombre del servicio donde puede ocurrir el cortocicuito, y el otro parametro es que en caso de que no funcione o que pase a alguna excepcion, error o que no me puedo comunicar con dicho servicio, necesito desviar ese flujo a otro lugar para evitar ese error en cuestion o que no se guardan datos que no se tienen que guardar
    //es decir, que fallbackmethod es poder tener un metodo en el cual redirigir en caso de errores
    @CircuitBreaker(name="hotels-service", fallbackMethod = "fallbackGetCitiesHotels")
    //esto significa que al tiempo de que se detecto el error va a volver a intentar comunicarse con el servicio para ver si esta funcionando correctamente y poder abrir el circuito
    @Retry(name="hotels-service")
    public CityDTO getCitiesHotels(String name, String country) {

        //creamos un metodo para buscar la ciudad original
        City city = this.findCity(name, country);

        //creamos ciudaddto + lista de hoteles
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCity_id(city.getCity_id());
        cityDTO.setName(city.getName());
        cityDTO.setCountry(city.getCountry());
        cityDTO.setContinent(city.getContinent());
        cityDTO.setState(city.getState());

        //buscamos la lista de hoteles en la api y la asignamos
        cityDTO.setHotelList(hotelsAPI.getHotelsByCityId(city.getCity_id()));

        //createException(); esta excepcion la creamos para comprobar el circuit breaker

        return cityDTO;

    }

    public CityDTO fallbackGetCitiesHotels(Throwable throwable){
        return new CityDTO(9999999999999L, "Fallido", "Fallido", "Fallido", "Fallido", null);
    }

    private void createException() {
        throw new IllegalArgumentException("Prueba resilience y circuit breaker");
    }

    private City findCity(String name, String country) {
        //lo primero que va a hacer este metodo es cargar las ciudades
        this.loadCities();
        //buscamos por cada ciudad
        for(City c:cities){
            //traemos el nombre, en caso de que el nombre sea igual al nombre que me pasaron por parametro
            if(c.getName().equals(name)){
                //y si el nombre del pais es el igual al nombre que me pasaron por el parametro
                if(c.getCountry().equals(country)){
                    //en caso de que sea correcto, que nos devuelva la ciudad
                    return c;
                }
            }
        }

        //en caso de que no encuentre nada
        return null;

    }

    private void loadCities() {

        //crea todas las ciudades
        cities.add(new City(1L, "Buenos Aires", "South America", "Argentina", "Buenos Aires"));
        cities.add(new City(2L, "Oberá", "South America", "Argentina", "Misiones"));
        cities.add(new City(3L, "Mexico City", "North America", "Mexico", "Mexico City"));
        cities.add(new City(4L, "Guadalajara", "North America", "Mexico", "Jalisco"));
        cities.add(new City(5L, "Bogota", "South America", "Colombia", "Cundinamarca"));
        cities.add(new City(6L, "Medellin", "South America", "Colombia", "Antioquia"));
        cities.add(new City(7L, "Santiago", "South America", "Chile", "Santiago Metropolitano"));
        cities.add(new City(8L, "Valparaiso", "South America", "Chile", "Valparaiso"));
        cities.add(new City(9L, "Asuncion", "South America", "Paraguay", "Asuncion"));
        cities.add(new City(10L, "Montevideo", "South America", "Uruguay", "Montevideo"));
        cities.add(new City(11L, "Madrid", "Europe", "Spain", "Community of Madrid"));
        cities.add(new City(12L, "Barcelona", "Europe", "Spain", "Catalonia"));
        cities.add(new City(13L, "Seville", "Europe", "Spain", "Andalucia"));
        cities.add(new City(14L, "Monterrey", "North America", "Mexico", "Nuevo Leon"));
        cities.add(new City(15L, "Valencia", "Europe", "Spain", "Valencian Community"));

    }
}
