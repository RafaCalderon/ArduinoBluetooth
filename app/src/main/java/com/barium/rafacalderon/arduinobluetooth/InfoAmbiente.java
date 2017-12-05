package com.barium.rafacalderon.arduinobluetooth;

public class InfoAmbiente {
    private int id;
    private String temperatura, humedad, fecha_hora;

    public InfoAmbiente() {
    }

    public InfoAmbiente(int id, String temperatura, String humedad, String fecha_hora) {
        this.id = id;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.fecha_hora = fecha_hora;
    }

    public InfoAmbiente(String temperatura, String humedad, String fecha_hora) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.fecha_hora = fecha_hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getHumedad() {
        return humedad;
    }

    public void setHumedad(String humedad) {
        this.humedad = humedad;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    @Override
    public String toString() {
        String[] fecha_hora_a = fecha_hora.split(" ");
        String r = "Temperatura: "+temperatura+"°C";
        r += "\nHumedad: "+ humedad+"%";
        if(fecha_hora_a.length > 1){
            r += "\nFecha: "+ fecha_hora_a[0];
            r += "\nHora: "+fecha_hora_a[1];
        }
        return r;
    }
}
