public class City {
    private String name;
    private String geographicArea;
    private String[] streets;

    public City(String name, String geographicArea, String[] streets){
        //סיבוכיות קבועה
        this.name = name;
        this.geographicArea = geographicArea;
        this.streets = streets;
    }

    public String getName() {
        //סיבוכיות קבועה
        return name;
    }
    public String[] getStreets() {
        //סיבוכיות קבועה
        return streets;
    }
}
