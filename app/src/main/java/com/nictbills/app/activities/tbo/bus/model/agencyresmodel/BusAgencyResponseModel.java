
package com.nictbills.app.activities.tbo.bus.model.agencyresmodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusAgencyResponseModel implements Serializable
{

    @SerializedName("AgencyType")
    @Expose
    private int agencyType;
    @SerializedName("CashBalance")
    @Expose
    private double cashBalance;
    @SerializedName("CashBalanceInPrefCurrency")
    @Expose
    private Object cashBalanceInPrefCurrency;
    @SerializedName("CreditBalance")
    @Expose
    private double creditBalance;
    @SerializedName("CreditBalanceInPrefCurrency")
    @Expose
    private Object creditBalanceInPrefCurrency;
    @SerializedName("DomHotelConfirmBookingLimitRequired")
    @Expose
    private boolean domHotelConfirmBookingLimitRequired;
    @SerializedName("DomHotelHoldBalanceLeft")
    @Expose
    private int domHotelHoldBalanceLeft;
    @SerializedName("DomHotelHoldBalanceLeftInPrefCurrency")
    @Expose
    private Object domHotelHoldBalanceLeftInPrefCurrency;
    @SerializedName("Error")
    @Expose
    private Error error;
    @SerializedName("IntlHotelConfirmBookingLimitRequired")
    @Expose
    private boolean intlHotelConfirmBookingLimitRequired;
    @SerializedName("IntlHotelHoldBalanceLeft")
    @Expose
    private int intlHotelHoldBalanceLeft;
    @SerializedName("IntlHotelHoldBalanceLeftInPrefCurrency")
    @Expose
    private Object intlHotelHoldBalanceLeftInPrefCurrency;
    @SerializedName("IsNonAirOverrideCreditLimit")
    @Expose
    private boolean isNonAirOverrideCreditLimit;
    @SerializedName("IsOverrideCreditLimit")
    @Expose
    private boolean isOverrideCreditLimit;
    @SerializedName("PreferredCurrency")
    @Expose
    private Object preferredCurrency;
    @SerializedName("Status")
    @Expose
    private int status;
    private final static long serialVersionUID = -6943186645993064095L;

    public int getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(int agencyType) {
        this.agencyType = agencyType;
    }

    public BusAgencyResponseModel withAgencyType(int agencyType) {
        this.agencyType = agencyType;
        return this;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public BusAgencyResponseModel withCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
        return this;
    }

    public Object getCashBalanceInPrefCurrency() {
        return cashBalanceInPrefCurrency;
    }

    public void setCashBalanceInPrefCurrency(Object cashBalanceInPrefCurrency) {
        this.cashBalanceInPrefCurrency = cashBalanceInPrefCurrency;
    }

    public BusAgencyResponseModel withCashBalanceInPrefCurrency(Object cashBalanceInPrefCurrency) {
        this.cashBalanceInPrefCurrency = cashBalanceInPrefCurrency;
        return this;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    public BusAgencyResponseModel withCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
        return this;
    }

    public Object getCreditBalanceInPrefCurrency() {
        return creditBalanceInPrefCurrency;
    }

    public void setCreditBalanceInPrefCurrency(Object creditBalanceInPrefCurrency) {
        this.creditBalanceInPrefCurrency = creditBalanceInPrefCurrency;
    }

    public BusAgencyResponseModel withCreditBalanceInPrefCurrency(Object creditBalanceInPrefCurrency) {
        this.creditBalanceInPrefCurrency = creditBalanceInPrefCurrency;
        return this;
    }

    public boolean isDomHotelConfirmBookingLimitRequired() {
        return domHotelConfirmBookingLimitRequired;
    }

    public void setDomHotelConfirmBookingLimitRequired(boolean domHotelConfirmBookingLimitRequired) {
        this.domHotelConfirmBookingLimitRequired = domHotelConfirmBookingLimitRequired;
    }

    public BusAgencyResponseModel withDomHotelConfirmBookingLimitRequired(boolean domHotelConfirmBookingLimitRequired) {
        this.domHotelConfirmBookingLimitRequired = domHotelConfirmBookingLimitRequired;
        return this;
    }

    public int getDomHotelHoldBalanceLeft() {
        return domHotelHoldBalanceLeft;
    }

    public void setDomHotelHoldBalanceLeft(int domHotelHoldBalanceLeft) {
        this.domHotelHoldBalanceLeft = domHotelHoldBalanceLeft;
    }

    public BusAgencyResponseModel withDomHotelHoldBalanceLeft(int domHotelHoldBalanceLeft) {
        this.domHotelHoldBalanceLeft = domHotelHoldBalanceLeft;
        return this;
    }

    public Object getDomHotelHoldBalanceLeftInPrefCurrency() {
        return domHotelHoldBalanceLeftInPrefCurrency;
    }

    public void setDomHotelHoldBalanceLeftInPrefCurrency(Object domHotelHoldBalanceLeftInPrefCurrency) {
        this.domHotelHoldBalanceLeftInPrefCurrency = domHotelHoldBalanceLeftInPrefCurrency;
    }

    public BusAgencyResponseModel withDomHotelHoldBalanceLeftInPrefCurrency(Object domHotelHoldBalanceLeftInPrefCurrency) {
        this.domHotelHoldBalanceLeftInPrefCurrency = domHotelHoldBalanceLeftInPrefCurrency;
        return this;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public BusAgencyResponseModel withError(Error error) {
        this.error = error;
        return this;
    }

    public boolean isIntlHotelConfirmBookingLimitRequired() {
        return intlHotelConfirmBookingLimitRequired;
    }

    public void setIntlHotelConfirmBookingLimitRequired(boolean intlHotelConfirmBookingLimitRequired) {
        this.intlHotelConfirmBookingLimitRequired = intlHotelConfirmBookingLimitRequired;
    }

    public BusAgencyResponseModel withIntlHotelConfirmBookingLimitRequired(boolean intlHotelConfirmBookingLimitRequired) {
        this.intlHotelConfirmBookingLimitRequired = intlHotelConfirmBookingLimitRequired;
        return this;
    }

    public int getIntlHotelHoldBalanceLeft() {
        return intlHotelHoldBalanceLeft;
    }

    public void setIntlHotelHoldBalanceLeft(int intlHotelHoldBalanceLeft) {
        this.intlHotelHoldBalanceLeft = intlHotelHoldBalanceLeft;
    }

    public BusAgencyResponseModel withIntlHotelHoldBalanceLeft(int intlHotelHoldBalanceLeft) {
        this.intlHotelHoldBalanceLeft = intlHotelHoldBalanceLeft;
        return this;
    }

    public Object getIntlHotelHoldBalanceLeftInPrefCurrency() {
        return intlHotelHoldBalanceLeftInPrefCurrency;
    }

    public void setIntlHotelHoldBalanceLeftInPrefCurrency(Object intlHotelHoldBalanceLeftInPrefCurrency) {
        this.intlHotelHoldBalanceLeftInPrefCurrency = intlHotelHoldBalanceLeftInPrefCurrency;
    }

    public BusAgencyResponseModel withIntlHotelHoldBalanceLeftInPrefCurrency(Object intlHotelHoldBalanceLeftInPrefCurrency) {
        this.intlHotelHoldBalanceLeftInPrefCurrency = intlHotelHoldBalanceLeftInPrefCurrency;
        return this;
    }

    public boolean isIsNonAirOverrideCreditLimit() {
        return isNonAirOverrideCreditLimit;
    }

    public void setIsNonAirOverrideCreditLimit(boolean isNonAirOverrideCreditLimit) {
        this.isNonAirOverrideCreditLimit = isNonAirOverrideCreditLimit;
    }

    public BusAgencyResponseModel withIsNonAirOverrideCreditLimit(boolean isNonAirOverrideCreditLimit) {
        this.isNonAirOverrideCreditLimit = isNonAirOverrideCreditLimit;
        return this;
    }

    public boolean isIsOverrideCreditLimit() {
        return isOverrideCreditLimit;
    }

    public void setIsOverrideCreditLimit(boolean isOverrideCreditLimit) {
        this.isOverrideCreditLimit = isOverrideCreditLimit;
    }

    public BusAgencyResponseModel withIsOverrideCreditLimit(boolean isOverrideCreditLimit) {
        this.isOverrideCreditLimit = isOverrideCreditLimit;
        return this;
    }

    public Object getPreferredCurrency() {
        return preferredCurrency;
    }

    public void setPreferredCurrency(Object preferredCurrency) {
        this.preferredCurrency = preferredCurrency;
    }

    public BusAgencyResponseModel withPreferredCurrency(Object preferredCurrency) {
        this.preferredCurrency = preferredCurrency;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BusAgencyResponseModel withStatus(int status) {
        this.status = status;
        return this;
    }

}
