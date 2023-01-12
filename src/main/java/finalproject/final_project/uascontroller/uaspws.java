/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.final_project.uascontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import finalproject.final_project.model.jpa.UaspwsJpaController;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HAHAHA
 * //20200140123
 */
@RestController
@CrossOrigin
@RequestMapping("/uaspws")
public class uaspws {
   //declare entity
    uaspws uas = new uaspws();
    //declare jpa
    UaspwsJpaController ctrl = new UaspwsJpaController();
    
    //get mapping all data
    @GetMapping
    public List<uaspws> getData(){
        List<uaspws> data = new ArrayList<>(); //new object arraylist
        try{
        data = ctrl.finduaspwsEntities();
                }catch (Exception e){
                    
                }
                return data;
        
    }  

 //get mapping by id(primary key table)
    @GetMapping("/{id}")
    public List<uaspws> getWartegEntities(@PathVariable("id")int id){
    List<uaspws> dataa = new ArrayList<uaspws>();
    try{
        uas = ctrl.finduaspws(id);
        dataa.add(uas);}
        catch(Exception e){}
        return dataa;
    }
    
    //post mapping
    @PostMapping
    public String insertData(HttpEntity<String> requestdata) throws JsonProcessingException{
        String message = "BERHASIL DITAMBAHKAN";
        try{
        String json_receive = requestdata.getBody();
        ObjectMapper map = new ObjectMapper();
        uas = map .readValue(json_receive, uaspws.class);
        ctrl.create(uas);} 
        catch (Exception ex) {
        message = "gagal";
        }
        return message;
               
    }
    
    //put mapping
    @PutMapping
    public String editData(HttpEntity<String> requestdata) throws JsonProcessingException{
        String message = "BERHASIL DI EDIT";
        try{
        String json_receive = requestdata.getBody();
        ObjectMapper map = new ObjectMapper();
        uas = map .readValue(json_receive, uaspws.class);
        ctrl.edit(uas);} 
        catch (Exception ex) {
        message = "gagal";
        }
        return message;
               
    }
    
    //delete
    @DeleteMapping
     public String deleteData(HttpEntity<String> requestdata) throws JsonProcessingException{
        String message = "BERHASIL DI HAPUS";
        try{
        String json_receive = requestdata.getBody();
        ObjectMapper map = new ObjectMapper();
        uas = map .readValue(json_receive, uaspws.class);
        ctrl.destroy(uas.getId());} 
        catch (Exception ex) {
        message = "gagal";
        }
        return message;
               
    }

    private Integer getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
