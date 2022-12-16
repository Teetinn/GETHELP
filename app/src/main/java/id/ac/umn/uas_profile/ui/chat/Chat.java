package id.ac.umn.uas_profile.ui.chat;

import com.google.firebase.firestore.DocumentId;

import java.io.Serializable;

public class Chat implements Serializable {

    String docId, name, status ,image, jobDesc, phone;

    public Chat() { }

    public Chat(String docId, String name, String status, String image, String jobDesc, String phone) {
        this.docId = docId;
        this.name = name;
        this.image = image;
        this.status = status;
        this.jobDesc = jobDesc;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobDesc() { return jobDesc; }

    public void setJobDesc(String jobDesc) { this.jobDesc = jobDesc; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status = status; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @DocumentId
    public String getDocId() {
        return docId;
    }

    @DocumentId
    public void setDocId(String docId) {
        this.docId = docId;
    }


}
