package entity;

public class TJobHistory {
    private Integer id;

    private Integer userId;

    private String compName;

    private Integer years;

    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "TJobHistory{" +
                "id=" + id +
                ", userId=" + userId +
                ", compName='" + compName + '\'' +
                ", years=" + years +
                ", title='" + title + '\'' +
                '}';
    }
}