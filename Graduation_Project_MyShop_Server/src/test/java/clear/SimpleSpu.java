package clear;

public class SimpleSpu {
    private Integer id;
    private Integer category3Id;

    private String spuName;

    @Override
    public String toString() {
        return "SimpleSpu{" +
                "id=" + id +
                ", category3Id=" + category3Id +
                ", spuName='" + spuName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(Integer category3Id) {
        this.category3Id = category3Id;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }
}
