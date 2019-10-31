package sk.feistu.furmi.androidserv.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.feistu.furmi.androidserv.model.Projectile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.*;

@Service
@Slf4j
public class PhysicServicesImplementation implements PhysicServices{

    private static double gVal = 9.81;

    @Override
    public List<Projectile> generatePositions(String velocity, String angle) {
        Float vel = Float.parseFloat(velocity);
        Float angl = Float.parseFloat(angle);
        float time = getTimeTotal(vel,angl);
        LinkedList<Projectile> projectiles = new LinkedList<>();
        float step = getIterationStep(time);
        float t = 0;
        while (time>t){
            Projectile rock = new Projectile(getPosXInTime(vel,angl,t),getPosYInTime(vel, angl, t),t);
            projectiles.add(rock);
            t+=step;
        }
        return projectiles;
    }

    @Override
    public List<Projectile> generateRelativePositions(String velocity, String angle, String displayWidth, String displayHeight) {
        List<Projectile> projectiles = generatePositions(velocity,angle);
        List<Projectile> projectilesRelative = new ArrayList<>();
        Float dWidth = Float.parseFloat(displayWidth)*0.8f;
        Float dHeight = Float.parseFloat(displayHeight)*0.8f;
        float maxWidth = projectiles.get(projectiles.size()-1).getXPos();
        float maxHeight = projectiles.get(projectiles.size()/2).getYPos();
        for (Projectile projectile: projectiles){
            projectilesRelative.add(new Projectile(getPosXInRelative(projectile.getXPos(),maxWidth,dWidth),getPosYInRelative(projectile.getYPos(),maxHeight,dHeight),projectile.getTimeVal()));
        }
        return projectilesRelative;
    }


    public float getIterationStep(float time){
        float i=0;
        if (time<1){
            return 0.01f;
        }
        if(time<10){
            return 0.1f;
        }
        if(time<100){
            return 1;
        }
        if(time<1000){
            return 10;
        }
        if(time<10000){
            return 100;
        }
        return i;
    }

    public float getTimeTotal(float velocity, float angle){
        double time = (2*velocity*sin(toRadians(angle)))/gVal;
        System.out.println("timeVal:"+ time);
        return (float)time;
    }

    public float getPosXInTime(float velocity, float angle, float timeVal){
        double xPos = velocity*timeVal*cos(toRadians(angle));
        System.out.println("xPos:"+ xPos);
        return (float)xPos;
    }
    public float getPosYInTime(float velocity, float angle, float timeVal){
        double yPos = (velocity*timeVal*sin(toRadians(angle)))-((gVal*timeVal*timeVal)/2);
        System.out.println("yPos:"+ yPos);
        return (float)yPos;
    }

    public float getPosXInRelative(float posX, float maxWidth, float displayWidth){
        return posX*displayWidth/maxWidth;
    }

    public float getPosYInRelative(float posY, float maxHeight, float displaHeight){
        return (displaHeight-(posY*displaHeight/maxHeight));
    }
}
