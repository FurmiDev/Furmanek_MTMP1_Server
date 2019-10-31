package sk.feistu.furmi.androidserv.services;

import sk.feistu.furmi.androidserv.model.Projectile;

import java.util.List;

public interface PhysicServices {

    List<Projectile> generatePositions(String velocity, String angle);
    public List<Projectile> generateRelativePositions(String velocity, String angle, String displayWidth, String displayHeight);
}
