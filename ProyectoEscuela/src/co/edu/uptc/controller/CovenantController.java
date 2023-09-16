package co.edu.uptc.controller;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Covenant;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;



public class CovenantController {

    private FileManagerController fmc;
    private HashSet<Covenant>covenants;
    private ArrayList<Category>categories;
    private Gson gson;
    private static final String fileName = "Covenants";
    public CovenantController() {
        categories=prechargeCategories();
        covenants=preChargeCovenants();
        fmc = new FileManagerController();
        gson = new Gson();
    }

    /**
     *method just for charge the initial covenants
     *
     * @return auxiliarTM, this is a treemap which contains the initial data
     */
    private HashSet<Covenant>preChargeCovenants(){
        HashSet<Covenant> auxiliarTM=new HashSet<>();
        Category cry=asignCategory();
        Covenant c=new Covenant("name","contact","admin","descripted","link123",cry);
        auxiliarTM.add(c);
        return auxiliarTM;
    }

    /**
     * this method is used to assign random categories to the precharged covenants
     * @return
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
    public String seeCovenants(){
        String response = this.fmc.read(fileName);
        if (response == null) return "There is no Champs";
        Covenant[] covenants1 = gson.fromJson(response, Covenant[].class);
        response = "";
        int index = 1;
        for (Covenant ch: covenants1){
            response += "\n"+ index + ". " + ch.toString();
            index++;
        }
        return response.replaceFirst("\n","");
    }
    public void addCovenant(String tittle, String contact, String nameofCreator, String description, String link,int position){
        Covenant c=new Covenant(tittle, contact, nameofCreator,description,link,categories.get(position));

        String content = fmc.read(fileName);

        fmc.writeJsonFile(fileName,c,false);
    }

}
