package sk.feistu.furmi.androidserv.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.feistu.furmi.androidserv.model.Projectile;
import sk.feistu.furmi.androidserv.services.PhysicServices;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@Slf4j
public class Physics {

    @Autowired
    Projectile projectile;

    @Autowired
    PhysicServices physicServices;

    @RequestMapping(value="/generateData", method = RequestMethod.GET)
    @ResponseBody
    public List<Projectile> generateData (@PathParam("velocity") String velocity, @PathParam("angle") String angle){
        List<Projectile> projectiles = physicServices.generatePositions(velocity,angle);
        return projectiles;
    }
    @RequestMapping(value="/generateDataRelative", method = RequestMethod.GET)
    @ResponseBody
    public List<Projectile> generateDataRelative (@PathParam("velocity") String velocity, @PathParam("angle") String angle, @PathParam("dWidth") String dWidth, @PathParam("dHeight") String dHeight){
        List<Projectile> projectiles = physicServices.generateRelativePositions(velocity,angle,dWidth,dHeight);
        return projectiles;
    }


}
