package com.example.vijaygarg.delagain.Model;

/**
 * Created by vijaygarg on 04/04/18.
 */

public class CompetitiveModel {
    String hp,lenovo,acer,other,dell,promoter_id,store_id;

    public CompetitiveModel() {
    }

    public CompetitiveModel(String hp, String lenovo, String acer, String other,String dell,String promoterid,String store_id) {
        this.hp = hp;
        this.lenovo = lenovo;
        this.acer = acer;
        this.dell=dell;
        this.other = other;
        this.promoter_id=promoterid;
        this.store_id=store_id;

    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getPromoter_id() {
        return promoter_id;
    }

    public void setPromoter_id(String promoter_id) {
        this.promoter_id = promoter_id;
    }

    public String getHp() {
        return hp;
    }

    public String getDell() {
        return dell;
    }

    public void setDell(String dell) {
        this.dell = dell;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getLenovo() {
        return lenovo;
    }

    public void setLenovo(String lenovo) {
        this.lenovo = lenovo;
    }

    public String getAcer() {
        return acer;
    }

    public void setAcer(String acer) {
        this.acer = acer;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
