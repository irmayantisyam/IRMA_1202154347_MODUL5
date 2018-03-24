package com.example.irmayantisyam.irma_1202154347_modul5;

/**
 * Created by IRMAYANTI SYAM on 3/23/2018.
 */

public class itemtodo {
    //medeklarasikan emua variabel
    String name, description, priority;

    public itemtodo(String name, String description, String priority) {
        //melakukan inisiasi
        this.name = name;
        this.description = description;
        this.priority = priority;
    }
    //mengatur untuk mendapatkan hasil input
    public String getName() {
        return name;
    }
    //mengatur input
    public void setName(String name) {
        this.name = name;
    }

    //mendapatkan hasil input description
    public String getDescription() {
        return description;
    }

    //mengatur input description
    public void setDescription(String description) {
        this.description = description;
    }

    //mendapatkan hasil input priority
    public String getPriority() {
        return priority;
    }

    //mengatur input priority
    public void setPriority(String priority) {
        this.priority = priority;
    }
}
