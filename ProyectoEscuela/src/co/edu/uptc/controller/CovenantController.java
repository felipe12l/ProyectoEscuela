package co.edu.uptc.controller;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Covenant;


import java.util.ArrayList;
import java.util.TreeMap;

public class CovenantController {

    private TreeMap<Integer, Covenant> covenants;
    private ArrayList<Category>categories;
    public CovenantController() {
        covenants=preChargeCovenants();
        categories=prechargeCategories();
    }

    /**
     *method just for charge the initial covenants
     *
     * @return auxiliarTM, this is a treemap which contains the initial data
     */
    private TreeMap<Integer, Covenant> preChargeCovenants(){
        TreeMap<Integer, Covenant> auxiliarTM=new TreeMap<>();




        return auxiliarTM;
    }
    private ArrayList<Category>prechargeCategories(){
        ArrayList<Category> auxC=new ArrayList<>();
        auxC.add(new Category());
        return auxC;
    }
}
