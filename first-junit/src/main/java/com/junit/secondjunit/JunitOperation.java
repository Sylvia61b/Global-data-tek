package com.junit.secondjunit;

import java.util.List;

public class JunitOperation {
    private String company="Synechron company";
    private String companyDuplicate=company;

    private String[] threeletters= {"A","B","C"};

    private List<String> compylist=List.of("syne","apple","samsung","indus");

    public String getCompany() {
        return company;
    }

    public String getCompanyDuplicate() {
        return companyDuplicate;
    }

    public String[] getThreeletters() {
        return threeletters;
    }

    public List<String> getCompylist() {
        return compylist;
    }

    /*
     * public void setCompylist(List<String> compylist) { this.compylist =
     * compylist; }
     */
    public Boolean isGreater(int num1, int num2)
    {
        if(num1>num2)
        {
            return true;

        }
        return false;
    }


    public String methodHasException(int num1) throws RuntimeException
    {
        if(num1< 0)
        {
            throw new RuntimeException("Value should be gretare than zero");

        }
        return "Value is greater than zero";
    }

}
