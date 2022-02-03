package model;

public class Customer {
    private String Customer_Id;
    private String Customer_Name;
    private String Customer_Address;
    private String Customer_Email;
    private String Customer_Telephone;

    public Customer() {
    }

    public Customer(String customer_Id, String customer_Name, String customer_Address, String customer_Email, String customer_Telephone) {
        Customer_Id = customer_Id;
        Customer_Name = customer_Name;
        Customer_Address = customer_Address;
        Customer_Email = customer_Email;
        Customer_Telephone = customer_Telephone;
    }

    public String getCustomer_Id() {
        return Customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        Customer_Id = customer_Id;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getCustomer_Address() {
        return Customer_Address;
    }

    public void setCustomer_Address(String customer_Address) {
        Customer_Address = customer_Address;
    }

    public String getCustomer_Email() {
        return Customer_Email;
    }

    public void setCustomer_Email(String customer_Email) {
        Customer_Email = customer_Email;
    }

    public String getCustomer_Telephone() {
        return Customer_Telephone;
    }

    public void setCustomer_Telephone(String customer_Telephone) {
        Customer_Telephone = customer_Telephone;
    }
}
