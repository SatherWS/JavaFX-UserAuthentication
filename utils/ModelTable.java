package utils;

public class ModelTable {
    private String id;
    private String lname;
    private String fname;
    private String address;
    private String email;
    private String phone;
    private String dob;
    private String ssn;
    private String pin;

    public ModelTable(String id, String lname, String fname, String address, String email, String phone, String dob, String pin){
        this.id = id;
        this.lname = lname;
        this.fname = fname;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.pin = pin;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
