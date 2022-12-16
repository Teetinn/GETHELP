package id.ac.umn.uas_profile;

import com.google.firebase.firestore.DocumentId;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    String docId, name, age, jobDesc, location, image, exp1, exp2, exp3,
            exptime1, exptime2, exptime3, fee, phone, rating;

    public CategoryModel() {
    }

    public CategoryModel(String docId, String image, String name, String age, String jobDesc, String location,
                         String exp1, String exp2, String exp3, String exptime1, String exptime2,
                         String exptime3, String fee, String phone, String rating) {
        this.docId = docId;
        this.name = name;
        this.jobDesc = jobDesc;
        this.age = age;
        this.location = location;
        this.image = image;
        this.rating = rating;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.exptime1 = exptime1;
        this.exptime2 = exptime2;
        this.exptime3 = exptime3;
        this.fee = fee;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobDesc() { return jobDesc; }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getAge() { return age; }

    public void setAge(String age) { this.age = age; }

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

    public String getRating() { return rating; }

    public void setRating(String rating) { this.rating = rating; }

    public String getExp1() { return exp1; }

    public void setExp1(String exp1) { this.exp1 = exp1; }

    public String getExp2() { return exp2; }

    public void setExp2(String exp2) { this.exp2 = exp2; }

    public String getExp3() { return exp3; }

    public void setExp3(String exp3) { this.exp3 = exp3; }

    public String getExptime1() { return exptime1; }

    public void setExptime1(String exptime1) { this.exptime1 = exptime1; }

    public String getExptime2() { return exptime2; }

    public void setExptime2(String exptime2) { this.exptime2 = exptime2; }

    public String getExptime3() { return exptime3; }

    public void setExptime3(String exptime3) { this.exptime3 = exptime3; }

    public String getFee() { return fee; }

    public void setFee(String fee) { this.fee = fee; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    @DocumentId
    public String getDocId() {
        return docId;
    }

    @DocumentId
    public void setDocId(String docId) {
        this.docId = docId;
    }


}
