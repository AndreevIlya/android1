package com.homework;

class DifferentSizeException extends Exception{
    @Override
    public String getMessage(){
        return "Options arrays have different sizes.";
    }
}
