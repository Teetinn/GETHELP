package id.ac.umn.uas_profile.ui.history;

import java.io.Serializable;

public class HistoryModel implements Serializable {
    private String name, jobDesc;
    private int image;

    public HistoryModel(int image, String name, String jobDesc) {
        this.image = image;
        this.name = name;
        this.jobDesc = jobDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }
}
