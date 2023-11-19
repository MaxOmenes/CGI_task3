package com.cgvsu.transformation;


import com.cgvsu.Main;
import com.cgvsu.math.Vector3f;
import com.cgvsu.math.Vector4f;

import javax.vecmath.*;
import java.util.List;

public class AffineTransformation {
    int scaleX = 1, scaleY = 1, scaleZ = 1;
    int rotateX = 0, rotateY = 0, rotateZ = 0;
    int transitionX = 0, transitionY = 0, transitionZ = 0;

    public AffineTransformation(int scaleX, int scaleY, int scaleZ,
                                int rotateX, int rotateY, int rotateZ,
                                int transitionX, int transitionY, int transitionZ) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
        this.rotateX = rotateX;
        this.rotateY = rotateY;
        this.rotateZ = rotateZ;
        this.transitionX = transitionX;
        this.transitionY = transitionY;
        this.transitionZ = transitionZ;
    }

    public AffineTransformation() {
    }

    public int getScaleX() {
        return scaleX;
    }

    public void setScaleX(int scaleX) {
        this.scaleX = scaleX;
    }

    public int getScaleY() {
        return scaleY;
    }

    public void setScaleY(int scaleY) {
        this.scaleY = scaleY;
    }

    public int getScaleZ() {
        return scaleZ;
    }

    public void setScaleZ(int scaleZ) {
        this.scaleZ = scaleZ;
    }

    public int getRotateX() {
        return rotateX;
    }

    public void setRotateX(int rotateX) {
        this.rotateX = rotateX;
    }

    public int getRotateY() {
        return rotateY;
    }

    public void setRotateY(int rotateY) {
        this.rotateY = rotateY;
    }

    public int getRotateZ() {
        return rotateZ;
    }

    public void setRotateZ(int rotateZ) {
        this.rotateZ = rotateZ;
    }

    public int getTransitionX() {
        return transitionX;
    }

    public void setTransitionX(int transitionX) {
        this.transitionX = transitionX;
    }

    public int getTransitionY() {
        return transitionY;
    }

    public void setTransitionY(int transitionY) {
        this.transitionY = transitionY;
    }

    public int getTransitionZ() {
        return transitionZ;
    }

    public void setTransitionZ(int transitionZ) {
        this.transitionZ = transitionZ;
    }

    private Matrix3f rotateMatrixX(){
        float[] matrix = new float[]{
                1, 0, 0,
                0, (float) Math.cos(rotateX), (float) Math.sin(rotateX),
                0, (float) -Math.sin(rotateX), (float) Math.cos(rotateX)
        };
        return new Matrix3f(matrix);
    }
    private Matrix3f rotateMatrixY(){
        float[] matrix = new float[]{
                (float) Math.cos(rotateY),0, (float) Math.sin(rotateY),
                0, 1, 0,
                (float) -Math.sin(rotateY),0, (float) Math.cos(rotateY)
        };
        return new Matrix3f(matrix);
    }
    private Matrix3f rotateMatrixZ(){
        float[] matrix = new float[]{
                (float) Math.cos(rotateZ), (float) Math.sin(rotateZ), 0,
                (float) -Math.sin(rotateZ), (float) Math.cos(rotateZ), 0,
                0, 0, 1
        };
        return new Matrix3f(matrix);
    }

    public Matrix3f rotate(){
        Matrix3f matrix = rotateMatrixZ();
        matrix.mul(rotateMatrixY());
        matrix.mul(rotateMatrixX());
        return matrix;
    }

    public Matrix3f scale(){
        return new Matrix3f(scaleX, 0, 0,
                            0, scaleY, 0,
                            0, 0, scaleZ);
    }

    public Matrix4f transition(){
        return new Matrix4f(1, 0, 0, transitionX,
                            0, 1, 0, transitionY,
                            0, 0, 1, transitionZ,
                            0, 0, 0, 1);
    }

    private Matrix4f translateMatrix3To4(Matrix3f m){
        return new Matrix4f(m.m00, m.m01, m.m02, 0,
                            m.m10, m.m11, m.m12, 0,
                            m.m20, m.m21, m.m22, 0,
                            0, 0, 0, 1);
    }

    public void calculate (List<Vector3f> vector){
        Matrix4f matrix = transition();
        matrix.sub(translateMatrix3To4(rotate()));
        matrix.sub(translateMatrix3To4(scale()));

        for(Vector3f v:vector){
            Vector4f v4 = new Vector4f(v.getX(), v.getY(), v.getZ(), 1);
            v4.sub(matrix);
            v.setX(v4.getX());
            v.setY(v4.getY());
            v.setZ(v4.getZ());
        }
    }
}