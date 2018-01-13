package superhumancrew.com.saveus;

/**
 * Created by dedie on 1/10/18.
 */

public class SaveUs {

    private String Nama;
    private String Waktu;
    private String Kejadian;
    private String lat;
    private String lng;

    public SaveUs(String nama, String waktu, String kejadian, String lat, String lng) {
        Nama = nama;
        Waktu = waktu;
        Kejadian = kejadian;
        this.lat = lat;
        this.lng = lng;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getWaktu() {
        return Waktu;
    }

    public void setWaktu(String waktu) {
        Waktu = waktu;
    }

    public String getKejadian() {
        return Kejadian;
    }

    public void setKejadian(String kejadian) {
        Kejadian = kejadian;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "SaveUs{" +
                "Nama='" + Nama + '\'' +
                ", Waktu='" + Waktu + '\'' +
                ", Kejadian='" + Kejadian + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }

    public SaveUs(){

    }
}
