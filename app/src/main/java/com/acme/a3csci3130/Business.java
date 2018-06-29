package com.acme.a3csci3130;

import java.io.Serializable;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 * @author Matthew MacMullni
 *
 */

public class Business implements Serializable {

    private  String businessNumber;
    private  String name;
    private  String primaryBusiness;
    private  String address;
    private  String province;

    /** Default constructor required for calls to DataSnapshot.getValue*/
    public Business() {}

    /**
     * Constructor that populates all the class fields
     * @param businessNumber - The Business Number
     * @param name - The business name
     * @param primaryBusiness - Primary business, can be Fisher, fish Monger, Processor,
     *                        or Distributor
     * @param address - Business Address
     * @param province - Province or territory the business is located, uses two letter (upper case)
     *                 abbreviation.
     */
    public Business(String businessNumber, String name, String primaryBusiness, String address,
                    String province) {
        this.businessNumber = businessNumber;
        this.name = name;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;
    }

    /**Getter for BusinessNumber field
     *
     * @return - businessNumber - Business Number*/
    public String getBusinessNumber() {
        return businessNumber;
    }

    /**Setter for businessNumber
     *
     * @param businessNumber - Business Number
     */
    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    /**
     * Getter for name field
     *
     * @return name - Business Name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name field
     *
     * @param name - Business name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * primaryBusiness getter
     *
     * @return - Field for Primary Business field
     */
    public String getPrimaryBusiness() {
        return primaryBusiness;
    }

    /**
     * Setter for primaryBusiness
     *
     * @param primaryBusiness - Primary Business
     */
    public void setPrimaryBusiness(String primaryBusiness) {
        this.primaryBusiness = primaryBusiness;
    }

    /**
     * getter for address field
     * @return - Business's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for address field
     *
     * @param address - Business's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getter for province field
     *
     * @return - Province the business is located
     */
    public String getProvince() {
        return province;
    }

    /**
     * Setter province field
     * @param province - Business's province
     */
    public void setProvince(String province) {
        this.province = province;
    }
}
