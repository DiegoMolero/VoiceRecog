package domain;

import java.util.Date;

public class CounterSeconds {
    private Date createdDate;
    public CounterSeconds(){
    	createdDate = new java.util.Date();
    }
    
    public int getAgeInSeconds() {
        java.util.Date now = new java.util.Date();
        return (int)((now.getTime() - this.createdDate.getTime()) / 1000);
    }
}
