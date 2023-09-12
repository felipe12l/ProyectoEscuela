package co.edu.uptc.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerControllerTest {
    FileManagerController fmc=new FileManagerController();
    @Test
    void createfile(){
        fmc.writeJsonFileAccounts("1",null);
    }
}