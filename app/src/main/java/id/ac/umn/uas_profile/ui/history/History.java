package id.ac.umn.uas_profile.ui.history;

import com.google.firebase.firestore.DocumentId;

import java.io.Serializable;

public class History implements Serializable {

        String docId, name, status ,image, date, fee, jobDesc, phone, Hfee, userAddress;

        public History() { }

    public History(String docId, String name, String status, String image, String date, String fee, String jobDesc, String phone, String Hfee, String userAddress) {
            this.docId = docId;
            this.name = name;
            this.image = image;
            this.status = status;
            this.date = date;
            this.fee = fee;
            this.jobDesc = jobDesc;
            this.phone = phone;
            this.Hfee = Hfee;
            this.userAddress = userAddress;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDate() { return date; }

        public void setDate(String date) { this.date = date; }

        public String getFee() { return fee; }

        public void setFee(String fee) { this.fee = fee; }

        public String getJobDesc() { return jobDesc; }

        public void setJobDesc(String jobDesc) { this.jobDesc = jobDesc; }

        public String getPhone() { return phone; }

        public void setPhone(String phone) { this.phone = phone; }

        public String getHFee() { return Hfee; }

        public void setHFee(String helperfee) { Hfee = helperfee; }

        public String getUserAddress() { return userAddress; }

        public void setUserAddress(String userAddress) { this.userAddress = userAddress;}

        @DocumentId
        public String getDocId() {
            return docId;
        }

        @DocumentId
        public void setDocId(String docId) {
            this.docId = docId;
        }


    }
