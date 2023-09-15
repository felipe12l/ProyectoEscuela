package co.edu.uptc.controller.old;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Covenant;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;



public class CovenantController {

    private ArrayList<Category>categories;
    private static final String fileName = "Covenants";
    private FileManagerController fmc;
    public CovenantController() {
        categories=prechargeCategories();
        fmc = new FileManagerController();
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
            s=s+"\n"+(i + 1)+"."+categories.get(i).nameToString();
        }
        return s;
    }
    public void addCovenant(String tittle, String contact, String nameofCreator, String description, String link,int position){
        Gson gson = new Gson();
        Covenant c=new Covenant(tittle, contact, nameofCreator,description,link,categories.get(position-1));
        fmc.writeJsonFileCovenant(fileName,c);
    }

    public String seeCovenants(){
        Gson gson = new Gson();
        String response = fmc.read(fileName);
        if(response == null) return "there is no covenants";
        Covenant[] covenants = gson.fromJson(response, Covenant[].class);
        response = "";
        int index = 1;
        for (Covenant ch: covenants){
            response += "\n"+ index + ". " + ch.toString();
            index++;
        }
        return response.replaceFirst("\n","");
    }

}
