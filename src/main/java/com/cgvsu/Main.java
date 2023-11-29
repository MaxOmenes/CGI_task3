package com.cgvsu;

import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.objwriter.ObjWriter;
import com.cgvsu.transformation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {
public static void main(String[] args) throws IOException {
        final String pathInputFile = "C:\\Users\\MaxOmenes\\Documents\\!Root\\Development\\Java\\CGI\\CGI_task3\\assets\\models\\WrapSkull.obj";
        Model model = ObjReader.read(Files.readString(Paths.get(pathInputFile)));
        Transformation af = new AffineTransformation(new DefaultRotate(11,0 , 0),
                                                        new DefaultScale(1, 1, 1),
                                                        new DefaultTransition(25, 0, 0));
        af.calculate(model.vertices);
        ObjWriter.write(model, "C:\\Users\\MaxOmenes\\Documents\\!Root\\Development\\Java\\CGI\\CGI_task3\\assets\\models\\output.obj");
}

    
}