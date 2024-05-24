package model;

public class City {
    private int cityId;
    private String cityName;

    public City(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    //////////////////////////////////////////////////////////////////GETTERS
    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }
    //////////////////////////////////////////////////////////////////SETTERS
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return cityName;
    }


}
