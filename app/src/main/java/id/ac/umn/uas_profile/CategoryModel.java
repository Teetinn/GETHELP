package id.ac.umn.uas_profile;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    String name, jobDesc, location, image;
    double rating;

    public CategoryModel() {
    }

    public CategoryModel(String image, String name, String jobDesc, String location, double rating) {
        this.name = name;
        this.jobDesc = jobDesc;
        this.location = location;
        this.image = image;
        this.rating = rating;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
