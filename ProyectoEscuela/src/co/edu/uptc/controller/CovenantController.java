package co.edu.uptc.controller;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Covenant;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * CovenantController is a class which allow to add,precharge and show the covenants
 * in the program.
 *
 * @author Felipe Luna
 */

public class CovenantController {

    private HashSet<Covenant>covenants;
    private ArrayList<Category>categories;
    public CovenantController() {
        categories=prechargeCategories();
        covenants=preChargeCovenants();
    }

    /**
     *method just for charge the initial covenants
     *
     * @return auxiliarTM, this is a Hashset which contains the initial data
     */
    private HashSet<Covenant>preChargeCovenants(){
        HashSet<Covenant> auxiliarTM=new HashSet<>();
        Category cry=asignCategory();
        Covenant c=new Covenant("name","contact","admin","descripted","link123",cry);
        auxiliarTM.add(c);
        return auxiliarTM;
    }

    /**
     * @autor Felipe Luna
     * this method is used to assign random categories to the precharged covenants
     * @return c a random category
     */
    private Category asignCategory(){
        Category c=categories.get(new Random().nextInt(categories.size()));
        return c;
    }

    /**
     * this method is used to precharge the categories until we can use persistence
     * @return auxC
     */
    private ArrayList<Category>prechargeCategories(){
        ArrayList<Category> auxC=new ArrayList<>();
        auxC.add(new Category("Convenios Interinstitucionales","Estos son acuerdos entre la UPTC y otras universidades, instituciones educativas o centros de investigación. Pueden abarcar colaboraciones académicas, intercambios estudiantiles y de profesores, programas conjuntos de investigación, entre otros."));
        auxC.add(new Category("Convenios de Movilidad","Acuerdos que facilitan el intercambio de estudiantes y profesores entre la UPTC y otras instituciones, permitiendo experiencias académicas en diferentes contextos."));
        auxC.add(new Category("Convenios Empresariales","Acuerdos con empresas u organizaciones para promover la vinculación con el sector privado, incluyendo prácticas profesionales, proyectos conjuntos y colaboraciones en la formación de estudiantes."));
        auxC.add(new Category("Convenios de Cooperación","La UPTC podría establecer acuerdos con entidades locales para promover proyectos de extensión y servicios a la comunidad, como programas de educación continua, asesoramiento técnico y social, entre otros."));
        auxC.add(new Category("Convenios de Extensión","Acuerdos con entidades locales para promover proyectos de extensión y servicios a la comunidad, como programas de educación continua, asesoramiento técnico y social."));
        auxC.add(new Category("Convenios Culturales y Deportivos", "Acuerdos con instituciones culturales o deportivas para promover actividades extracurriculares, eventos y competiciones."));
        auxC.add(new Category("Convenios de Intercambio Académico","Acuerdos que permiten a estudiantes y profesores de la UPTC estudiar, investigar o enseñar en otras instituciones durante un período determinado."));
        return auxC;
    }
    public String showDataCategoryNames(){
        String s="";
        for (int i=0;i<categories.size();i++) {
            s=s+"\n"+i+"."+categories.get(i).nameToString();
        }
        return s;
    }
    public String showAllC(){
        String s="";
        for (int i=0;i<categories.size();i++) {
            s=s+"\n"+i+"."+categories.get(i).toString();
        }
        return s;
    }
    public String showCovenant(){
        String s="";
        for (Covenant c:covenants) {
            s=s+"\n"+c.toString();
        }
        return s;
    }
    public void addCovenant(String tittle, String contact, String nameofCreator, String description, String link,int position){

        Covenant c=new Covenant(tittle, contact, nameofCreator,description,link,categories.get(position));
        covenants.add(c);
    }
    public boolean DeleteCovenant(String name){
        for (Covenant c:covenants) {
            if (c.getTittle().equals(name)){
                covenants.remove(c);
                return true;
            }
        }
        return false;
    }

}
