/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnm.promotion;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SE130008
 */
public class PromoObj implements Serializable{
    private String userId;
    private Map<String,Integer> items;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the items
     */
    public Map<String,Integer> getItems() {
        return items;
    }
 
    public boolean addToPromotion(String user_id, int rank){
        if(this.items==null){
            this.items = new HashMap<>();
        }
        if(!this.items.containsKey(user_id)){
            this.items.put(user_id, rank);
            return true;
        }
        return false;
    }   
}
