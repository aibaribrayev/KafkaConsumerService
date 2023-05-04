package com.example.kafkaconsumerservice.model;

public class ParkingSensor {
    private String sensorId;
    private boolean isOccupied;
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }
    public String getSensorId(){
        return sensorId;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
    public boolean getIsOccupied(){
        return isOccupied;
    }

    public ParkingSensor(String sensorId, boolean isOccupied){
        this.sensorId = sensorId;
        this.isOccupied = isOccupied;
    }
    public ParkingSensor() {
        // Пустой конструктор
    }

    @Override
    public String toString() {
        return "Sensor id: "+sensorId+" Occupancy: "+ isOccupied;
    }
}
