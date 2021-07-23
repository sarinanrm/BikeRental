package com.example.bikerental.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookingDetailsSchema implements Serializable {

    @SerializedName("booking_id")
    @Expose
    private Integer bookingId;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("bd_id")
    @Expose
    private Integer bdId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("brand_id")
    @Expose
    private Integer brandId;
    @SerializedName("bd_catid")
    @Expose
    private Integer bdCatid;
    @SerializedName("cc")
    @Expose
    private Integer cc;
    @SerializedName("mileage")
    @Expose
    private Integer mileage;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("bd_image")
    @Expose
    private String bdImage;
    @SerializedName("bike_name")
    @Expose
    private String bikeName;
    @SerializedName("brand_name")
    @Expose
    private String brandName;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBdId() {
        return bdId;
    }

    public void setBdId(Integer bdId) {
        this.bdId = bdId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getBdCatid() {
        return bdCatid;
    }

    public void setBdCatid(Integer bdCatid) {
        this.bdCatid = bdCatid;
    }

    public Integer getCc() {
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBdImage() {
        return bdImage;
    }

    public void setBdImage(String bdImage) {
        this.bdImage = bdImage;
    }

    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

}