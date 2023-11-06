package com.cgvsu;

import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.objwriter.ObjWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {
public static void main(String[] args) throws IOException {
        final String pathInputFile = "C:\\Users\\MaxOmenes\\Documents\\!Root\\Development\\Java\\CGI\\CGI_task3\\assets\\models\\Torus.obj";
        Model model = ObjReader.read(Files.readString(Paths.get(pathInputFile)));
        ObjWriter.write(model, "C:\\Users\\MaxOmenes\\Documents\\!Root\\Development\\Java\\CGI\\CGI_task3\\assets\\models\\output.obj");
}

    
}