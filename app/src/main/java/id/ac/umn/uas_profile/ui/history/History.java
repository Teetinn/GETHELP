package id.ac.umn.uas_profile.ui.history;

import com.google.firebase.firestore.DocumentId;

import java.io.Serializable;

public class History implements Serializable {

        String docId, name, status ,image;

        public History() { }

        public History(String docId, String name, String status, String image) {
            this.docId = docId;
            this.name = name;
            this.image = image;
            this.status = status;
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

        @DocumentId
        public String getDocId() {
            return docId;
        }

        @DocumentId
        public void setDocId(String docId) {
            this.docId = docId;
        }


    }
